/* Es 1 - Ex 1 (Main) */
import ast_elements.ProgramAST;
import java_cup.runtime.Symbol;

import java.io.*;
   
public class Main {
  @SuppressWarnings("deprecation")
  static public void main(String argv[]) {
    try {
      /* Scanner instantiation */
      Yylex l = new Yylex(new FileReader(".//example_expr.txt"));
      /* Parser instantiation */
      parser p = new parser(l);
      /* Start the parser */
      Object result = p.parse().value;
      if (result instanceof ProgramAST) {
        System.out.println("===================");
        System.out.print(result.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}


