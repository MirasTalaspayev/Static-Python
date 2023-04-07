package SemanticAnalysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

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
    public boolean couldBeOperated() {
        
        return false;
    }
}
class Rules {
    public void generateRules() {
        HashMap<Rule, Type> rules = new HashMap<>();
        rules.put(new Rule(), new ListType(null));
    }
    public void invoke(Expression e1, Expression e2, String op, Type expectedType) throws SemanticAnalysisException {
        SemanticAnalysisException ex = new SemanticAnalysisException(e1 + " and " + e2 + " does not support operation '" + op + "'");
        if (op == "+") {
            if (!isAddable(e1, e2)) {
                throw ex;
            }
        }
        else if (op == "-") {

        }
        else if (op == "*") {

        }
        else {
            throw ex;            
        }
    }
    public void isAddable(Expression ex1, Expression ex2, Type expectedType) throws SemanticAnalysisException {
        ex1.analyze(null, null, expectedType);
        ex2.analyze(null, null, expectedType);
    }
    public void isMultipliable(Expression ex1, Expression ex2, Type expectedType) {
        
    }
}
class Rule {
    Expression ex1;
    Expression ex2;
    String op;

    Type result;
    
    @Override
    public int hashCode() {
        return (ex1 + op + ex2).hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }
}