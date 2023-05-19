package org.example.ch3.memoize.test;

import org.example.ch3.memoize.ListLexer;
import org.example.ch3.memoize.MemoizeParser;

public class MemoizeParserTest {
    public static void main(String[] args) {
        String input="[a,v,c]=[q,w,e]";
        ListLexer lexer = new ListLexer(input);
        MemoizeParser memoizeParser = new MemoizeParser(lexer);
        memoizeParser.stat();
    }
}
