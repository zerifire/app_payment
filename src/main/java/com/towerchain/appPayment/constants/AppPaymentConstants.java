package com.towerchain.appPayment.constants;

/**
 * Created by TowerChain_T01 on 2018/9/12.
 */
public interface AppPaymentConstants {
    // app 的appId
    String APP_WEIXIN_APY_URL = "https://api.mch.weixin.qq.com/";
    String APP_WEIXIN_UNIFIEDORDER_URL = APP_WEIXIN_APY_URL + "pay/unifiedorder"; //微信生成统一订单的接口
    String APP_WEIXIN_ORDERQUERY_URL = APP_WEIXIN_APY_URL + "pay/orderquery";  //微信查询订单接口
    String APP_WEIXIN_CLOSE_ORDER_URL = APP_WEIXIN_APY_URL + "pay/closeorder"; // 关闭订单
    String APP_WEIXIN_REDUND_URL = APP_WEIXIN_APY_URL + "secapi/pay/refund";  //申请退款
    String APP_WEIXIN_REFUND_QUERY_URL = APP_WEIXIN_APY_URL + "pay/refundquery";  //查询退款信息
    String APP_WEIXIN_DOWNLOAD_BILL_URL = APP_WEIXIN_APY_URL + "pay/downloadbill";  // 下载对账信息
    String APP_WEIXIN_BATCH_QUERY_COMMENT_URL = APP_WEIXIN_APY_URL + "billcommentsp/batchquerycomment"; //拉取订单评价数据
    String APP_WEIXNI_DOWNLOAD_FUND_FLOW_URL = APP_WEIXIN_APY_URL + "pay/downloadfundflow";  //下载资金账单


}
