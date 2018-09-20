package com.towerchain.appPayment.exception;

/**
 * Created by TowerChain_T01 on 2018/9/12.
 */
public class ParamException extends Exception {

    private int error_code;
    private String error_msg;

    public ParamException(int error_code) {
        this.error_code = error_code;
    }

    public ParamException(String error_msg) {
        this.error_msg = error_msg;
    }

    public ParamException(int error_code, String error_msg) {
        this.error_code = error_code;
        this.error_msg = error_msg;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
