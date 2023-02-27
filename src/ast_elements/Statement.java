package ast_elements;

public abstract class Statement extends ASTElement {

    public String toString() {
        return toString(0).toString();
    }

    public abstract StringBuilder toString(int indent);

}
