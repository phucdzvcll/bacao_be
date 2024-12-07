package com.p5k.bacao.http.core.exception;

import com.p5k.bacao.http.core.base.ResultObject;
import com.p5k.bacao.http.core.enums.ServiceCodeEnum;
import com.p5k.bacao.http.core.exception.template.IResponseFactory;
import com.p5k.bacao.http.core.util.ResponseUtil;
import com.p5k.bacao.http.core.util.WebUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class RestExceptionHandler {
    private final IResponseFactory responseFactory;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResultObject<Object>> exception(final HttpServletRequest servletRequest,
                                                          final Exception exception) {
//        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
//        String message;
//        if (exception instanceof ServiceException serviceException
//                && null != serviceException.getResponseCodeEnum()) {
//            code = serviceException.getCode();
//            message = WebUtils.getMessage(serviceException.getMessage(), serviceException.getParams());
//        } else {
//        }
        String message = exception.getMessage();
        ResultObject<Object> dto = responseFactory.produce(exception);
        log.error(message, exception);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ExceptionHandler({ServiceException.class})
    @ResponseBody
    public ResponseEntity<ResultObject<Object>> handleAPIException(final HttpServletRequest servletRequest,
                                                                   final ServiceException exception) {
        ResultObject<Object> dto;
        if (null != exception.getResponseCodeEnum()) {
            String message = WebUtils.getMessage(exception.getResponseCodeEnum().getMessage()
                    , exception.getParams());
            dto = responseFactory.produce(exception.getResponseCodeEnum().getCode(), message);
        } else {
            dto = responseFactory.produce(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        }
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ResultObject<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException exception) {
        final List<FieldErrorModel> fieldErrors =
                exception.getBindingResult().getFieldErrors().stream()
                        .map(
                                error -> {
                                    FieldErrorModel fieldError = new FieldErrorModel();
                                    fieldError.setClazzName(exception.getParameter().getParameterName());
                                    fieldError.setErrorCode(error.getCode());
                                    fieldError.setViolateField(error.getField());
                                    fieldError.setViolateMsg(WebUtils.getMessage(error.getDefaultMessage(), error.getField()));
                                    return fieldError;
                                })
                        .toList();
        return ResponseUtil.error(exception.getClass().getSimpleName(), fieldErrors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ResultObject<Object> constraintViolationException(final ConstraintViolationException e) {

        final List<FieldErrorModel> fieldErrors = new ArrayList<>();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            final FieldErrorModel fieldError = new FieldErrorModel();
            final String errorMessage = violation.getMessage();
            final String fieldName = getErrorFileName(violation, errorMessage);
            fieldError.setViolateField(fieldName);
            try {
                fieldError.setViolateMsg(WebUtils.getMessage(errorMessage, fieldName));
            } catch (Exception exception) {
                fieldError.setViolateMsg(errorMessage);
            }
            fieldErrors.add(fieldError);
        }
        return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), fieldErrors);
    }

    private String getErrorFileName(final ConstraintViolation<?> violation,
                                    final String errorMessage) {
        if (ServiceCodeEnum.USER_EXCEPTION_CONFIRM_PASSWORD_DOES_NOT_MATCH.getMessage()
                .equals(errorMessage)) {
            return "confirmPassword";
        }
        if (ServiceCodeEnum.USER_EXCEPTION_ROLE_INVALID.getMessage().equals(errorMessage)) {
            return "roleName";
        }
        return violation.getPropertyPath().toString();
    }

}
