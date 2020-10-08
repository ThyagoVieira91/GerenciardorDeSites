package br.com.transmissionadmin.Controller;



import br.com.transmissionadmin.Model.Contato;
import br.com.transmissionadmin.Model.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/transmission/contato")
public class ContatoController {

    private final ContatoRepository contatorepository;

    @Autowired
    public ContatoController(ContatoRepository contatorepository) {

        this.contatorepository = contatorepository;
    }

    @GetMapping
    public List<Contato> obterTodos(){
        return contatorepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contato salvar(@RequestBody @Valid Contato contato){

        return contatorepository.save(contato);
    }

    @GetMapping("/{id}")
    public Contato acharPorId(@PathVariable Integer id){
        return contatorepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){

        contatorepository.findById(id).map( contato -> {
            contatorepository.delete(contato); return Void.TYPE;}).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable @Valid Integer id, @RequestBody Contato contatoAtualizado){
        contatorepository.findById(id).map(contato -> {contato.setNome(contatoAtualizado.getNome()); contato.setEmail(contatoAtualizado.getEmail()); contato.setMotivo(contatoAtualizado.getMotivo()); return contatorepository.save(contatoAtualizado);}).orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }

}


