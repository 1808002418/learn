package org.example.ch2;

public class ListParser extends Parser {
    public ListParser(Lexer lexer) {
        super(lexer);
    }

    public void list() {
        match(ListLexer.LBRACK);
        elements();
        match(ListLexer.RBRACK);
    }

    public void elements() {
        element();
        while (lookahead.getType() == ListLexer.COMMA) {
            match(ListLexer.COMMA);
            element();
        }
    }

    public void element() {
        if (lookahead.getType() == ListLexer.LBRACK) {
            list();
        } else if (lookahead.getType() == ListLexer.NAME) {
            match(ListLexer.NAME);
        } else {
            throw new Error("expecting element or list; but found " + lookahead.getText());
        }
    }

}
