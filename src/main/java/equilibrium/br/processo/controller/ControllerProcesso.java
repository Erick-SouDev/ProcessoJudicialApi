package equilibrium.br.processo.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import equilibrium.br.processo.HandlerExeption.ErroResponse;
import equilibrium.br.processo.entity.Processo;
import equilibrium.br.processo.exptions.ProcessoNaoEncontradoException;
import equilibrium.br.processo.services.ServiceProcesso;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = { "/api/processo" }, produces = "application/json")
@Tag(name = "Processos", description = "Gerenciamento de Processos")
public class ControllerProcesso {

	@Autowired
	private ServiceProcesso serviceProcesso;

	@Operation(summary = "Salvar ou atualizar processo", description = "Este endpoint salva ou atualiza um processo com base nos dados enviados.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Processo salvo ou atualizado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Processo.class))),
	    @ApiResponse(responseCode = "400", description = "Requisição malformada. Dados de entrada inválidos.")
	})
	@PostMapping("/salvar") // Esse endpoint cria ou atualiza um processo
	public ResponseEntity<Processo> novoProcesso(@RequestBody Processo processo) throws ProcessoNaoEncontradoException {
	    Processo newProcesso = serviceProcesso.salvarProcesso(processo);
	    return ResponseEntity.status(HttpStatus.CREATED).body(newProcesso);
	}

	@Operation(summary = "Buscar Processo por ID", description = "Busca um processo pelo seu ID no banco de dados.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Processo encontrado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Processo.class))),
	    @ApiResponse(responseCode = "404", description = "Processo não encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponse.class))) })
	@GetMapping("/{id}") // Busca um processo pelo ID
	public ResponseEntity<Processo> buscarProcessoPorId(@PathVariable Long id) {
	    Processo processo = serviceProcesso.buscarProcesso(id);
	    return ResponseEntity.status(HttpStatus.OK).body(processo);
	}

	@Operation(summary = "Remover Processo por ID", description = "Remove um processo pelo seu ID.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Processo removido com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
	    @ApiResponse(responseCode = "404", description = "Processo não encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponse.class))) })
	@DeleteMapping("/excluirprocesso/{id}") // Remove um processo pelo ID
	public ResponseEntity<String> removerProcessoPorId(@PathVariable Long id) {
	    serviceProcesso.removerProcesso(id);
	    return ResponseEntity.status(HttpStatus.OK).body("Processo removido com sucesso.");
	}

	@Operation(summary = "Buscar Processo por Número", description = "Busca um processo pelo número de processo.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Processo encontrado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Processo.class))),
	    @ApiResponse(responseCode = "404", description = "Processo não encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponse.class))) })
	@GetMapping("/{numeroProcesso}") // Busca um processo pelo número do processo
	public ResponseEntity<Processo> buscarProcessoPorNumero(@PathVariable String numeroProcesso)
	        throws ProcessoNaoEncontradoException {

	    return ResponseEntity.status(HttpStatus.OK).body(serviceProcesso.pesquisarProcessoPorNumero(numeroProcesso));

	}

	@Operation(summary = "Listar Processos", description = "Lista os processos com paginação.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de processos retornada com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Processo.class))) })
	@GetMapping("/processos") // Lista todos os processos
	public ResponseEntity<?> listarProcessos(@RequestParam int page, @RequestParam int limit) {
	    return ResponseEntity.status(HttpStatus.OK).body(serviceProcesso.buscarProcessosPaginados(page, limit));
	}


}
