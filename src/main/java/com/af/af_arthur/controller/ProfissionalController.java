package com.af.af_arthur.controller;

import java.util.List;

import com.af.af_arthur.entity.Agendamento;
import com.af.af_arthur.entity.Profissional;
import com.af.af_arthur.entity.Servico;
import com.af.af_arthur.service.AgendamentoService;
import com.af.af_arthur.service.ClienteService;
import com.af.af_arthur.service.ProfissionalService;
import com.af.af_arthur.service.ServicoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfissionalController {
    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private ServicoService servicoService;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping("/profissionais")
    public ModelAndView getProfissionals(){
        ModelAndView mv = new ModelAndView("profissionais");
        mv.addObject("profissionais", profissionalService.getProfissionais());
        mv.addObject("profissional", new Profissional());
        mv.addObject("servicos", servicoService.getServicos());
        System.out.println(servicoService.getServicos());
        return mv;
    }

    @GetMapping("/profissionais/{id}")
    public ModelAndView getCursoDetalhes(@PathVariable(name = "id") Integer id) {

        Profissional profissional = profissionalService.getProfissionalById(id);
        ModelAndView mv = new ModelAndView("profissionaisDetails");
        List<Servico> noServicos = servicoService.getServicos();
        noServicos.removeAll(profissional.getServicos()); 
        mv.addObject("servicos", noServicos);
        mv.addObject("profissional", profissional);
        mv.addObject("clientes", clienteService.getClientes());
        mv.addObject("agendamento", new Agendamento());
        // List <Book> noBooks = bookService.getBooks();
        // noBooks.removeAll(profissional.getBooks());
        // mv.addObject("books", noBooks);
        System.out.println(noServicos);
        return mv;
    }

    @PostMapping("/saveprofissional")
    public String save(@ModelAttribute Profissional profissional){
        profissionalService.save(profissional);
        return "redirect:/profissionais";
    }
    
    @PostMapping("/associarServico")
    public String associarServico(@ModelAttribute Servico servico, @RequestParam Integer idProfissional) {
        Profissional profissional = profissionalService.getProfissionalById(idProfissional);
        servico = servicoService.getServicoById(servico.getIdServico());

        profissional.getServicos().add(servico);
        profissionalService.save(profissional);

        return "redirect:/profissionais/" + idProfissional;
    }

    @GetMapping("/desassociarServico")
    public String desassociarServico(@RequestParam Integer idServico, @RequestParam Integer idProfissional) {
        Profissional profissional = profissionalService.getProfissionalById(idProfissional);
        Servico servico = servicoService.getServicoById(idServico);

        profissional.getServicos().remove(servico);
        profissionalService.save(profissional);

        return "redirect:/profissionais/" + idProfissional;
    }
    
    @PostMapping("/associarAgendamento")
    public String associarAgendamento(@ModelAttribute Agendamento agendamento, @RequestParam Integer idProfissional) {
        Profissional profissional = profissionalService.getProfissionalById(idProfissional);
        agendamento.setProfissional(profissional);

        agendamentoService.save(agendamento);

        return "redirect:/profissionais/" + idProfissional;
    }

    @GetMapping("/editarProfissional")
    public ModelAndView editarAluno(@RequestParam Integer idProfissional){
        
        ModelAndView mv = new ModelAndView("profissionaisEdit");
    
        Profissional profissional = profissionalService.getProfissionalById(idProfissional); 
        mv.addObject("profissional",  profissional);

        return mv;
   
    }

    @GetMapping("/deletarProfissional")
    public String deletarProfissional(@RequestParam Integer idProfissional) {
        Profissional profissional = profissionalService.getProfissionalById(idProfissional);
        profissionalService.delete(profissional);

        return "redirect:/profissionais/";
    }
}