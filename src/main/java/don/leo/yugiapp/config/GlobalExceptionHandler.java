package don.leo.yugiapp.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(new ErrorMessage(ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(DataIntegrityViolationException ex) {
        var status = HttpStatus.BAD_REQUEST;
        var errorMessage = new ErrorMessage(extrairMensagemDatabase(ex), status.value());
        return new ResponseEntity<>(errorMessage, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGenericException(Exception ex) {
        return new ResponseEntity<>(new ErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String extrairMensagemDatabase(DataIntegrityViolationException ex) {
        Pattern pattern = Pattern.compile("(?s)\\[(.*?)]");
        Matcher matcher = pattern.matcher(ex.getMessage());
        String mensagem;

        if (matcher.find()) {
            mensagem = matcher.group(1);
        } else {
            mensagem = "Houve um erro ao acessar os dados da aplicação";
        }

        return mensagem;
    }

    public record ErrorMessage(
            String erro,
            int status
    ) {
        public ErrorMessage(IllegalArgumentException ex) {
            this(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        }

        public ErrorMessage() {
            this("Erro: Ocorreu um erro interno no servidor.",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
