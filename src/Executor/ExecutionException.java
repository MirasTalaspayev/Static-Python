package Executor;

public class ExecutionException extends Exception {
    public ExecutionException(String msg) {
        super("Execution Error: " + msg);
    }
}
