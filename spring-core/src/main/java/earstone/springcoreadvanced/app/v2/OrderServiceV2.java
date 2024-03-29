package earstone.springcoreadvanced.app.v2;

import earstone.springcoreadvanced.trace.TraceId;
import earstone.springcoreadvanced.trace.TraceStatus;
import earstone.springcoreadvanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId, String itemId) {
        TraceStatus status = null;
        try {
            status = trace.beginSync(traceId, "OrderService.orderItem()");
            orderRepository.save(status.getTraceId(), itemId);
            trace.end(status);

        } catch (Exception ex) {
            trace.exception(status, ex);
            throw ex;
        }
    }
}
