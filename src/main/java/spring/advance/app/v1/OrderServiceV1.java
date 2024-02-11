package spring.advance.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.advance.trace.TraceId;
import spring.advance.trace.TraceStatus;
import spring.advance.trace.hellotrace.HelloTraceV1;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 orderRepositoryV1;
    private final HelloTraceV1 trace;

    public void orderItem(String itemId, TraceId traceId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem", traceId);
            orderRepositoryV1.save(itemId, status.getTraceId().createNextId());
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
