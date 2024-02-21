package spring.advance.proxy;

import org.junit.jupiter.api.Test;
import spring.advance.proxy.code.ProxyClient;
import spring.advance.proxy.code.ProxyPattern;
import spring.advance.proxy.code.RealSubject;
import spring.advance.proxy.code.Subject;

public class ProxyTest {
    @Test
    public void noProxy() {
        Subject subject = new RealSubject();
        ProxyClient client = new ProxyClient(subject);
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    public void proxyPattern() {
        Subject subject = new RealSubject();
        Subject proxy = new ProxyPattern(subject);
        ProxyClient client = new ProxyClient(proxy);
        client.execute();
        client.execute();
        client.execute();
    }
}
