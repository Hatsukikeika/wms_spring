package com.wms.errors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.wms.model.ResponseBodyWrapper;
import com.wms.service.Exceptions.DataNotFoundException;
import com.wms.service.Exceptions.FieldMissingException;
import com.wms.service.Exceptions.IllegalActionException;
import com.wms.service.Exceptions.RegistrationException;

//extends DefaultHandlerExceptionResolver

@ControllerAdvice
@Order(1)
public class GeneralErrorHandler extends ExceptionHandlerExceptionResolver {

	@ExceptionHandler({ RegistrationException.class, FieldMissingException.class })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseBodyWrapper registrationException(Exception ex) {
		return new ResponseBodyWrapper().setStatus(HttpStatus.BAD_REQUEST.value()).setMessage(ex.getMessage());
	}

	@ExceptionHandler({ DataNotFoundException.class })
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseBodyWrapper dataNotFoundException(Exception ex) {
		return new ResponseBodyWrapper().setStatus(HttpStatus.NOT_FOUND.value()).setMessage(ex.getMessage());
	}
	
	@ExceptionHandler({IllegalActionException.class})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseBodyWrapper illegalActionException(Exception ex) {
		return new ResponseBodyWrapper().setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value()).setMessage(ex.getMessage());
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseBodyWrapper MethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		List<FieldError> errs = ex.getBindingResult().getFieldErrors();
		
		List<String> msgs = new ArrayList<>();
		for(FieldError err : errs) {
			msgs.add(err.getField() + err.getDefaultMessage());
		}
		return new ResponseBodyWrapper().setStatus(HttpStatus.BAD_REQUEST.value()).setMessage(msgs);
	}
}
