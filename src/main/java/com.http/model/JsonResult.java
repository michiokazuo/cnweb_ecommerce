package com.http.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonResult {
    private Integer code;
    private Object data;

    public JsonResult() {
    }

    public JsonResult(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }

    public String jsonSuccess(Object data) {
        Gson gson = new GsonBuilder()
                .setDateFormat("MM-dd-yyyy").create();
        return gson.toJson(new JsonResult(200, data));
    }

    public String jsonFailure(Object data) {
        return new Gson().toJson(new JsonResult(400, data));
    }
}
