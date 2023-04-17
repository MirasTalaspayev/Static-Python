package ast_elements;

import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public abstract class Expression extends ASTElement {
    
    public abstract String toString();

    public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException 
    {
        return null;
    }

    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType) throws SemanticAnalysisException {

    }

    public void add(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        throw new SemanticAnalysisException(this + " and " + ex + " does not support operand '+'");
    }

    public void subtract(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        throw new SemanticAnalysisException(this + " and " + ex + " does not support operand '-'");
    }

    public void multiply(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        throw new SemanticAnalysisException(this + " and " + ex + " does not support operand '*'");
    }

    public void divide(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        throw new SemanticAnalysisException(this + " and " + ex + " does not support operand '*'");
    }

    public void isEqual(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        throw new SemanticAnalysisException(this + " and " + ex + " does not support operand '=='");
    }

    public void notEqual(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        isEqual(variable_Map, func_Map, expectedType, ex);
    }

    public void greater(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        throw new SemanticAnalysisException(this + " and " + ex + " does not support operand '>'");
    }

    public void greater_or_equal(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        throw new SemanticAnalysisException(this + " and " + ex + " does not support operand '>='");
    }

    public void less(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        throw new SemanticAnalysisException(this + " and " + ex + " does not support operand '<'");
    }

    public void less_or_equal(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        throw new SemanticAnalysisException(this + " and " + ex + " does not support operand '<='");
    }

    public void union(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        throw new SemanticAnalysisException(this + " and " + ex + " does not support operand '|'");
    }

    public void intersection(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        throw new SemanticAnalysisException(this + " and " + ex + " does not support operand '&'");
    }

    public void xor(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        throw new SemanticAnalysisException(this + " and " + ex + " does not support operand '=='");
    }

    public void membership(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
       throw new SemanticAnalysisException(this + " and " + ex + " does not support operand '=='");
    }
}
