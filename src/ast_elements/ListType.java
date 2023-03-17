package ast_elements;

public class ListType extends Type {
    private Type elements_Type;
    public ListType(Type elements_Type) {
        this.elements_Type = elements_Type;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("list[");
        sb.append(elements_Type).append("]");
        
        return sb.toString();
    }
}
