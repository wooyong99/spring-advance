package spring.advance.app.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.advance.trace.TraceId;
import spring.advance.trace.TraceStatus;
import spring.advance.trace.hellotrace.HelloTraceV1;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {
    private final HelloTraceV1 trace;

    public void save(String itemId, TraceId beforeTraceId) {
        TraceStatus status = null;
        try {
            status = trace.beginSync(beforeTraceId, "OrderRepository.save");
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
