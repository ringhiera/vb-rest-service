package com.impe.demo.vb.web.exceptionhandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.impe.demo.vb.exceptions.MalformedRequestException;
import com.impe.demo.vb.exceptions.ResourceNotFoundException;
import com.impe.demo.vb.model.ErrorMessageDto;

/**
 * Handles exceptions thrown by Rest Controllers
 * It might need to be adjusted for Dev/Prod
 * or configured different RestExceptionHandler per env
 * @author impe
 *
 */
@ControllerAdvice(annotations = RestController.class)
@RestController
public class RestExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	private AtomicLong counter = new AtomicLong();
	

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public ErrorMessageDto handleResourceNotFoundException(HttpServletRequest request, Exception ex){
		ErrorMessageDto errorMessageDto = new ErrorMessageDto();
		errorMessageDto.setId(counter.incrementAndGet());
		errorMessageDto.setTimestamp(new Date());
		errorMessageDto.setMessage(ex.getLocalizedMessage());
		LOGGER.error(errorMessageDto.toString());
		return errorMessageDto;
	}
	
	@ExceptionHandler(MalformedRequestException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public ErrorMessageDto handleMalformedRequestException(HttpServletRequest request, Exception ex){
		ErrorMessageDto errorMessageDto = new ErrorMessageDto();
		errorMessageDto.setId(counter.incrementAndGet());
		errorMessageDto.setTimestamp(new Date());
		errorMessageDto.setMessage(ex.getLocalizedMessage());
		LOGGER.error(errorMessageDto.toString());
		return errorMessageDto;
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public ErrorMessageDto handleConstraintViolationException(HttpServletRequest request, Exception ex){
		ConstraintViolationException e = (ConstraintViolationException)ex;
		ErrorMessageDto errorMessageDto = new ErrorMessageDto();
		errorMessageDto.setId(counter.incrementAndGet());
		errorMessageDto.setTimestamp(new Date());
		errorMessageDto.setMessage(ex.getLocalizedMessage());
		 // handle here
		List<String> details = new ArrayList<>();
		for (ConstraintViolation<?> c : e.getConstraintViolations()) {
			details.add(c.getMessage());
		}
		errorMessageDto.setDetails(details);
		LOGGER.error("ConstraintViolationException"+errorMessageDto.toString());
		return errorMessageDto;
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public ErrorMessageDto handleMethodArgumentNotValidException(HttpServletRequest request, Exception ex){
		MethodArgumentNotValidException e = (MethodArgumentNotValidException)ex;
		ErrorMessageDto errorMessageDto = new ErrorMessageDto();
		errorMessageDto.setId(counter.incrementAndGet());
		errorMessageDto.setTimestamp(new Date());
		errorMessageDto.setMessage(ex.getLocalizedMessage());
		LOGGER.error("MethodArgumentNotValidException"+errorMessageDto.toString());
		return errorMessageDto;
	}

	
	
//	A few more specific cases can be added here
	
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST) //we could also use a 500 class
	public ErrorMessageDto handle(HttpServletRequest request, Exception ex){
		ErrorMessageDto errorMessageDto = new ErrorMessageDto();
		errorMessageDto.setId(counter.incrementAndGet());
		errorMessageDto.setTimestamp(new Date());
		errorMessageDto.setMessage(ex.getLocalizedMessage());
		 // handle here
		LOGGER.error("Exception"+errorMessageDto.toString());
		return errorMessageDto;
	}

}
