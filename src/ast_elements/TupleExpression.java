package ast_elements;

import java.util.List;
import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public class TupleExpression extends Expression {
    
    private List<Expression> values;
        
    
    public TupleExpression(List<Expression> values)
    {
        this.values = values;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int size = values.size();
        sb.append("(");
        for (int i = 0; i < size - 1; i++) {
            sb.append(values.get(i)).append(", ");
        }
        if (size >= 1) {
            sb.append(values.get(size - 1));
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType)
            throws SemanticAnalysisException {
                
                if (!(expectedType instanceof TupleType)) {
                    throw new SemanticAnalysisException(this + " is not instance of " + expectedType);
                }

                TupleType tuple_type = (TupleType) expectedType;
                if (tuple_type.getSubTypes().size() != values.size()){
                    for (int i = 0; i < values.size(); i++) {
                        values.get(i).analyze(variable_Map, func_Map, tuple_type.getSubTypes().get(i));
                    }
                }
    }
}
