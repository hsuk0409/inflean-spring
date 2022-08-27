package earstone.springcoreadvanced.trace.template;

import earstone.springcoreadvanced.trace.template.code.SubClassLogic1;
import earstone.springcoreadvanced.trace.template.code.SubClassLogic2;
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
        long startTime = System.currentTimeMillis();
        log.info("비지니스 로직1 실행");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime=" + resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        log.info("비지니스 로직2 실행");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime=" + resultTime);
    }

    @DisplayName("템플릿 메소드를 적용하여 비지니스 로직을 실행한다.")
    @Test
    void templateMethodV1() {
        SubClassLogic1 template1 = new SubClassLogic1();
        template1.execute();

        SubClassLogic2 template2 = new SubClassLogic2();
        template2.execute();
    }

}
