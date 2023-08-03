package org.example.demo.llk.ir;


import com.alibaba.fastjson.JSONArray;
import org.example.demo.llk.Token;

public class ASTHelper extends ASTree {

    public ASTHelper() {
        super();
    }

    public ASTHelper(Token token) {
        super(token);
    }

    @Override
    protected void fillJSONArray(JSONArray array) {
        if (array==null){
            return;
        }
        for (ASTree child : children) {
            if (child.isJSONObject()) {
                array.add(child.toJSONObject());
            } else if (child.isJSONArray()) {
                array.add(child.toJSONArray());
            } else {
                array.add(child.getTokenText());
            }
        }
    }
}
