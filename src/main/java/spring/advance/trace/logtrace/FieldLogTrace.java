package spring.advance.trace.logtrace;

import lombok.extern.slf4j.Slf4j;
import spring.advance.trace.TraceId;
import spring.advance.trace.TraceStatus;

@Slf4j
public class FieldLogTrace implements LogTrace {
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private TraceId traceIdHolder = new TraceId();

    @Override
    public TraceStatus begin(String message) {
        traceIdHolder = syncTraceId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceIdHolder.getId(), addSpace(START_PREFIX,
                traceIdHolder.getLevel()), message);
        return new TraceStatus(traceIdHolder, startTimeMs, message);
    }

    private TraceId syncTraceId() {
        if (traceIdHolder == null) {
            return new TraceId();
        }
        return traceIdHolder.createNextId();
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
        traceIdHolder = releaseTraceId();
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
        traceIdHolder = releaseTraceId();
    }

    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(),
                    addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(),
                    resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(),
                    addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs,
                    e.toString());
        }
    }

    private TraceId releaseTraceId() {
        if (traceIdHolder.isFirstLevel()) {
            return null;
        }
        return traceIdHolder.createPreviousId();
    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }
}
