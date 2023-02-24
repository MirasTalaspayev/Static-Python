package ast_elements;

public class IndentUtil {

    public static String indentStr(int indent) {

        if (indent == 0) return "";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < indent; i++) {
            sb.append("    ");
        }
        return sb.toString();
    }
}
