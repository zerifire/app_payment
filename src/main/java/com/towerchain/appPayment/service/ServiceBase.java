package com.towerchain.appPayment.service;

import com.towerchain.appPayment.constants.AppPaymentErrorId;
import com.towerchain.appPayment.exception.ParamException;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
            throw new ParamException(AppPaymentErrorId.ERROR_PARAMETER,name);
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
            throw new ParamException(AppPaymentErrorId.ERROR_PARAMETER,name);
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



    /**
     * 获取请求参数
     * 返回微信通知的结果
     *
     * @param request
     * @return
     * @throws IOException
     */
    protected String getWXReturn(HttpServletRequest request) throws IOException {
        ByteArrayOutputStream outStream = null;
        InputStream inStream = null;
        String result = null;
        byte[] tempBytes;
        try {
            request.setCharacterEncoding("UTF-8");
            inStream = request.getInputStream();
            int _buffer_size = 1024;
            if (inStream != null) {
                outStream = new ByteArrayOutputStream();
                tempBytes = new byte[_buffer_size];
                int count = -1;
                while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
                    outStream.write(tempBytes, 0, count);
                }
                outStream.flush();
                //将流转换成字符串
                result = new String(outStream.toByteArray(), "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (inStream != null) {
                inStream.close();
            }
            if (outStream != null) {
                outStream.close();
            }
        }
        return result;
    }
}
