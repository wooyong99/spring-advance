package spring.advance.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.advance.trace.HelloTraceV1;
import spring.advance.trace.TraceId;
import spring.advance.trace.TraceStatus;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 orderRepositoryV1;
    private final HelloTraceV1 trace;

    public void orderItem(String itemId, TraceId beforeId) {
        TraceStatus status = null;
        try {
            status = trace.beginSync(beforeId, "OrderService.orderItem");
            orderRepositoryV1.save(itemId, status.getTraceId());
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
