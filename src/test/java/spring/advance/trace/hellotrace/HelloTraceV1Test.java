package spring.advance.trace.hellotrace;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.advance.trace.TraceId;
import spring.advance.trace.TraceStatus;

public class HelloTraceV1Test {

    @DisplayName("로그 추적기가 정상적으로 수행된다.")
    @Test
    public void success() {
        HelloTraceV1 helloTraceV1 = new HelloTraceV1();
        TraceStatus traceStatus = helloTraceV1.begin("hello", new TraceId());
        helloTraceV1.end(traceStatus);
    }

    @DisplayName("로그 추적기가 IllegalArugumentException을 호출한다.")
    @Test
    public void exception() {
        HelloTraceV1 helloTraceV1 = new HelloTraceV1();
        TraceStatus traceStatus = helloTraceV1.begin("hello", new TraceId());
        helloTraceV1.exception(traceStatus, new IllegalArgumentException("예외 호출"));
    }
}
