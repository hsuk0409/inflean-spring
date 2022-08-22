package earstone.springcoreadvanced.app.v3;

import earstone.springcoreadvanced.trace.TraceStatus;
import earstone.springcoreadvanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

    private final OrderRepositoryV3 orderRepository;
    private final LogTrace trace;

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
