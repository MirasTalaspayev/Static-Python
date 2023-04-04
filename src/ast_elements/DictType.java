package ast_elements;

public class DictType extends CollectionType {
    
    private Type key_Type;
    private Type value_Type;

    public DictType(Type key_Type, Type value_Type) {
        this.key_Type = key_Type;
        this.value_Type = value_Type;
        elements_Type = this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("dict[");
        sb.append(key_Type).append(", ").append(value_Type).append("]");
        return sb.toString();
    }

    public Type getKey_Type() {
        return key_Type;
    }

    public void setKey_Type(Type key_Type) {
        this.key_Type = key_Type;
    }

    public Type getValue_Type() {
        return value_Type;
    }

    public void setValue_Type(Type value_Type) {
        this.value_Type = value_Type;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) 
            return true;
        
        if (!(other instanceof DictType)) 
            return false;
        
        DictType o = (DictType)other;
        return key_Type.equals(o.key_Type) && value_Type.equals(o.value_Type);
    }
}
