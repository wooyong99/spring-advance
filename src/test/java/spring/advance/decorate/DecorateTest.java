package spring.advance.decorate;

import org.junit.jupiter.api.Test;
import spring.advance.decorate.code.DecorateClient;
import spring.advance.decorate.code.DecorateProxy;
import spring.advance.decorate.code.RealSubject;
import spring.advance.decorate.code.Subject;

public class DecorateTest {

    @Test
    public void noDecorate() {
        Subject realSubject = new RealSubject();
        DecorateClient client = new DecorateClient(realSubject);
        client.execute("noDecorate");
    }

    @Test
    public void applyDecorate() {
        Subject realSubject = new RealSubject();
        Subject proxy = new DecorateProxy(realSubject);
        DecorateClient client = new DecorateClient(proxy);
        client.execute("noDecorate");
    }
}
