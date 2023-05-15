package org.example.ch2.LLK;

public class ListParser extends Parser{
    public ListParser(Lexer lexer, int k) {
        super(lexer, k);
    }

    public void list(){
        match(ListLexer.LBRACK);
        elements();
        match(ListLexer.RBRACK);
    }
    public void elements(){
        element();
        while (LA(1) == ListLexer.COMMA) {
            match(ListLexer.COMMA);
            element();
        }
    }

    public void element(){
        switch (LA(1)){
            case ListLexer.LBRACK:
                list();
                break;
            case ListLexer.NAME:
                // 但这种switch的写法不符合实际的文法推理过程 回溯
                // if的写法就很符合. 如果这个判断推演结果不一致就回溯到开头重新匹配
                match(ListLexer.NAME);
                if (LA(1)==ListLexer.EQUALS){
                    match(ListLexer.EQUALS);
                    match(ListLexer.NAME);
                }
                break;
            default:
                throw new Error(String.format("expecting name or list, but found %s",LT(1).getText()));
        }
/*        if (LA(1) == ListLexer.LBRACK) {
            list();
        }else if (LA(1) == ListLexer.NAME &&LA(2) == ListLexer.EQUALS) {
            match(ListLexer.NAME);
            match(ListLexer.EQUALS);
            match(ListLexer.NAME);
        }else if (LA(1)==ListLexer.NAME){
            match(ListLexer.NAME);
        }else {
            throw new Error(String.format("expecting name or list, but found %s",LT(1).getText()));
        }*/
    }
}
