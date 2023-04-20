import ast_elements.ProgramAST;
import java_cup.runtime.Symbol;
import java_cup.runtime.*;

import java.io.*;
import java.util.Scanner;

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
    if (args.length > 0) {
      String fileName = args[0];
      File file = new File(fileName);
      if (!file.exists()) {
        System.err.println("Error: File " + fileName + " not found.");
        return;
      }
      if (file.isDirectory()) {
        processFilesInFolder(file);
      } else if (file.isFile() && (file.getName().endsWith(".spy") || file.getName().endsWith(".txt"))) {
        processFile(fileName);
      } else {
        System.err.println("Error: Invalid file or extension. Only .spy files are accepted.");
        return;
      }
    } else {
      // Accept user input from terminal
      System.out.println("Welcome to Static Python Terminal!");
      System.out.println("Enter your code. Press Enter to stop or type 'exit' to quit.");
      while (true) {
        StringBuilder inputCode = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        while (true) {
          System.out.print("> ");
          String input = scanner.nextLine();
          if (input.equals("exit")) {
            return;
          }
          if (input.isEmpty()) {
            break; // Stop inputting code if Enter is pressed
          }
          inputCode.append(input).append("\n");
        }

        // Process user input as a block of code
        try {
          StringReader reader = new StringReader(inputCode.toString());
          Yylex lexer = new Yylex(reader);
          parser parser = new parser(lexer);
          Object result = parser.parse().value;
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
}
