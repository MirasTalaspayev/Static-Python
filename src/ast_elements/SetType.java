package ast_elements;

public class SetType extends CollectionType {
    public SetType(Type elements_Type) {
        this.elements_Type = elements_Type;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("set[");
        sb.append(elements_Type).append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) 
            return true;
        
        if (!(other instanceof SetType))
            return false;
        
        return this.elements_Type.equals(((SetType)other).elements_Type);
    }
}
