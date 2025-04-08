package br.senac.menota.services;

import br.senac.menota.dtos.ProjetoFeedResponseDTO;
import br.senac.menota.exceptions.NotFoundException;
import br.senac.menota.model.Projeto;
import br.senac.menota.model.UpvoteCount;
import br.senac.menota.repositories.EmpresarioRepository;
import br.senac.menota.repositories.ProjetoRepository;
import br.senac.menota.repositories.UpvoteCountRepository;
import br.senac.menota.repositories.UpvoteRepository;
import br.senac.menota.strategy.NewProjetoValidationStrategy;
import br.senac.menota.utils.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final List<NewProjetoValidationStrategy> newProjetoValidationStrategies;
    private final EmpresarioRepository empresarioRepository;
    private final UpvoteCountRepository upvoteCountRepository;
    private final UpvoteRepository upvoteRepository;

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
        var projetos =  projetoRepository.findAll();

        var user = AuthenticationUtil.retriveAuthenticatedUser();

        if (Objects.equals(user.getUserType(), "EMPRESARIO")){
            return projetos.stream()
                    .map(projeto -> ProjetoFeedResponseDTO.fromEntity
                            (projeto, false))
                    .toList();
        }

        return projetos.stream()
                .map(projeto -> ProjetoFeedResponseDTO.fromEntity
                        (projeto, upvoteRepository.existsByInvestidorIdAndProjetoId(user.getId(), projeto.getId())))
                .toList();
    }

    public Projeto getById(Long id){
        return projetoRepository.findById(id).orElseThrow(() -> new NotFoundException("Projeto não encontrado"));
    }
}
