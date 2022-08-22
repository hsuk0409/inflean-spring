package earstone.springcoreadvanced.trace.logtrace;

import earstone.springcoreadvanced.trace.TraceId;
import earstone.springcoreadvanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class FieldLogTrace implements LogTrace{

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private TraceId traceIdHolder; // traceId 동기화, 동시성 이슈 발생

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = traceIdHolder;
        long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        // 로그 출력
        return new TraceStatus(traceId, startTimeMs, message);
    }

    private void syncTraceId() {
        if (traceIdHolder == null) { // 최초 호출일 시 traceId 새로 생성
            traceIdHolder = new TraceId();
        } else { // 직전 로그 있을 경우 레벨 증가시켜 동기화
            traceIdHolder = traceIdHolder.createNextId();
        }
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();

        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms",
                    traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(),
                    resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}",
                    traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(),
                    resultTimeMs, e.toString());
        }

        releaseTraceId();
    }

    private void releaseTraceId() {
        if (traceIdHolder.isFirstLevel()) { // 첫번째 레벨이면 초기화
            traceIdHolder = null;
        } else {
            traceIdHolder = traceIdHolder.createPreviousId(); // 끝날 때 위에서 아래로 가꾸로 내려온다.
        }
    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; ++i) {
            sb.append((i == level - 1) ? "|" + prefix : "|    ");
        }
        return sb.toString();
    }
}
