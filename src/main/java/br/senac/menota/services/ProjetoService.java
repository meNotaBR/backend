package br.senac.menota.services;

import br.senac.menota.dtos.ProjetoFeedResponseDTO;
import br.senac.menota.exceptions.NotFoundException;
import br.senac.menota.model.Projeto;
import br.senac.menota.model.UpvoteCount;
import br.senac.menota.repositories.*;
import br.senac.menota.repositories.specifications.ProjetoSpecification;
import br.senac.menota.strategy.NewProjetoValidationStrategy;
import br.senac.menota.utils.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final List<NewProjetoValidationStrategy> newProjetoValidationStrategies;
    private final EmpresarioRepository empresarioRepository;
    private final UpvoteCountRepository upvoteCountRepository;
    private final UpvoteRepository upvoteRepository;
    private final StartupRepository startupRepository;

    @Transactional
    public Projeto create(Projeto projeto){

        var empresario = empresarioRepository.findById(AuthenticationUtil.retriveAuthenticatedUser().getId())
                .orElseThrow(() -> new NotFoundException("Empresário não encontrado"));

        var startup = startupRepository.findByEmpresarioId(empresario.getId());

        if (startup == null){
            throw new NotFoundException("Você ainda não tem nenhuma startup cadastrada");
        }

        projeto.setStartup(startup);

        newProjetoValidationStrategies.forEach(validation -> validation.validate(projeto));

        var proj =  projetoRepository.save(projeto);
        upvoteCountRepository.save(new UpvoteCount(projeto, 0));

        return proj;
    }

    @Transactional
    public Projeto update(Long id, Projeto projeto){

        var projetoDB = projetoRepository.findById(id).orElseThrow(() -> new NotFoundException("Projeto não encontrado"));

        if (projeto.getNome() != null){
            projetoDB.setNome(projeto.getNome());
        }

        if (projeto.getDescricao() != null){
            projetoDB.setDescricao(projeto.getDescricao());
        }

        if (projeto.getDataPrevistaInicio() != null){
            projetoDB.setDataPrevistaInicio(projeto.getDataPrevistaInicio());
        }

        if (projeto.getDataPrevistaEntrega() != null){
            projetoDB.setDataPrevistaEntrega(projeto.getDataPrevistaEntrega());
        }

        return projetoRepository.save(projetoDB);
    }

    public List<ProjetoFeedResponseDTO> getAll(){
        var projetos = projetoRepository.findAll();

        return projetos.stream()
                .map(projeto -> ProjetoFeedResponseDTO.fromEntity
                        (projeto, upvoteRepository.existsByUserIdAndProjetoId(AuthenticationUtil.retriveAuthenticatedUser().getId(), projeto.getId())))
                .toList();
    }

    public List<ProjetoFeedResponseDTO> getUltimosProjetos(){
        var spec = Specification.where(ProjetoSpecification.getUltimosProjetos());

        var projetos = projetoRepository.findAll(spec);

        return projetos.stream()
                .map(projeto -> ProjetoFeedResponseDTO.fromEntity
                        (projeto, upvoteRepository.existsByUserIdAndProjetoId(AuthenticationUtil.retriveAuthenticatedUser().getId(), projeto.getId())))
                .toList();
    }

    @Transactional
    public List<ProjetoFeedResponseDTO> getProjetosUsuario(){

        var startupId = startupRepository.findByEmpresarioId(AuthenticationUtil.retriveAuthenticatedUser().getId());

        return projetoRepository.findAllByStartupId(startupId.getId())
                .stream()
                .map(projeto -> ProjetoFeedResponseDTO.fromEntity
                        (projeto, upvoteRepository.existsByUserIdAndProjetoId(AuthenticationUtil.retriveAuthenticatedUser().getId(), projeto.getId())))
                .toList();
    }

    public List<ProjetoFeedResponseDTO> getProjetosByStartupId(Long startupId) {
        if (!startupRepository.existsById(startupId)) {
            throw new NotFoundException("Startup não encontrada com o ID: " + startupId);
        }

        return projetoRepository.findAllByStartupId(startupId)
                .stream()
                .map(projeto -> ProjetoFeedResponseDTO.fromEntity
                        (projeto, upvoteRepository.existsByUserIdAndProjetoId(AuthenticationUtil.retriveAuthenticatedUser().getId(), projeto.getId())))
                .toList();
    }

    public List<ProjetoFeedResponseDTO> getProjetosCurtidos(){
        var upvotes = upvoteRepository.findAllByUserId(AuthenticationUtil.retriveAuthenticatedUser().getId());

        List<ProjetoFeedResponseDTO> response = new ArrayList<>();

        upvotes.forEach(upvote -> {
            response.add(ProjetoFeedResponseDTO.fromEntity(projetoRepository.findByUpvoteId(upvote.getId()), true));
        });

        return response;
    }

    public Projeto getById(Long id){
        return projetoRepository.findById(id).orElseThrow(() -> new NotFoundException("Projeto não encontrado"));
    }

    public void delete(Long id){
        var projeto = projetoRepository.findById(id).orElseThrow(() -> new NotFoundException("Projeto não encontrado"));
        projetoRepository.delete(projeto);
    }
}
