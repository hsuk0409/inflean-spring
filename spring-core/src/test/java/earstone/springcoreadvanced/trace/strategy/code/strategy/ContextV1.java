package earstone.springcoreadvanced.trace.strategy.code.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ContextV1 {

    /**
     * 핵심은 Strategy 인터페이스에 의존하는 것이다.
     * "선 조립 후 실행"
     * 스프링의 원리 --> 애플리케이션 실행 시점에 빈들을 미리 생성해놓는 것
     */
    private final Strategy strategy;

    public void execute() {
        long startTime = System.currentTimeMillis();
        strategy.call(); // 위임
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

}
