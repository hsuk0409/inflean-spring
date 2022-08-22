package earstone.springcoreadvanced.app.v3;

import earstone.springcoreadvanced.trace.TraceStatus;
import earstone.springcoreadvanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

    private final LogTrace trace;

    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");
            if (itemId.equals("ex")) {
                throw new IllegalArgumentException("예외 발생!");
            }
            sleep(1000);

            trace.end(status);
        } catch (Exception ex) {
            trace.exception(status, ex);
            throw ex;
        }
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
