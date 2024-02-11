package spring.advance.trace.logtrace;

import org.junit.jupiter.api.Test;
import spring.advance.trace.TraceStatus;

public class ThreadLogTraceTest {
    ThreadLogTrace trace = new ThreadLogTrace();

    @Test
    public void begin_end_test() {
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    public void begin_exception_test() {
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.begin("hello2");
        trace.exception(status2, new IllegalArgumentException());
        trace.exception(status1, new IllegalArgumentException());
    }
}
