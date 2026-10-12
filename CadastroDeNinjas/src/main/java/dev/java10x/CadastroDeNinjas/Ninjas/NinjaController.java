package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Mensagem de boas vindas",description = "Essa rota da uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota";
    }



    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja",description = "Rota cria um novo ninja e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400",description = "Error na criação no ninja")
    })

    // RequestBody ---> Essa anotação diz ao Spring para ler o JSON enviado no corpo da requisição e convertê-lo em um objeto Java.
    public ResponseEntity<String> criarNinja(@RequestBody NinjaModel ninja){

        NinjaModel novoNinja = ninjaService.criarNinja(ninja);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso "  + novoNinja.getNome() + " (ID): " + novoNinja.getId()) ;

    }


     @GetMapping("/listar")
     @Operation(summary = "Lista os ninjas",description = "Lista os ninjas do banco de dados em formatado JSON")

        public List<NinjaModel> listarNinjas(){
        return ninjaService.listarNinjas();
        }



     @GetMapping("/listar/{id}")
     @Operation(summary = "Lista ninja por id",description = "Rota lista o ninja por id")
     @Parameter(description="Usuario manda o id no caminho da requisição" )
     @ApiResponses(value = {
             @ApiResponse(responseCode = "200", description = "Ninja listado com sucesso"),
             @ApiResponse(responseCode = "404",description = "Ninja não encontrado")
     })

     //Path varible faz parte do caminho /listar/1 --> url viro variavel
         public ResponseEntity<?> mostrarNinjaPorId(@PathVariable  Long id){
         NinjaModel ninja = ninjaService.listarNinjasPorId(id);
         if(ninja != null){

             return ResponseEntity.ok(ninja);
         }else{
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado");
         }

     }


        @PutMapping("/alterar/{id}")
        @Parameter(description = "Usuário manda id através da requisição")
        @Operation(summary = "Altera os dados do ninja",description = "Passa o id e altera os dados do ninja")
        @ApiResponses(value={
                @ApiResponse(responseCode = "200",description = "Ninja alterado"),
                @ApiResponse(responseCode = "404",description = "ID não encontrado")
        })
        public ResponseEntity<?> alterarNinjaPorId(@PathVariable Long id,@RequestBody NinjaModel ninjaa){
            NinjaModel ninja = ninjaService.atualizarNinjaPor(id,ninjaa);

            if(ninja != null){

                return ResponseEntity.ok().body(ninja);
            }else{

                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com Id " + id + " não encontrado");
            }

        }


     @DeleteMapping("/deletar/{id}")
     @Parameter(description ="Usuario manda o id através da requisição")
     @Operation(summary = "Deleta o ninja",description = "Deleta o ninja do banco de dados através do ID")
     @ApiResponses(value = {
             @ApiResponse(responseCode = "200",description = "Ninja Deletado com sucesso"),
             @ApiResponse(responseCode = "404",description = "Ninja não encontrado no banco de dados")
     })
     public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id){

        if(ninjaService.listarNinjasPorId(id) != null){
            ninjaService.deletarPorId(id);
            return ResponseEntity.ok().body("Ninja com o ID " + id + " deletado com sucesso!");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com ID " + id + " não encontrado!");
        }

     }
}
