
package equilibrium.br.processo.HandlerExeption;
import ch.qos.logback.core.status.Status;
import equilibrium.br.processo.exeption.ProcessoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {


    // Handler para exceções genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleException(Exception ex) {
        ErroResponse erroResponse = new ErroResponse(Status.ERROR , "Error algo deu errado" , ex.getMessage());
        return new ResponseEntity<>(erroResponse, HttpStatus.NOT_FOUND);
    }

    // exceções de argumento inválido
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErroResponse erroResponse = new ErroResponse(Status.INFO, "Argumento invalido" , ex.getMessage());
        return new ResponseEntity<>(erroResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ProcessoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleIllegalArgumentException(ProcessoNaoEncontradoException ex) {
        ErroResponse erroResponse = new ErroResponse(Status.INFO , "Error ", ex.getMessage());
        return new ResponseEntity<>(erroResponse, HttpStatus.BAD_REQUEST);
    }








}
