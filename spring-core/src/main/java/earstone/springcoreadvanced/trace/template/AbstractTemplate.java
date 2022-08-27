package earstone.springcoreadvanced.trace.template;

import earstone.springcoreadvanced.trace.TraceStatus;
import earstone.springcoreadvanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 변하지 않는 코드를 템플릿 메서드 패턴을 사용해 추상화
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public T execute(String message) {
        TraceStatus status = null;

        try {
            status = trace.begin(message);

            // 로직 호출
            T result = call();

            trace.end(status);
            return result;
        } catch (Exception ex) {
            trace.exception(status, ex);
            throw ex;
        }

    }

    protected abstract T call();

}
