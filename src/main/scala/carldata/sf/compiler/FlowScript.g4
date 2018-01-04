grammar FlowScript;

/* Main compilation unit */
compilationUnit
    : externalFunDef* functionDefinition* EOF
    ;

externalFunDef
    : EXTERNAL DEF Identifier '(' paramList? ')' ':' typeDefinition
    ;

functionDefinition
    : DEF Identifier '(' paramList? ')' ':' typeDefinition '=' functionBody
    ;

paramList
	: param (',' param)*
    ;

param
	: Identifier ':' typeDefinition
    ;

typeDefinition
    : Identifier | (typeList '=>' Identifier)
    ;

typeList
	: Identifier (',' Identifier)*
    ;

functionBody
    : 'let' assignment* 'in' expression
    | expression
    ;

assignment
    : Identifier '=' expression
    ;

expression
    : IF ifExpr=expression THEN expression ELSE expression
    | '(' expression ')'
    | minusOp=MINUS expression
    | expression MultiplyOp expression
    | expression addOp=(PLUS | MINUS) expression
    | expression RelationOp expression
    | negOp=NEG expression
    | expression boolOp=(AND | OR) expression
    | nullLiteral
    | stringLiteral
    | numberLiteral
    | variableExpr
    | funApp
    ;

nullLiteral
    : NULL
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
PLUS: '+';
MINUS: '-';
AND: '&&';
OR: '||';
NEG: '!';
IF: 'if';
THEN: 'then';
ELSE: 'else';
NULL: 'NULL';

// Whitespace and comments
WS  :  [ \t\r\n\u000C]+ -> skip;
LINE_COMMENT:   '//' ~[\r\n]* -> skip;

Identifier
	: Letter LetterOrDigit*
    ;

QuotedString
    : '\'' (~('\n'|'\r'|'\''))* '\''
    | '"' ('\\"' | ~('\n'|'\r'|'"'))* '"'
    ;

Integer
    : Digit+
    ;

AddOp
    : '+' | '-'
    ;

MultiplyOp
    : '*' | '/'
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

