package ast_elements;

import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public abstract class Expression extends ASTElement {
    
    public abstract String toString();

    public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException 
    {
        return null;
    }
    public void analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType) throws SemanticAnalysisException {

    }
}
