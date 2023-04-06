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
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType) throws SemanticAnalysisException {
        if (!(expectedType instanceof DictType)) {
            throw new SemanticAnalysisException(this + " is not a instance of " + expectedType);
        }
        dictType = (DictType)expectedType;
        key.analyze(variable_Map, func_Map, dictType.getKey_Type());
        value.analyze(variable_Map, func_Map, dictType.getValue_Type());
    }
}
