package com.company.expection.handler;

import com.company.config.i18n.MessageService;
import com.company.expection.exp.AppBadRequestException;
import com.company.expection.exp.DuplicateItemException;
import com.company.expection.exp.ItemNotFoundException;
import com.company.model.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    private final MessageService messageService;

    @ExceptionHandler(AppBadRequestException.class)
    public ResponseEntity<ApiResponse<?>> handleAppBadRequestException(AppBadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(messageService.getMessage(e.getMessage()), HttpStatus.BAD_REQUEST.value(), true));
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleItemNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(messageService.getMessage("item.not.found"), HttpStatus.NOT_FOUND.value(), true));
    }

    @ExceptionHandler(DuplicateItemException.class)
    public ResponseEntity<ApiResponse<?>> handleDuplicateItemException(DuplicateItemException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(messageService.getMessage("item.already.exists"), HttpStatus.BAD_REQUEST.value(), true));
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodNotAllowedException(MethodNotAllowedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ApiResponse<>(messageService.getMessage("method.not.allowed"), HttpStatus.METHOD_NOT_ALLOWED.value(), true));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiResponse<>(messageService.getMessage("access.denied.exp"), HttpStatus.FORBIDDEN.value(), true));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), true));
    }
}
