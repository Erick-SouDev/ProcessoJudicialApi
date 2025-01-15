package equilibrium.br.processo.HandlerExeption;
import ch.qos.logback.core.status.Status;
import equilibrium.br.processo.exptions.ProcessoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handler para a exceção "Processo não encontrado"
    @ExceptionHandler(ProcessoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleProcessoNaoEncontradoException(ProcessoNaoEncontradoException ex) {
        ErroResponse erroResponse = new ErroResponse(Status.INFO  , ex.getMessage(),"Processo  não foi encontrado ou não  existe");
        return new ResponseEntity<>(erroResponse, HttpStatus.NOT_FOUND);
    }

    // Handler para exceções genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleException(Exception ex) {
        ErroResponse erroResponse = new ErroResponse(Status.ERROR , ex.getMessage() , "Error interno");
        return new ResponseEntity<>(erroResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handler para exceções de argumento inválido (por exemplo, parâmetros errados na requisição)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErroResponse erroResponse = new ErroResponse(Status.ERROR , ex.getMessage() , " Argumento invalido");
        return new ResponseEntity<>(erroResponse, HttpStatus.BAD_REQUEST);
    }








}