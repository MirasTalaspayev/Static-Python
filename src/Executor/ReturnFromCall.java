package Executor;

public class ReturnFromCall extends Exception {
    private Object returnValue;

    public ReturnFromCall(Object value) {
        this. returnValue = value;
    }

    public Object getReturnValue() {
        return returnValue;
    }
}
