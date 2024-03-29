package ast_elements;

import java.util.Map;

import Executor.ExecutionException;
import SemanticAnalysis.SemanticAnalysisException;

public class FunctionStatement extends Statement {

    private FunctionCall func_call;
    
    public FunctionStatement(FunctionCall func_call) {
        this.func_call = func_call;
    }

    public StringBuilder toString(int indent) {
        return func_call.toString(indent).append("\n");
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
        func_call.analyze(variable_Map, func_Map, null);
    }

    @Override
    public void execute(Map<String, Object> variable_Map, Map<String, FunctionDeclaration> func_Map)
            throws ExecutionException {
        func_call.evaluate(variable_Map, func_Map);
    }
}