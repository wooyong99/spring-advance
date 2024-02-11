package spring.advance.trace.logtrace;


import org.junit.jupiter.api.Test;
import spring.advance.trace.TraceStatus;

public class FieldTraceTest {
    FieldLogTrace trace = new FieldLogTrace();

    @Test
    public void begin_test() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    public void begin_exception_test() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.exception(status2, new IllegalArgumentException());
        trace.exception(status1, new IllegalArgumentException());
    }
}
