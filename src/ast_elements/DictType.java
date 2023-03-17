package ast_elements;

public class DictType extends Type {
    private Type key_Type;
    private Type value_Type;

    public DictType(Type key_Type, Type value_Type) {
        this.key_Type = key_Type;
        this.value_Type = value_Type;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("dict[");
        sb.append(key_Type).append(", ").append(value_Type).append("]");
        return sb.toString();
    }
}
