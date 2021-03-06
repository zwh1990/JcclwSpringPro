package com.zwh.jcclwspring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author by zwh
 * @description:
 * @date Created in 2021/8/25 15:11
 */
@Aspect
@Component
public class HttpAspect {

    private static final Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.zwh.jcclwspring.controller.LuckymoneyController.*(..))")
    public void log(){
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        /**
         * url
         */
        logger.info("url={}",request.getRequestURL());
        /**
         * 方法
         */
        logger.info("method={}",request.getMethod());
        /**
         * ip
         */
        logger.info("ip={}",request.getRemoteAddr());
        /**
         * 类方法
         */
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"." +
                joinPoint.getSignature().getName());
        /**
         * 参数
         */
        logger.info("args={}",joinPoint.getArgs());

    }

    @After("log()")
    public void doAfter(){
        logger.info("22222222222");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturning(Object object){
        logger.info("response={}",object);
    }
}
