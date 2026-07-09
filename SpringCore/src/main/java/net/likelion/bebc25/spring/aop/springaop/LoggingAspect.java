package net.likelion.bebc25.spring.aop.springaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect // 횡단 관심사
public class LoggingAspect {

    @Pointcut("execution(* net.likelion.bebc25.spring.aop.springaop.*Driver.*(..))")
    private void springaopPackageMethod(){ }

    @Before("springaopPackageMethod()")
    public void logBefore(JoinPoint joinPoint){ // 메서드 수행 전에 로그 메세지를 출력
        System.out.println("[AOP 로그 before] 메세드 실행 전에 처리할 코드를 작성합니다.");
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
    }

    @After("springaopPackageMethod()")
    public void logAfter(){ // 메서드 수행 후에 로그 메세지를 출력
        System.out.println("[AOP 로그 after] 메세드 실행 후에 처리할 코드를 작성합니다.");
    }

    @Around("springaopPackageMethod()")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable { //메서드 수행 전/후에 로그 메세지 출력
        System.out.println("[AOP 로그 around] 메세드 실행 전에 처리할 코드를 작성합니다.");
        joinPoint.proceed(); // 대상 메소드를 호출한다
        System.out.println("[AOP 로그 around] 메세드 실행 후에 처리할 코드를 작성합니다.");
    }
}
