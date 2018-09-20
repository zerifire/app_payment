package com.towerchain.appPayment.entity;

/**
 * Created by TowerChain_T01 on 2018/9/18.
 */
public class BatchQueryCommentEntity extends BaseEntity {
    private String begin_time;
    private String end_time;
    private String offset;
    private String limit;

    public String getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
