package com.af.af_arthur.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Agendamento implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idAgendamento;
    private Date data;

    @ManyToOne
    @JoinColumn(name="ID_CLIENTE")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="ID_SERVICO")
    private Servico servico;

    @ManyToOne
    @JoinColumn(name="ID_PROFISSIONAL")
    private Profissional profissional;

    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public Date getData() {
        return this.data;
    }
    
    public String getDataFormatada() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(this.data);
    }

    public void setData(String data) {
        try {
            this.data = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(data);
        } catch (Exception e) {
            this.data = null;
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    @Override
    public String toString() {
        return "Agendamento [data=" + data + ", idAgendamento=" + idAgendamento + "]";
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}