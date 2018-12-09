package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 切面声明【DONE】
 * @author keepal
 * @date 2018年11月17日
 */
@Aspect
@Component
public class PeopleAop {
	
	private Logger log = LoggerFactory.getLogger(PeopleAop.class);
	
	/**
	 * 切点定义
	 */
	@Pointcut(value="execution (* com.example.controller.*.*(..))")
	public void wow(){
	}
	
	/**
	 * AOP 前置advice
	 */
	@Before(value = "wow()")
	public void before(){
		log.info("before");
	}
	
	/**
	 * AOP 后置advice
	 */
	@After(value = "wow()")
	public void after(){
		log.info("after");
	}
	
	/**
	 * AOP return后置advice
	 */
	@AfterReturning(value = "wow()")
	public void afterReturning(){
		log.info("afterReturning");
	}
	
	/**
	 * AOP 异常后置advice
	 * 声明ex时指定的类型会限制目标方法必须抛出指定类型的异常
	 * 此处将ex的类型声明为Throwable，意味着对目标方法抛出的异常不加限制
	 * 
	 * 【注意】如果存在around-advice的话 这个afterThrowing可能无法生效
	 * 因为抛出异常的位置并不是指定的切点，而是在around方法内部的proceedingJoinPoint.proceed()
	 */
	@AfterThrowing(value = "wow()",throwing="ex")
	public void afterThrowing(Throwable ex){
		log.info("AfterThrowing--exMsg:{}",ex.getMessage());
	}
	
	/**
	 * AOP 环绕advice
	 * 
	 * 这个advie极其强大，甚至能决定指定切点的方法是否执行。
	 * 当存在advice的时候 执行顺序是 advice前->before->advice后->after->afterReturning
	 * 并且异常时，afterThrowing不生效。
	 */
	@Around(value = "wow()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint){
        log.info("环绕前通知的目标方法名："+proceedingJoinPoint.getSignature().getName());
        try {
        	log.info("around前");
        	//执行
			Object obj = proceedingJoinPoint.proceed();
			log.info("around后");
			return obj;
		} catch (Throwable e) {
			e.printStackTrace();
		}
        return null;
	}

}
