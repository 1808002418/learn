package org.example.ch2.LL1;

public abstract class Parser {
    private Lexer lexer;
    protected Token lookahead;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.lookahead=lexer.nextToken();
    }

    protected void consume() {
        lookahead = lexer.nextToken();
    }

    protected void match(int x) {
        if (lookahead.getType() == x) {
            consume();
        } else {
            throw new Error(String.format("expecting %s; found %s", lexer.getTokenName(x), lookahead.getText()));
        }
    }
}
