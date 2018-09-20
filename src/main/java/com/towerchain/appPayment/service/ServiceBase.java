package com.towerchain.appPayment.service;

import com.towerchain.appPayment.constants.AppPaymentErrorId;
import com.towerchain.appPayment.exception.ParamException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by TowerChain_T01 on 2018/9/12.
 */
public class ServiceBase {

    /**
     * 判断对象不为空
     *
     * @param o
     * @param name
     * @return
     */
    protected void checkParamNotNull(Object o, String name) throws ParamException {
        if (o == null) {
            System.out.println(name);
            throw new ParamException(AppPaymentErrorId.ERROR_PARAMETER);
        }
    }

    /**
     * 判断参数不为空
     *
     * @param param
     * @param name
     * @return
     */
    protected void checkParamNotNull(String param, String name) throws ParamException {
        if (param == null || param.equals("")) {
            System.out.println(name);
            throw new ParamException(AppPaymentErrorId.ERROR_PARAMETER);
        }
    }


    /**
     * App支付 时间转换时间戳 参数时间格式为yyyyMMddHHmm  返回为 long
     *
     * @param str
     * @return
     * @throws ParseException
     */
    protected long getTimeMillis(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        return sdf.parse(str).getTime();
    }

    /**
     * 将时间戳转换成微信的时间格式：：yyyyMMddHHmmss
     *
     * @param times
     * @return String类型的时间
     */
    protected String formatData(Long times) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(times);
    }
}
