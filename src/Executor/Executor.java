package Executor;

import ast_elements.FunctionDeclaration;
import ast_elements.ProgramAST;
import ast_elements.Statement;

import java.util.HashMap;

public class Executor {
    private ProgramAST programAST;
    private HashMap<String, Object> var_Map;
    private HashMap<String, FunctionDeclaration> func_Map;

    public Executor(ProgramAST programAST) {
        this.programAST = programAST;
        var_Map = new HashMap<>();
        func_Map = new HashMap<>();
    }
    public void runProgram() throws ExecutionException, ReturnFromCall {
        try {
            for (Statement stmt : programAST.getStatements()) {
                stmt.execute(var_Map, func_Map);
            }
        } catch (ReturnFromCall e) {
            System.out.println(e.getCause());
        }
    }
}
