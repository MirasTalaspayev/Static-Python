package ast_elements;

import java.util.Map;

public class LocalVarDeclaration extends Declaration {
    private String var_name;
    private Type type;

    public LocalVarDeclaration(String var_name, Type type) {
        this.var_name = var_name;
        this.type = type;
    }

    public StringBuilder toString(int indent) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.var_name + ":").append(this.type);
        return sb;
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'analyze'");
    }
}
