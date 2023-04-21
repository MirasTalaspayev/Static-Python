import ast_elements.ProgramAST;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import SemanticAnalysis.SemanticAnalyzer;

public class Main {
	public static void processFiles(File folder, String additionalFilePath) {
		int compiledCount = 0;
		int failedCount = 0;
		File[] files = folder.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					processFiles(file, additionalFilePath);
				} else if (file.isFile() && file.getName().endsWith(".spy")) {
					try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
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
							compiledCount++;
						}
					} catch (Exception e) {
						e.printStackTrace();
						failedCount++;
					}
				}
			}
		}
		File additionalFile = new File(additionalFilePath);
		if (additionalFile.isFile() && additionalFile.getName().endsWith(".spy")) {
			try (BufferedReader reader = new BufferedReader(new FileReader(additionalFile))) {
				System.out.println("Processing file: " + additionalFile.getName());
				String line;
				while ((line = reader.readLine()) != null) {
					// Do something with each line
				}
			} catch (Exception e) {
				e.printStackTrace();
				failedCount++;
			}
		}
		System.out.println("\n\nCompilation summary:");
		System.out.println("Total files processed: " + (compiledCount + failedCount));
		System.out.println("Number of files compiled: " + compiledCount);
		System.out.println("Number of files failed to compile: " + failedCount);
	}

	public static void main(String[] args) {
		String folderPath = "../Before-After-Python/After";
		String additionalFilePath = ".//example_expr.spy";
		File folder = new File(folderPath);
		processFiles(folder, additionalFilePath);
	}
}
