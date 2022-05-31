package br.espm.cambio;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CambioResource {

    @Autowired
    private MoedaService moedaService;

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello ESPM";
    }

    @GetMapping("/moeda")
    public List<Moeda> listMoeda(){
        //moedas.add(new Moeda("Rublo", "RUB"));
        //moedas.add(new Moeda("Dólar", "USD"));
        //moedas.add(new Moeda("Euro", "EUR"));
        //moedas.add(new Moeda("Real", "BRL"));
            
        return moedaService.listAll();
    }

    @PostMapping("/moeda")
    public void save(@RequestBody Moeda moeda){
        moedaService.create(moeda);
        //moedas.add(moeda);
    }

    @GetMapping("/moeda/{simbolo:[A-Z]{3,}}")
    public Moeda findMoedaBySimbolo(@PathVariable String simbolo) {
        return moedaService.findBySimbolo(simbolo);
    }

    @GetMapping("/moeda/{id:[a-f0-9]{8}(?:-[a-f0-9]{4}){4}[a-f0-9]{8}}")
    public Moeda findMoedaById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        return moedaService.findBy(uuid);
    }

    @DeleteMapping("/moeda/{simbolo}")
    public void delete(@PathVariable String simbolo){
        moedaService.deleteBy(simbolo);
    }
    
    
}
