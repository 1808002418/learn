package org.example.ch3.memoize;

import org.example.ch3.MismatchedTokenException;
import org.example.ch3.NoViableAltException;
import org.example.ch3.RecognitionException;

import java.util.HashMap;
import java.util.Map;

/**
 * list 回溯记忆解析器
 */
public class MemoizeParser extends Parser {
    private final Map<Integer, Integer> listMemoize;

    public MemoizeParser(Lexer lexer) {
        super(lexer);
        listMemoize = new HashMap<>();
    }

    @Override
    protected void clearMemize() {
        listMemoize.clear();
    }

    public void stat() throws RecognitionException {
        if (speculateStatAlt1()) {
            list();
            match(Lexer.EOF_TYPE);
        } else if (speculateStatAlt2()) {
            assign();
            match(Lexer.EOF_TYPE);
        } else {
            // 肯定出错了
            // LT(1) 表示第一个向前看符号
            throw new NoViableAltException("excepting stat found " + LT(1));
        }
    }

    private boolean speculateStatAlt1() {
        boolean success = true;
        // 标记当前未知以便放回输入
        mark();
        try {
            list();
            match(Lexer.EOF_TYPE);
        } catch (MismatchedTokenException e) {
            success = false;
        }
        // 不管是否匹配,都要放回输入
        release();
        return success;
    }

    private boolean speculateStatAlt2() {
        boolean success = true;
        // 标记当前未知以便放回输入
        mark();
        try {
            assign();
            match(Lexer.EOF_TYPE);
        } catch (MismatchedTokenException e) {
            success = false;
        }
        // 不管是否匹配,都要放回输入
        release();
        return success;
    }


    private void list() {
        boolean failed = false;
        int startTokenIndex = p;
        if (isSpeculating() && alreadyParserdRule(listMemoize)) {
            return;
        }
        try {
            _list();
        } catch (RecognitionException re) {
            failed = true;
            throw re;
        } finally {
            if (isSpeculating()) {
                memoize(listMemoize, startTokenIndex, failed);
            }
        }
    }

    private void _list() {
        match(ListLexer.LBRACK);
        elements();
        match(ListLexer.RBRACK);
    }

    private void assign() {
        list();
        match(ListLexer.EQUALS);
        list();

    }

    private void elements() {
        element();
        while (LA(1) == ListLexer.COMMA) {
            match(ListLexer.COMMA);
            element();
        }
    }

    private void element() {
        if (LA(1) == ListLexer.LBRACK) {
            list();
        } else if (LA(1) == ListLexer.NAME && LA(2) == ListLexer.EQUALS) {
            match(ListLexer.NAME);
            match(ListLexer.EQUALS);
            match(ListLexer.NAME);
        } else if (LA(1) == ListLexer.NAME) {
            match(ListLexer.NAME);
        } else {
            throw new Error("expecting element or list but found " + lexer.getTokenName(LA(1)));
        }
    }
}
