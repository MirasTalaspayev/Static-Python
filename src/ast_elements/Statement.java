package ast_elements;

import java.util.Map;

import Executor.ExecutionException;
import Executor.ReturnFromCall;
import SemanticAnalysis.SemanticAnalysisException;

public abstract class Statement extends ASTElement {

    public String toString() {
        return toString(0).toString();
    }

    public abstract StringBuilder toString(int indent);

    public abstract void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException;

    public void execute(Map<String, Object> variable_Map, Map<String, FunctionDeclaration> func_Map) throws ExecutionException, ReturnFromCall {
        
    }
}
