package spring.advance.trace.template;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spring.advance.trace.TraceStatus;
import spring.advance.trace.logtrace.LogTrace;

@RequiredArgsConstructor
@Slf4j
public abstract class AbstractTemplate<T> {
    private final LogTrace trace;

    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);
            T result = call();
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, new IllegalArgumentException());
            throw e;
        }
    }

    protected abstract T call();
}
