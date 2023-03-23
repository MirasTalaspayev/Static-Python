package ast_elements;

public class VariableType extends Type {
    
    private String type;
    public VariableType(String type)
    {
        this.type = type;
    }

    public String toString() {
        return this.type;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        
        if (!(other instanceof VariableType)) {
            return false;
        }

        VariableType varType = (VariableType) other;

        return varType.getType().equals(this.getType());
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }
}
