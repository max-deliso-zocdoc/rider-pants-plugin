grammar PantsBuild;

buildFile : (statement NEWLINE)* ;

statement
    : functionCall
    | assignment
    ;

functionCall
    : IDENTIFIER '(' (argument (',' argument)*)? ')'
    ;

assignment
    : IDENTIFIER '=' expression
    ;

argument
    : IDENTIFIER '=' expression
    | expression
    ;

expression
    : list
    | dict
    | STRING
    | IDENTIFIER
    | NUMBER
    ;

list
    : '[' (expression (',' expression)*)? ']'
    ;

dict
    : '{' (dictEntry (',' dictEntry)*)? '}'
    ;

dictEntry
    : STRING ':' expression
    ;

IDENTIFIER
    : [a-zA-Z_][a-zA-Z0-9_]*
    ;

NUMBER
    : [0-9]+ ('.' [0-9]+)?
    ;

STRING
    : '"' (~["\\] | '\\' .)* '"'
    | '\'' (~['\\] | '\\' .)* '\''
    ;

NEWLINE
    : '\r'? '\n'
    ;

WS
    : [ \t]+ -> skip
    ;
