package earstone.springcoreadvanced.trace.logtrace;

import earstone.springcoreadvanced.trace.TraceStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FieldLogTraceTest {

    FieldLogTrace trace = new FieldLogTrace();

    @DisplayName("traceId 동기화 사용해서 로그 추적기 개발")
    @Test
    void beginEndLevel2Test() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");

        trace.end(status2);
        trace.end(status1);
    }

    @DisplayName("traceId 동기화 사용해서 로그 추적기 개발, 예외 발생 기능 확인")
    @Test
    void beginExceptionLevel2Test() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");

        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }

}