package edu.miu.springdataday3.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class CalculateTimeAspect {

    private final HttpServletRequest httpServletRequest;
    private final ActivityLogRepo activityLogRepo;
    @Pointcut("@annotation(edu.miu.springdataday3.aspect.annotation.ExecutionTime)")
    public void executionActivityLogPointcut(){}


    @Around("executionActivityLogPointcut()")
    public Object durationCalculate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Long startTime = System.nanoTime();
        Object forward = proceedingJoinPoint.proceed(); // this proceeds the function
        Long endTime = System.nanoTime();

        ActivityLog activityLog = new ActivityLog();
        activityLog.setDate(LocalDateTime.now());
        activityLog.setOperation(proceedingJoinPoint.getSignature().toShortString());
        activityLog.setDuration(endTime-startTime);

         activityLogRepo.save(activityLog);
        return forward;
    }


    @Pointcut("execution( * edu.miu.springdataday3.service.*.*(..))")
    public void serviceExecutionPointcut(){}

    @Before("serviceExecutionPointcut()")
    public void serviceForPostExecution() throws AopIsAwesomeHeaderException{
        if ( httpServletRequest.getMethod().equals("POST")){
            if (httpServletRequest.getHeader("AOP-IS-AWESOME") == null){
                //throw new AopIsAwesomeHeaderException("AOP-IS-AWESOME is required");
//                System.out.println("AOP-IS-AWESOME is required");
            }
        }
    }


}
