package earstone.springcoreadvanced.app.v1;

import earstone.springcoreadvanced.trace.TraceStatus;
import earstone.springcoreadvanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepository;
    private final HelloTraceV1 trace;

    public void orderItem(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");
            orderRepository.save(itemId);
            trace.end(status);

        } catch (Exception ex) {
            trace.exception(status, ex);
            throw ex;
        }
    }
}
