package spring.advance.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.advance.trace.HelloTraceV1;
import spring.advance.trace.TraceId;
import spring.advance.trace.TraceStatus;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderServiceV1;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = trace.begin("OrderControllerV1.request", new TraceId());
            orderServiceV1.orderItem(itemId, traceStatus.getTraceId().createNextId());
            trace.end(traceStatus);
            return "ok";
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }
    }
}
