grammar FlowScript;

/* Main compilation unit */
compilationUnit
    : moduleDeclaration functionDefinition EOF
    ;

moduleDeclaration
    : MODULE Identifier
    ;

functionDefinition
    : DEF Identifier '(' paramList? ')' '=' expression
    ;

paramList
	: param (',' param)*
    ;

param
	: Identifier
    ;

expression
    : variableExpr
    ;

variableExpr
    : Identifier
    ;

// LEXER
DEF : 'def';
MODULE : 'module';

// Whitespace and comments
WS  :  [ \t\r\n\u000C]+ -> skip;
LINE_COMMENT:   '//' ~[\r\n]* -> skip;

Identifier
	: Letter LetterOrDigit*
    ;

fragment
Letter
	: [a-zA-Z$_] // these are the allowed characters
	;

fragment
LetterOrDigit
	: [a-zA-Z0-9$_] // these are the allowed characters or digits
    ;

