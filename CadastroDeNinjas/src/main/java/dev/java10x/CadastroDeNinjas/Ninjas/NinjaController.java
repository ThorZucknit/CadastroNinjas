package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // informa ao Spring que a classe recebe requisições web e retorna dados na resposta
@RequestMapping("/ninjas")
public class NinjaController {
    private final NinjaService ninjaService;

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
    public ResponseEntity<String> criarNinja(@RequestBody NinjaModel ninja){

        NinjaModel novoNinja = ninjaService.criarNinja(ninja);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso "  + novoNinja.getNome() + " (ID): " + novoNinja.getId()) ;

    }

    //Listar ninjas (READ)
     @GetMapping("/listar")
        public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas();
        }


    //MOSTRAR OS NINJAS POR ID (READ)
     @GetMapping("/listar/{id}")
     //Path varible faz parte do caminho /listar/1 --> url viro variavel
         public ResponseEntity<?> mostrarNinjaPorId(@PathVariable  Long id){
         NinjaModel ninja = ninjaService.listarNinjasPorId(id);
         if(ninja != null){

             return ResponseEntity.ok(ninja);
         }else{
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado");
         }

     }

    //Alterar dados dos ninjas (UPDATE)

        @PutMapping("/alterar/{id}")
        public ResponseEntity<?> alterarNinjaPorId(@PathVariable Long id,@RequestBody NinjaModel ninjaa){
            NinjaModel ninja = ninjaService.atualizarNinjaPor(id,ninjaa);

            if(ninja != null){

                return ResponseEntity.ok().body(ninja);
            }else{

                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com Id " + id + " não encontrado");
            }

        }

    //Deletar Ninja (DELETE)

     @DeleteMapping("/deletar/{id}")
     public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id){

        if(ninjaService.listarNinjasPorId(id) != null){
            ninjaService.deletarPorId(id);
            return ResponseEntity.ok().body("Ninja com o ID " + id + " deletado com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com ID " + id + " não encontrado!");
        }

     }
}
