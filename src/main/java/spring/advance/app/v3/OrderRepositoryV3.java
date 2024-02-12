package spring.advance.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.advance.trace.logtrace.LogTrace;
import spring.advance.trace.template.AbstractTemplate;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {
    private final LogTrace trace;

    public void save(String itemId) {
        AbstractTemplate template = new AbstractTemplate(trace) {
            @Override
            protected Void call() {
                if (itemId.equals("ex")) {
                    throw new IllegalArgumentException("예외 발생 !");
                }
                sleep(1000);
                return null;
            }
        };
        template.execute("OrderRepository.save");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
