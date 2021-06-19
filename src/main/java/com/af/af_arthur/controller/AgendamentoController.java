package com.af.af_arthur.controller;

import java.util.Date;

import com.af.af_arthur.entity.Agendamento;
import com.af.af_arthur.service.AgendamentoService;
import com.af.af_arthur.service.ClienteService;
import com.af.af_arthur.service.ProfissionalService;
import com.af.af_arthur.service.ServicoService;
import com.af.af_arthur.utils.Erros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AgendamentoController {
    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProfissionalService profissionalService;
    
    @Autowired
    private ServicoService servicoService;

    @GetMapping("/agendamentos")
    public ModelAndView getAgendamentos(){
        ModelAndView mv = new ModelAndView("agendamentos");
        mv.addObject("profissionais", profissionalService.getProfissionais());
        mv.addObject("clientes", clienteService.getClientes());
        mv.addObject("servicos", servicoService.getServicos());
        mv.addObject("agendamento", new Agendamento());
        mv.addObject("error", Erros.getMsg());
        mv.addObject("agendamentos", agendamentoService.getAgendamentos());
        Erros.setMsg("");
        Erros.setOnError(false);
        return mv;
    }

    @GetMapping("/editarAgendamento")
    public ModelAndView editarAluno(@RequestParam Integer idAgendamento){
        
        ModelAndView mv = new ModelAndView("agendamentosEdit");
    
        Agendamento agendamento = agendamentoService.getAgendamentoById(idAgendamento); 
        mv.addObject("agendamento",  agendamento);
        mv.addObject("servicos", servicoService.getServicos());
        mv.addObject("clientes", clienteService.getClientes());
        mv.addObject("profissionais", profissionalService.getProfissionais());
        return mv;
   
    }

    @GetMapping("/removerAgendamento")
    public String removerAgendamento(@RequestParam Integer idAgendamento){
        Agendamento agendamento = agendamentoService.getAgendamentoById(idAgendamento);
        agendamentoService.remove(agendamento);
        return "redirect:/agendamentos";
    }

    @PostMapping("/saveagendamento")
    public String save(@ModelAttribute Agendamento agendamento){
        Date data = agendamento.getData();
        boolean verificador = false;
        if(data==null){
            Erros.setMsg("Formato de data inválido.");
            return "redirect:/agendamentos";
        }
        for (Agendamento a : agendamento.getProfissional().getAgendamentos()){
            System.out.println(a.getData());
            if(a.getData().compareTo(data) == 0){
                verificador = true;
            }
        }
        if(data.compareTo(new Date()) < 0)
            Erros.setMsg("Data inválida.");
        else if(verificador == true && agendamento.getIdAgendamento() < 1)
            Erros.setMsg("Essa data já foi registrada.");
        else{
            agendamentoService.save(agendamento);
        }
            
        return "redirect:/agendamentos";
    }
}