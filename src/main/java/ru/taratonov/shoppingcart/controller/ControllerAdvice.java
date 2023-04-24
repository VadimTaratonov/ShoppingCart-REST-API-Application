package ru.taratonov.shoppingcart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.taratonov.shoppingcart.dto.ErrorDTO;
import ru.taratonov.shoppingcart.util.OrderDetailNotFoundException;
import ru.taratonov.shoppingcart.util.OrderNotFoundException;
import ru.taratonov.shoppingcart.util.ProductIsNotInStockException;

import java.time.LocalDateTime;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
            ({OrderNotFoundException.class, OrderDetailNotFoundException.class,
                    ProductIsNotInStockException.class, Exception.class})
    public final ResponseEntity<ErrorDTO> handleException(Exception ex) {

        if (ex instanceof OrderNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            OrderNotFoundException exception = (OrderNotFoundException) ex;
            return NotFoundException(exception, status);
        } else if (ex instanceof OrderDetailNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            OrderDetailNotFoundException exception = (OrderDetailNotFoundException) ex;
            return NotFoundException(exception, status);
        } else if (ex instanceof ProductIsNotInStockException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            ProductIsNotInStockException exception = (ProductIsNotInStockException) ex;
            return NotFoundException(exception, status);
        } else {
            return otherException(ex);
        }
    }

    protected ResponseEntity<ErrorDTO> NotFoundException(OrderNotFoundException ex, HttpStatus status) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), LocalDateTime.now(), status);
        return new ResponseEntity<>(error, status);
    }

    protected ResponseEntity<ErrorDTO> NotFoundException(OrderDetailNotFoundException ex, HttpStatus status) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), LocalDateTime.now(), status);
        return new ResponseEntity<>(error, status);
    }

    protected ResponseEntity<ErrorDTO> NotFoundException(ProductIsNotInStockException ex, HttpStatus status) {
        ErrorDTO error = new ErrorDTO(ex.getMessage(), LocalDateTime.now(), status);
        return new ResponseEntity<>(error, status);
    }

    protected ResponseEntity<ErrorDTO> otherException(Exception ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDTO error = new ErrorDTO(ex.getMessage(), LocalDateTime.now(), status);
        return new ResponseEntity<>(error, status);
    }
}
