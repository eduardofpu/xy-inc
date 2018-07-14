package com.org.config;

import javax.servlet.http.HttpServletRequest;

import com.org.config.exeptions.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

		@ExceptionHandler(Exception.class)
		@ResponseBody
		public ResponseEntity<ErrorInfo> handleBusinessException(HttpServletRequest req, Throwable ex) {
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setMessage(ex.getMessage());
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(errorInfo);
		}

}
