package spring.advance.trace.hellotrace;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.advance.trace.HelloTraceV1;
import spring.advance.trace.TraceStatus;

public class HelloTraceV1Test {

    @DisplayName("로그 추적기가 정상적으로 수행된다.")
    @Test
    public void success() {
        HelloTraceV1 helloTraceV1 = new HelloTraceV1();
        TraceStatus traceStatus = helloTraceV1.begin("hello");
        helloTraceV1.end(traceStatus);
    }

    @DisplayName("로그 추적기가 IllegalArugumentException을 호출한다.")
    @Test
    public void exception() {
        HelloTraceV1 helloTraceV1 = new HelloTraceV1();
        TraceStatus traceStatus = helloTraceV1.begin("hello");
        helloTraceV1.exception(traceStatus, new IllegalArgumentException("예외 호출"));
    }

    @Test
    public void begin_end() {
        HelloTraceV1 helloTraceV1 = new HelloTraceV1();
        TraceStatus status1 = helloTraceV1.begin("hello1");
        TraceStatus status2 = helloTraceV1.beginSync(status1.getTraceId(), "hello2");
        helloTraceV1.end(status2);
        helloTraceV1.end(status1);
    }

    @Test
    public void begin_exception() {
        HelloTraceV1 helloTraceV1 = new HelloTraceV1();
        TraceStatus status1 = helloTraceV1.begin("hello1");
        TraceStatus status2 = helloTraceV1.beginSync(status1.getTraceId(), "hello2");
        helloTraceV1.exception(status2, new IllegalArgumentException());
        helloTraceV1.exception(status1, new IllegalArgumentException());
    }
}
