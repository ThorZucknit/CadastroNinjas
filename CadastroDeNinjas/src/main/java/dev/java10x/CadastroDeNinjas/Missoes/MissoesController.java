package dev.java10x.CadastroDeNinjas.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Mensagem de boas vindas",description = "Manda mensagem de boas-vindas para missão controller")

    public String boasVindas(){
        return "Bem-vindo ao missões" ;
    }


    @GetMapping("/listar")
    @Operation(summary = "Lista as missões",description = "Lista as missões armazenadas no banco de dados")
        public List<MissoesModel> listarMissoes(){
        return missoesService.listarMissoes();
    }




    @GetMapping("/listarID/{id}")
    @Operation(summary = "Lista as missões por id",description = "Rota encaminha para listagem de missões através do id")
    @Parameter(description = "Usuario manda o id no caminho da requisição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "ID encontrado om sucesso "),
            @ApiResponse(responseCode = "404",description = "ID não encontrado")
    })
        public MissoesModel listarMissoesPorId(@PathVariable  Long id){

        return missoesService.listarMissoesPorId(id);
    }


    @PostMapping("/criar")
    @Operation(summary = "Cria uma missão",description = "Cria uma missão no banco de dados")
    @Parameter(description = "Passa as informações e faz um post no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400",description = "Não foi possivel enviar o post")
    })
    public MissoesModel criarMissao(@RequestBody MissoesModel missoesModel){
        return missoesService.criarMissoes(missoesModel);
    }



    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera a missão",description = "Altera os dados da missão")
    @Parameter(description = "Usuario manda o id no caminho da requisição e as alterações para o put")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Alterado com sucesso"),
            @ApiResponse(responseCode = "404",description = "ID não encontrado")
    })
        public MissoesModel alterarPorId(@PathVariable Long id , @RequestBody MissoesModel missao){
        return missoesService.atualizarMissoesPorID(id,missao);
    }


    @DeleteMapping("/deletar/{id}")
    @Parameter(description ="Usuario manda o id através da requisição")
    @Operation(summary = "Deleta a missão",description = "Deleta a missão do banco de dados através do ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Missão deletada com sucesso"),
            @ApiResponse(responseCode = "404",description = "Missão não encontrada no banco de dados")
    })
        public void deletarPorId(@PathVariable  Long id){
            missoesService.deletarMissaoPorID(id);
    }
}
