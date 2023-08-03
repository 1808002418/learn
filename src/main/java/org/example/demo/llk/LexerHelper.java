package org.example.demo.llk;

import java.util.Locale;
import java.util.Objects;

public abstract class LexerHelper extends AbstractLexer {
    protected LexerHelper(String input) {
        super(input);
    }

    private boolean compareStr(String s1, int start, int end, boolean ignoreCase) {
        String s2 = peekStr(start, end);
        if (ignoreCase) {
            s2 = s2.toLowerCase(Locale.ROOT);
        }
        return Objects.equals(s1, s2);
    }

    public boolean isEscape(){
        return c=='\\' && (peekChar(p-1)=='{' || peekChar(p-1)=='[' || peekChar(p-1)==','|| peekChar(p-1)==':');
    }

    public boolean isNumber() {
        return c >= '0' && c <= '9' || c=='.';
    }

    public boolean isWhitespace() {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }


    public boolean isNull() {
        return compareStr("null", p, p + 4, true);
    }

    public boolean isLogical() {
        return compareStr("true", p, p + 4, true) ||
                compareStr("false", p, p + 5, true);
    }

    public Token getNumbers() {
        StringBuilder builder = new StringBuilder();
        do {
            builder.append(c);
            consume();
        } while (isNumber());
        return new Token(TokenType.NUMBER, builder.toString());
    }

    public Token getWords() {
        StringBuilder sb = new StringBuilder();
        char pre = c;
        while (c != TokenType.EOF) {
            if (c == '"') {
                if (pre == '\\') {
                    char tempC=c;
                    int tempP=p;
                    consume();
                    while (isWhitespace()){
                        consume();
                    }
                    if (c==':' || c==',' || c=='}'){
                        sb.deleteCharAt(sb.length()-1);
                        break;
                    }
                    c=tempC;
                    p=tempP;
                    sb.append(c);
                    consume();
                    continue;
                } else if (pre == '{') {  // 下面三个处理缺失转义符
                    return new Token(TokenType.LBRACE, "{");
                } else if (pre == '[') {
                    return new Token(TokenType.LBRACK, "[");
                } else if (pre == ',') {
                    String subString = peekStr(p - 3, p - 2);
                    if (sb.toString().trim().length() == 1 &&
                            (Objects.equals(subString, "}") || Objects.equals(subString, "]"))) {
                        return null;
                    }
                } else {
                    consume();
                    break;
                }
            }
            sb.append(c);
            pre = c;
            consume();
        }

        if (pre == '}' && sb.toString().trim().length() == 1) {
            return new Token(TokenType.RBRACE, "}");
        } else if (pre == ']' && sb.toString().trim().length() == 1) {
            return new Token(TokenType.RBRACK, "]");
        } else {
            return new Token(TokenType.CHARACTER, sb.toString());
        }
    }

    public Token getNull() {
        for (int i = 0; i < 4; i++) {
            consume();
        }
        return new Token(TokenType.NULL, null);
    }

    public Token getLogical() {
        String text;
        int size;
        if (c == 't') {
            text = "true";
            size = 4;
        } else {
            text = "false";
            size = 5;
        }
        for (int i = 0; i < size; i++) {
            consume();
        }
        return new Token(TokenType.LOGICAL, text);
    }
}
