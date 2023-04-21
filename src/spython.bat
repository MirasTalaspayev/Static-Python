@echo off

REM Check if filename argument is provided
if "%1"=="" (
  echo Error: No filename argument provided
  exit /b 1
)

REM Compile JFlex scanner
jflex scanner.jflex

echo "hui"
REM Generate JavaCUP parser
cup parser.cup

REM Compile Java source files
javac *.java

REM Run Spy interpreter with input file
java -cp . Main %1