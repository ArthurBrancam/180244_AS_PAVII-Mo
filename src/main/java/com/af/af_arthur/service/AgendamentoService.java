package com.af.af_arthur.service;

import java.util.List;

import com.af.af_arthur.entity.Agendamento;
import com.af.af_arthur.repository.AgendamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository repository;

    public List<Agendamento> getAgendamentos(){
        return repository.findAll();
    }

    public Agendamento getAgendamentoById(int id){
        return repository.findById(id).get();
    }

    public void save(Agendamento agendamento){
        repository.save(agendamento);
    }

    public void remove(Agendamento agendamento){
        repository.delete(agendamento);
    }
}