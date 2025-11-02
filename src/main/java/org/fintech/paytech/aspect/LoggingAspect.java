package org.fintech.paytech.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.fintech.paytech.domain.core.logger.service.LoggerService;
import org.fintech.paytech.util.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String TRACE_ID = "traceId";
    private static final String USER_ID = "userId";

    private final ObjectMapper objectMapper;
    private final LoggerService loggerService;

    public LoggingAspect(ObjectMapper objectMapper, LoggerService loggerService) {
        this.objectMapper = objectMapper;
        this.loggerService = loggerService;
    }

    @Around("(@annotation(org.springframework.web.bind.annotation.GetMapping)  || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping)  || " +
            "@annotation(org.springframework.web.bind.annotation.PatchMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping))  ")
    public Object logHttpTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        org.fintech.paytech.domain.core.logger.model.Logger logEntry = new org.fintech.paytech.domain.core.logger.model.Logger();
        String traceId = MDC.get(TRACE_ID);
        String userId = MDC.get(USER_ID);
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        // Before Execution
        HttpServletRequest request =
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpServletResponse httpResponse =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        String requestBody = getRequestBody(joinPoint);

        logEntry.setTraceId(traceId);
        logEntry.setUserId(userId);
        logEntry.setClassName(className);
        logEntry.setMethodName(methodName);
        logEntry.setMethod(request.getMethod());
        logEntry.setEndpoint(request.getRequestURI());
        logEntry.setRequestHeader(LoggerUtil.trimToLength(getAllRequestHeaders(request), 9900));
        logEntry.setRequestBody(LoggerUtil.trimToLength(requestBody, 9900));

        logger.info("TraceId: {}, UserId: {}, Class: {}, Method: {} - Request: Method: {}, URI: {}, Body: {}",
                traceId,
                userId,
                className,
                methodName,
                request.getMethod(),
                request.getRequestURI(),
                requestBody
        );

        // After Execution: joinPoint.proceed()
        Object response = null;
        long startTime = System.currentTimeMillis();
        long endTime;
        long duration;

        try {
            response = joinPoint.proceed();
            endTime = System.currentTimeMillis();
        } catch (Throwable e) {
            logger.error("Exception: Method: {}, URI: {} failed with exception message: {}",
                    request.getMethod(),
                    request.getRequestURI(),
                    e.getMessage()
            );
            int status = httpResponse != null ? httpResponse.getStatus() : 0;
            String responseBody = convertObjectToJson(response);
            endTime = System.currentTimeMillis();
            duration = endTime - startTime;

            logEntry.setResult("error");
            logEntry.setStatus(status);

            responseBody = String.join(e.getMessage(), " - ", responseBody);
            logEntry.setResponseBody(LoggerUtil.trimToLength(responseBody, 9900));

            logEntry.setDuration(duration);

            loggerService.createLogger(logEntry);
            throw e;
        }

        duration = endTime - startTime;
        String responseBody = convertObjectToJson(response);
        int status = httpResponse != null ? httpResponse.getStatus() : 0;
        logger.info("Response: Method: {}, URI: {}, Status {} - Body: {} Time Taken: {} ms",
                request.getMethod(),
                request.getRequestURI(),
                status,
                responseBody,
                duration
        );

        logEntry.setResult("success");
        logEntry.setStatus(status);
        logEntry.setResponseBody(responseBody);
        logEntry.setDuration(duration);

        loggerService.createLogger(logEntry);

        return response;
    }

    private String getRequestBody(ProceedingJoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        if (args.length > 0) {
            try {
                return Arrays.stream(args)
                        .map(this::convertObjectToJson)
                        .reduce((arg1, arg2) -> arg1 + ", " + arg2)
                        .orElse("");
            } catch (Exception e) {
                logger.error("Error serializing request body", e);
            }
        }
        return "";
    }

    private String getAllRequestHeaders(HttpServletRequest request) {

        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }

        try {
            return objectMapper.writeValueAsString(headers);
        } catch (JsonProcessingException e) {
            logger.error("Header'lar JSON'a çevrilemedi", e);
            return "";
        }
    }

    private String convertObjectToJson(Object object) {

        if (object == null)
            return "";

        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("Error serializing object to JSON", e);
            return "Error serializing object to JSON";
        }
    }
}
