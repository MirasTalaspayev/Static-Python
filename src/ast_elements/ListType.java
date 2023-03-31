package ast_elements;

public class ListType extends Type {
    private Type elements_Type;
    public ListType(Type elements_Type) {
        this.elements_Type = elements_Type;
    }

    public Type getElements_Type() {
        return elements_Type;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("list[");
        sb.append(elements_Type).append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) 
            return true;
        
        if (!(other instanceof ListType))
            return false;
        
        return this.elements_Type.equals(((ListType)other).elements_Type);
    }
}
