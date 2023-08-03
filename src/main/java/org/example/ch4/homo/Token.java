package org.example.ch4.homo;

public class Token {
    public static final int INVALID_TOKEN_TYPE = 0;
    public static final int PLUS = 1; // token types
    public static final int INT = 2;

    private final int type;
    private final String text;

    public Token(int type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
