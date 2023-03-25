package ast_elements;

import java.util.List;
import java.util.Map;

public class SetExpression extends CollectionExpressions {
    
    private List<Expression> values;

    public SetExpression(List<Expression> values)
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
    public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'analyzeAndGetType'");
    }

    @Override
    public int size() {
        return values.size();
    }
}
