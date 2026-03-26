package pro.ms.billetera.infrastructure.controller.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pro.ms.billetera.application.exception.*;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            CuentaNoEncontradaException.class,
            MonedaNoEncontradaException.class,
            EntidadPagoNoEncontradaException.class,
            ServicioNoEncontrado.class
    })
    public ResponseEntity<ApiError> handleNotFound(Exception ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(MonedaInvalidaException.class)
    public ResponseEntity<ApiError> handleBadRequest(MonedaInvalidaException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<ApiError> handleConflict(SaldoInsuficienteException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }
    @ExceptionHandler(MontoInvalidoException.class)
    public ResponseEntity<ApiError> handleMontoInvalido(MontoInvalidoException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex, HttpServletRequest request) {
        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocurrio un error interno inesperado",
                request
        );
    }

    private ResponseEntity<ApiError> buildResponse(HttpStatus status, String message, HttpServletRequest request) {
        ApiError body = new ApiError(
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI(),
                OffsetDateTime.now()
        );
        return ResponseEntity.status(status).body(body);
    }

    public record ApiError(
            int status,
            String error,
            String message,
            String path,
            OffsetDateTime timestamp
    ) {}
}
