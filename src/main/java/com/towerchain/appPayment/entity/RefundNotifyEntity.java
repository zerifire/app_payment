package com.towerchain.appPayment.entity;

/**
 * Created by TowerChain_T01 on 2018/9/18.
 */
public class RefundNotifyEntity {

    private String return_code;
    private String return_msg;
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String req_info;
    private String out_trade_no;
    private String refund_id;
    private String out_refund_no;
    private String total_fee;
    private String settlement_total_fee;
    private String refund_fee;
    private String settlement_refund_fee;
    private String refund_status;
    private String success_time;
    private String refund_recv_accout;
    private String refund_account;
    private String refund_request_source;


    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getReq_info() {
        return req_info;
    }

    public void setReq_info(String req_info) {
        this.req_info = req_info;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(String settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getSettlement_refund_fee() {
        return settlement_refund_fee;
    }

    public void setSettlement_refund_fee(String settlement_refund_fee) {
        this.settlement_refund_fee = settlement_refund_fee;
    }

    public String getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(String refund_status) {
        this.refund_status = refund_status;
    }

    public String getSuccess_time() {
        return success_time;
    }

    public void setSuccess_time(String success_time) {
        this.success_time = success_time;
    }

    public String getRefund_recv_accout() {
        return refund_recv_accout;
    }

    public void setRefund_recv_accout(String refund_recv_accout) {
        this.refund_recv_accout = refund_recv_accout;
    }

    public String getRefund_account() {
        return refund_account;
    }

    public void setRefund_account(String refund_account) {
        this.refund_account = refund_account;
    }

    public String getRefund_request_source() {
        return refund_request_source;
    }

    public void setRefund_request_source(String refund_request_source) {
        this.refund_request_source = refund_request_source;
    }
}
