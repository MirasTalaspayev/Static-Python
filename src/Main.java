/* Es 1 - Ex 1 (Main) */
import ast_elements.ProgramAST;
import java_cup.runtime.Symbol;

import java.io.*;

import Executor.Executor;
import SemanticAnalysis.SemanticAnalyzer;
   
public class Main {
  @SuppressWarnings("deprecation")
  static public void main(String argv[]) {
    try {
      /* Scanner instantiation */
      Yylex l = new Yylex(new FileReader(argv[0]));
      /* Parser instantiation */
      parser p = new parser(l);
      /* Start the parser */
      Object result = p.parse().value;
      if (result instanceof ProgramAST) {
        System.out.println("===================");
        System.out.print(result.toString());

        System.out.println("=================== Phase 3: Typecheck ===================");
        SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer((ProgramAST)result);
        semanticAnalyzer.analyze();
        System.out.println("=================== Phase 4: Execute ===================");
        Executor executor = new Executor((ProgramAST)result);
        executor.runProgram();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}


