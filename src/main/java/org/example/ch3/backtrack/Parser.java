package org.example.ch3.backtrack;

import org.example.ch3.MismatchedTokenException;

import java.util.ArrayList;
import java.util.List;

public abstract class Parser {
    protected final Lexer lexer;
    // 栈,存放用于记录未知的位标
    private final List<Integer> markers;
    // 大小可变的缓冲区
    private final List<Token> lookahead;
    // 当前向前看词法单元的下标
    int p = 0;

    protected Parser(Lexer lexer) {
        this.lexer = lexer;
        this.markers = new ArrayList<>();
        this.lookahead = new ArrayList<>();
    }

    protected boolean isSpeculating() {
        return !markers.isEmpty();
    }

    protected void seek(int index) {
        p = index;
    }

    protected void release() {
        int marker = markers.get(markers.size() - 1);
        markers.remove(markers.size() - 1);
        seek(marker);
    }

    protected int mark() {
        markers.add(p);
        return p;
    }

    protected void consume() {
        p++;
        // 非推断状态,而且到达向前看缓冲区的末尾
        if (p == lookahead.size() && !isSpeculating()) {
            // 到了末尾,重新从0开始填入新的词法单元
            p = 0;
            // 大小清0,但不回收内存
            lookahead.clear();
        }
        // 取一个新词法单元
        sync(1);
    }

    protected void fill(int n) {
        for (int i = 0; i < n; i++) {
            Token token = lexer.nextToken();
            lookahead.add(token);
        }
    }

    protected void sync(int i) {
        if ((p + i - 1) > (lookahead.size() - 1)) {
            int n = (p + i - 1) - (lookahead.size() - 1);
            fill(n);
        }
    }

    protected Token LT(int i) {
        sync(i);
        Token token = lookahead.get(p + i - 1);
        return token;
    }

    protected int LA(int i) {
        Token token = LT(i);
        return token.getType();
    }

    protected void match(int type) throws MismatchedTokenException {
        if ((LA(1) == type)) {
            consume();
        } else {
            throw new MismatchedTokenException(String.format("expecting %s, but found %s", lexer.getTokenName(type), lexer.getTokenName(LA(1))));
        }
    }


}
