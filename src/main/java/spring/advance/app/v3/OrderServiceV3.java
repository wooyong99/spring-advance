package spring.advance.app.v3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.advance.trace.logtrace.LogTrace;
import spring.advance.trace.template.AbstractTemplate;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepositoryV3;
    private final LogTrace trace;

    public void orderItem(String itemId) {

        AbstractTemplate template = new AbstractTemplate(trace) {
            @Override
            protected Void call() {
                orderRepositoryV3.save(itemId);
                return null;
            }
        };
        template.execute("OrderService.orderItem");
    }
}
