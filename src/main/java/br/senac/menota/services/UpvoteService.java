package br.senac.menota.services;

import br.senac.menota.exceptions.ValidationException;
import br.senac.menota.model.Upvote;
import br.senac.menota.repositories.UpvoteCountRepository;
import br.senac.menota.repositories.UpvoteRepository;
import br.senac.menota.utils.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpvoteService {

    private final UpvoteRepository upvoteRepository;
    private final UpvoteCountRepository upvoteCountRepository;

    public Upvote create(Upvote upvote){

        if (upvoteRepository.existsByUserIdAndProjetoId(AuthenticationUtil.retriveAuthenticatedUser().getId(), upvote.getProjeto().getId())){
            throw new ValidationException("ja laikado");
        }

        upvote.setUserId(AuthenticationUtil.retriveAuthenticatedUser().getId());

        var count = upvoteCountRepository.findByProjetoId(upvote.getProjeto().getId());

        count.setCount(count.getCount() + 1);

        upvoteCountRepository.save(count);

        return upvoteRepository.save(upvote);
    }

    public void deleteUpvote(Upvote upvote){

        var upvoteFind = upvoteRepository.findByUserIdAndProjetoId(AuthenticationUtil.retriveAuthenticatedUser().getId(), upvote.getProjeto().getId());

        upvoteRepository.delete(upvoteFind);

        var count = upvoteCountRepository.findByProjetoId(upvote.getProjeto().getId());

        count.setCount(count.getCount() - 1);

        upvoteCountRepository.save(count);

    }
}
