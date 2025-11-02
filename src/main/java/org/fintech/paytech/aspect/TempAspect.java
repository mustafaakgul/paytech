package org.fintech.paytech.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TempAspect {

    // private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /*private void logMessage(org.slf4j.event.Level level, String message, Object... args) {
        switch (level) {
            case INFO -> log.info(message, args);
            case WARN -> log.warn(message, args);
            case ERROR -> log.error(message, args);
            case DEBUG -> log.debug(message, args);
            case TRACE -> log.trace(message, args);
            default -> log.info(message, args);
        }
    }*/

    // Proxied Object, Target Object and Weaving

    // Before Advice
    // @Before("userServiceMethods()")
    /* @Before("execution(* org.fintech.budget.domains.transaction.controllers.TransactionController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {} ,Class Name:{}", joinPoint.getSignature().getName(), joinPoint.getArgs(), joinPoint.getSignature().getDeclaringTypeName());
    }*/

    // After Advice
    /* @AfterReturning(pointcut = "execution(* org.fintech.budget.domains.transaction.controllers.TransactionController.*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: {} with result: {} ,Class Name:{}", joinPoint.getSignature().getName(), result, joinPoint.getSignature().getDeclaringTypeName());
    }*/

    /* @AfterReturning(pointcut = "@annotation(Logging)", returning = "result")
    public void logSuccess(JoinPoint joinPoint, Object result) throws JsonProcessingException
    {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        result = result == null ? "No response" : result;
        String response = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(result);
        logger.debug("Operation " + "' " + " ' completed with response ");
        logger.debug(response);
    }*/

    // After Throwing Advice
    /* @AfterThrowing(pointcut = "@annotation(Logging)", throwing = "ex")
    private void logError(JoinPoint joinPoint, Exception ex)
    {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        logger.error("Operation " + "' " + " ' Failed with exception");
        logger.error("Exception " + ex);
    }*/

    // Transaction Management
    /*@Around("execution(* org.fintech.budget.domains.transaction.services.*.*(..))")
    public Object manageTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            beginTransaction();
            Object result = joinPoint.proceed();  // Proceed with the method call
            commitTransaction();
            return result;
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        }
    }*/

    // Security
    /*@Before("execution(* org.fintech.budget.security.*.*(..))")
    public void checkSecurity(JoinPoint joinPoint) {
        if (!isUserAuthorized()) {
            throw new SecurityException("Unauthorized access");
        }
    }*/

    // Pointcut to Match All Methods in UserService
    /*@Pointcut("execution(* com.example.service.UserService.*(..))")
    public void userServiceMethods() {}*/

    // Pointcut to match all methods in the Service layer
    // Create a MethodExecutionTimeAspect that will log the execution time of specific methods.
    /*@Around("execution(* com.example.service.*.*(..))")
    public Object measureExecutionTime3(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis(); // Start time
        Object result = joinPoint.proceed(); // Proceed with the method execution
        long endTime = System.currentTimeMillis(); // End time

        System.out.println("Execution time for " + joinPoint.getSignature() + ": " + (endTime - startTime) + " ms");

        final String methodName = joinPoint.getSignature().getName();
        //final String className = bean.getClass().getSimpleName();
        return result; // Return the result of the method
    }*/

    // Join point: any method in the service layer that throws an exception
    // captures exceptions thrown by any method in the service layer.
    /*@AfterThrowing(pointcut = "execution(* com.example.service.*.*(..))", throwing = "ex")
    public void logException(Throwable ex) {
        System.out.println("Exception thrown: " + ex.getMessage());
        // Here, you can also log to a logging framework or a file.
    }*/

    // This advice Commonly used for logging, authentication checks, or setting up preconditions.
    /*@Before("execution(* com.example.service.UserService.*(..))")
    public void logBeforeMethod() {
        System.out.println("Method execution started...");
    }*/

    //Pointcuts: Define where the aspect should be applied, specifying join points (e.g., method calls).
    //Advice: The action taken at the join points defined by the pointcuts (e.g., logging, security checks).


    // Use Case: This advice Useful for cleanup actions, logging, or releasing resources.
    /*@After("execution(* com.example.service.UserService.*(..))")
    public void logAfterMethod() {
        System.out.println("Method execution finished.");
    }*/

    // This advice runs after the join point, only if the method executed successfully and did not throw an exception.
    // Use Case: Often used for logging the result of a method execution or for post-processing the returned data.
    /*@AfterReturning(pointcut = "execution(* com.example.service.UserService.*(..))", returning = "result")
    public void logAfterReturning(Object result) {
        System.out.println("Method returned: " + result);
    }*/

    // This advice runs if the join point throws an exception.
    // Use Case: Useful for logging errors, performing cleanup, or alerting mechanisms.
    /*@AfterThrowing(pointcut = "execution(* com.example.service.UserService.*(..))", throwing = "ex")
    public void logAfterThrowing(Throwable ex) {
        System.out.println("Exception thrown: " + ex.getMessage());
    }*/

    // This advice wraps the join point and has the ability to control whether the join point is executed, as well as executing code before and after it.
    // Use Case: Used for performance measurement, logging, and transactional behavior.
    /*@Around("execution(* com.example.service.UserService.*(..))")
    public Object measureExecutionTime2(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed(); // Execute the method
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
        return result;
    }*/
}
