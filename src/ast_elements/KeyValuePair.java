package ast_elements;

public class KeyValuePair {
    private Expression key;
    private Expression value;

    public KeyValuePair(Expression key, Expression value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.key).append(": " + this.value);
        return sb.toString();
    }
}
