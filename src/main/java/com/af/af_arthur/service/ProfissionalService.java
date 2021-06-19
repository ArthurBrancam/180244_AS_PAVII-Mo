package com.af.af_arthur.service;

import java.util.List;

import com.af.af_arthur.entity.Profissional;
import com.af.af_arthur.repository.ProfissionalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfissionalService {
    @Autowired
    private ProfissionalRepository repository;

    public List<Profissional> getProfissionais(){
        return repository.findAll();
    }

    public Profissional getProfissionalById(int id){
        return repository.findById(id).get();
    }

    public void save(Profissional profissional){
        repository.save(profissional);
    }
    
    public void delete(Profissional profissional){
        repository.delete(profissional);
    }
}