package org.example.ch2.LLK.test;


import org.example.ch2.LLK.ListLexer;
import org.example.ch2.LLK.ListParser;

public class ListParserTest {
    public static void main(String[] args) {
        String input="[a,b,a=c]";
        ListLexer lexer = new ListLexer(input);
        ListParser parser = new ListParser(lexer, 3);
        parser.list();

        System.out.println((char) 0xFFFF);
        System.out.println((char) 0x0000);
        System.out.println(Integer.toHexString(','));
    }
}
