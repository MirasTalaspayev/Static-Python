package ast_elements;

import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public class KeyValuePair extends Expression {
    
    private Expression key;
    private Expression value;

    private DictType dictType;

    public KeyValuePair(Expression key, Expression value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.key).append(": " + this.value);
        return sb.toString();
    }

    public Expression getKey() {
        return key;
    }

    public Expression getValue() {
        return value;
    }

    @Override
    public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
        dictType = new DictType(key.analyzeAndGetType(variable_Map, func_Map), value.analyzeAndGetType(variable_Map, func_Map));
        return dictType;
    }
}
