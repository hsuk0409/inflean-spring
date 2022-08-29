package earstone.springcoreadvanced.trace.strategy;

import earstone.springcoreadvanced.trace.strategy.code.strategy.ContextV1;
import earstone.springcoreadvanced.trace.strategy.code.strategy.Strategy;
import earstone.springcoreadvanced.trace.strategy.code.strategy.StrategyLogic1;
import earstone.springcoreadvanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @DisplayName("변하는 것과 변하지 않는 코드가 중복으로 포함된 비지니스 로직을 실행한다.")
    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        log.info("비지니스 로직1 실행");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        log.info("비지니스 로직2 실행");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    @DisplayName("전략패턴을 사용한 비지니스 로직 로그 실행한다.")
    @Test
    void strategyV1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
    }

    @DisplayName("익명 내부클래스를 사용해 전략퍄턴 로직을 실행한다.")
    @Test
    void strategyV2() {
        Strategy strategyLogic1 = () -> log.info("비지니스 로직1 실행");
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        Strategy strategyLogic2 = () -> log.info("비지니스 로직2 실행");
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
    }

    @DisplayName("익명 내부클래스를 사용해 전략퍄턴 로직을 실행한다. (리팩토링)")
    @Test
    void strategyV3() {
        ContextV1 contextV1 = new ContextV1(() -> log.info("비지니스 로직1 실행"));
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(() -> log.info("비지니스 로직2 실행"));
        contextV2.execute();
    }

}
