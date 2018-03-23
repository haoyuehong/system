package system.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: mol
 * @Description:
 * @Date: create in 8:33 2018/3/14
 */
public class JsonData<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    private JsonData(int status) {
        this.status = status;
    }

    private JsonData(String msg) {
        this.msg = msg;
    }

    private JsonData(T data) {
        this.data = data;
    }

    private JsonData(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private JsonData(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private JsonData(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    //使之不在json返回结果当中
    @JsonIgnore
    public boolean isSuccess() {
        return this.status == 1;
    }

    public static JsonData createSuccess() {
        return new JsonData(1);
    }

    public static JsonData createSuccess(String msg) {
        return new JsonData(1, msg);
    }

    public static <T> JsonData<T> createSuccess(T data) {
        return new JsonData(1, data);
    }

    public static <T> JsonData<T> createSuccess(String msg, T data) {
        return new JsonData(1, msg, data);
    }

    public static JsonData createError() {
        return new JsonData(1);
    }

    public static JsonData createError(String msg) {
        return new JsonData(0, msg);
    }

    public static JsonData createError(int status, String msg) {
        return new JsonData(status, msg);
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("status", status);
        result.put("msg", msg);
        if(data != null){
            result.put("data", data);
        }
        return result;
    }
}
