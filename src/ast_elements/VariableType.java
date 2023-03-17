package ast_elements;

import java.util.ArrayList;

public class VariableType extends Statement {
    private String type;
    public ArrayList<VariableType> sub_Types;

    public VariableType(String type, ArrayList<VariableType> sub_Types) {
        this.type = type;
        this.sub_Types = sub_Types;
    }
    
    @Override
    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);

        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(type);
        if (sub_Types.size() > 0)
        {
            sb.append("[");
            int size = sub_Types.size();
            for (int i = 0; i < size - 1; i++)
            {
                sb.append(sub_Types.get(i)).append(", ");
            }
            sb.append(sub_Types.get(size)).append("]");
        }
        
        return sb;
    }

}
