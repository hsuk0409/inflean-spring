package earstone.springcoreadvanced.trace.strategy;

import earstone.springcoreadvanced.trace.strategy.code.strategy.ContextV2;
import earstone.springcoreadvanced.trace.strategy.code.strategy.StrategyLogic1;
import earstone.springcoreadvanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    /**
     * 선 조립 후 실행이 아니라 컨텍스트를 실행 할 때마다 전략을 인수로 전달한다.
     */
    @DisplayName("전략패턴을 파라미터 주입방식으로 로직 실행한다.")
    @Test
    void strategyV1() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

}
