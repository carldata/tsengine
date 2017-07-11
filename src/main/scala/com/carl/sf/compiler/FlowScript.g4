grammar FlowScript;

/* Main compilation unit */
compilationUnit
    : moduleDeclaration externalFunDef* functionDefinition EOF
    ;

moduleDeclaration
    : MODULE Identifier
    ;

externalFunDef
    : EXTERNAL DEF Identifier '(' paramList? ')' ':' typeDefinition
    ;

functionDefinition
    : DEF Identifier '(' paramList? ')' ':' typeDefinition '=' expression
    ;

paramList
	: param (',' param)*
    ;

param
	: Identifier ':' typeDefinition
    ;

typeDefinition
    : Identifier
    ;

expression
    : variableExpr
    | funApp
    ;

variableExpr
    : Identifier
    ;

funApp
    : Identifier '(' expressionList? ')'
    ;

expressionList
	: expression (',' expression)*
    ;

// LEXER
DEF : 'def';
EXTERNAL: 'external';
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

