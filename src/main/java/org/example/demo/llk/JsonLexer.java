package org.example.demo.llk;

/**
 * 对象 词法解析器
 */
public class JsonLexer extends LexerHelper {


    public JsonLexer(String input) {
        super(input);
    }


    @Override
    public Token nextToken() {
        while (c != TokenType.EOF) {
            switch (c) {
                case '{':
                    consume();
                    return new Token(TokenType.LBRACE, "{");
                case '}':
                    consume();
                    return new Token(TokenType.RBRACE, "}");
                case '[':
                    consume();
                    return new Token(TokenType.LBRACK, "[");
                case ']':
                    consume();
                    return new Token(TokenType.RBRACK, "]");
                case ',':
                    consume();
                    return new Token(TokenType.COMMA, ",");
                case ':':
                    consume();
                    return new Token(TokenType.COLON, ":");
                case '"':
                    consume();
                    if ((c == '{' || c == '[') && peekChar(p + 1) == '\\') {
                        if (c == '{') {
                            consume();
                            consume();
                            return new Token(TokenType.LBRACE, "{");
                        } else {
                            consume();
                            consume();
                            return new Token(TokenType.RBRACK, "[");
                        }
                    }
                    if (c == ',' && peekChar(p - 1) == '"' && peekChar(p + 1) == '"') {
                        continue;
                    }
                    Token token = getWords();
                    if (token == null) {
                        continue;
                    }
                    return token;
                default:
                    if (isWhitespace()) {
                        consume();
                    } else if (isNumber()) {
                        return getNumbers();
                    } else if (isLogical()) {
                        return getLogical();
                    } else if (isNull()) {
                        return getNull();
                    } else if (isEscape()) {
                        consume();
                        break;
                    } else {
                        throw new Error("Object parse error. index: " + p + ",invalid character: " + c);
                    }
            }
        }
        return terminalToken();
    }
}
