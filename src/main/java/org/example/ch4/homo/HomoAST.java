package org.example.ch4.homo;


import java.util.ArrayList;
import java.util.List;

public class HomoAST {
    private Token token;
    private List<HomoAST> children;

    public HomoAST(Token token) {
        this.token = token;
    }

    public void  addChild(HomoAST child) {
        if (children==null){
            children=new ArrayList<>();
        }
        children.add(child);
    }

    public boolean isNil() { return token==null; }

    public String toString() { return token!=null?token.toString():"nil"; }

    public String toStringTree() {
        if ( children==null || children.size()==0 ) return this.toString();
        StringBuilder buf = new StringBuilder();
        if ( !isNil() ) {
            buf.append("(");
            buf.append(this.toString());
            buf.append(' ');
        }
        for (int i = 0; i < children.size(); i++) {
            HomoAST t = children.get(i); // normalized (unnamed) children
            if ( i>0 ) buf.append(' ');
            buf.append(t.toStringTree());
        }
        if ( !isNil() ) buf.append(")");
        return buf.toString();
    }
}
