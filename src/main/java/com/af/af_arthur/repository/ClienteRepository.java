package com.af.af_arthur.repository;

import com.af.af_arthur.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Integer>{
    
}