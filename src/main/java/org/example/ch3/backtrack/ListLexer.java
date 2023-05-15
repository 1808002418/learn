package org.example.ch3.backtrack;

/**
 * list 词法解析器
 */
public class ListLexer extends Lexer {
    public static final int NAME = 2;
    public static final int COMMA = 3;
    public static final int LBRACK = 4;
    public static final int RBRACK = 5;
    public static final int EQUALS = 6;
    public static final String[] tokenNames = {
            "n/a",
            "<EOF>",
            "NAME",
            "COMMA",
            "LBRACK",
            "RBRACK",
            "EQUALS"
    };

    private Token terminalToken;

    public ListLexer(String input) {
        super(input);
    }

    private boolean isLetter() {
        return c >= 'a' && c <= 'z' ||
                c >= 'A' && c <= 'Z';
    }

    private boolean isWhitespace() {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    private Token getWord() {
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(c);
            consume();
        } while (isLetter());
        return new Token(NAME, sb.toString(), this);
    }

    @Override
    public Token nextToken() {
        while (c != EOF) {
            switch (c) {
                case '[':
                    consume();
                    return new Token(LBRACK, "[", this);
                case ']':
                    consume();
                    return new Token(RBRACK, "]", this);
                case ',':
                    consume();
                    return new Token(COMMA, ",", this);
                case '=':
                    consume();
                    return new Token(EQUALS,"=",this);
                default:
                    if (isWhitespace()) {
                        consume();
                    } else if (isLetter()) {
                        return getWord();
                    } else {
                        throw new Error("invalid character: " + c);
                    }
            }
        }
        return terminalToken();
    }

    @Override
    public Token terminalToken() {
        if (terminalToken == null) {
            terminalToken = new Token(EOF_TYPE, getTokenName(EOF_TYPE), this);
        }
        return terminalToken;
    }

    @Override
    public String getTokenName(int tokenType) {
        return tokenNames[tokenType];
    }
}
