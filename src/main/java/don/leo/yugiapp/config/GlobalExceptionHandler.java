package don.leo.yugiapp.config;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(Exception ex, HttpServletRequest request) {
        logException(ex, request);
        var error = getErrorMessage(ex);
        return new ResponseEntity<>(error, error.httpStatus());
    }

    private void logException(Exception ex, HttpServletRequest request) {
        var classeOrigem = ex.getStackTrace()[0].getClassName();
        var url = request.getRequestURL().toString();
        log.error("Excecao lancada na classe: {}. URL: {}. Error: {}", classeOrigem, url, ex.getMessage());
    }

    private ErrorMessage getErrorMessage(Exception ex) {
        return switch (ex) {
            case IllegalArgumentException e -> new ErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST);
            case EntityNotFoundException e ->
                    new ErrorMessage(coalescer(e.getMessage(), "Entidade não encontrada"), HttpStatus.NOT_FOUND);
            case DataIntegrityViolationException e ->
                    new ErrorMessage(extrairMensagemDatabase(e), HttpStatus.INTERNAL_SERVER_ERROR);
            case BadCredentialsException e -> new ErrorMessage(e.getMessage(), HttpStatus.UNAUTHORIZED);
            case AccessDeniedException e -> new ErrorMessage(e.getMessage(), HttpStatus.FORBIDDEN);
            default -> new ErrorMessage("Ocorreu um erro interno no servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }

    private String coalescer(String left, String right) {
        return StringUtils.hasText(left)
                ? left
                : right;
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
            HttpStatus httpStatus
    ) { }
}
