package org.example.demo.llk.test;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.StopWatch;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import org.example.demo.llk.AbstractLexer;
import org.example.demo.llk.AbstractParser;
import org.example.demo.llk.JsonLexer;
import org.example.demo.llk.JsonParser;
import org.example.demo.llk.ir.ASTree;

import java.util.concurrent.TimeUnit;

public class JsonParserTest {
    public static void main(String[] args) {

//        extracted();
        String jsonStr = FileUtil.readString("D:\\company\\jsonFile.txt", "utf-8");
        StopWatch stopWatch = StopWatch.create(Thread.currentThread().getName());
        stopWatch.start();
        for (int i = 0; i < 1000000; i++) {
//            JSONArray objects = JSON.parseArray(jsonStr);   //1000000 3秒574毫秒
            myParser(jsonStr);    //1000000 1秒291毫秒
        }
        stopWatch.stop();
        String formatted = DateUtil.formatBetween(stopWatch.getTotal(TimeUnit.MILLISECONDS));
        System.out.println(formatted);


    }

    private static void myParser(String jsonStr) {
        AbstractLexer lexer = new JsonLexer(jsonStr);
        AbstractParser<ASTree> parser=new JsonParser(new JsonLexer(jsonStr),3);
        parser.parse();
        ASTree ast = parser.AST();
        ast.toJSONArray();
    }

    private static void testDemo() {
        String jsonStr = FileUtil.readString("D:\\company\\jsonFile.txt", "utf-8");
        AbstractLexer lexer = new JsonLexer(jsonStr);
        AbstractParser<ASTree> parser=new JsonParser(new JsonLexer(jsonStr),3);
        parser.parse();
        ASTree ast = parser.AST();
        System.out.println(ast.toString());

        if (ast.isJSONArray()) {
            System.out.println("JSON Array");
            System.out.println(ast.toJSONArray());
        }

        if (ast.isJSONObject()){
            System.out.println("JSON Object");
            System.out.println(ast.toJSONObject());
        }
    }
}
