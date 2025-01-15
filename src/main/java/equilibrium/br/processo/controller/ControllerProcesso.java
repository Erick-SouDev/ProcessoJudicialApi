package equilibrium.br.processo.controller;

import equilibrium.br.processo.dto.DtoProcesso;
import equilibrium.br.processo.entity.ProcessoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import equilibrium.br.processo.HandlerExeption.ErroResponse;
import equilibrium.br.processo.exptions.ProcessoNaoEncontradoException;
import equilibrium.br.processo.services.ServiceProcesso;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/v1/processo"}, produces = "application/json")
@Tag(name = "Processos", description = "Gerenciamento de Processos")
public class ControllerProcesso {

    @Autowired
    private ServiceProcesso serviceProcesso;


    @Operation(summary = "Cria novos  processos", description = "Este endpoint salva  um processo com base nos dados enviados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ProcessoModel salvo  com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessoModel.class))),
            @ApiResponse(responseCode = "400", description = " Dados de entrada inválidos.")
    })
    @PostMapping("/novoprocesso")
    public ResponseEntity<ProcessoModel> novoProcesso(@RequestBody DtoProcesso dtoProcesso) throws ProcessoNaoEncontradoException {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceProcesso.salvarProcesso(dtoProcesso));
    }


    @Operation(summary = " Atualizar processo", description = "Este endpoint  atualiza um processo com base nos dados enviados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ProcessoModel  atualizado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessoModel.class))),
            @ApiResponse(responseCode = "400", description = " Dados de entrada inválidos.")
    })
    @PutMapping("/atualizarprocesso") // Esse endpoint cria ou atualiza um processo
    public ResponseEntity<ProcessoModel> editarProcesso(@RequestBody ProcessoModel processo) throws ProcessoNaoEncontradoException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(serviceProcesso.editarProcesso(processo));
    }


    @Operation(summary = "Buscar processo por ID", description = "Busca um processo pelo seu ID no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Processo encontrado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessoModel.class))),
            @ApiResponse(responseCode = "404", description = "Processo não encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponse.class)))})
    @GetMapping("/buscar/{id}") // Busca um processo pelo ID
    public ResponseEntity<ProcessoModel> buscarProcessoPorId(@PathVariable Long id) {
        ProcessoModel processo = serviceProcesso.buscarProcesso(id);
        return ResponseEntity.status(HttpStatus.OK).body(processo);
    }


    @Operation(summary = "Remover processo por ID", description = "Remove um processo pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Processo removido com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Processo não encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponse.class)))})
    @DeleteMapping("/excluir/{id}") // Remove um processo pelo ID
    public ResponseEntity<String> removerProcessoPorId(@PathVariable Long id) {
        serviceProcesso.removerProcesso(id);
        return ResponseEntity.status(HttpStatus.OK).body("ProcessoModel removido com sucesso.");
    }


    @Operation(summary = "Buscar processo  pelo numero gerado de processo ", description = "Busca um processo pelo número .")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ProcessoModel encontrado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessoModel.class))),
            @ApiResponse(responseCode = "404", description = "ProcessoModel não encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponse.class)))})
    @GetMapping("/pesquisar/{numeroProcesso}") // Busca um processo pelo número do processo
    public ResponseEntity<ProcessoModel> buscarProcessoPorNumero(@PathVariable String numeroProcesso)
            throws ProcessoNaoEncontradoException {

        return ResponseEntity.status(HttpStatus.OK).body(serviceProcesso.pesquisarProcessoPorNumero(numeroProcesso));

    }

    @Operation(summary = "Listar processos  com paginação", description = "Lista os processos por  pagina.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de processos retornada com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessoModel.class)))})
    @GetMapping("/processos") // Lista todos os processos
    public ResponseEntity<List<ProcessoModel>> listarProcessos(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "2") int limit) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceProcesso.buscarProcessosPaginados(page, limit));
    }
    
    @Operation(summary = "Carregar processos por tipo de processo ", description = "Carrega todos os processos associados a um tipo de processo especificado pela descrição.")
            @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista de processos retornada com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessoModel.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de processo não encontrado.", content = @Content(mediaType = "application/json"))})
    @GetMapping("/processosportipo/{descricao}")
    public ResponseEntity<List<ProcessoModel>> carregarProcessosPorTipo(@PathVariable String descricao) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceProcesso.carregarProcessosPorTipo(descricao));
    }


}
