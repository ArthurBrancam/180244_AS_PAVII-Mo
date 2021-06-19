package com.af.af_arthur.repository;

import com.af.af_arthur.entity.Agendamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository <Agendamento, Integer>{
    
}