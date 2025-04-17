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

    public Projeto create(Projeto projeto){

        var empresario = empresarioRepository.findById(AuthenticationUtil.retriveAuthenticatedUser().getId())
                .orElseThrow(() -> new NotFoundException("Empresário não encontrado"));

        if (empresario.getStartup() == null){
            throw new NotFoundException("Você ainda não tem nenhuma startup cadastrada");
        }

        projeto.setStartup(empresario.getStartup());

        newProjetoValidationStrategies.forEach(validation -> validation.validate(projeto));

        var proj =  projetoRepository.save(projeto);
        upvoteCountRepository.save(new UpvoteCount(projeto, 0));

        return proj;
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

    public List<ProjetoFeedResponseDTO> getProjetosUsuario(){

        var startupId = startupRepository.findByEmpresarioId(AuthenticationUtil.retriveAuthenticatedUser().getId());

        return projetoRepository.findAllByStartupId(startupId.getId())
                .stream()
                .map(projeto -> ProjetoFeedResponseDTO.fromEntity
                        (projeto, upvoteRepository.existsByUserIdAndProjetoId(AuthenticationUtil.retriveAuthenticatedUser().getId(), projeto.getId())))
                .toList();
    }

    public Projeto getById(Long id){
        return projetoRepository.findById(id).orElseThrow(() -> new NotFoundException("Projeto não encontrado"));
    }
}
