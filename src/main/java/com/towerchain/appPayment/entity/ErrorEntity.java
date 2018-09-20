package com.towerchain.appPayment.entity;

/**
 * Created by TowerChain_T01 on 2018/9/19.
 */
public class ErrorEntity {
    private int code;
    private  String msg;
    private  String data;

    public ErrorEntity() {
    }

    public ErrorEntity(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ErrorEntity(String data) {
        this.code = 200;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
