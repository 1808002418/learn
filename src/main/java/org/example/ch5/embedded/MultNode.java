package org.example.ch5.embedded;

/***
 * Excerpted from "Language Implementation Patterns",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/tpdsl for more book information.
***/
public class MultNode extends ExprNode {
    ExprNode left, right; // named, node-specific, irregular children
    public MultNode(ExprNode left, Token t, ExprNode right) {
        super(t);
        this.left = left;
        this.right = right;
    }
    public void print() {
        left.print();
        System.out.print("*");
        right.print();
    }
}
