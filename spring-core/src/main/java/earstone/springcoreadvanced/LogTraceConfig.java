package earstone.springcoreadvanced;

import earstone.springcoreadvanced.trace.logtrace.LogTrace;
import earstone.springcoreadvanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

}
