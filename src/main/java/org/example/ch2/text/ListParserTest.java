package org.example.ch2.text;

import org.example.ch2.ListLexer;
import org.example.ch2.ListParser;

public class ListParserTest {
    public static void main(String[] args) {
        String input = "[a,b,c,[a,b,c]]";
        ListLexer lexer = new ListLexer(input);
        ListParser listParser = new ListParser(lexer);
        listParser.list();
    }
}
