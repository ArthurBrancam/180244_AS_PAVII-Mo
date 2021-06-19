package com.af.af_arthur.controller;

import com.af.af_arthur.entity.Cliente;
import com.af.af_arthur.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public ModelAndView getClientes(){
        ModelAndView mv = new ModelAndView("clientes");
        mv.addObject("clientes", clienteService.getClientes());
        return mv;
    }

    @PostMapping("/savecliente")
    public String save(@ModelAttribute Cliente cliente){
        clienteService.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/clienteDetails/{id}")
    public ModelAndView getCursoDetalhes(@PathVariable(name = "id") Integer id) {

        Cliente cliente = clienteService.getClienteById(id);
        ModelAndView mv = new ModelAndView("clientesDetails");
        mv.addObject("cliente", cliente);

        return mv;
    }
    
    @GetMapping("/deletarCliente")
    public String deletarCliente(@RequestParam Integer idCliente) {
        Cliente profissional = clienteService.getClienteById(idCliente);
        clienteService.remove(profissional);

        return "redirect:/clientes/";
    }

    @GetMapping("/editarCliente")
    public ModelAndView editarAluno(@RequestParam Integer idCliente){
        
        ModelAndView mv = new ModelAndView("clientesEdit");
    
        Cliente cliente = clienteService.getClienteById(idCliente); 
        mv.addObject("cliente",  cliente);

        return mv;
   
    }


}