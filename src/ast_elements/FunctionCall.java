package ast_elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Executor.ExecutionException;
import Executor.ReturnFromCall;
import SemanticAnalysis.SemanticAnalysisException;

public class FunctionCall extends Expression {

    private String func_name;
    private List<Expression> ex_list;
    private Expression obj;
    private Type type;

    public FunctionCall(String func_name, List<Expression> ex_list, Expression obj) {
        this.func_name = func_name;
        this.ex_list = ex_list;
        this.obj = obj;
    }

    public String getFunc_name() {
        return func_name;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind);
        if (this.obj == null)
            sb.append(this.func_name + "(");
        else
            sb.append(this.obj + "." + this.func_name + "(");
        int size = ex_list.size();
        for (int i = 0; i < size - 1; i++)
            sb.append(ex_list.get(i)).append(", ");
        if (size >= 1)
            sb.append(ex_list.get(size - 1) + ")");
        else
            sb.append(")");
        // sb.append("\n>>>> ex_list.size(): " + this.ex_list.size() + " <<<<");
        return sb;
    }

    @Override
    public String toString() {
        return toString(0).toString();
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType)
            throws SemanticAnalysisException {
        // System.out.println(">>>> func_name: " + this.func_name + " <<<<");
        Type type = analyzeAndGetType(variable_Map, func_Map);
        if (expectedType != null && expectedType != type)
            throw new SemanticAnalysisException("expected type and return type don't match");
    }

    @Override
    public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map)
            throws SemanticAnalysisException {
        if (this.obj == null) {
            if (func_name.equals("print")) {
                return null;
            }
            if (!func_Map.containsKey(func_name))
                throw new SemanticAnalysisException("function doesn't exist");

            if (ex_list.size() != func_Map.get(func_name).getParam_list().size())
                throw new SemanticAnalysisException("number of parameters doesn't match");

            for (int i = 0; i < ex_list.size(); i++)
                ex_list.get(i).analyze(variable_Map, func_Map,
                        func_Map.get(func_name).getParam_list().get(i).getType());
        } else {
            Type obj_Type = this.obj.analyzeAndGetType(variable_Map, func_Map);
            System.out.println(">>>> obj_Type: " + obj_Type + " <<<<");

            if (obj_Type instanceof ListType) {
                ListType obj_list_type = (ListType) obj_Type;
                System.out.println(">>>> obj_list_type: " + obj_list_type + " <<<<");
                if (this.func_name.equals("copy") || this.func_name.equals("clear") || this.func_name.equals("reverse")
                        || this.func_name.equals("sort")) {
                    if (this.ex_list.size() != 0) {
                        throw new SemanticAnalysisException(this.func_name + " does not have arguments");
                    }
                    if (this.func_name.equals("copy"))
                        return obj_list_type;
                    return null;
                }

                if (this.func_name.equals("append") || this.func_name.equals("remove")
                        || this.func_name.equals("count")) {
                    if (ex_list.size() != 1) {
                        throw new SemanticAnalysisException(
                                this.func_name + " cannot have less or more than one argument");
                    }
                    this.ex_list.get(0).analyze(variable_Map, func_Map, obj_list_type.getElements_Type());
                    if (this.func_name.equals("append"))
                        return obj_list_type;
                    return null;
                }

                if (this.func_name.equals("index")) {
                    if (this.ex_list.size() == 0) {
                        throw new SemanticAnalysisException(this.func_name + " must have at least one argument");
                    } else if (this.ex_list.size() > 3) {
                        throw new SemanticAnalysisException(this.func_name + " cannot have more that three arguments");
                    }
                    this.ex_list.get(0).analyze(variable_Map, func_Map, obj_list_type.getElements_Type());
                    for (int i = 1; this.ex_list.get(i) != null; i++) {
                        this.ex_list.get(i).analyze(variable_Map, func_Map, NumberExpression.TYPE);
                    }
                }
            }

            if (obj_Type instanceof SetType) {
                if (this.func_name.equals("copy") || this.func_name.equals("clear") || this.func_name.equals("reverse")
                        || this.func_name.equals("sort")) {
                    if (this.ex_list.size() != 0) {
                        throw new SemanticAnalysisException(this.func_name + " does not have arguments");
                    }
                    return null;
                }
            }

            if (obj_Type instanceof VariableType) {
                VariableType obj_var_Type = (VariableType) obj_Type;
                if (obj_var_Type.getType() == "int") {

                } else if (obj_var_Type.getType() == "float") {

                } else if (obj_var_Type.getType() == "str") {

                } else if (obj_var_Type.getType() == "bool") {

                }
            }
        }
        return type;
    }

    @Override
    public Object evaluate(Map<String, Object> variable_Map, Map<String, FunctionDeclaration> func_Map)
            throws ExecutionException {
        if (obj == null) {
            if (func_name.equals("print")) {
                System.out.println(ex_list.get(0).evaluate(variable_Map, func_Map));
                return null;
            }

            if (func_name.equals("range")) {
                int start = 0;
                int counter = 1;
                int end = 0;

                if (ex_list.size() == 1) {
                    end = (Integer) ex_list.get(0).evaluate(variable_Map, func_Map);
                } else if (ex_list.size() == 2) {
                    start = (Integer) ex_list.get(0).evaluate(variable_Map, func_Map);
                    end = (Integer) ex_list.get(1).evaluate(variable_Map, func_Map);
                }

                ArrayList<Integer> range_list = new ArrayList<>();

                for (; start < end; start += counter) {
                    range_list.add(start);
                }
                return range_list;
            }
            FunctionDeclaration func_decl = func_Map.get(func_name);

            Map<String, Object> localVar_Map = new HashMap<String, Object>(variable_Map);
            Map<String, FunctionDeclaration> localFun_Map = new HashMap<String, FunctionDeclaration>(func_Map);

            try {
                for (Statement stmt : func_decl.getBody()) {
                    stmt.execute(localVar_Map, localFun_Map);
                }
            } catch (ReturnFromCall e) {
                return e.getReturnValue();
            }
        }
        return null;
    }
}
