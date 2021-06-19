package com.af.af_arthur.service;

import java.util.List;

import com.af.af_arthur.entity.Cliente;
import com.af.af_arthur.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public List<Cliente> getClientes(){
        return repository.findAll();
    }
    
    public Cliente getClienteById(int id){
        return repository.findById(id).get();
    }

    public void save(Cliente cliente){
        repository.save(cliente);
    }

    public void remove(Cliente cliente){
        repository.delete(cliente);
    }
}