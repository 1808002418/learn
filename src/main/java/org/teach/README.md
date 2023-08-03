# 编程语言实现模式
## 基本解析模式
    - 从文法到递归下降识别器    parsing/topdown
    - LL1递归下降词法解释器     parsing/lexer
    - LL1递归下降语法解释器     parsing/recursive-descent
    - LLK递归下降语法解释器     parsing/multi
## 高级解析模式
    - 回溯解释器               parsing/backtrack
    - 记忆解释器               parsing/memoize
    - 谓词解释器            

## 从语法树构建中间表示
    - 解析树                   IR/ParseTree.java
    - 同型AST                  IR/Homo/
    - 规范化异型AST             IR/Normalized/
    - 不规则异型AST             IR/Hetero
## 遍历并改写树型结构
    - 内嵌式遍历器              walking/embedded
    - 外部访问者                walking/visitor
    - 树文法                    walking/tree-grammar
    - 模式匹配器                walking/pattern 
## 记录并识别程序中的符号
    - 单作用符号表              symtab/monolithic
    - 嵌套作用域符号表           symtab/nested
## 管理数据聚集的符号表
    - 数据聚集的符号表           symtab/aggr
    - 类的符号表                 symtab/class
## 静态类型检查
    - 计算表达式类型             semantics/types
    - 自动类型提升               semantics/promote
    - 检查类型安全               semantics/safety
    - 多态类型检查               semantics/oo
## 构建高级解释器
    - 语法指导解释器             interp/syntax
    - 基于树的解释器             interp/tree
## 构建字节码解释器
    - 字节码汇编器               interp/asm
    - 栈解释器                   interp/stack
    - 寄存器解释器               interp/reg

## 语言的翻译
    - 语法指导的翻译              trans/make
    - 基于柜子的翻译              trans/wiki   
    - 特定目标的生成类            trans/sql
## 使用模板生成DSL
    - trans/intro                         
    - trans/web
    - trans/ast
    - trans/st
    - trans/sql
