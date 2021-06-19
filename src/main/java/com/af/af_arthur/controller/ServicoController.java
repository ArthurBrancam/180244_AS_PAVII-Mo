package com.af.af_arthur.controller;

import com.af.af_arthur.entity.Servico;
import com.af.af_arthur.service.ServicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ServicoController {
    @Autowired
    private ServicoService servicoService;

    @GetMapping("/servicos")
    public ModelAndView getServicos(){
        ModelAndView mv = new ModelAndView("servicos");
        mv.addObject("servicos", servicoService.getServicos());
        return mv;
    }

    @GetMapping("/editarServico")
    public ModelAndView editarAluno(@RequestParam Integer idServico){
        
        ModelAndView mv = new ModelAndView("servicosEdit");
    
        Servico servico = servicoService.getServicoById(idServico); 
        mv.addObject("servico",  servico);

        return mv;
   
    }

    @PostMapping("/saveservico")
    public String save(@ModelAttribute Servico servico){
        servicoService.save(servico);
        return "redirect:/servicos";
    }


    @GetMapping("/deletarServico")
    public String deletarServico(@RequestParam Integer idServico) {
        Servico servico = servicoService.getServicoById(idServico);
        servicoService.remove(servico);

        return "redirect:/servicos/";
    }
}