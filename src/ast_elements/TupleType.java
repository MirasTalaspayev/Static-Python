package ast_elements;

import java.util.List;

public class TupleType extends Type {
    public List<Type> sub_Types;

    public TupleType(List<Type> sub_Types) {
        this.sub_Types = sub_Types;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("tuple[");
        int size = sub_Types.size();
        for (int i = 0; i < size - 1; i++)
        {
            sb.append(sub_Types.get(i)).append(", ");
        }
        sb.append(sub_Types.get(size - 1)).append("]");
        return sb.toString();

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) 
            return true;
        
        if (!(other instanceof ListType))
            return false;
        
        return this.sub_Types.equals(((TupleType)other).sub_Types);
    }

    public List<Type> getSubTypes() {
        return this.sub_Types;
    }
}
