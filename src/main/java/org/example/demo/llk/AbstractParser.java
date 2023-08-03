package org.example.demo.llk;

public abstract class AbstractParser<T> {
    private final AbstractLexer lexer;
    private final Token[] lookahead;
    // 向前看符号词法个数
    private final int k;
    private int p=0;

    protected AbstractParser(AbstractLexer lexer, int k) {
        this.lexer = lexer;
        this.k = k;
        this.lookahead=new Token[k];
        for (int i = 0; i < k; i++) {
            consume();
        }
    }

    protected void consume(){
        lookahead[p]=lexer.nextToken();
        p=(p+1)%k;
    }

    /**
     * LT (Lexical Analyzer with Backtracking)是一个使用回溯技术的词法分析器。词法分析器用于将输入源代码分解成单个令牌（Token），而LT是一种特殊类型的词法分析器，可以在遇到模棱两可的情况时回溯并尝试使用其他规则重新解析输入。
     * 回溯是指在分析过程中，如果当前分析路径无法匹配下一个符号，则会返回之前进行的分析并选择另一条路径。这使得编写词法分析器变得更加复杂，但它可以处理包含模糊单词或词法歧义的语言。
     * 相比于传统的有限自动机和正则表达式等方式，LT词法分析器需要更多的计算资源和时间来完成分析工作。 因此，除非分析文本包含复杂的词法结构，否则通常不建议使用进行回溯的LT词法分析器。
     * 环式取值
     * @param i
     * @return
     */
    protected Token LT(int i){
        return lookahead[(p+i-1)%k];
    }

    /**
     * 在编译原理中，LA和LL是与语法分析相关的两个概念。它们分别代表"Look-Ahead"和"Left-to-Right, Leftmost derivation"。
     * Look-Ahead (LA)
     * LA（前瞻）是指在进行语法分析时，向输入流中"前看"一个或多个单词来确定接下来要采取的操作。例如，当使用递归下降分析器时，可能需要进行一些错误恢复操作。这时就需要根据输入流的一些特征来预测将出现的单词序列，并做出相应的操作。LA就是指向输入流中"前看"的单词数量。
     * Left-to-Right, Leftmost derivation (LL)
     * 总之，LA和LL都是跟语法分析相关的概念。LA用于指定在语法分析过程中读取输入流的前半部分，LL 则指定了语法规则的左推导顺序。     * LL（从左到右、从左侧推导）是指一种自顶向下语法分析方法，也被称为LL分析器。在LL分析器中，使用左到右扫描输入流，并尝试寻找最左侧的导出路径以匹配输入符号串。因此，LL分析器需要在识别语法构造时做出选择，并且不能回溯。 LL分析器通常使用LL表（一种预测分析表）来确定哪个选项适用于给定的输入符号串。
     * 总之，LA和LL都是跟语法分析相关的概念。LA用于指定在语法分析过程中读取输入流的前半部分，LL 则指定了语法规则的左推导顺序。
     */
    protected int LA(int i){
        return LT(i).getType();
    }

    protected Token match(int type){
        Token token = LT(1);
        if (token.getType()==type){
            consume();
            return token;
        }else {
            throw new Error(String.format("expecting %s,but found %s", lexer.getTokenName(type),lexer.getTokenName(LA(1))));
        }
    }

    public abstract void parse();


    public abstract T AST();


}
