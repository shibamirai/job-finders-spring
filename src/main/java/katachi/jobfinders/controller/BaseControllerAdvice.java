package katachi.jobfinders.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import katachi.jobfinders.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class BaseControllerAdvice {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.initDirectFieldAccess();

		// Formの未入力項目を空文字でなくnullにする場合は、以下のコメントを外す
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@ExceptionHandler(NotFoundException.class)
	public String notFoundExceptionHandler(NotFoundException e, Model model) {

		model.addAttribute("error", e.getClass().getSimpleName());
		model.addAttribute("message", e.getCause());
		model.addAttribute("status", HttpStatus.NOT_FOUND);

		return "error/404";
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		log.error(e.getLocalizedMessage());
		e.printStackTrace();

		model.addAttribute("error", e.getClass().getSimpleName());
		model.addAttribute("message", e.getCause());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}
}
