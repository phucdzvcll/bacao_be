//package com.p5k.bacao.http.core.exception;
//
//import com.corundumstudio.socketio.SocketIOServer;
//import com.p5k.bacao.http.core.base.ResultObject;
//import com.p5k.bacao.http.core.enums.ServiceCodeEnum;
//import com.p5k.bacao.http.core.exception.template.IResponseFactory;
//import com.p5k.bacao.http.core.util.ResponseUtil;
//import com.p5k.bacao.http.core.util.WebUtils;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.validation.ConstraintViolation;
//import jakarta.validation.ConstraintViolationException;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//@RestControllerAdvice
//@AllArgsConstructor
//public class SocketExceptionHandler {
//
//    @ExceptionHandler({SocketException.class})
//    @ResponseBody
//    public void handleAPIException(final SocketException exception) {
//        ResultObject<Object> dto;
//        if (null != exception.getResponseCodeEnum()) {
//            String message = WebUtils.getMessage(exception.getResponseCodeEnum().getMessage()
//                    , exception.getParams());
//            dto = responseFactory.produce(exception.getResponseCodeEnum().getCode(), message);
//        } else {
//            dto = responseFactory.produce(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
//        }
//        log.error(exception.getMessage(), exception);
//        return new ResponseEntity<>(dto, HttpStatus.OK);
//    }
//
//
//}
