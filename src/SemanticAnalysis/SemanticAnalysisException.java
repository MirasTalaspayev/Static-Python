package SemanticAnalysis;

public class SemanticAnalysisException extends Exception {
    
    public SemanticAnalysisException(String msg) {
        super("TypeError: " + msg);
    }
}
