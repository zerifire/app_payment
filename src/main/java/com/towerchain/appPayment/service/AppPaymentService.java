package com.towerchain.appPayment.service;

import com.towerchain.appPayment.entity.*;
import com.towerchain.appPayment.exception.ParamException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by TowerChain_T01 on 2018/9/12.
 */
public interface AppPaymentService {

    /**
     * 生成统一订单
     *
     * @param appPaymentEntity
     * @param request
     * @return
     */

    AppPaymentResultEntity appPaymentCreateOrder(AppPaymentEntity appPaymentEntity, HttpServletRequest request) throws ParamException;

    /**
     * 支付通知
     *
     * @param request
     * @return
     * @throws IOException
     */
    String receiveAppPaymentMessage(HttpServletRequest request) throws IOException;

    /**
     * 查询订单
     *
     * @param appPaymentEntity
     * @return
     */
    QueryOrderDownEntity appPaymentOrderQuery(AppPaymentEntity appPaymentEntity) throws ParamException;

    /**
     * 关闭订单
     *
     * @param upEntity
     * @return
     */
    AppPaymentResultEntity appPaymentCloseOrder(AppPaymentEntity upEntity) throws ParamException;

    /**
     * 申请退款
     *
     * @param upEntity
     * @return
     */
    RefundDownEntity appPaymentRefund(RefundUpEntity upEntity) throws ParamException;

    /**
     * 查询退款
     *
     * @param upEntity
     * @return
     */
    RefundDownEntity appPaymentRefundQuery(RefundUpEntity upEntity) throws ParamException;

    /**
     * 下载对账单
     *
     * @param upEntity
     * @return
     */
    boolean appPaymentDownloadBill(DownloadBillEntity upEntity) throws ParamException;

    /**
     * 下载资金账单
     *
     * @param upEntity
     */
    void appPaymentDownloadFundFlow(DownloadFundFlowEntity upEntity) throws ParamException;

    /**
     * 接收微信返回的支付信息
     *
     * @param request
     * @return
     */
    String receiveRefundNotify(HttpServletRequest request);

    /**
     * 拉取订单评价数据
     *
     * @param upEntity
     * @return
     * @throws ParamException
     */
    boolean appPaymentBatchQueryComment(BatchQueryCommentEntity upEntity) throws ParamException;

}
