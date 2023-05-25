package org.example.ch3.backtrack.test;

import org.example.ch3.backtrack.ListLexer;
import org.example.ch3.backtrack.BacktrackParser;

public class BacktrackParserTest {
    public static void main(String[] args) {
        String input="[a,v,c]=[q,w,e]";
        ListLexer lexer = new ListLexer(input);
        BacktrackParser parser = new BacktrackParser(lexer);
        parser.stat();

    }
}
