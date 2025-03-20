package br.senac.menota.services;

import br.senac.menota.model.Upvote;
import br.senac.menota.repositories.UpvoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpvoteService {

    private final UpvoteRepository upvoteRepository;

    public Upvote create(Upvote upvote){
        return upvoteRepository.save(upvote);
    }
}
