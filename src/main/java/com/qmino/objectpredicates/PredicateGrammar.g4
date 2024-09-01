grammar PredicateGrammar;

@header {
package com.qmino.objectpredicates.antlr;
}

start :
    expression EOF
;

expression:
    dateOperatorExpression
    | numberOperatorExpression
    | stringOperatorExpression
    | methodBooleanExpression
    | doubleMethodOperatorExpression
    | numberInExpression
    | stringInExpression
    | bracketExpression
    | andExpression
    | orExpression
    | notExpression
    ;

numberInExpression:
    WS? METHOD WS? IN WS? '{' WS? NUMBER (WS? ',' WS? NUMBER)* '}' WS?
    ;

stringInExpression:
    WS? METHOD WS? IN WS? '{' WS? STRING (WS? ',' WS? STRING)* '}' WS?
    ;

dateOperatorExpression:
    WS? DATE WS? OPERATOR WS? DATE WS?
    | WS? DATE WS? OPERATOR WS? METHOD WS?
    | WS? METHOD WS? OPERATOR WS? DATE WS?
    | WS? DATE WS? OPERATOR WS? NULLVALUE WS?
    | WS? NULLVALUE WS? OPERATOR WS? DATE WS?
    ;
numberOperatorExpression:
    WS? NUMBER WS? OPERATOR WS? NUMBER WS?
    | WS? NUMBER WS? OPERATOR WS? METHOD WS?
    | WS? METHOD WS? OPERATOR WS? NUMBER WS?
    | WS? NUMBER WS? OPERATOR WS? NULLVALUE WS?
    | WS? NULLVALUE WS? OPERATOR WS? NUMBER WS?
    ;
stringOperatorExpression:
    WS? STRING WS? OPERATOR WS? STRING WS?
    | WS? STRING WS? OPERATOR WS? METHOD WS?
    | WS? METHOD WS? OPERATOR WS? STRING WS?
    | WS? STRING WS? OPERATOR WS? NULLVALUE WS?
    | WS? NULLVALUE WS? OPERATOR WS? STRING WS?
    ;
doubleMethodOperatorExpression:
    WS? METHOD WS? OPERATOR WS? METHOD WS?
    | WS? METHOD WS? OPERATOR WS? NULLVALUE WS?
    | WS? NULLVALUE WS? OPERATOR WS? METHOD WS?
    ;
methodBooleanExpression:
    WS? METHOD WS? OPERATOR WS? BOOLEAN WS?
    | WS? BOOLEAN WS? OPERATOR WS? METHOD WS?
    ;

bracketExpression:
    '(' WS? expression WS? ')'
    ;
andExpression
    :
    AND WS? '(' expression (',' expression)* WS? ')'
    ;
orExpression
    :
    OR WS? '(' expression (',' expression)* WS? ')'
    ;
notExpression
    :
    NOT WS? expression
    ;

WS: (' ' | '\t' | '\r' | '\n')+ -> skip;
AND: 'AND' | 'and';
OR: 'OR' | 'or';
NOT: 'NOT' | 'not' | '!';
IN: 'IN' | 'in';

OPERATOR: '==' | '!=' | '>' | '<' | '>=' | '<=' | 'ILIKE' | 'ilike' | 'LIKE' | 'like';

NULLVALUE: 'null' | 'NULL';
BOOLEAN: 'true' | 'false' | 'TRUE' | 'FALSE';
METHOD: [a-zA-Z_][a-zA-Z0-9_.]*;
NUMBER: [0-9.]+;
STRING: '"' [a-zA-Z0-9%]+ '"';
DATE: [0-9][0-9][0-9][0-9][-][0-9][0-9][-][0-9][0-9]('T'[0-9][0-9]':'[0-9][0-9]':'[0-9][0-9])?;
