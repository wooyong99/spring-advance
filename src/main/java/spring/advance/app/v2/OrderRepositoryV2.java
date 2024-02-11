package spring.advance.app.v2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.advance.trace.TraceId;
import spring.advance.trace.TraceStatus;
import spring.advance.trace.logtrace.LogTrace;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {
    private final LogTrace trace;

    public void save(String itemId, TraceId traceId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save");
            if (itemId.equals("ex")) {
                throw new IllegalArgumentException("예외 발생 !");
            }
            sleep(1000);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
