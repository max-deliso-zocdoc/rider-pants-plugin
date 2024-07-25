grammar PantsBuild;

buildFile : (statement NEWLINE)* ;

statement
    : functionCall
    | assignment
    ;

functionCall
    : IDENTIFIER LPAREN (argument (COMMA argument)*)? RPAREN
    ;

assignment
    : IDENTIFIER ASSIGN expression
    ;

argument
    : IDENTIFIER ASSIGN expression
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
    : LBRACKET (expression (COMMA expression)*)? RBRACKET
    ;

dict
    : LBRACE (dictEntry (COMMA dictEntry)*)? RBRACE
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

LPAREN
    : '('
    ;

RPAREN
    : ')'
    ;

LBRACKET
    : '['
    ;

RBRACKET
    : ']'
    ;

LBRACE
    : '{'
    ;

RBRACE
    : '}'
    ;

COMMA
    : ','
    ;

COLON
    : ':'
    ;

ASSIGN
    : '='
    ;

NEWLINE
    : '\r'? '\n'
    ;

COMMENT
    : '#' ~[\r\n]* -> channel(HIDDEN)
    ;

WS
    : [ \t\r\n]+ -> channel(HIDDEN)
    ;
