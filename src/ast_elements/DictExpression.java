package ast_elements;

import java.util.List;
import java.util.Map;

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
	public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
		if (values.size() == 0) {
            return collectionType;
        }

        DictType dictType = new DictType(values.get(0).getKey().analyzeAndGetType(variable_Map, func_Map), 
            values.get(0).getValue().analyzeAndGetType(variable_Map, func_Map));

        for (int i = 1; i < values.size(); i++) {
            if (!dictType.equals(values.get(i).analyzeAndGetType(variable_Map, func_Map))) {
                throw new SemanticAnalysisException("type of " + values.get(i) + " does not match with " + elementsType);
            }
        }

        elementsType = dictType;
        return elementsType;
	}

    @Override
    public int size() {
        return values.size();
    }
    
}