parser grammar <%= languageName %>Parser;

options {   tokenVocab = <%= languageName %>Lexer; }

compilationUnit: helloStmt* EOF;

helloStmt: HELLO name=ID;
