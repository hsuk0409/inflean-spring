package earstone.springcoreadvanced.app.V4;

import earstone.springcoreadvanced.trace.logtrace.LogTrace;
import earstone.springcoreadvanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

    private final OrderServiceV4 orderService;
    private final LogTrace trace;

    @GetMapping("/v4/request")
    public String request(String itemId) {

        // V3에 비해 딱 비지니스 로직만 남았다.
        AbstractTemplate<String> template = new AbstractTemplate<>(trace) {
            // 익명 내부 클래스
            @Override
            protected String call() {
                orderService.orderItem(itemId);
                return "ok";
            }
        };

        return template.execute("OrderController.request()");
    }
}
