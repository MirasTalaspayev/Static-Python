import java_cup.runtime.*;
// import sym;

%%

%unicode
%cup

nl		    =  \n|\r|\r\n
intNumber	=  [1-9][0-9]*
digit       =  [0-9]
// floatNumber   =   ("+"|"-")? ( [1-9][0-9]* "." [0-9]* ) | ( "." [0-9]+ ) | ( 0 "." [0-9]* )
letter      =  [a-z|A-Z]
label       =  [a-z|A-Z][a-z|A-Z|0-9]*  

%%

{intNumber}	{System.out.println("NUMBER:"+yytext());return new Symbol(sym.NUMBER, new Integer(yytext()));}
{label}     {System.out.println("LABEL:"+yytext()); return new Symbol(sym.LABEL, new String(yytext()));}

"*"     {System.out.println("MULT");return new Symbol(sym.MULT);}

"+"		{System.out.println("PLUS");return new Symbol(sym.PLUS);}
"-"		{System.out.println("MINUS");return new Symbol(sym.MINUS);}


"("		{System.out.println("OPEN BRACKET");return new Symbol(sym.OBRACKET);}
")"		{System.out.println("CLOSE BRACKET");return new Symbol(sym.CBRACKET);}

"="		{System.out.println("EQUAL");return new Symbol(sym.EQUAL);}

":"     {System.out.println("COLON"); return new Symbol(sym.COLON);}

"["     {System.out.println("LBRACK"); return new Symbol(sym.LBRACK);}

"]"     {System.out.println("RBRACK"); return new Symbol(sym.RBRACK);}

{nl}|" " 	{;}

.		{System.out.println("Error:" + yytext());}

