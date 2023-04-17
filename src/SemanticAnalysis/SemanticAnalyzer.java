package SemanticAnalysis;

import java.util.HashMap;
import java.util.Map;

import ast_elements.*;

public class SemanticAnalyzer {
    private ProgramAST programAST;

    private Map<String, Type> variable_Map;
    private Map<String, FunctionDeclaration> func_Map;

    public SemanticAnalyzer(ProgramAST programAST) {
		this.programAST = programAST;
        variable_Map = new HashMap<String, Type>();
        func_Map = new HashMap<String, FunctionDeclaration>();
	}

    public void analyze() throws SemanticAnalysisException {
        for (Statement stmt : programAST.getStatements()) {
            stmt.analyze(variable_Map, func_Map);
        }
    }
}