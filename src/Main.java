import ast_elements.ProgramAST;
import java_cup.runtime.Symbol;

import java.io.*;

import SemanticAnalysis.SemanticAnalyzer;

public class Main {
  public static void processFiles(File folder, String additionalFilePath) {
    File[] files = folder.listFiles();
    if (files == null) {
      System.err.println("Error: Unable to read contents of directory " + folder.getAbsolutePath());
      return;
    }
    for (File file : files) {
      if (file.isDirectory()) {
        processFiles(file, additionalFilePath);
      } else if (file.isFile() && (file.getName().endsWith(".spy") || file.getName().endsWith(".txt"))) {
        try {
          System.out.println("Processing file: " + file.getName());
          if (file.getName().endsWith(".spy")) {
            Yylex l = new Yylex(new FileReader(file));
            parser p = new parser(l);
            Object result = p.parse().value;
            if (result instanceof ProgramAST) {
              System.out.println("===================");
              System.out.print(result.toString());

              System.out.println("=================== Phase3: Typecheck ===================");
              SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer((ProgramAST) result);
              semanticAnalyzer.analyze();
            }
          } else if (file.getName().endsWith(".txt")) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
              // Do something with each line
            }
            reader.close();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    // Process the additional file
    File additionalFile = new File(additionalFilePath);
    if (additionalFile.isFile() && additionalFile.getName().endsWith(".txt")) {
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
  
          System.out.println("=================== Phase3: Typecheck ===================");
          SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer((ProgramAST)result);
          semanticAnalyzer.analyze();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    String folderPath = "../Before-After-Python/After";
    String additionalFilePath = ".//example_expr.txt";
    File folder = new File(folderPath);
    processFiles(folder, additionalFilePath);
  }
}