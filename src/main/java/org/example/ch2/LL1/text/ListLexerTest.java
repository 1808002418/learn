package org.example.ch2.LL1.text;

import org.example.ch2.LL1.ListLexer;
import org.example.ch2.LL1.Token;

public class ListLexerTest {
    public static void main(String[] args) {
        String input = "[a,b,]";
        ListLexer listLexer = new ListLexer(input);
        while (true) {
            Token token = listLexer.nextToken();
            System.out.println(token);
            if (token.equals(listLexer.terminalToken())) {
                break;
            }
        }
    }
}
