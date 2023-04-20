/* Es 1 - Ex 1 (Main) */
import ast_elements.ProgramAST;
import java_cup.runtime.Symbol;

import java.io.*;

import SemanticAnalysis.SemanticAnalyzer;
   

public static void processFolder(String folderPath) {
	File folder = new File(folderPath);
	processFiles(folder);
}

public static void processFile(String filePath) {
	File file = new File(filePath);
	if (file.isFile() && file.getName().endsWith(".spy")) {
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
	} else {
		 System.err.println("Error: Invalid file or extension. Only .spy files are accepted.");
	}
}

public static void main(String[] args) {
	String folderPath = "../Before-After-Python/After";
	processFolder(folderPath);

	String filePath = ".//example_expr.txt";
	processFile(filePath);
}
