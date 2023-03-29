import java_cup.runtime.*;
import java.util.ArrayList;

// import sym;
%%

%{
  int col_num = 0, curr_col = 0;
  boolean at_line_begin = true;

  ArrayList<Symbol> tokenBuffer = new ArrayList<>();

  Symbol addAndReturnNext(int symCategory) {
    return addAndReturnNext(new Symbol(symCategory));
  }

  Symbol addAndReturnNext(int symCategory, Object value) {
    return addAndReturnNext(new Symbol(symCategory, value));
  }

  Symbol addAndReturnNext(Symbol symbol) {
    if (at_line_begin) {
        if (col_num > curr_col) {
            for (int i=curr_col; i < col_num; i++ ) {
              tokenBuffer.add(new Symbol(sym.CI, "END BLOCK"));
            }
            col_num = curr_col;
            System.out.println("ENDING BLOCK");
        } else if (col_num < curr_col) {
            tokenBuffer.add(new Symbol(sym.OI, "BEGIN BLOCK"));
            col_num = curr_col;
            System.out.println("BEGINNING BLOCK");
        }
        at_line_begin = false;
    }
    tokenBuffer.add(symbol);
    return tokenBuffer.remove(0);
  }

    public java_cup.runtime.Symbol next_token() throws java.io.IOException {
        if (tokenBuffer.isEmpty()) {
            return process_next_token();
        }
        return tokenBuffer.remove(0);
    }
%}

%unicode
%line
%column

%implements java_cup.runtime.Scanner
%function process_next_token
%type java_cup.runtime.Symbol
%eofval{
  return new java_cup.runtime.Symbol(sym.EOF);
%eofval}
%eofclose

tab         = \t|"    "
newLine     = \n|\r|\r\n
anyChar     = [^\r\n]
digit       = [0-9]
letter      = [a-z|A-Z]
intNumber  = (-)?[0-9]+|0
floatNumber = ("+"|"-")? ( {intNumber}"." [{digit}]* ) | ( "." [{digit}]+ ) | ( 0 "." [{digit}]* )
label       = {letter}[{letter}|{digit}|_]*  
string      = \"([^\n\r\"\\]|\\[\"\\ntbrf])*\"|\'([^\n\r\'\\]|\\[\'\\ntbrf])*\'

%state IN_COMMENT

%%
<YYINITIAL> {
  {floatNumber}   {System.out.println("FLOAT:"+yytext());return addAndReturnNext(sym.FLOAT, new Float(yytext()));}
  {intNumber}	    {System.out.println("NUMBER:"+yytext());return addAndReturnNext(sym.NUMBER, new Integer(yytext()));}
  "def"           {System.out.println("DEF");return addAndReturnNext(sym.DEF, new String(yytext()));}
  "if"            {System.out.println("IF");return addAndReturnNext(sym.IF, new String(yytext()));}
  "while"         {System.out.println("WHILE");return addAndReturnNext(sym.WHILE, new String(yytext()));}
  "for"           {System.out.println("FOR");return addAndReturnNext(sym.FOR, new String(yytext()));}
  "in"            {System.out.println("IN");return addAndReturnNext(sym.IN, new String(yytext()));}
  "True"          {System.out.println("TRUE");return addAndReturnNext(sym.TRUE, new String(yytext()));}
  "False"         {System.out.println("FALSE");return addAndReturnNext(sym.FALSE, new String(yytext()));}
  "return"        {System.out.println("RETURN");return addAndReturnNext(sym.RETURN, new String(yytext()));}
  "list"          {System.out.println("list");return addAndReturnNext(sym.LIST, new String(yytext()));}
  "set"           {System.out.println("set");return addAndReturnNext(sym.SET, new String(yytext()));}
  "dict"          {System.out.println("dict");return addAndReturnNext(sym.DICT, new String(yytext()));}
  "tuple"         {System.out.println("tuple");return addAndReturnNext(sym.TUPLE, new String(yytext()));}
  {string}        {System.out.println("STRING:"+yytext());return addAndReturnNext(sym.STRING, new String(yytext()));}
  {label}         {System.out.println("LABEL:"+yytext());return addAndReturnNext(sym.LABEL, new String(yytext()));}
  {tab}           {System.out.println("Tab: " +yycolumn);if (at_line_begin) {curr_col += 1;}}

  // {four_spaces}   {System.out.println("Four spaces: " +yycolumn);}

  "+"		          {System.out.println("PLUS");return addAndReturnNext(sym.PLUS, new String(yytext()));}
  "-"		          {System.out.println("MINUS");return addAndReturnNext(sym.MINUS, new String(yytext()));}
  "*"             {System.out.println("TIMES");return addAndReturnNext(sym.TIMES, new String(yytext()));}
  "/"             {System.out.println("DIVIDE");return addAndReturnNext(sym.DIVIDE, new String(yytext()));}
  "%"             {System.out.println("MOD");return addAndReturnNext(sym.MOD, new String(yytext()));}
  "**"            {System.out.println("EXPONENT");return addAndReturnNext(sym.EXPONENT, new String(yytext()));}
  "//"            {System.out.println("FLOOR");return addAndReturnNext(sym.FLOOR, new String(yytext()));}
  "("		          {System.out.println("LPAREN");return addAndReturnNext(sym.LPAREN);}
  ")"		          {System.out.println("RPAREN");return addAndReturnNext(sym.RPAREN);}
  "="		          {System.out.println("EQUAL");return addAndReturnNext(sym.EQUAL);}
  ":"             {System.out.println("COLON"); return addAndReturnNext(sym.COLON);}
  ","             {System.out.println("COMMA"); return addAndReturnNext(sym.COMMA, new String(yytext()));}
  "["             {System.out.println("LBRACK"); return addAndReturnNext(sym.LBRACK);}
  "]"             {System.out.println("RBRACK"); return addAndReturnNext(sym.RBRACK);}
  "{"             {System.out.println("LCURLY"); return addAndReturnNext(sym.LCURLY);}
  "}"             {System.out.println("RCURLY"); return addAndReturnNext(sym.RCURLY);}

  "->"            {System.out.println("ARROW"); return addAndReturnNext(sym.ARROW);}

  "=="            {System.out.println("EQUAL EQUAL"); return addAndReturnNext(sym.EQEQ, new String(yytext()));}
  "!="            {System.out.println("NOT EQUAL"); return addAndReturnNext(sym.NOTEQ, new String(yytext()));}
  "<"		          {System.out.println("LESS");return addAndReturnNext(sym.LESS, new String(yytext()));}
  ">"		          {System.out.println("MORE");return addAndReturnNext(sym.MORE, new String(yytext()));}
  "<="	          {System.out.println("LESS OR EQUAL");return addAndReturnNext(sym.LESSEQ, new String(yytext()));}
  ">="	          {System.out.println("MORE OR EQUAL");return addAndReturnNext(sym.MOREEQ, new String(yytext()));}

  {newLine}       {curr_col = 0;at_line_begin = true;}

  " "             {;}

  // {comment}       {;}
  "#"             {yybegin(IN_COMMENT);}
}
<IN_COMMENT> {
  {anyChar}                 {;}
  {newLine}                 {yybegin(YYINITIAL); curr_col = 0;at_line_begin = true;}
}

.		            {System.out.println("Error:" + yytext());}

// jflex scanner.jflex
// java java_cup.MainDrawTree parser.cup
// javac *.java