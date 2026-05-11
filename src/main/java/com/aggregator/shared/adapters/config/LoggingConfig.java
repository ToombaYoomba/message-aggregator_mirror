package com.aggregator.shared.adapters.config;

import com.aggregator.shared.adapters.logger.LoggingMethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {
    @Bean
    public LoggingMethodInterceptor loggingMethodInterceptor() {
        return new LoggingMethodInterceptor();
    }

    @Bean
    public Advisor loggingAdvisor(LoggingMethodInterceptor interceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(
                "execution(* com.aggregator..usecases..*(..)) || execution(* com.aggregator..adapters..*(..))");
        return new DefaultPointcutAdvisor(pointcut, interceptor);
    }
}
