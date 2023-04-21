package ast_elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Executor.ExecutionException;
import SemanticAnalysis.SemanticAnalysisException;

public class DictExpression extends CollectionExpressions {
    
    private List<KeyValuePair> values;
    
    public DictExpression(List<KeyValuePair> values)
    {
        this.values = values;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int size = values.size();
        sb.append("{");
        for (int i = 0; i < size - 1; i++) {
            sb.append(values.get(i)).append(", ");
        }
        if (size >= 1) {
            sb.append(values.get(size - 1));
        }
        sb.append("}");
        return sb.toString();
    }

	@Override
	public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType) throws SemanticAnalysisException {
        super.analyze(variable_Map, func_Map, expectedType);

        if (!(expectedType instanceof DictType)) {
            throw new SemanticAnalysisException(this + " is not instance of " + expectedType);
        }

        collectionType = (DictType)expectedType;
        elements_Type = collectionType.elements_Type;

        for (int i = 0; i < values.size(); i++) {
            values.get(i).analyze(variable_Map, func_Map, elements_Type);
        }
	}

    @Override
    public Object evaluate(Map<String, Object> variable_Map, Map<String, FunctionDeclaration> func_Map)
            throws ExecutionException {
        HashMap<Object, Object> dict = new HashMap<>();
        for (KeyValuePair ex : values) {
            dict.put(ex.getKey().evaluate(variable_Map, func_Map), ex.getValue().evaluate(variable_Map, func_Map));
        }
        return dict;
    }
    @Override
    public int size() {
        return values.size();
    }
    
}