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

    public void orderItem(String itemId, TraceId traceId) {
        TraceStatus traceStatus = null;
        try {
            traceStatus = trace.begin("OrderService.orderItem", traceId);
            orderRepositoryV1.save(itemId, traceStatus.getTraceId().createNextId());
            trace.end(traceStatus);
        } catch (Exception e) {
            trace.exception(traceStatus, e);
            throw e;
        }
    }
}
