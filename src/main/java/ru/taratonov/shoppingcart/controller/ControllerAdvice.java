package ru.taratonov.shoppingcart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.taratonov.shoppingcart.dto.ErrorDTO;
import ru.taratonov.shoppingcart.exception.OrderDetailNotFoundException;
import ru.taratonov.shoppingcart.exception.OrderNotFoundException;
import ru.taratonov.shoppingcart.exception.ProductIsNotInStockException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({OrderNotFoundException.class, OrderDetailNotFoundException.class,
            ProductIsNotInStockException.class})
    public ErrorDTO handleNotFoundException(Exception ex) {
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected List<ErrorDTO> otherException(MethodArgumentNotValidException ex) {
        List<ErrorDTO> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(e ->
                errors.add(new ErrorDTO(e.getField() + " " + e.getDefaultMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST)));
        return errors;
    }
}
