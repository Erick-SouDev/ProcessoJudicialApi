package equilibrium.br.processo.controller;

import equilibrium.br.processo.dto.DtoTipoProcesso;
import equilibrium.br.processo.entity.TipoProcessoModel;
import equilibrium.br.processo.services.ServiceTipoProcesso;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/v1/processo"})
public class ControllerTipoProcesso {

    @Autowired
    private ServiceTipoProcesso serviceTipoProcesso;

    @Operation(summary = "Cria tipo de processo ", description = "Este endpoint criar tipo de processo  com base nos dados enviados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ProcessoModel salvo  com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TipoProcessoModel.class))),
            @ApiResponse(responseCode = "400", description = " Dados de entrada inválidos.")
    })
    @PostMapping("/novotipo")
    public ResponseEntity<TipoProcessoModel> criarNovoTipoProcesso(@RequestBody DtoTipoProcesso dtoTipoProcesso)  {

        return ResponseEntity.status(HttpStatus.CREATED).body(serviceTipoProcesso.criarTipoProcesso(dtoTipoProcesso));
    }

    @GetMapping("/tiposprocessos")
    @Operation(summary = "Lista todos os tipos de processo", responses = {} , ignoreJsonView = false)
    @ApiResponses(value = {
           @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json" ,schema = @Schema(implementation = TipoProcessoModel.class)) ,useReturnTypeSchema = true )
    })
    public List<TipoProcessoModel>  listarTiposProcesso() {

    return serviceTipoProcesso.listarTiposProcessos();
    }
}
