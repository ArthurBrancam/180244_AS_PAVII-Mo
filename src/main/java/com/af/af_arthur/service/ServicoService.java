package com.af.af_arthur.service;

import java.util.List;

import com.af.af_arthur.entity.Servico;
import com.af.af_arthur.repository.ServicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {
    @Autowired
    private ServicoRepository repository;

    public List<Servico> getServicos(){
        return repository.findAll();
    }

    public Servico getServicoById(Integer id){
        return repository.findById(id).get();
    }

    public void save(Servico servico){
        repository.save(servico);
    }

    public void remove(Servico servico){
        repository.delete(servico);
    }
}