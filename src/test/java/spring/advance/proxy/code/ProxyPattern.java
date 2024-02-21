package spring.advance.proxy.code;

public class ProxyPattern implements Subject {
    private String data;
    private Subject subject;

    public ProxyPattern(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String operation() {
        if (data == null) {
            data = subject.operation();
        }
        return data;
    }
}
