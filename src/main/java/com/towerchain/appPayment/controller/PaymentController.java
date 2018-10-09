package com.towerchain.appPayment.controller;

import com.towerchain.appPayment.entity.*;
import com.towerchain.appPayment.exception.ParamException;
import com.towerchain.appPayment.service.impl.AppPaymentServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * 打包方式，黑窗口，进入该文件夹，
 * 在该文件夹路径下 执行mvn clean package
 */
//@Controller(value = "/app/wxpay")
@Controller
public class PaymentController {

    @Autowired
    private AppPaymentServiceImpl paymentService;
    private static Logger logger = Logger.getLogger(PaymentController.class);

    /**
     * app支付统一生成订单接口
     *
     * @param entity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/appPaymentCreateOrder", consumes = "application/json;utf-8", method = RequestMethod.POST)
    @ResponseBody
    public AppPaymentResultEntity appPaymentCreateOrder(@RequestBody AppPaymentEntity entity, HttpServletRequest request) throws Exception {
        return paymentService.appPaymentCreateOrder(entity, request);
    }

    /**
     * 接收微信返回的支付信息
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/receiveAppPaymentMessage")
    @ResponseBody
    public String receiveAppPaymentMessages(HttpServletRequest request) throws IOException {
        return paymentService.receiveRefundNotify(request);
    }

    /**
     * app微信支付   查询订单
     *
     * @return
     */
    @RequestMapping(value = "/appPaymentOrderQuery", consumes = "application/json;utf-8", method = RequestMethod.POST)
    @ResponseBody
    public QueryOrderDownEntity appPaymentOrderQuery(@RequestBody AppPaymentEntity appPaymentEntity) throws ParamException {
        return paymentService.appPaymentOrderQuery(appPaymentEntity);
    }

    /**
     * app微信支付  关闭订单
     *
     * @param upEntity
     */
    @RequestMapping(value = "/appPaymentCloseOrder", consumes = "application/json;utf-8", method = RequestMethod.POST)
    @ResponseBody
    public void appPaymentCloseOrder(@RequestBody AppPaymentEntity upEntity) throws ParamException {
        paymentService.appPaymentCloseOrder(upEntity);
    }

    /**
     * 微信申请退款
     *
     * @param upEntity
     */
    @RequestMapping(value = "/appPaymentRefund", consumes = "application/json;utf-8", method = RequestMethod.POST)
    @ResponseBody
    public RefundDownEntity appPaymentRefund(@RequestBody RefundUpEntity upEntity) throws ParamException {
        return paymentService.appPaymentRefund(upEntity);
    }


    /**
     * 查询退款信息
     *
     * @param upEntity
     * @return
     */
    @RequestMapping(value = "/appPaymentRefundQuery", consumes = "application/json;utf-8", method = RequestMethod.POST)
    @ResponseBody
    public RefundDownEntity appPaymentRefundQuery(@RequestBody RefundUpEntity upEntity) throws ParamException {
        return paymentService.appPaymentRefundQuery(upEntity);
    }

    /**
     * 下载对账单
     *
     * @param upEntity
     * @return 成功true
     */

    @RequestMapping(value = "/appPaymentDownloadBill", consumes = "application/json;utf-8", method = RequestMethod.POST)
    @ResponseBody
    public boolean appPaymentDownloadBill(@RequestBody DownloadBillEntity upEntity) throws ParamException {
        return paymentService.appPaymentDownloadBill(upEntity);
    }

    /**
     * 下载资金账单
     *
     * @param upEntity
     * @throws ParamException
     */
    @RequestMapping(value = "/appPaymentDownloadFundFlow", consumes = "application/json;utf-8", method = RequestMethod.POST)
    @ResponseBody
    public void appPaymentDownloadFundFlow(@RequestBody DownloadFundFlowEntity upEntity) throws ParamException {
        paymentService.appPaymentDownloadFundFlow(upEntity);
    }

    /**
     * 微信申请退款通知（回调）
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/receiveRefundNotify", consumes = "application/json;utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String receiveRefundNotify(HttpServletRequest request) {
        return paymentService.receiveRefundNotify(request);
    }

    /**
     * 拉取订单评价数据
     *
     * @param upEntity
     * @return
     */
    @RequestMapping(value = "/appPaymentBatchQueryComment", consumes = "application/json;utf-8", method = RequestMethod.POST)
    @ResponseBody
    public boolean appPaymentBatchQueryComment(@RequestBody BatchQueryCommentEntity upEntity) throws ParamException {
        return paymentService.appPaymentBatchQueryComment(upEntity);
    }
}
