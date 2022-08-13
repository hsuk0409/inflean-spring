package earstone.springcoreadvanced;

import earstone.springcoreadvanced.trace.logtrace.FieldLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public FieldLogTrace logTrace() {
        return new FieldLogTrace();
    }

}
