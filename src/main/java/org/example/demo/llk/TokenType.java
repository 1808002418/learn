package org.example.demo.llk;

import cn.hutool.core.lang.Assert;

public class TokenType {

    private TokenType() {
    }

    public static final int NA = 150;
    public static final int COMMA = 151;
    public static final int COLON = 152;
    public static final int DQMARK = 153;
    public static final int SQMARK = 154;
    public static final int LBRACK = 155;
    public static final int RBRACK = 156;
    public static final int LBRACE = 157;
    public static final int RBRACE = 158;

    public static final int NUMBER = 159;
    public static final int CHARACTER = 160;
    public static final int LOGICAL = 161;
    public static final int NULL = 162;

    public static final int JSON = 163;
    public static final int ARRAY = 164;
    public static final int VIRTUAL = 165;
    public static final int EOF = 166;


    protected static final String[] tokenNames = {
            "N/A",  // 占位符
            "COMMA",  // ,
            "COLON",  // :
            "DQMARK", // "
            "SQMARK", // '
            "LBRACK", // [
            "RBRACK",
            "LBRACE", // {
            "RBRACE",

            "NUMBER",  // 数值
            "CHARACTER",  // 字符串
            "LOGICAL",
            "NULL",

            "JSON",
            "ARRAY",
            "VIRTUAL",
            "EOF",
    };

    public static final Token NA_TOKEN = new Token(NA,getTokenName(NA));
    public static final Token EOF_TOKEN = new Token(EOF,getTokenName(EOF));
    public static final Token JSON_TOKEN = new Token(JSON,getTokenName(JSON));
    public static final Token ARRAY_TOKEN = new Token(ARRAY,getTokenName(ARRAY));
    public static final Token VIRTUAL_TOKEN = new Token(VIRTUAL,getTokenName(VIRTUAL));
    public static final Token COLON_TOKEN = new Token(COLON,getTokenName(COLON));

    public static String getTokenName(int type){
        Assert.checkBetween(type,NA,EOF,"指定类型未定义");
        return tokenNames[type-NA];
    }


}
