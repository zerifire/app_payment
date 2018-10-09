package com.towerchain.appPayment.service.impl;

import com.towerchain.appPayment.constants.AppPaymentErrorId;
import com.towerchain.appPayment.constants.AppPaymentConstants;
import com.towerchain.appPayment.entity.*;
import com.towerchain.appPayment.exception.ParamException;
import com.towerchain.appPayment.service.AppPaymentService;
import com.towerchain.appPayment.service.ServiceBase;
import com.towerchain.appPayment.utils.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Service
public class AppPaymentServiceImpl extends ServiceBase implements AppPaymentService {

    private static Logger logger = Logger.getLogger(AppPaymentServiceImpl.class);

    /**
     * 必传参数
     * appid，mch_id，nonce_str，sign，body，out_trade_no，
     * total_fee，spbill_create_ip，notify_url，trade_type
     * <p>
     * 微信支付 创建统一订单
     *
     * @param appPaymentEntity
     * @return
     * @throws Exception
     */
    @Override
    public AppPaymentResultEntity appPaymentCreateOrder(AppPaymentEntity appPaymentEntity, HttpServletRequest request) throws ParamException {
        AppPaymentResultEntity resultEntity = new AppPaymentResultEntity();
        checkParamNotNull(appPaymentEntity, "upEntity");
        checkParamNotNull(appPaymentEntity.getBody(), "body");
        checkParamNotNull(appPaymentEntity.getTotal_fee(), "total_fee");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//        随机字符串
        String nonce_str = WXPayUtil.generateNonceStr();
//        时间戳
        long times = System.currentTimeMillis();
//        获取请求IP地址
        String ip = HttpUtil.getIpAddress(request);
        //TODO 测试打印logger  待删除
        logger.info(ip);
        appPaymentEntity.setAppid(AppPaymentConstants.APP_APPID);
        appPaymentEntity.setMch_id(AppPaymentConstants.APP_MCH_ID);
        appPaymentEntity.setDevice_info("WEB");
        appPaymentEntity.setNonce_str(nonce_str);
        appPaymentEntity.setSign_type("MD5");
        appPaymentEntity.setDetail("您在XXXXX的消费信");
        appPaymentEntity.setAttach("上海店");
        appPaymentEntity.setOut_trade_no(nonce_str);
        //TODO 测试打印logger  待删除
        logger.info("订单号：" + nonce_str);
        appPaymentEntity.setFee_type("CNY");
        appPaymentEntity.setSpbill_create_ip(ip);
        appPaymentEntity.setTime_start(dateFormat.format(times));
        appPaymentEntity.setTime_expire(dateFormat.format(times + 2 * 60 * 1000));
        appPaymentEntity.setGoods_tag("WXG");
        appPaymentEntity.setNotify_url(AppPaymentConstants.APP_WEIXIN_NOTIFY_URL);
        appPaymentEntity.setTrade_type("APP");
        appPaymentEntity.setLimit_pay("no_credit");
        SceneInfo sceneInfo = new SceneInfo();
        sceneInfo.setStore_id(AppPaymentConstants.APP_STORE_ID);
        sceneInfo.setStore_name("上海陆家嘴");
        appPaymentEntity.setScene_info(JsonUtility.obj2Json(sceneInfo));
        Map<String, String> paramMap = null;
        try {
//            参数转换成map集合
            paramMap = JsonUtility.objToMap(appPaymentEntity);
//            将map转换成xml并添加sign
            String data = WXPayUtil.generateSignedXml(paramMap, AppPaymentConstants.APP_WEIXIN_SECRET_KEY);
//            发送xml请求
            String result = HttpUtil.doPostXml(AppPaymentConstants.APP_WEIXIN_UNIFIEDORDER_URL, data);
//            xml转map
            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
            if (resultMap.get("return_code").equals("SUCCESS")) {
//                验签
                boolean b = WXPayUtil.isSignatureValid(resultMap, AppPaymentConstants.APP_WEIXIN_SECRET_KEY);
                if (!b) {
                    // 验签失败
                    resultEntity.setErr_code(String.valueOf(AppPaymentErrorId.ERROR_VERIFY_SIGN_FAILED));
                    // TODO
                } else {
                    //将返回的结果转成对象
                    resultEntity = JsonUtility.json2Object(JsonUtility.obj2Json(resultMap), AppPaymentResultEntity.class);
                    resultEntity.setAppId(appPaymentEntity.getAppid());
                    resultEntity.setStartTime(getTimeMillis(appPaymentEntity.getTime_start()));
                    // TODO
                }
            } else {
                resultEntity = JsonUtility.json2Object(JsonUtility.obj2Json(resultMap), AppPaymentResultEntity.class);
                //TODO 测试打印logger  待删除
                logger.info(JsonUtility.obj2Json(resultEntity));
            }
        } catch (Exception e) {
            //TODO
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return resultEntity;
    }

    /**
     * 微信回调，接受微信的 支付结果通知
     *
     * @param request
     * @return
     */
    @Override
    public String receiveAppPaymentMessage(HttpServletRequest request) throws IOException {
        String result = null;
//        成功返回微信信息
        String return_success = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
//        失败返回
        String return_fail = "fail";
        try {
//            获取微信传回来的参数
            result = getWXReturn(request);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
            AppPaymentMessageEntity entity = JsonUtility.json2Object(JsonUtility.obj2Json(resultMap), AppPaymentMessageEntity.class);
            if (WXPayUtil.isSignatureValid(resultMap, AppPaymentConstants.APP_WEIXIN_SECRET_KEY)) {
                //TODO　验签成功
                System.out.println(JsonUtility.obj2Json(entity));
                logger.info(JsonUtility.obj2Json(entity));
            } else {
                //TODO 验签失败
                logger.equals("验签失败！！");
                System.out.println("验签失败！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return return_success;
    }

    /**
     * 查询订单信息
     * <p>
     * 必传参数：appid，mch_id，out_trade_no，nonce_str，sign
     *
     * @param appPaymentEntity
     * @return
     */
    @Override
    public QueryOrderDownEntity appPaymentOrderQuery(AppPaymentEntity appPaymentEntity) throws ParamException {
        checkParamNotNull(appPaymentEntity, "upEntity");
        checkParamNotNull(appPaymentEntity.getOut_trade_no(), "out_trade_no");
        QueryOrderDownEntity downEntity = new QueryOrderDownEntity();
        try {
            appPaymentEntity.setAppid(AppPaymentConstants.APP_APPID);
            appPaymentEntity.setMch_id(AppPaymentConstants.APP_MCH_ID);
            appPaymentEntity.setNonce_str(WXPayUtil.generateNonceStr());
            //转换成Map集合
            Map<String, String> paramMap = JsonUtility.objToMap(appPaymentEntity);
            String data = WXPayUtil.generateSignedXml(paramMap, AppPaymentConstants.APP_WEIXIN_SECRET_KEY);
            String result = HttpUtil.doPostXml(AppPaymentConstants.APP_WEIXIN_ORDERQUERY_URL, data);
            Map<String, String> downMap = WXPayUtil.xmlToMap(result);
            if (downMap.get("return_code").equals("SUCCESS")) {
                if (WXPayUtil.isSignatureValid(result, AppPaymentConstants.APP_WEIXIN_SECRET_KEY)) {
                    downEntity = JsonUtility.json2Object(JsonUtility.obj2Json(downMap), QueryOrderDownEntity.class);
                } else {
                    downEntity.setReturn_code("FAIL");
                    downEntity.setReturn_msg("签名错误");
                }
            } else {
                downEntity = JsonUtility.json2Object(JsonUtility.obj2Json(downMap), QueryOrderDownEntity.class);
                //TODO 测试打印logger  待删除
                logger.info(JsonUtility.obj2Json(downMap));
            }
        } catch (Exception e) {
            //TODO
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return downEntity;
    }

    /**
     * 关闭订单
     * 必传参数：appid，mch_id，out_trade_no，nonce_str，sign
     *
     * @param upEntity 订单号
     * @return
     */
    @Override
    public AppPaymentResultEntity appPaymentCloseOrder(AppPaymentEntity upEntity) throws ParamException {
        checkParamNotNull(upEntity, "upEntity");
        checkParamNotNull(upEntity.getOut_trade_no(), "out_trade_no");
        AppPaymentResultEntity downEntity = null;
        try {
            upEntity.setMch_id(AppPaymentConstants.APP_MCH_ID);
            upEntity.setAppid(AppPaymentConstants.APP_APPID);
            upEntity.setNonce_str(WXPayUtil.generateNonceStr());
            Map<String, String> paramMap = JsonUtility.objToMap(upEntity);
            String paramXML = WXPayUtil.generateSignedXml(paramMap, AppPaymentConstants.APP_WEIXIN_SECRET_KEY);
            String result = HttpUtil.doPostXml(AppPaymentConstants.APP_WEIXIN_CLOSE_ORDER_URL, paramXML);
            System.out.println(result);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
            if (WXPayUtil.isSignatureValid(resultMap, AppPaymentConstants.APP_WEIXIN_SECRET_KEY)) {
                //TODO 测试打印logger  待删除
                logger.info(result);
                downEntity = JsonUtility.json2Object(JsonUtility.obj2Json(resultMap), AppPaymentResultEntity.class);
            } else {
                //TODO 测试打印logger
                logger.error("验签失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return downEntity;
    }

    /**
     * 申请退款
     * <p>
     * 必传参数：appid，mch_id，out_trade_no，nonce_str，sign，
     * out_refund_no，total_fee，refund_fee
     *
     * @param upEntity
     */
    @Override
    public RefundDownEntity appPaymentRefund(RefundUpEntity upEntity) throws ParamException {
        //TODO　商户退款单号	out_refund_no
        checkParamNotNull(upEntity, "upEntity");
        checkParamNotNull(upEntity.getOut_trade_no(), "out_trade_no");
        checkParamNotNull(upEntity.getTotal_fee(), "total_fee");
        checkParamNotNull(upEntity.getRefund_fee(), "refund_fee");

        upEntity.setMch_id(AppPaymentConstants.APP_MCH_ID);
        upEntity.setAppid(AppPaymentConstants.APP_APPID);
        upEntity.setNotify_url(AppPaymentConstants.APP_WEIXIN_REFUND_NOTIFY_URL);
        upEntity.setNonce_str(WXPayUtil.generateNonceStr());
        try {
            Map<String, String> paramMap = JsonUtility.objToMap(upEntity);
            String paramXml = WXPayUtil.generateSignedXml(paramMap, AppPaymentConstants.APP_WEIXIN_SECRET_KEY);
            String resultXml = HttpUtil.doPostXml(AppPaymentConstants.APP_WEIXIN_REDUND_URL, paramXml);
            //TODO 测试输出信息  待删除
            System.out.println(resultXml);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultXml);
            if (WXPayUtil.isSignatureValid(resultMap, AppPaymentConstants.APP_WEIXIN_SECRET_KEY)) {
                RefundDownEntity downEntity = JsonUtility.json2Object(JsonUtility.obj2Json(resultMap), RefundDownEntity.class);
                return downEntity;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 查询退款信息
     * 必传参数：appid，mch_id，out_trade_no，nonce_str，sign
     *
     * @param upEntity
     * @return
     */
    @Override
    public RefundDownEntity appPaymentRefundQuery(RefundUpEntity upEntity) throws ParamException {
        checkParamNotNull(upEntity, "upEntity");
        checkParamNotNull(upEntity.getOut_trade_no(), "out_trade_no");
        upEntity.setMch_id(AppPaymentConstants.APP_MCH_ID);
        upEntity.setAppid(AppPaymentConstants.APP_APPID);
        upEntity.setNonce_str(WXPayUtil.generateNonceStr());
        String paramXml = null;
        try {
            Map<String, String> paramMap = JsonUtility.objToMap(upEntity);
            paramXml = WXPayUtil.generateSignedXml(paramMap, AppPaymentConstants.APP_WEIXIN_SECRET_KEY);
            String resultXml = HttpUtil.doPostXml(AppPaymentConstants.APP_WEIXIN_REFUND_QUERY_URL, paramXml);
            //TODO 测试输出  待删除
            System.out.println(resultXml);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultXml);
            if (WXPayUtil.isSignatureValid(resultMap, AppPaymentConstants.APP_WEIXIN_SECRET_KEY)) {
                RefundDownEntity downEntity = JsonUtility.json2Object(JsonUtility.obj2Json(resultMap), RefundDownEntity.class);
                return downEntity;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 下载对账单，并生成Excel文件
     * 必传参数：appid，mch_id，out_trade_no，nonce_str，sign
     *
     * @param upEntity
     */
    @Override
    public boolean appPaymentDownloadBill(DownloadBillEntity upEntity) throws ParamException {
        checkParamNotNull(upEntity, "upEntity");
        checkParamNotNull(upEntity.getBill_date(), "bill_date");
        upEntity.setMch_id(AppPaymentConstants.APP_MCH_ID);
        upEntity.setAppid(AppPaymentConstants.APP_APPID);
        upEntity.setBill_type("ALL");
        upEntity.setNonce_str(WXPayUtil.generateNonceStr());
        Map<String, String> paramMap = JsonUtility.objToMap(upEntity);
        try {
            String paramXml = WXPayUtil.generateSignedXml(paramMap, AppPaymentConstants.APP_WEIXIN_SECRET_KEY);
            String result = HttpUtil.doPostXml(AppPaymentConstants.APP_WEIXIN_DOWNLOAD_BILL_URL, paramXml);
//            Excel表格的第一行列名称
            String title = "交易时间,公众账号ID,商户号,子商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,总金额,企业红包金额,微信退款单号,商户退款单号,退款金额,企业红包退款金额,退款类型,退款状态,商品名称,商户数据包,手续费,费率";
//            Excel表格的统计的列的名称
            String secTitle = "总交易单数,总交易额,总退款金额,总企业红包退款金额,手续费总金额";
            String status = ExportToExcel.generateExcel(result, title, secTitle);
            if (status.equals("SUCCESS")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            //TODO
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 下载资金账单
     * 商户可以通过该接口下载自2017年6月1日起 的历史资金流水账单。
     *
     * @param upEntity 参数：：account_type  Basic  基本账户
     *                 Operation 运营账户
     *                 Fees 手续费账户
     *                 <p>
     *                 此方法 sign_type目前仅支持 ： HMAC-SHA256  (前面方法默认为MD5)
     */
    @Override
    public void appPaymentDownloadFundFlow(DownloadFundFlowEntity upEntity) throws ParamException {
        checkParamNotNull(upEntity, "upEntity");
        checkParamNotNull(upEntity.getBill_date(), "bill_date");
        checkParamNotNull(upEntity.getAccount_type(), "account_type");
        upEntity.setMch_id(AppPaymentConstants.APP_MCH_ID);
        upEntity.setAppid(AppPaymentConstants.APP_APPID);
        upEntity.setNonce_str(WXPayUtil.generateNonceStr());
        try {
            Map<String, String> paramMap = JsonUtility.objToMap(upEntity);
            String paramXml = WXPayUtil.generateSignedXml(paramMap, AppPaymentConstants.APP_WEIXIN_SECRET_KEY, WXPayConstants.SignType.HMACSHA256);
            String result = HttpUtil.doPostXml(AppPaymentConstants.APP_WEIXNI_DOWNLOAD_FUND_FLOW_URL, paramXml);
            //TODO 测试输出  待删除
            System.out.println(result);
            try {
                String title = "记账时间,微信支付业务单号,资金流水单号,业务名称,业务类型,收支类型,收支金额（元）,账户结余（元）,资金变更提交申请人,备注,业务凭证号";
                String secTitle = "资金流水总笔数,收入笔数,收入金额,支出笔数,支出金额";
                String status = ExportToExcel.generateExcel(result, title, secTitle);
                if (status.equals("SUCCESS")) {
                    //成功返回并解析
                    //TODO
                }
            } catch (Exception e) {
                //TODO
                //出现错误
                Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 申请微信退款通知结果
     *
     * @param request
     * @return
     */
    @Override
    public String receiveRefundNotify(HttpServletRequest request) {
        String result = null;
        //成功时返回
        String return_success = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        String return_fail = "fail";
        try {
            //将流转换成字符串
            result = getWXReturn(request);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
            RefundNotifyEntity entity = JsonUtility.json2Object(JsonUtility.obj2Json(resultMap), RefundNotifyEntity.class);
            if (WXPayUtil.isSignatureValid(resultMap, AppPaymentConstants.APP_WEIXIN_SECRET_KEY)) {
                //TODO　验签成功  待删除
                System.out.println(JsonUtility.obj2Json(entity));
                logger.info(JsonUtility.obj2Json(entity));
                return return_success;
            } else {
                //TODO  验签失败   待删除
                logger.warn("验签失败！！");
                System.out.println("验签失败！！");
                return return_fail;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return return_fail;
        }
    }

    /**
     * 拉取订单评价数据
     * 必传参数：appid，mch_id，nonce_str，sign，begin_time，end_time，offset
     *
     * @param upEntity
     * @return
     */
    @Override
    public boolean appPaymentBatchQueryComment(BatchQueryCommentEntity upEntity) throws ParamException {
        checkParamNotNull(upEntity, "upEntity");
        checkParamNotNull(upEntity.getBegin_time(), "begin_time");
        checkParamNotNull(upEntity.getEnd_time(), "end_time");
        checkParamNotNull(upEntity.getOffset(), "offset");
        upEntity.setMch_id(AppPaymentConstants.APP_MCH_ID);
        upEntity.setAppid(AppPaymentConstants.APP_APPID);
        upEntity.setNonce_str(WXPayUtil.generateNonceStr());
        try {
            Map<String, String> paramMap = JsonUtility.objToMap(upEntity);
            String paramXml = WXPayUtil.generateSignedXml(paramMap, AppPaymentConstants.APP_WEIXIN_SECRET_KEY, WXPayConstants.SignType.HMACSHA256);
            System.out.println(paramXml);
            String result = HttpUtil.doPostXml(AppPaymentConstants.APP_WEIXIN_BATCH_QUERY_COMMENT_URL, paramXml);
            //TODO 测试打印logger 待删除
            logger.info(result);
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}