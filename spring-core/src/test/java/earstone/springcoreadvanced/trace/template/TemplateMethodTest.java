package earstone.springcoreadvanced.trace.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @DisplayName("변하는 것과 변하지 않는 코드가 중복으로 포함된 비지니스 로직을 실행한다.")
    @Test
    void templateMethod0() {
        logic1();
        logic2();
    }

    private void logic1() {
        // 1. 변하하지 않는 것
        // 2. 변하는 것
        long startTime = System.currentTimeMillis(); // 1
        log.info("비지니스 로직1 실행"); // 2
        long endTime = System.currentTimeMillis(); // 1
        long resultTime = endTime - startTime; // 1
        log.info("resultTime=" + resultTime); // 1
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        log.info("비지니스 로직2 실행");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime=" + resultTime);
    }

}
