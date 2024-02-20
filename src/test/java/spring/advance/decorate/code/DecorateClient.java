package spring.advance.decorate.code;

public class DecorateClient {
    private Subject subject;

    public DecorateClient(Subject subject) {
        this.subject = subject;
    }

    public void execute(String data) {
        subject.execute(data);
    }
}
