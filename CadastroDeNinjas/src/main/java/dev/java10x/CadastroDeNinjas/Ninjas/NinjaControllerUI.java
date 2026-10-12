package dev.java10x.CadastroDeNinjas.Ninjas;


import dev.java10x.CadastroDeNinjas.Missoes.MissoesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUI {

    private final NinjaService ninjaService;
    private final MissoesRepository missoesRepository;

    // ATUALIZADO: Construtor para incluir o MissoesRepository
    public NinjaControllerUI(NinjaService ninjaService, MissoesRepository missoesRepository) {
        this.ninjaService = ninjaService;
        this.missoesRepository = missoesRepository;
    }

    @GetMapping("/listar")
    public String mostrarTodosOsNinjas(Model model) {
        List<NinjaModel> ninjas = ninjaService.listarNinjas();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas";
    }




    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute("ninja") NinjaModel ninja) {
        if (ninja.getId() == null) {
            ninjaService.criarNinja(ninja);
        } else {
            ninjaService.atualizarNinjaPor(ninja.getId(), ninja);
        }
        return "redirect:formulario";
    }




    @DeleteMapping("/deletar/{id}")
    public String deletarNinjaPorID(@PathVariable Long id) {
        ninjaService.listarNinjasPorId(id);
        return "redirect:todos";
    }

    @GetMapping("/listar/{id}")
    public String mostrarDetalhesDoNinja(@PathVariable Long id, Model model) {
        NinjaModel ninja = ninjaService.listarNinjasPorId(id);
        model.addAttribute("ninja", ninja);
        return "detalhesNinja";
    }
}