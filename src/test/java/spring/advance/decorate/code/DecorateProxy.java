package spring.advance.decorate.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecorateProxy implements Subject {
    private Subject subject;

    public DecorateProxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void execute(String data) {
        log.info("Decorate Proxy 실행");
        subject.execute("**********" + data + "**********");
    }
}
