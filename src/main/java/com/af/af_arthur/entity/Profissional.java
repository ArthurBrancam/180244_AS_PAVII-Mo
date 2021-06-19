package com.af.af_arthur.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class Profissional implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idProfissional;
    private String nome;
    private String telefone;
    private String endereco;
    private Date dataNascimento;

    @OneToMany
    @JoinColumn(name="ID_PROFISSIONAL")
    private List<Agendamento> agendamentos; 
    
    @ManyToMany
    @JoinTable(
        name="ProfissioalServico",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_servico", "id_profissional"}),
        joinColumns = @JoinColumn(name = "id_profissional"),
        inverseJoinColumns =  @JoinColumn(name = "id_servico")
    )
    private List<Servico> servicos;
    //atende clientes
    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
    
    public String getDataNascimentoFormatada() {
        return new SimpleDateFormat("dd/MM/yyyy").format(this.dataNascimento);
    }

    public void setDataNascimento(String dataNascimento) {
        try {
            this.dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimento);
        } catch (Exception e) {
            this.dataNascimento = null;
        }
    }

    @Override
    public String toString() {
        return "Profissional [idProfissional=" + idProfissional + ", nome=" + nome + "]";
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }
}