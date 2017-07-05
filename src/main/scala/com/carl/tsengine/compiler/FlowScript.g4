grammar FlowScript;

// LEXER
DEF : 'def';
MODULE : 'module';
// Whitespace and comments

WS  :  [ \t\r\n\u000C]+ -> skip;
LINE_COMMENT
    :   '//' ~[\r\n]* -> skip
    ;

Identifier
	: Letter LetterOrDigit*
    ;


// Parser
/* Main compilation unit */
compilationUnit
    : moduleDeclaration functionDeclaration EOF
    ;

moduleDeclaration
    : MODULE Identifier
    ;

functionDeclaration
    : DEF Identifier '(' paramList? ')' '=' functionBody
    ;

paramList
	: param (',' param)*
    ;

param
	: Identifier
    ;

functionBody
    : Identifier
    ;

fragment
Letter
	: [a-zA-Z$_] // these are the allowed characters
	;

fragment
LetterOrDigit
	: [a-zA-Z0-9$_] // these are the allowed characters or digits
    ;