package org.example.demo.llk.ir;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.demo.llk.TokenType;

public class KVNode extends ASTree {
    private final ASTree key;
    private final ASTree value;

    public KVNode(ASTree key, ASTree value) {
        super(TokenType.COLON_TOKEN);
        this.key = key;
        this.value = value;
        addChild(key).addChild(value);
    }

    @Override
    protected void fillJSONObject(JSONObject object) {
        if (value.isJSONObject()) {
            object.put(key.getTokenText(),value.toJSONObject());
        }else if (value.isJSONArray()){
            object.put(key.getTokenText(),value.toJSONArray());
        }else {
            object.put(key.getTokenText(),value.getTokenText());
        }
    }

    @Override
    protected void fillJSONArray(JSONArray array) {
        throw new UnsupportedOperationException("node type is not supported fill JSONArray.");
    }
}
