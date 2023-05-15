package org.example.ch2.LLK;

public class Token {
    private final int type;
    private final String text;
    private final Lexer lexer;

    public Token(int type, String text, Lexer lexer) {
        this.type = type;
        this.text = text;
        this.lexer = lexer;
    }

    @Override
    public String toString() {
        return String.format("<%s,%s>", lexer.getTokenName(type), text);
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
