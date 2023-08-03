package org.example.demo.llk;

public class Token {
    private final int type;
    private final String text;

    public Token(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return String.format("<%s\t%s>", TokenType.getTokenName(type), text);
    }
}
