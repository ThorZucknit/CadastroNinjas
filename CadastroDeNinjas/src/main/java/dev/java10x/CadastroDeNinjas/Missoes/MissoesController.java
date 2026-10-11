package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController (MissoesService missoesService){
        this.missoesService = missoesService;
    }

    @GetMapping("/boasvindas")
    public String boasVindas(){
        return "Bem-Vindo!" ;
    }

    @GetMapping("/listar")
        public List<MissoesModel> listarMissoes(){
        return missoesService.listarMissoes();
    }
    @GetMapping("/listarID/{id}")
        public MissoesModel listarMissoesPorId(@PathVariable  Long id){

        return missoesService.listarMissoesPorId(id);
    }

    @PostMapping("/criar")

    public MissoesModel criarMissao(@RequestBody MissoesModel missoesModel){
        return missoesService.criarMissoes(missoesModel);
    }

    @PutMapping("/alterar/{id}")
        public MissoesModel alterarPorId(@PathVariable Long id , @RequestBody MissoesModel missao){
        return missoesService.atualizarMissoesPorID(id,missao);
    }

    @DeleteMapping("/deletar/{id}")
        public void deletarPorId(@PathVariable  Long id){
            missoesService.deletarMissaoPorID(id);
    }
}
