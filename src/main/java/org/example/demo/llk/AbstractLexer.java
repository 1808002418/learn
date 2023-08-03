package org.example.demo.llk;

public abstract class AbstractLexer {
    private final String input;
    protected int p = 0;
    protected char c;

    protected AbstractLexer(String input) {
        this.input = input;
        this.c = input.charAt(p);
    }

    public void consume() {
        p++;
        if (p >= input.length()) {
            c = TokenType.EOF;
        } else {
            c = input.charAt(p);
        }
    }

    public void match(char x) {
        if (c == x) {
            consume();
        } else {
            throw new Error("expecting " + x + "; found " + c);
        }
    }

    public String getTokenName(int tokenType) {
        return TokenType.getTokenName(tokenType);
    }

    public Token terminalToken() {
        return TokenType.EOF_TOKEN;
    }

    /**
     * 输入的子串. 不修改指针
     */
    public String peekStr(int start, int end) {
        if (start < 0) {
            return TokenType.getTokenName(TokenType.NA);
        }
        if (end >= input.length()) {
            return TokenType.getTokenName(TokenType.EOF);
        }
        return input.substring(start, end);
    }

    public char peekChar(int start){
        if (start<input.length()){
            return input.charAt(start);
        }else {
            return TokenType.EOF;
        }
    }


    public abstract Token nextToken();

}
