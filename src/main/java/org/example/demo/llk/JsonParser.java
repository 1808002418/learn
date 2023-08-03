package org.example.demo.llk;


import org.example.demo.llk.ir.*;

public class JsonParser extends AbstractParser<ASTree> {
    private ASTree ast;

    public JsonParser(AbstractLexer lexer, int k) {
        super(lexer, k);
    }

    @Override
    public void parse() {
        switch (LA(1)) {
            case TokenType.LBRACE:
                ast = object();
                break;
            case TokenType.LBRACK:
                ast = array();
                break;
            default:
                throw new Error(String.format("expecting { or [, but found %s", LT(1).getText()));
        }
    }

    @Override
    public ASTree AST() {
        return ast;
    }

    private ASTree object() {

        match(TokenType.LBRACE);
        ASTree tree = new JsonNode();
        attributes(tree);
        match(TokenType.RBRACE);

        return tree;
    }

    private ASTree array() {
        match(TokenType.LBRACK);
        ASTree tree = new ArrayNode();
        elements(tree);
        match(TokenType.RBRACK);
        return tree;
    }

    private void attributes(ASTree tree) {
        ASTree key = attributeName();
        match(TokenType.COLON);
        ASTree value = attributeValue();

        tree.addChild(new KVNode(key,value));
        while (LA(1) == TokenType.COMMA) {
            match(TokenType.COMMA);
            key = attributeName();
            match(TokenType.COLON);
            value = attributeValue();
            tree.addChild(new KVNode(key,value));
        }
    }

    private void elements(ASTree tree) {
        tree.addChild(element());

        while (LA(1) == TokenType.COMMA) {
            match(TokenType.COMMA);
            tree.addChild(element());
        }
    }

    private ASTree attributeName() {
        Token token = match(TokenType.CHARACTER);
        return new LeafNode(token);
    }

    private ASTree attributeValue() {
        return element();
    }

    private ASTree element() {
        // switch执行效率比if高
        Token token;
        switch (LA(1)) {
            case TokenType.LBRACE:
                return object();
            case TokenType.LBRACK:
                return array();
            case TokenType.NUMBER:
                token = match(TokenType.NUMBER);
                break;
            case TokenType.CHARACTER:
                token = match(TokenType.CHARACTER);
                break;
            case TokenType.LOGICAL:
                token = match(TokenType.LOGICAL);
                break;
            case TokenType.NULL:
                token = match(TokenType.NULL);
                break;
            default:
                throw new Error(String.format("expecting item or { or [, but found %s", LT(1).getText()));
        }
        return new LeafNode(token);
    }
}
