package com.towerchain.appPayment.entity;

/**
 * Created by TowerChain_T01 on 2018/9/17.
 */
public class RefundDownEntity extends AppPaymentEntity {

    private String out_refund_no;
    private String refund_id;
    private String refund_fee;
    private String settlement_refund_fee;
    private String settlement_total_fee;
    private String cash_refund_fee;
    private String coupon_type_$n;
    private String coupon_refund_fee;
    private String coupon_refund_fee_$n;
    private String coupon_refund_count;
    private String coupon_refund_id_$n;


    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
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

    public String getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(String settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    public String getCash_refund_fee() {
        return cash_refund_fee;
    }

    public void setCash_refund_fee(String cash_refund_fee) {
        this.cash_refund_fee = cash_refund_fee;
    }

    public String getCoupon_type_$n() {
        return coupon_type_$n;
    }

    public void setCoupon_type_$n(String coupon_type_$n) {
        this.coupon_type_$n = coupon_type_$n;
    }

    public String getCoupon_refund_fee() {
        return coupon_refund_fee;
    }

    public void setCoupon_refund_fee(String coupon_refund_fee) {
        this.coupon_refund_fee = coupon_refund_fee;
    }

    public String getCoupon_refund_fee_$n() {
        return coupon_refund_fee_$n;
    }

    public void setCoupon_refund_fee_$n(String coupon_refund_fee_$n) {
        this.coupon_refund_fee_$n = coupon_refund_fee_$n;
    }

    public String getCoupon_refund_count() {
        return coupon_refund_count;
    }

    public void setCoupon_refund_count(String coupon_refund_count) {
        this.coupon_refund_count = coupon_refund_count;
    }

    public String getCoupon_refund_id_$n() {
        return coupon_refund_id_$n;
    }

    public void setCoupon_refund_id_$n(String coupon_refund_id_$n) {
        this.coupon_refund_id_$n = coupon_refund_id_$n;
    }
}
