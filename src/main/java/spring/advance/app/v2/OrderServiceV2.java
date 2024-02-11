package spring.advance.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.advance.trace.TraceId;
import spring.advance.trace.TraceStatus;
import spring.advance.trace.logtrace.LogTrace;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepositoryV2;
    private final LogTrace trace;

    public void orderItem(String itemId, TraceId traceId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem");
            orderRepositoryV2.save(itemId, status.getTraceId().createNextId());
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
