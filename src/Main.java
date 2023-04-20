import ast_elements.ProgramAST;
import java_cup.runtime.Symbol;

import java.io.*;

import SemanticAnalysis.SemanticAnalyzer;

public class Main {
  public static void processFilesInFolder(File folder) {
    File[] files = folder.listFiles();
    if (files == null) {
      System.err.println("Error: Unable to read contents of directory " + folder.getAbsolutePath());
      return;
    }
    for (File file : files) {
      if (file.isDirectory()) {
        processFilesInFolder(file);
      } else if (file.isFile() && file.getName().endsWith(".spy")) {
        try {
          System.out.println("Processing file: " + file.getName());
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
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void processFile(String filePath) {
    if (filePath != null && filePath.endsWith(".txt")) {
      try {
        /* Scanner instantiation */
        Yylex l = new Yylex(new FileReader(filePath));
        /* Parser instantiation */
        parser p = new parser(l);
        /* Start the parser */
        Object result = p.parse().value;
        if (result instanceof ProgramAST) {
          System.out.println("===================");
          System.out.print(result.toString());

          System.out.println("=================== Phase3: Typecheck ===================");
          SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer((ProgramAST) result);
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
    processFilesInFolder(folder);
    processFile(additionalFilePath);
  }
}