package ast_elements;

public class SetType extends Type {
    private Type elements_Type;
    public SetType(Type elements_Type) {
        this.elements_Type = elements_Type;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("set[");
        sb.append(elements_Type).append("]");
        return sb.toString();
    }
}
