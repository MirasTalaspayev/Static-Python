package ast_elements;

import java.util.List;
import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public class ListExpression extends CollectionExpressions {
    
    private List<Expression> values;
    
    public ListExpression(List<Expression> values)
    {
        this.values = values;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int size = values.size();
        sb.append("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(values.get(i)).append(", ");
        }
        if (size >= 1) {
            sb.append(values.get(size - 1));
        }
        sb.append("]");
        return sb.toString();
    }

	@Override
	public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType) throws SemanticAnalysisException {
		super.analyze(variable_Map, func_Map, expectedType);
        
        if (!(expectedType instanceof ListType)) {
            throw new SemanticAnalysisException(this + " is not instance of " + expectedType);
        }
        
        collectionType = (ListType)expectedType;
        elements_Type = collectionType.elements_Type;

        for (int i = 0; i < values.size(); i++) {
            values.get(i).analyze(variable_Map, func_Map, elements_Type);
        }
	}

    @Override
    public int size() {
        return values.size();
    }
}
