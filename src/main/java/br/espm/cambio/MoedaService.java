package br.espm.cambio;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//esse e o microsserviço de moeda
@Transactional
@Component
public class MoedaService {
    
    @Autowired
    private MoedaRepository moedaRepository;
    
    public List<Moeda> listAll(){
        return StreamSupport
            //Transforma de iterável para lista
            .stream(moedaRepository.findAll().spliterator(), false)
            .collect(Collectors.toList())
            //Transforma de model para objeto
            .stream().map(MoedaModel::to)
            .collect(Collectors.toList());

        
    }

    public Moeda create(Moeda vo){
        vo.setId(UUID.randomUUID());
        return moedaRepository.save(new MoedaModel(vo)).to();
    }
    
    public Moeda findBySimbolo(String simbolo){
        return moedaRepository.findBySimbolo(simbolo).map(MoedaModel::to).orElse(null);
    }

    public Moeda findBy(UUID id){
        return moedaRepository.findById(id.toString()).map(MoedaModel::to).orElse(null);
    }

    public Moeda deleteBy(String simbolo){
        return moedaRepository.deleteBySimbolo(simbolo).map(MoedaModel::to).orElse(null);
    }
}
