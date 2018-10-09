package com.towerchain;

import com.towerchain.appPayment.utils.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * Created by TowerChain_T01 on 2018/9/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpTest {

    @Test
    public void test() {
        String data =
                "<xml>" +
                        "<appid><![CDATA[wx2421b1c4370ec43b]]></appid>" +
                        "<attach><![CDATA[支付  测试]]></attach>" +
                        "<bank_type><![CDATA[CFT]]></bank_type>" +
                        "<fee_type><![CDATA[CNY]]></fee_type>" +
                        "<is_subscribe><![CDATA[Y]]></is_subscribe>" +
                        "<mch_id><![CDATA[10000100]]></mch_id>" +
                        "<nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>" +
                        "<openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>" +
                        "<out_trade_no><![CDATA[1409811653]]></out_trade_no>" +
                        "<result_code><![CDATA[SUCCESS]]></result_code>" +
                        "<return_code><![CDATA[SUCCESS]]></return_code>" +
                        "<sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>" +
                        "<sub_mch_id><![CDATA[10000100]]></sub_mch_id>" +
                        "<time_end><![CDATA[20140903131540]]></time_end>" +
                        "<total_fee>111</total_fee><coupon_fee><![CDATA[10]]></coupon_fee>" +
                        "<coupon_count><![CDATA[1]]></coupon_count>" +
                        "<coupon_type><![CDATA[CASH]]></coupon_type>" +
                        "<coupon_id><![CDATA[10000]]></coupon_id>" +
                        "<coupon_fee_0><![CDATA[100]]></coupon_fee_0>" +
                        "<trade_type><![CDATA[JSAPI]]></trade_type>" +
                        "<transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>" +
                        "</xml>";

        try {
            String result = HttpUtil.doPostXml("http://45.113.68.178:8081/receiveAppPaymentMessage", data);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }


    }

    @Test
    public void http() {
        String data =
                "<xml>" +
                        "<appid><![CDATA[wx2421b1c4370ec43b]]></appid>" +
                        "<attach><![CDATA[支付  测试]]></attach>" +
                        "<bank_type><![CDATA[CFT]]></bank_type>" +
                        "<fee_type><![CDATA[CNY]]></fee_type>" +
                        "<is_subscribe><![CDATA[Y]]></is_subscribe>" +
                        "<mch_id><![CDATA[10000100]]></mch_id>" +
                        "<nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>" +
                        "<openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>" +
                        "<out_trade_no><![CDATA[1409811653]]></out_trade_no>" +
                        "<result_code><![CDATA[SUCCESS]]></result_code>" +
                        "<return_code><![CDATA[SUCCESS]]></return_code>" +
                        "<sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>" +
                        "<sub_mch_id><![CDATA[10000100]]></sub_mch_id>" +
                        "<time_end><![CDATA[20140903131540]]></time_end>" +
                        "<total_fee>111</total_fee><coupon_fee><![CDATA[10]]></coupon_fee>" +
                        "<coupon_count><![CDATA[1]]></coupon_count>" +
                        "<coupon_type><![CDATA[CASH]]></coupon_type>" +
                        "<coupon_id><![CDATA[10000]]></coupon_id>" +
                        "<coupon_fee_0><![CDATA[100]]></coupon_fee_0>" +
                        "<trade_type><![CDATA[JSAPI]]></trade_type>" +
                        "<transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>" +
                        "</xml>";
//        HttpHelper client = new HttpHelper();
//        try {
//          String result =  client.postXml("http://45.113.68.178:8081/receiveAppPaymentMessage", data);
//            System.out.println(result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


}
