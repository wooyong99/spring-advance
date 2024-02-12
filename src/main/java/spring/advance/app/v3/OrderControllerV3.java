package spring.advance.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.advance.trace.logtrace.LogTrace;
import spring.advance.trace.template.AbstractTemplate;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {
    private final OrderServiceV3 orderServiceV3;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String request(String itemId) {
        AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {
            @Override
            protected String call() {
                orderServiceV3.orderItem(itemId);
                return "ok";
            }
        };
        return template.execute("OrderController.request");
    }
}
