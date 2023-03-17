package ast_elements;

import java.util.List;

public class DictExpression extends Expression {
    
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
    
}