package spring.advance.proxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyClient {
    private Subject subject;

    public ProxyClient(Subject subject) {
        this.subject = subject;
    }

    public void execute() {
        log.info(subject.operation());
    }
}
