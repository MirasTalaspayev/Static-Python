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
intNumber	  = [1-9][{digit}]*
floatNumber = ("+"|"-")? ( {intNumber}"." [{digit}]* ) | ( "." [{digit}]+ ) | ( 0 "." [{digit}]* )
label       = {letter}[{letter}|{digit}]*
string      = \'{label}\'
// comment     = "#" {anyChar}* {newLine}?

%state IN_COMMENT

%%

<YYINITIAL> {floatNumber}   {System.out.println("FLOAT:"+yytext());return addAndReturnNext(sym.FLOAT, new Float(yytext()));}
<YYINITIAL> {intNumber}	    {System.out.println("NUMBER:"+yytext());return addAndReturnNext(sym.NUMBER, new Integer(yytext()));}
<YYINITIAL> "def"           {System.out.println("DEF");return addAndReturnNext(sym.DEF, new String(yytext()));}
<YYINITIAL> "if"            {System.out.println("IF");return addAndReturnNext(sym.IF, new String(yytext()));}
<YYINITIAL> "while"         {System.out.println("WHILE");return addAndReturnNext(sym.WHILE, new String(yytext()));}
<YYINITIAL> "for"           {System.out.println("FOR");return addAndReturnNext(sym.FOR, new String(yytext()));}
<YYINITIAL> "in"            {System.out.println("IN");return addAndReturnNext(sym.IN, new String(yytext()));}
<YYINITIAL> "True"          {System.out.println("TRUE");return addAndReturnNext(sym.TRUE, new String(yytext()));}
<YYINITIAL> "False"         {System.out.println("FALSE");return addAndReturnNext(sym.FALSE, new String(yytext()));}
<YYINITIAL> "return"        {System.out.println("RETURN");return addAndReturnNext(sym.RETURN, new String(yytext()));}
<YYINITIAL> "list"          {System.out.println("list");return addAndReturnNext(sym.LIST, new String(yytext()));}
<YYINITIAL> "set"           {System.out.println("set");return addAndReturnNext(sym.SET, new String(yytext()));}
<YYINITIAL> "dict"          {System.out.println("dict");return addAndReturnNext(sym.DICT, new String(yytext()));}
<YYINITIAL> "tuple"         {System.out.println("tuple");return addAndReturnNext(sym.TUPLE, new String(yytext()));}
<YYINITIAL> {string}        {System.out.println("STRING:"+yytext());return addAndReturnNext(sym.STRING, new String(yytext()));}
<YYINITIAL> {label}         {System.out.println("LABEL:"+yytext());return addAndReturnNext(sym.LABEL, new String(yytext()));}
<YYINITIAL> {tab}           {System.out.println("Tab: " +yycolumn);if (at_line_begin) {curr_col += 1;}}

// {four_spaces}   {System.out.println("Four spaces: " +yycolumn);}

<YYINITIAL> "+"		          {System.out.println("PLUS");return addAndReturnNext(sym.PLUS, new String(yytext()));}
<YYINITIAL> "-"		          {System.out.println("MINUS");return addAndReturnNext(sym.MINUS, new String(yytext()));}
<YYINITIAL> "*"             {System.out.println("TIMES");return addAndReturnNext(sym.TIMES, new String(yytext()));}
<YYINITIAL> "/"             {System.out.println("DIVIDE");return addAndReturnNext(sym.DIVIDE, new String(yytext()));}
<YYINITIAL> "%"             {System.out.println("MOD");return addAndReturnNext(sym.MOD, new String(yytext()));}
<YYINITIAL> "**"            {System.out.println("EXPONENT");return addAndReturnNext(sym.EXPONENT, new String(yytext()));}
<YYINITIAL> "//"            {System.out.println("FLOOR");return addAndReturnNext(sym.FLOOR, new String(yytext()));}
<YYINITIAL> "("		          {System.out.println("LPAREN");return addAndReturnNext(sym.LPAREN);}
<YYINITIAL> ")"		          {System.out.println("RPAREN");return addAndReturnNext(sym.RPAREN);}
<YYINITIAL> "="		          {System.out.println("EQUAL");return addAndReturnNext(sym.EQUAL);}
<YYINITIAL> ":"             {System.out.println("COLON"); return addAndReturnNext(sym.COLON);}
<YYINITIAL> ","             {System.out.println("COMMA"); return addAndReturnNext(sym.COMMA, new String(yytext()));}
<YYINITIAL> "["             {System.out.println("LBRACK"); return addAndReturnNext(sym.LBRACK);}
<YYINITIAL> "]"             {System.out.println("RBRACK"); return addAndReturnNext(sym.RBRACK);}
<YYINITIAL> "{"             {System.out.println("LCURLY"); return addAndReturnNext(sym.LCURLY);}
<YYINITIAL> "}"             {System.out.println("RCURLY"); return addAndReturnNext(sym.RCURLY);}

<YYINITIAL> "->"            {System.out.println("ARROW"); return addAndReturnNext(sym.ARROW);}

<YYINITIAL> "=="            {System.out.println("EQUAL EQUAL"); return addAndReturnNext(sym.EQEQ, new String(yytext()));}
<YYINITIAL> "!="            {System.out.println("NOT EQUAL"); return addAndReturnNext(sym.NOTEQ, new String(yytext()));}
<YYINITIAL> "<"		          {System.out.println("LESS");return addAndReturnNext(sym.LESS, new String(yytext()));}
<YYINITIAL> ">"		          {System.out.println("MORE");return addAndReturnNext(sym.MORE, new String(yytext()));}
<YYINITIAL> "<="	          {System.out.println("LESS OR EQUAL");return addAndReturnNext(sym.LESSEQ, new String(yytext()));}
<YYINITIAL> ">="	          {System.out.println("MORE OR EQUAL");return addAndReturnNext(sym.MOREEQ, new String(yytext()));}

<YYINITIAL> {newLine}       {curr_col = 0;at_line_begin = true;}

<YYINITIAL> " "             {;}

// {comment}       {;}
<YYINITIAL> "#"             {yybegin(IN_COMMENT);}
<IN_COMMENT> {
  {anyChar}                 {;}
  {newLine}                 {yybegin(YYINITIAL); curr_col = 0;at_line_begin = true;}
}

.		            {System.out.println("Error:" + yytext());}

// jflex scanner.jflex
// java java_cup.MainDrawTree parser.cup
// javac *.java