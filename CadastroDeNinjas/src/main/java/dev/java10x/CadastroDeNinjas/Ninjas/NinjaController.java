package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // informa ao Spring que a classe recebe requisições web e retorna dados na resposta
@RequestMapping("/ninjas")
public class NinjaController {
    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")//Cria um endpoint que responde http do tipo get

    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }

    //Adicionar Ninja (CREATE)
    @PostMapping("/criar")
    // RequestBody ---> Essa anotação diz ao Spring para ler o JSON enviado no corpo da requisição e convertê-lo em um objeto Java.
    public NinjaModel criarNinja(@RequestBody NinjaModel ninja){
        return ninjaService.criarNinja(ninja) ;
    }

    //Listar ninjas (READ)
     @GetMapping("/listar")
        public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas();
        }

    //MOSTRAR OS NINJAS POR ID (READ)
     @GetMapping("listar/{id}")
     //Path varible faz parte do caminho /listar/1 --> url viro variavel
    public NinjaModel mostrarNinjaPorId(@PathVariable  Long id){
        return ninjaService.listarNinjasPorId(id);
     }

    //Alterar dados dos ninjas (UPDATE)

        @PutMapping("/alterarID")
        public String alterarNinjaPorId(){
            return "Alterar ninja por id";
        }
    //Deletar Ninja (DELETE)

     @DeleteMapping("/deletarID")
     public String deletarNinjaPorId(){
         return "deletar ninja por id";
     }

}
