package spring.advance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.advance.trace.logtrace.LogTrace;
import spring.advance.trace.logtrace.ThreadLogTrace;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace() {
//        return new FieldLogTrace();
        return new ThreadLogTrace();
    }
}
