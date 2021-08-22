package ru.pronichev.handlers;

import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.pronichev.exceptions.NotFoundException;

@ControllerAdvice
public class UserExceptionController {

    @ExceptionHandler(value = NotFoundException.class)
    public ModelAndView notFound(NotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ModelAndView sqlViolation(SQLIntegrityConstraintViolationException exception) {
        ModelAndView modelAndView = new ModelAndView("operation_error");
        modelAndView.addObject("message", "This object is used in others ones, delete them first");
        modelAndView.addObject("description", exception.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}