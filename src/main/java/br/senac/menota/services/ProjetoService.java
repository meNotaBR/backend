package br.senac.menota.services;

import br.senac.menota.dtos.ProjetoCarrosselResponseDTO;
import br.senac.menota.dtos.ProjetoCreateRequestDTO;
import br.senac.menota.dtos.ProjetoFeedResponseDTO;
import br.senac.menota.exceptions.NotFoundException;
import br.senac.menota.model.Projeto;
import br.senac.menota.model.ProjetoImagemCapa;
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
    private final ProjetoImagemCapaRepository projetoImagemCapaRepository;

    @Transactional
    public Projeto create(ProjetoCreateRequestDTO projetoDTO){

        var empresario = empresarioRepository.findById(AuthenticationUtil.retriveAuthenticatedUser().getId())
                .orElseThrow(() -> new NotFoundException("Empresário não encontrado"));

        var startup = startupRepository.findByEmpresarioId(empresario.getId());

        if (startup == null){
            throw new NotFoundException("Você ainda não tem nenhuma startup cadastrada");
        }

        var projeto = Projeto
                    .builder()
                    .nome(projetoDTO.nome())
                    .dataPrevistaInicio(projetoDTO.dataPrevistaInicio())
                    .dataPrevistaEntrega(projetoDTO.dataPrevistaEntrega())
                    .descricao(projetoDTO.descricao())
                    .build();

        projeto.setStartup(startup);

        newProjetoValidationStrategies.forEach(validation -> validation.validate(projeto));

        var proj =  projetoRepository.save(projeto);
        upvoteCountRepository.save(new UpvoteCount(projeto, 0));

        if (!projetoDTO.imageBase64().isEmpty()){
            projetoImagemCapaRepository.save(new ProjetoImagemCapa(projeto, projetoDTO.imageBase64()));
        }

        return proj;
    }

    @Transactional
    public List<ProjetoCarrosselResponseDTO> getProjetosCarrossel(){
        var upvotecount = upvoteCountRepository.getProjectsByUpvoteCount();

        List<ProjetoCarrosselResponseDTO> response = new ArrayList<>();

        upvotecount.forEach(count -> {
            var projeto = projetoRepository.findById(count.getProjeto().getId()).orElseThrow(() -> new NotFoundException("Projeto Não Encontrado"));
            String base64 = projetoImagemCapaRepository.findByProjetoId(count.getProjeto().getId()).getImageBase64();

            response.add(new ProjetoCarrosselResponseDTO(
                    projeto.getId(),
                    projeto.getNome(),
                    base64,
                    projeto.getDescricao(),
                    count.getTotalUpvotes()
            ));
        });

        return response;
    }

    @Transactional
    public Projeto update(Long id, ProjetoCreateRequestDTO projeto){

        var projetoDB = projetoRepository.findById(id).orElseThrow(() -> new NotFoundException("Projeto não encontrado"));

        if (projeto.nome() != null){
            projetoDB.setNome(projeto.nome());
        }

        if (projeto.descricao() != null){
            projetoDB.setDescricao(projeto.descricao());
        }

        if (projeto.dataPrevistaInicio() != null){
            projetoDB.setDataPrevistaInicio(projeto.dataPrevistaInicio());
        }

        if (projeto.dataPrevistaEntrega() != null){
            projetoDB.setDataPrevistaEntrega(projeto.dataPrevistaEntrega());
        }

        if (!projeto.imageBase64().isEmpty()){
            var image = projetoImagemCapaRepository.findByProjetoId(projetoDB.getId());

            if (image != null){
                image.setImageBase64(projeto.imageBase64());
                projetoImagemCapaRepository.save(image);
            }else {
                projetoImagemCapaRepository.save(new ProjetoImagemCapa(projetoDB, projeto.imageBase64()));
            }
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

    @Transactional
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
