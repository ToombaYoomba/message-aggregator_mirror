package com.aggregator.shared.adapters.logger;

import java.util.Arrays;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingMethodInterceptor implements MethodInterceptor {
    private static final Logger log = LoggerFactory.getLogger(LoggingMethodInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        String className = invocation.getMethod().getDeclaringClass().getSimpleName();
        Object[] args = invocation.getArguments();

        String[] maskedArgs = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            String lowerMethodName = methodName.toLowerCase();
            if (args[i] instanceof String
                    && (lowerMethodName.contains("password")
                            || lowerMethodName.contains("login")
                            || lowerMethodName.contains("register")
                            || lowerMethodName.contains("auth"))) {
                maskedArgs[i] = "***HIDDEN***";
            } else {
                maskedArgs[i] = String.valueOf(args[i]);
            }
        }

        log.info(
                "START: {}.{}() with args: {}", className, methodName, Arrays.toString(maskedArgs));
        long startTime = System.currentTimeMillis();

        try {
            Object result = invocation.proceed();

            long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("SUCCESS: {}.{}() with args: {}", className, methodName, elapsedTime);

            return result;
        } catch (Exception e) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            log.error(
                    "ERROR: {}.{}() with args {}. REASON: {}",
                    className,
                    methodName,
                    elapsedTime,
                    e.getMessage());
            throw e;
        }
    }
}
