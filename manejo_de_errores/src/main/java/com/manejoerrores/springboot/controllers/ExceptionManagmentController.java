package com.manejoerrores.springboot.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.manejoerrores.springboot.exception.ProjectException;

@ControllerAdvice
@Controller
public class ExceptionManagmentController implements ErrorController {
	
	@Autowired
    private ErrorAttributes errorAttributes;

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<Object> handleControllerException(HttpServletRequest req, Throwable ex) {
		Map<String, String> responseBody = new HashMap<>();
		responseBody.put("path", req.getContextPath());
		responseBody.put("message", ex.getMessage());

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

		if (ex instanceof ProjectException) {
			status = ((ProjectException) ex).getStatus();
		}
		return new ResponseEntity<Object>(responseBody, status);
	}

    @RequestMapping("/error")
    public ResponseEntity<Object> handleError(HttpServletRequest request, WebRequest webRequest, Model model) {
 
 
        Map<String, Object> mapErrors = errorAttributes.getErrorAttributes(webRequest, true);
        HttpStatus status = HttpStatus.valueOf((int) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        
        return new ResponseEntity<Object>(mapErrors, status);
    }
    
	@Override
	public String getErrorPath() {
		return "/error";
	}
}
