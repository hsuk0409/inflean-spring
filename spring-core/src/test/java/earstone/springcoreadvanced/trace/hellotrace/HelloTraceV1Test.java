package earstone.springcoreadvanced.trace.hellotrace;

import earstone.springcoreadvanced.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HelloTraceV1Test {

    @DisplayName("로그 추적기 시작 후 성공적으로 끝났을 때 테스트")
    @Test
    void begin_end() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    @DisplayName("로그 추적기 시작 후 익셉션 났을 때 테스트")
    @Test
    void begin_exception() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalArgumentException());
    }
}