package spring.advance.decorate.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements Subject {
    String data;

    @Override
    public void execute(String data) {
        this.data = data;
        log.info(data);
    }
}
