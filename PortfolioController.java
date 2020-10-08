package br.com.transmissionadmin.Controller;

import br.com.transmissionadmin.Model.Portfolio;
import br.com.transmissionadmin.Model.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Portfolio")
public class PortfolioController {

        private final PortfolioRepository portfoliorepository;

        @Autowired
        public PortfolioController(PortfolioRepository portfoliorepository) {
            this.portfoliorepository = portfoliorepository;
        }


        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deletar(@PathVariable Integer id) {
            portfoliorepository.findById(id).map(biografia -> {
                portfoliorepository.delete(biografia);
                return Void.TYPE;
            }).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND));
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Portfolio salvar(@RequestBody @Valid Portfolio portfolio) {

            return portfoliorepository.save(portfolio);
        }


        @GetMapping
        public List<Portfolio> getBiografia(){

            return portfoliorepository.findAll();
        }

        @PutMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void atualizar(@PathVariable @Valid Integer id, @RequestBody Portfolio portfolioAtualizado){
            portfoliorepository.findById(id).map(portfolio -> {portfolio.setTitulo(portfolioAtualizado.getTitulo()); portfolio.setDescricao(portfolioAtualizado.getDescricao()); return portfoliorepository.save(portfolioAtualizado);}).orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND) );
        }
    }

