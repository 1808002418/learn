package org.example.ch3.memoize;

public abstract class Lexer {
    protected static final char EOF=(char)-1;
    protected static final int EOF_TYPE=1;

    private String input;
    protected int p=0;
    protected char c;

    protected Lexer(String input) {
        this.input = input;
        this.c=input.charAt(p);
    }

    public void consume(){
        p++;
        if (p>=input.length()){
            c=EOF;
        }else {
            c=input.charAt(p);
        }
    }

    public void match(char x){
        if (c==x){
            consume();
        }else {
            throw new  Error("expecting "+x+"; found "+c);
        }
    }

    public abstract Token nextToken();

    /**
     * 标记终结的token
     */
    public abstract Token terminalToken();
    public abstract String getTokenName(int tokenType);
}
