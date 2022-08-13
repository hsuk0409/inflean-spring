package earstone.springcoreadvanced.trace.hellotrace;

import earstone.springcoreadvanced.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @DisplayName("로그 추적기 시작 후 성공적으로 끝났을 때 테스트")
    @Test
    void begin_end() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");

        trace.end(status2);
        trace.end(status1);
    }

    @DisplayName("로그 추적기 시작 후 익셉션 났을 때 테스트")
    @Test
    void begin_exception() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");

        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}