package job_tracker.domain;

import java.util.ArrayList;
import java.util.List;

public class Result<T> {
    private final List<String> messages = new ArrayList<>();
    private ResultType type = ResultType.SUCCESS;
    private T payload;

    public ResultType getType() {
        return type;
    }

    public boolean isSuccess() {
        return type == ResultType.SUCCESS;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public T getPayload() {
        return payload;
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public void addMessage(String message, ResultType type) {
        messages.add(message);
        this.type = type;
    }
}
