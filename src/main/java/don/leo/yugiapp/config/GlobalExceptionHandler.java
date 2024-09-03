package don.leo.yugiapp.config;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException ex) {
        var error = new ErrorMessage(ex);
        return new ResponseEntity<>(error, error.httpStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        var error = new ErrorMessage(ex);
        return new ResponseEntity<>(error, error.httpStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleIEntityNotFoundException(EntityNotFoundException ex) {
        var error = new ErrorMessage(ex);
        return new ResponseEntity<>(error, error.httpStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorMessage> handleBadCredentialsException(BadCredentialsException ex) {
        var error = new ErrorMessage(ex);
        return new ResponseEntity<>(error, error.httpStatus());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> handleAccessDeniedException(AccessDeniedException ex) {
        var error = new ErrorMessage(ex);
        return new ResponseEntity<>(error, error.httpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGenericException(Exception ex) {
        var error = new ErrorMessage();
        return new ResponseEntity<>(error, error.httpStatus());
    }

    public record ErrorMessage(
            String erro,
            HttpStatus httpStatus,
            int statusCode
    ) {
        public ErrorMessage(IllegalArgumentException ex) {
            this(
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST.value());
        }

        public ErrorMessage(EntityNotFoundException ex) {
            this(
                    coalescer(ex.getMessage(), "Entidade não encontrada"),
                    HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value());
        }

        public ErrorMessage(DataIntegrityViolationException ex) {
            this(
                    extrairMensagemDatabase(ex),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        public ErrorMessage(BadCredentialsException ex) {
            this(
                    ex.getMessage(),
                    HttpStatus.UNAUTHORIZED,
                    HttpStatus.UNAUTHORIZED.value());
        }

        public ErrorMessage(AccessDeniedException ex) {
            this(
                    ex.getMessage(),
                    HttpStatus.FORBIDDEN,
                    HttpStatus.FORBIDDEN.value());
        }

        public ErrorMessage() {
            this("Erro: Ocorreu um erro interno no servidor.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        private static String extrairMensagemDatabase(DataIntegrityViolationException ex) {
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

        private static String coalescer(String left, String right) {
            return StringUtils.hasText(left)
                    ? left
                    : right;
        }
    }
}
