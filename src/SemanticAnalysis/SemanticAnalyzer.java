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
    public boolean couldBeOperated() {
        
        return false;
    }
}
class Rules {
    public boolean plus(Expression ex1, Expression ex2) {
        if (ex1 instanceof NumberExpression && ex2 instanceof NumberExpression) {
            return true;
        }
        if (ex1 instanceof ListExpression && ex2 instanceof ListExpression) {
            ListExpression e1 = (ListExpression)ex1;
            ListExpression e2 = (ListExpression)ex2;
            
            return e1.getElementType().equals(e2.getElementType());
        }
        if (ex1 instanceof FloatExpression && ex2 instanceof FloatExpression) {
            return true;
        }
        return false;
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