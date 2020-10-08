package br.com.transmissionadmin.Controller;

import br.com.transmissionadmin.Model.Biografia;
import br.com.transmissionadmin.Model.repository.BiografiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/Transmission/Biografia")
public class BiografiaController {

    private final BiografiaRepository biografiarepository;

    @Autowired
    public BiografiaController(BiografiaRepository biografiarepository) {
        this.biografiarepository = biografiarepository;
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        biografiarepository.findById(id).map(biografia -> {
            biografiarepository.delete(biografia);
            return Void.TYPE;
        }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Biografia salvar(@RequestBody @Valid Biografia biografia) {
        return biografiarepository.save(biografia);
    }


    @GetMapping
    public List<Biografia> getBiografia(){

        return biografiarepository.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable @Valid Integer id, @RequestBody Biografia biografiaAtualizado){
        biografiarepository.findById(id).map(biografia -> {biografia.setTexto(biografiaAtualizado.getTexto());return biografiarepository.save(biografiaAtualizado);}).orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }
}
