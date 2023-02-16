import java_cup.runtime.*;
// import sym;

%%

%unicode
%cup
%line
%column

%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

WhiteSpace      = [\t\f]
nl		        = \n|\r|\r\n
//intNumber	    = [1-9][0-9]*
//digit         = [0-9]
// floatNumber  = ("+"|"-")? ( [1-9][0-9]* "." [0-9]* ) | ( "." [0-9]+ ) | ( 0 "." [0-9]* )
Number          = [0-9]+
letter          = [a-z|A-Z]
label           = [a-z|A-Z][a-z|A-Z|0-9]*  

%%

//{intNumber}	{System.out.println("NUMBER:"+yytext()); return new Symbol(sym.NUMBER, new Integer(yytext()));}
{Number}        {System.out.println("NUMBER:"+yytext()); return new Symbol(sym.NUMBER, new Integer(yytext()));}
{label}         {System.out.println("LABEL:"+yytext()); return new Symbol(sym.LABEL, new String(yytext()));}

"*"             {System.out.println("TIMES");return new Symbol(sym.TIMES);}
"/"             {System.out.println("DIVIDE");return new Symbol(sym.DIVIDE);}
"%"             {System.out.println("MOD");return new Symbol(sym.MOD);}

"+"		        {System.out.println("PLUS");return new Symbol(sym.PLUS);}
"-"		        {System.out.println("MINUS");return new Symbol(sym.MINUS);}


"("		        {System.out.println("LEFT PAREN");return new Symbol(sym.LPAREN);}
")"		        {System.out.println("RIGHT PAREN");return new Symbol(sym.RPAREN);}

"="		        {System.out.println("EQUAL");return new Symbol(sym.EQUAL);}

":"             {System.out.println("COLON"); return new Symbol(sym.COLON);}
";"             {System.out.println("SEMI"); return new Symbol(sym.SEMI);}

"["             {System.out.println("LEFT BRACKET"); return new Symbol(sym.LBRACKET);}

"]"             {System.out.println("RIGHT BRACKET"); return new Symbol(sym.RBRACKET);}

{nl} 	        {/* ignore */}
{WhiteSpace}    {/* ignore */}
.               {System.out.println("ERROR"); return symbol(sym.ERROR, yytext());}