package org.example.demo.llk.ir;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.demo.llk.Token;

public class LeafNode extends ASTree {
    private final Token leaf;
    public LeafNode(Token token) {
        super(token);
        this.leaf=token;
    }

    @Override
    protected void fillJSONObject(JSONObject object) {
        throw new UnsupportedOperationException("node type is not supported fill JSONObject.");
    }

    @Override
    protected void fillJSONArray(JSONArray array) {
        array.add(leaf.getText());
    }
}
