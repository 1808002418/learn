package org.example.demo.llk.ir;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.demo.llk.Token;
import org.example.demo.llk.TokenType;

import java.util.ArrayList;
import java.util.List;

public class ASTree {
    private Token token;
    protected List<ASTree> children;

    public ASTree() {
        this(TokenType.NA_TOKEN);
    }

    public ASTree(Token token) {
        this.token = token;
    }

    public ASTree addChild(ASTree child) {
        if (children == null) children = new ArrayList<>();
        children.add(child);
        return this;
    }

    public String getTokenText() {
        return token.getText();
    }

    public int getTokenType() {
        return token.getType();
    }

    public boolean isJSONObject() {
        return getTokenType() == TokenType.JSON;
    }

    public boolean isJSONArray() {
        return getTokenType() == TokenType.ARRAY;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        fillJSONObject(jsonObject);
        return jsonObject;
    }

    public JSONArray toJSONArray() {
        JSONArray result = new JSONArray();
        fillJSONArray(result);
        return result;
    }

    protected void fillJSONObject(JSONObject object) {
        if (isJSONObject() && children != null) {
            for (ASTree child : children) {
                child.fillJSONObject(object);
            }
        }
    }

    protected void fillJSONArray(JSONArray array) {
        if (isJSONArray() && children != null) {
            for (ASTree child : children) {
                child.fillJSONArray(array);
            }
        }
    }

    @Override
    public String toString() {
        return toString(0);
    }

    protected String toString(int tiered) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tiered; i++) {
            builder.append("-");
        }
        builder.append(token).append("\r\n");
        if (children != null) {
            for (ASTree child : children) {
                builder.append(child.toString(tiered + 4));
            }
        }
        return builder.toString();
    }
}
