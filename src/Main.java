import ast_elements.ProgramAST;
import java_cup.runtime.Symbol;
import java_cup.runtime.*;

import java.io.*;
import java.util.Scanner;

import SemanticAnalysis.SemanticAnalyzer;

public class Main {
	public static void processFiles(File fileOrFolder, int[] counts) {
		if (fileOrFolder.isFile() && fileOrFolder.getName().endsWith(".spy")) {
			processFile(fileOrFolder.getPath(), counts);
		} else if (fileOrFolder.isDirectory()) {
			for (File file : fileOrFolder.listFiles()) {
				if (file.isFile() && file.getName().endsWith(".spy")) {
					processFile(file.getPath(), counts);
				} else if (file.isDirectory()) {
					processFiles(file, counts);
				}
			}
		}
	}

	public static void processFile(String filePath, int[] counts) {
		try {
			Yylex l = new Yylex(new FileReader(filePath));
			parser p = new parser(l);
			Object result = p.parse().value;
			if (result instanceof ProgramAST) {
				System.out.println("===================");
				System.out.print(result.toString());

				System.out.println("=================== Phase3: Typecheck ===================");
				SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer((ProgramAST) result);
				semanticAnalyzer.analyze();
				counts[0]++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			counts[1]++;
		}
	}

	public static void main(String[] args) {
		int[] counts = { 0, 0 };
		if (args.length > 0) {
			File fileOrFolder = new File(args[0]);
			if (!fileOrFolder.exists()) {
				System.err.println("Error: File " + args[0] + " not found.");
				return;
			}
			processFiles(fileOrFolder, counts);
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
					if (input.equals("exit")) {пше
						return;
					}
					if (input.isEmpty()) {
						break;
					}
					inputCode.append(input).append("\n");
				}

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
						counts[0]++;
					}
				} catch (Exception e) {
					e.printStackTrace();
					counts[1]++;
				}
			}
		}
		System.out.println("Processed " + (counts[0] + counts[1]) + " files. " + counts[0]
				+ " files successfully compiled, " + counts[1] + " files failed to compile.");
	}
}
