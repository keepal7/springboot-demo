package com.example.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常捕获【DONE】
 * @author keepal
 * @date 2018年11月15日
 */
@ControllerAdvice
public class WholeExceptionHandler {

	/**
	 * @ExceptionHandler捕获Exception后，根据声明的thymeleaf可以直接跳转到
	 * 指定的页面文件从而不会显示异常页面给用户
	 * thymeleaf的配置是固定的，需要额外导入对应的依赖，详情见application.properties
	 * 
	 * 这里需要细化处理，应该对异常进行分类处理。这里作为demo就不细化啦。
	 */
	@ExceptionHandler(value = Exception.class)
	public Object errorHandler(HttpServletRequest req, HttpServletResponse res, Exception e) {
//		ModelAndView m = new ModelAndView();
//		m.setViewName("NewFile");
		return "exceptionPage";
	}

}
