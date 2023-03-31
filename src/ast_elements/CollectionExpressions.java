package ast_elements;

public abstract class CollectionExpressions extends Expression {
    protected Type collectionType;
    protected Type elementsType;

    public abstract int size();
    
    public void setCollectionType(Type type) {
        collectionType = type;
    }

    public Type getElementType() {
        return elementsType;
    }
}
