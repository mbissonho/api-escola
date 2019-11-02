package com.escola.api.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SystemExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler({ DataIntegrityViolationException.class } )
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		String mensagemUsuario = messageSource.getMessage("data_integrity_violation_exception", null, ex.getMessage(), LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<SystemError> erros = Arrays.asList(new SystemError(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest reques) {
		String userMessage = messageSource.getMessage("empty_result_data_access_exception", null, ex.getMessage(), LocaleContextHolder.getLocale());
		String devMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<SystemError> SystemErrorrs = Arrays.asList(new SystemError(userMessage, devMessage));
		return handleExceptionInternal(ex, SystemErrorrs, new HttpHeaders(), HttpStatus.BAD_REQUEST, reques);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status,  WebRequest reques){
		String userMessage = messageSource.getMessage("http_message_not_readable", null, ex.getMessage(), LocaleContextHolder.getLocale());
		String devMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<SystemError> SystemErrorrs = Arrays.asList(new SystemError(userMessage, devMessage));
		return handleExceptionInternal(ex, SystemErrorrs, new HttpHeaders(), HttpStatus.BAD_REQUEST, reques);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<SystemError> SystemErrors = makeSystemErrorsList(ex.getBindingResult());
		return handleExceptionInternal(ex, SystemErrors, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	private List<SystemError> makeSystemErrorsList(BindingResult bindingResult) {
		List<SystemError> SystemErrors = new ArrayList<>();
		
		for (FieldError fieldSystemError : bindingResult.getFieldErrors()) {
			String userMessage = messageSource.getMessage(fieldSystemError, LocaleContextHolder.getLocale());
			String devMessage = fieldSystemError.toString();
			SystemErrors.add(new SystemError(userMessage, devMessage));
		}
			
		return SystemErrors;
	}
	
	
}
