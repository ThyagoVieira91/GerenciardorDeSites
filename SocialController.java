package br.com.transmissionadmin.Controller;

import br.com.transmissionadmin.Model.Portfolio;
import br.com.transmissionadmin.Model.Social;
import br.com.transmissionadmin.Model.repository.PortfolioRepository;
import br.com.transmissionadmin.Model.repository.SocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/social")
public class SocialController {


    private final SocialRepository socialrepository;

    @Autowired
    public SocialController(SocialRepository socialrepository) {
        this.socialrepository = socialrepository;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        socialrepository.findById(id).map(social -> {
            socialrepository.delete(social);
            return Void.TYPE;
        }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Social salvar(@RequestBody @Valid Social social) {

        return socialrepository.save(social);
    }


    @GetMapping
    public List<Social> getSocial(){

        return socialrepository.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable @Valid Integer id, @RequestBody Social socialAtualizado){
        socialrepository.findById(id).map(social -> {social.setTextoSocial(socialAtualizado.getTextoSocial()); return socialrepository.save(socialAtualizado);}).orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }
}


