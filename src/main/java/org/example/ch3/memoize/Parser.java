package org.example.ch3.memoize;

import org.example.ch3.MismatchedTokenException;
import org.example.ch3.PreviousParseFailedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Parser {
    /**
     * 解析状态失败   -1
     * 未解析        0
     * 解析成功      index
     */
    private static final int FAILED =-1;

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

    /**
     * 在当前的位置解析过这个规则吗? 如果不用重新解析,会将缓冲区的下标向前移动,免去本次解析.
     * 空    没有解析过
     * FAILED   解析失败
     * 大于等于0    这是词法单元缓冲区的下标,表示上次解析成功
     * 方法有副作用
     * @param memoization
     * @return
     * @throws PreviousParseFailedException
     */
    protected boolean alreadyParserdRule(Map<Integer,Integer> memoization) throws PreviousParseFailedException {
        Integer memoize = memoization.get(p);
        if (memoize==null){
            return false;
        }
        if (memoize ==FAILED){
            throw new PreviousParseFailedException();
        }
        // 跳到这里,假装再次解析了
        seek(memoize);
        return true;
    }

    /**
     * 回溯时,记录解析的中间结果
     */
    protected void memoize(Map<Integer,Integer> memoization,int startTokenIndex,boolean failed){
        int stopTokenIndex;
        if (failed){
            stopTokenIndex= FAILED;
        }else {
            stopTokenIndex=p;
        }
        // 记录解析结果. 不管是成功还是失败.
        memoization.put(startTokenIndex,stopTokenIndex);
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

    protected abstract void clearMemize();

    protected void consume() {
        p++;
        // 非推断状态,而且到达向前看缓冲区的末尾
        if (p == lookahead.size() && !isSpeculating()) {
            // 到了末尾,重新从0开始填入新的词法单元
            p = 0;
            // 大小清0,但不回收内存
            lookahead.clear();
            clearMemize();
        }
        // 取一个新词法单元
        sync(1);
    }

    protected void fill(int n) {
        for (int i = 0; i < n; i++) {
            Token token = lexer.nextToken();
            System.out.println(token);
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
        return lookahead.get(p + i - 1);
    }

    protected int LA(int i) {
        Token token = LT(i);
        System.out.println(token);
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
