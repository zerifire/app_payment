package com.towerchain.appPayment.entity;

/**
 * Created by TowerChain_T01 on 2018/9/20.
 */
public class DownloadFundFlowEntity extends DownloadBillEntity {

    private String account_type;

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }
}
