package org.example.demo.llk.test;

import cn.hutool.core.io.FileUtil;
import org.example.demo.llk.AbstractLexer;
import org.example.demo.llk.JsonLexer;
import org.example.demo.llk.Token;
import org.example.demo.llk.TokenType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonLexerTest {
    public static void main(String[] args) {
        Pattern compile = Pattern.compile("(?!\\b(0{6}|00{5})\\b)[1-9]\\d{5}(?:(19\\d{2})|(20(?:0[0-4]|[1-2]\\d)))(?:(?:0[13578]|1[02])(?:0[1-9]|[1-2]\\d|3[01])|(?:0[469]|11)(?:0[1-9]|[1-2]\\d|30)|02(?:0[1-9]|1\\d|2[0-8]))\\d{3}[\\dXx]");

        Matcher matcher = compile.matcher("结束时间:2023-06-01,轨迹服务Id:1889807375639d9d017568c24ef40000,ac8181036b88bb4e016bbc36b8040184,360481198812014060ac8181036b88bb4e016bbc36b8040182,ac8181036b88bb4e016bbc36b8040181,188980b4724aab9b01724eae36e8000d,开始时间:2022-06-01,关键字:360481198812014060");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
/*        String jsonStr = FileUtil.readString("D:\\company\\jsonFile.txt", "utf-8");
        AbstractLexer lexer = new JsonLexer(jsonStr);
        while (true){
            Token token = lexer.nextToken();
            System.out.println(token);
            if (token.getType()== TokenType.EOF){
                return;
            }
        }*/
    }
}
