package earstone.springcoreadvanced.trace;

public class TraceStatus {

    private TraceId traceId; // 내부 트랜잭션 ID와 level 갖고있음
    private Long startTimeMs; // 로그 시작시간. 종료 시 이 시간 기준으로 시작 ~ 종료까지 수행시간 알 수 있다.
    private String message; // 시작 시 사용되는 메세지. 이후 로그 종료 시에도 메세지 사용해서 출력 가능

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }
}
