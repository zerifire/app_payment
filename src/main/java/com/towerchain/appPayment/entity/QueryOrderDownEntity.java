package com.towerchain.appPayment.entity;

/**
 * Created by TowerChain_T01 on 2018/9/13.
 */
public class QueryOrderDownEntity extends AppPaymentOderDetailEntity {
    private String trade_state_desc;
    private String trade_state;
    private String settlement_total_fee;
    private String coupon_type_$n;

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(String settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    public String getCoupon_type_$n() {
        return coupon_type_$n;
    }

    public void setCoupon_type_$n(String coupon_type_$n) {
        this.coupon_type_$n = coupon_type_$n;
    }
}
