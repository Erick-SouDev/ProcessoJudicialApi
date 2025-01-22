package equilibrium.br.processo.HandlerExeption;
import ch.qos.logback.core.status.Status;
import equilibrium.br.processo.exeption.ProcessoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {




    // Handler para exceções genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleException(Exception ex) {
        ErroResponse erroResponse = new ErroResponse(Status.ERROR , "valor invalido" , ex.getMessage());
        return new ResponseEntity<>(erroResponse, HttpStatus.NOT_FOUND);
    }

    // exceções de argumento inválido
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErroResponse erroResponse = new ErroResponse(Status.ERROR , "Argumento invalido" , ex.getMessage());
        return new ResponseEntity<>(erroResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErroResponse> handleIllegalArgumentException(MissingServletRequestParameterException ex) {
        ErroResponse erroResponse = new ErroResponse(Status.ERROR , " Parametro de busca obrigatorio", ex.getMessage());
        return new ResponseEntity<>(erroResponse, HttpStatus.BAD_REQUEST);
    }








}
