package org.example.ch4.homo.test;

import org.example.ch4.homo.HomoAST;
import org.example.ch4.homo.Token;

public class HomoASTTest {


    public static void main(String[] args) {

        Token plus = new Token(Token.PLUS,"+");
        Token one = new Token(Token.INT,"1");
        Token two = new Token(Token.INT,"2");
        HomoAST root = new HomoAST(plus);
        root.addChild(new HomoAST(one));
        root.addChild(new HomoAST(two));
        System.out.println("1+2 tree: "+root.toStringTree());

    }
}
