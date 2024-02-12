package spring.advance.trace.templatemethod;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import spring.advance.trace.templatemethod.code.AbstractTemplate;
import spring.advance.trace.templatemethod.code.SubClassLogic1;
import spring.advance.trace.templatemethod.code.SubClassLogic2;

@Slf4j
public class TemplateMethodTest {
    @Test
    public void templatemethodV0() {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();
        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    @Test
    public void templatemethodV1() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직 실행 1");
            }
        };
        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직 실행 2");
            }
        };
        template2.execute();
    }
}
