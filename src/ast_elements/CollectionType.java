package ast_elements;

public class CollectionType extends Type {
    protected Type elements_Type;
    public Type getElementsType() {
        return elements_Type;
    }
}
