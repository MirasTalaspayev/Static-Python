package ast_elements;

import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public abstract class CollectionExpressions extends Expression {
    protected CollectionType collectionType;
    protected Type elements_Type;

    public abstract int size();
    
    public void setCollectionType(Type type) throws SemanticAnalysisException {
        if (!(type instanceof CollectionType)) {
            throw new SemanticAnalysisException(type + " is not a collection type");
        }
        collectionType = (CollectionType)type;
        elements_Type = collectionType.elements_Type;
    }

    public Type getElementType() {
        return elements_Type;
    }

    @Override
    public void analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map,
            Type expectedType) throws SemanticAnalysisException {
        if (size() == 0) {
            setCollectionType(expectedType);
            return;
        }
    }
}
