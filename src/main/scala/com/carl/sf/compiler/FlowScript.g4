grammar FlowScript;

/* Main compilation unit */
compilationUnit
    : moduleDeclaration externalFunDef* functionDefinition* EOF
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
    : expression BinaryOp expression
    | expression RelationOp expression
    | boolLiteral
    | stringLiteral
    | numberLiteral
    | variableExpr
    | funApp
    ;

boolLiteral
    : TRUE
    | FALSE
    ;

stringLiteral
    : QuotedString
    ;

numberLiteral
    : Integer ('.' Integer)?
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
TRUE: 'True';
FALSE: 'False';

// Whitespace and comments
WS  :  [ \t\r\n\u000C]+ -> skip;
LINE_COMMENT:   '//' ~[\r\n]* -> skip;

Identifier
	: Letter LetterOrDigit*
    ;

QuotedString
    : '\'' (~('\n'|'\r'))* '\''
    | '"' ('\\"' | ~('\n'|'\r'))* '"'
    ;

Integer
    : Digit+
    ;

BinaryOp
    : '+' | '-' | '*' | '/'
    ;

RelationOp
    : '==' | '>' | '<' | '>=' | '<=' | '!='
    ;

fragment
Letter
	: [a-zA-Z$_] // these are the allowed characters
	;

fragment
LetterOrDigit
	: [a-zA-Z0-9$_] // these are the allowed characters or digits
    ;

fragment
Digit
    : ('0'..'9')
    ;

