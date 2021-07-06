lexer grammar <%= languageName %>Lexer;

// Keywords
HELLO : 'hello';

// Identifiers
ID : [a-zA-Z][_a-zA-Z0-9]*;

// Whitespace
WS : [\r\n\t ] -> channel(HIDDEN);
