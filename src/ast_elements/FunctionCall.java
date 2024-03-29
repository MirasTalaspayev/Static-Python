package ast_elements;

import java.util.*;
import java.util.List;
import java.util.Map;

import Executor.ExecutionException;
import Executor.ReturnFromCall;
import SemanticAnalysis.SemanticAnalysisException;

public class FunctionCall extends Expression {

    private String func_name;
    private List<Expression> ex_list;
    private String obj;
    private Type type;

    public FunctionCall(String func_name, List<Expression> ex_list, String obj) {
        this.func_name = func_name;
        this.ex_list = ex_list;
        this.obj = obj;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind);
        if (this.obj == null)
            sb.append(this.func_name + "(");
        else
            sb.append(this.obj + "." + this.func_name + "(");
        int ex_size = this.ex_list.size();
        for (int i = 0; i < ex_size - 1; i++)
            sb.append(ex_list.get(i)).append(", ");
        if (ex_size >= 1)
            sb.append(ex_list.get(ex_size - 1) + ")");
        else
            sb.append(")");
        return sb;
    }



    @Override
    public String toString() {
        return toString(0).toString();
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType)
            throws SemanticAnalysisException {
        Type type = analyzeAndGetType(variable_Map, func_Map);
        if (expectedType != null && !expectedType.equals(type))
            throw new SemanticAnalysisException("expected type and return type don't match");
    }

    @Override
    public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
        int ex_size = this.ex_list.size();
        if (this.obj == null) {
            if (this.func_name.equals("print")) {
                for (int i=0; i < ex_size; i++) {
                    if (this.ex_list.get(i) instanceof LabelExpression)
                        this.ex_list.get(i).analyzeAndGetType(variable_Map, func_Map);
                }
                return null;
            }

            if (this.func_name.equals("range")) {
                if (ex_size == 0)
                    throw new SemanticAnalysisException(this.func_name + "() must have at least one argument");
                if (ex_size > 2)
                    throw new SemanticAnalysisException(this.func_name + "() cannot have more than two arguments");
                for (int i = 0; i < ex_size; i++)
                    this.ex_list.get(i).analyze(variable_Map, func_Map, NumberExpression.TYPE);
                return new ListType(NumberExpression.TYPE);
            }

            if (this.func_name.equals("len")) {
                if (ex_size != 1)
                    throw new SemanticAnalysisException(this.func_name + "() can have only one argument");
                Type t = this.ex_list.get(0).analyzeAndGetType(variable_Map, func_Map);
                if (!(t instanceof CollectionType))
                    throw new SemanticAnalysisException(t + " is not instance of Collection Type");
                return NumberExpression.TYPE;
            }
            
            if (!func_Map.containsKey(func_name))
                throw new SemanticAnalysisException("function doesn't exist");

            if (ex_size != func_Map.get(func_name).getParam_list().size())
                throw new SemanticAnalysisException("number of parameters doesn't match");

            for (int i = 0; i < ex_size; i++)
                ex_list.get(i).analyze(variable_Map, func_Map, func_Map.get(func_name).getParam_list().get(i).getType());
            return func_Map.get(this.func_name).getReturn_Type();
        } else {
            Type obj_Type = variable_Map.get(this.obj);
            if (obj_Type instanceof CollectionType) {
                CollectionType obj_collection_type = (CollectionType)obj_Type;
                if (this.func_name.equals("copy")) {
                    if (ex_size != 0)
                        throw new SemanticAnalysisException(this.func_name + "() cannot have arguments");
                    return obj_collection_type;
                }

                if (this.func_name.equals("clear")) {
                    if (ex_size != 0)
                        throw new SemanticAnalysisException(this.func_name + "() cannot have arguments");
                    return null;
                }
            }
            
            if (obj_Type instanceof ListType) {
                ListType obj_list_type = (ListType)obj_Type;
                if (this.func_name.equals("reverse") || this.func_name.equals("sort")) {
                    if (ex_size != 0)
                        throw new SemanticAnalysisException(this.func_name + "() cannot have arguments");
                    return null;
                } 
                
                if (this.func_name.equals("append") || this.func_name.equals("remove")) {
                    if (ex_size != 1)
                        throw new SemanticAnalysisException(this.func_name + "() can have only one argument");
                    this.ex_list.get(0).analyze(variable_Map, func_Map, obj_list_type.getElements_Type());
                    return null;
                }

                if (this.func_name.equals("count") || this.func_name.equals("index")) {
                    if (ex_size != 1)
                        throw new SemanticAnalysisException(this.func_name + "() can have only one argument");
                    this.ex_list.get(0).analyze(variable_Map, func_Map, obj_list_type.getElements_Type());
                    return NumberExpression.TYPE;
                }

                if (this.func_name.equals("get")) {
                    if (ex_size != 1)
                        throw new SemanticAnalysisException(this.func_name + "() can have only one argument");
                    this.ex_list.get(0).analyze(variable_Map, func_Map, obj_list_type.getElements_Type());
                    return obj_list_type.getElements_Type();
                }

                if (this.func_name.equals("put")) {
                    if (ex_size != 2)
                        throw new SemanticAnalysisException(this.func_name + "() must have two arguments");
                    this.ex_list.get(0).analyze(variable_Map, func_Map, NumberExpression.TYPE);
                    this.ex_list.get(1).analyze(variable_Map, func_Map, obj_list_type.getElements_Type());
                    return null;
                }
            }

            if (obj_Type instanceof SetType) {
                SetType obj_set_type = (SetType)obj_Type;
                if (this.func_name.equals("add") || this.func_name.equals("remove") || this.func_name.equals("update")) {
                    if (ex_size != 1)
                        throw new SemanticAnalysisException(this.func_name + "() can have only one argument");
                    this.ex_list.get(0).analyze(variable_Map, func_Map, obj_set_type.getElements_Type());
                    return null;
                }
                
                if (this.func_name.equals("issubset")) {
                    if (ex_size != 1)
                        throw new SemanticAnalysisException(this.func_name + "() can have only one argument");
                    this.ex_list.get(0).analyze(variable_Map, func_Map, obj_set_type);
                    return BooleanExpression.TYPE;
                }
                
                if (this.func_name.equals("difference")) {
                    if (ex_size != 1)
                        throw new SemanticAnalysisException(this.func_name + "() can have only one argument");
                    this.ex_list.get(0).analyze(variable_Map, func_Map, obj_set_type);
                    return obj_set_type;
                }

                if (this.func_name.equals("get")) {
                    if (ex_size != 1)
                        throw new SemanticAnalysisException(this.func_name + "() can have only one argument");
                    this.ex_list.get(0).analyze(variable_Map, func_Map, obj_set_type.getElements_Type());
                    return obj_set_type.getElements_Type();
                }

                if (this.func_name.equals("put")) {
                    if (ex_size != 2)
                        throw new SemanticAnalysisException(this.func_name + "() must have two arguments");
                    this.ex_list.get(0).analyze(variable_Map, func_Map, NumberExpression.TYPE);
                    this.ex_list.get(1).analyze(variable_Map, func_Map, obj_set_type.getElements_Type());
                    return null;
                }
            }

            if (obj_Type instanceof DictType) {
                DictType obj_dict_type = (DictType)obj_Type;
                if (this.func_name.equals("keys")) {
                    if (ex_size != 0)
                        throw new SemanticAnalysisException(this.func_name + "() cannot have arguments");
                    return new ListType(obj_dict_type.getKey_Type());
                }
                
                if (this.func_name.equals("values")) {
                    if (ex_size != 0)
                        throw new SemanticAnalysisException(this.func_name + "() cannot have arguments");
                    return new ListType(obj_dict_type.getValue_Type());
                }

                if (this.func_name.equals("get")) {
                    if (ex_size != 1)
                        throw new SemanticAnalysisException(this.func_name + "() can have only one argument");
                    this.ex_list.get(0).analyze(variable_Map, func_Map, obj_dict_type.getKey_Type());
                    return obj_dict_type.getValue_Type();
                }

                if (this.func_name.equals("put")) {
                    if (ex_size != 2)
                        throw new SemanticAnalysisException(this.func_name + "() must have two arguments");
                    this.ex_list.get(0).analyze(variable_Map, func_Map, obj_dict_type.getKey_Type());
                    this.ex_list.get(1).analyze(variable_Map, func_Map, obj_dict_type.getValue_Type());
                    return null;
                }
            }

            if (obj_Type instanceof VariableType) {
                VariableType obj_var_Type = (VariableType) obj_Type;
                if (obj_var_Type.getType() == "int") {

                } else if (obj_var_Type.getType() == "float") {

                } else if (obj_var_Type.getType().equals("str")) {
                    if (this.func_name.equals("count")) {
                        if (ex_size != 1)
                            throw new SemanticAnalysisException(this.func_name + "() can have only one argument");
                        this.ex_list.get(0).analyze(variable_Map, func_Map, StringExpression.TYPE);
                        return NumberExpression.TYPE;
                    }
                    
                    if (this.func_name.equals("strip")) {
                        if (ex_size > 1)
                            throw new SemanticAnalysisException(this.func_name + "() cannot have more than one argument");
                        for (int i=0; i < ex_size; i++)
                            this.ex_list.get(0).analyze(variable_Map, func_Map, StringExpression.TYPE);
                        return StringExpression.TYPE;
                    }
                    
                    if (this.func_name.equals("split")) {
                        if (ex_size > 1)
                            throw new SemanticAnalysisException(this.func_name + "() cannot have more than one argument");
                        this.ex_list.get(0).analyze(variable_Map, func_Map, StringExpression.TYPE);
                        return new ListType(StringExpression.TYPE);
                    }
                    
                    if (this.func_name.equals("replace")) {
                        if (ex_size != 2)
                            throw new SemanticAnalysisException(this.func_name + "() must have only two arguments");
                        this.ex_list.get(0).analyze(variable_Map, func_Map, StringExpression.TYPE);
                        this.ex_list.get(1).analyze(variable_Map, func_Map, StringExpression.TYPE);
                        return obj_var_Type;
                    }
                } else if (obj_var_Type.getType() == "bool") {

                }
            } throw new SemanticAnalysisException(this.func_name + "() function doesn't exist for the " + obj_Type + " type");
        }
    }

    @Override
    public Object evaluate(Map<String, Object> variable_Map, Map<String, FunctionDeclaration> func_Map)
            throws ExecutionException {
        if (obj == null) {
            if (func_name.equals("print")) {
                for (int i = 0; i < ex_list.size(); i++) {
                    System.out.print(ex_list.get(i).evaluate(variable_Map, func_Map) + " ");
                }
                System.out.println();
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

            if (func_name.equals("len")) {
                return ((ArrayList)ex_list.get(0).evaluate(variable_Map, func_Map)).size();
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
        Object object = variable_Map.get(obj);
        if (object instanceof ArrayList) {
            ArrayList<Object> object_List = (ArrayList)object;
            if (func_name.equals("get")) {
                return object_List.get((Integer)ex_list.get(0).evaluate(variable_Map, func_Map));
            }
            if (func_name.equals("put")) {
                object_List.set((Integer)ex_list.get(0).evaluate(variable_Map, func_Map), ex_list.get(1).evaluate(variable_Map, func_Map));
                return null;
            }

        }
        return null;
    }
}
