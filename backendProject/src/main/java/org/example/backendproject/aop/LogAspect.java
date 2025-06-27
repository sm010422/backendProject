package org.example.backendproject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class LogAspect {


  //Pointcut
  //AOP를 적용할 클래스
  @Pointcut("execution(* org.example.backendproject.board.service.BoardService..*(..)) " + 
            "execution(* org.example.backendproject.board.controller..*(..)) ")
  public void method(){

  }
  
  @Around("method()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    long start  = System.currentTimeMillis();

    String methodName = joinPoint.getSignature().getName();

    try{
        log.info("[AOP_LOG] {} 메소드 호출 시작", methodName);


        Object result = joinPoint.proceed();
        return result;

    }
    catch(Exception e){
        log.info("[AOP_LOG] {} 메소드 예외 {}", methodName, e.getMessage());
        return e;
    }
    finally{
      long end = System.currentTimeMillis();
        log.info("[AOP_LOG] {} 메서드 실행 완료 시간 = {}", methodName, end - start);
    }
  }
}
