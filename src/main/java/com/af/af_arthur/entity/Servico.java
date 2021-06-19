package com.af.af_arthur.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

@Entity
public class Servico implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idServico;
    private String nome;

    @ManyToMany
    @JoinTable(
        name="ProfissioalServico",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_servico", "id_profissional"}),
        joinColumns = @JoinColumn(name = "id_servico"),
        inverseJoinColumns =  @JoinColumn(name = "id_profissional")
    )
    private List<Profissional> profissionais;

    @OneToMany
    @JoinColumn(name="ID_SERVICO")
    private List<Agendamento> agendamentos;

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Servico [idServico=" + idServico + ", nome=" + nome + "]";
    }

    public List<Profissional> getProfissionais() {
        return profissionais;
    }

    public void setProfissionais(List<Profissional> profissionais) {
        this.profissionais = profissionais;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }
}