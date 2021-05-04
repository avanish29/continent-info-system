package net.avanishkpandey.universum.continentservice.web.error;

import java.time.LocalDateTime;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.avanishkpandey.universum.continentservice.domain.exception.NotFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler({ EntityNotFoundException.class })
	public ResponseEntity<Object> handleEntityNotFoundException(HttpServletRequest request,
			EntityNotFoundException ex) {
		log.error("handleEntityNotFoundException {} \n", request.getRequestURI(), ex);
		APIError apiError = new APIError(HttpStatus.NOT_FOUND, ex);
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(HttpServletRequest request, NotFoundException ex) {
		log.error("handleNotFoundException {} \n", request.getRequestURI(), ex);
		APIError apiError = new APIError(HttpStatus.NOT_FOUND, ex);
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleInternalServerError(HttpServletRequest request, Exception ex) {
		log.error("handleInternalServerError {}\n", request.getRequestURI(), ex);
		APIError apiError = new APIError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(final APIError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@Data
	private static class APIError {
		private HttpStatus status;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss")
		private LocalDateTime timestamp;
		private String message;
		private String debugMessage;

		public APIError() {
			timestamp = LocalDateTime.now();
		}

		public APIError(HttpStatus status, Throwable ex) {
			this();
			this.status = status;
			this.message = ex.getMessage();
			this.debugMessage = ex.getLocalizedMessage();
		}

		public Integer getStatusCode() {
			return status != null ? status.value() : null;
		}
	}
}
