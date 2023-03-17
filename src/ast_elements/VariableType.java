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
}
