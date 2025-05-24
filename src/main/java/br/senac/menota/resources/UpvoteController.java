package br.senac.menota.resources;

import br.senac.menota.dtos.UpvoteGroupedByDate;
import br.senac.menota.model.Upvote;
import br.senac.menota.services.UpvoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/upvote")
@RequiredArgsConstructor
public class UpvoteController {

    private final UpvoteService upvoteService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Upvote upvote){
        upvoteService.create(upvote);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody Upvote upvote){
        upvoteService.deleteUpvote(upvote);
    }

    @GetMapping("/projeto/{projetoId}/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UpvoteGroupedByDate> findAll(@PathVariable("projetoId") Long id){
        return upvoteService.findAllByProjetoId(id);
    }
}
