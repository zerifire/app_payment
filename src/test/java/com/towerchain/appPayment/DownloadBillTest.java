package com.towerchain.appPayment;

import com.towerchain.appPayment.utils.ExportToExcel;
import com.towerchain.appPayment.utils.WXPayUtil;
import org.junit.Test;

/**
 * Created by TowerChain_T01 on 2018/9/18.
 */
public class DownloadBillTest {

    @Test
    public void billTest(){

        String result =
                "交易时间,公众账号ID,商户号,子商户号,设备号,微信订单号,商户订单号,用户标识,交易类型,交易状态,付款银行,货币种类,总金额,企业红包金额,微信退款单号,商户退款单号,退款金额,企业红包退款金额,退款类型,退款状态,商品名称,商户数据包,手续费,费率"+
                "`2018-09-17 10:19:25,`wxa6628dbe9d6efb40,`1511727381,`0,`WEB,`4200000159201809174535836453,`eD2Jmfz8Jvf5ONDT1ncus5R2tU6X3OL8,`oCq4c1JiuSSv-c54oH4jZhzQbyh8,`APP,`SUCCESS,`CFT,`CNY,`0.01,`0.00,`0,`0,`0.00,`0.00,`,`,`备注信息，充值抽奖送名著：三国演义,`上海店,`0.00000,`0.60%" +
                "`2018-09-17 11:53:15,`wxa6628dbe9d6efb40,`1511727381,`0,`WEB,`4200000161201809179521564592,`P1BestCsbGJihXN7mW2ZR9VMESOl48ca,`oCq4c1JiuSSv-c54oH4jZhzQbyh8,`APP,`SUCCESS,`CFT,`CNY,`0.01,`0.00,`0,`0,`0.00,`0.00,`,`,`备注信息，充值抽奖送名著：三国演义,`上海店,`0.00000,`0.60%" +
                "`2018-09-17 12:00:39,`wxa6628dbe9d6efb40,`1511727381,`0,`WEB,`4200000163201809175405571340,`PiZ2nAFuie8OOQkG3Ssy1hsgGfmWMOwP,`oCq4c1JiuSSv-c54oH4jZhzQbyh8,`APP,`SUCCESS,`CFT,`CNY,`0.01,`0.00,`0,`0,`0.00,`0.00,`,`,`备注信息，充值抽奖送名著：三国演义,`上海店,`0.00000,`0.60%" +
                "`2018-09-17 16:41:06,`wxa6628dbe9d6efb40,`1511727381,`0,`WEB,`4200000165201809171803465084,`iUnwqDtylFLarrRDGpW9DZoChcyVWQRY,`oCq4c1JiuSSv-c54oH4jZhzQbyh8,`APP,`SUCCESS,`CFT,`CNY,`0.01,`0.00,`0,`0,`0.00,`0.00,`,`,`备注信息，充值抽奖送名著：三国演义,`上海店,`0.00000,`0.60%" +
                "`2018-09-17 12:09:09,`wxa6628dbe9d6efb40,`1511727381,`0,`WEB,`4200000171201809173172635810,`2xS9x1bEF9uk9G6B4xbn4jtuOXcYmega,`oCq4c1JiuSSv-c54oH4jZhzQbyh8,`APP,`SUCCESS,`CFT,`CNY,`0.01,`0.00,`0,`0,`0.00,`0.00,`,`,`备注信息，充值抽奖送名著：三国演义,`上海店,`0.00000,`0.60%" +
                "`2018-09-17 14:06:12,`wxa6628dbe9d6efb40,`1511727381,`0,`WEB,`4200000174201809173472845055,`HxlD1bUTn1p48F2NvZbkUev7bCq3KNwV,`oCq4c1JiuSSv-c54oH4jZhzQbyh8,`APP,`SUCCESS,`CFT,`CNY,`0.01,`0.00,`0,`0,`0.00,`0.00,`,`,`备注信息，充值抽奖送名著：三国演义,`上海店,`0.00000,`0.60%" +
                "`2018-09-17 14:11:32,`wxa6628dbe9d6efb40,`1511727381,`0,`WEB,`4200000186201809179577844138,`uZvUU5ymRBhso8TfIr2AFMccng55MhSO,`oCq4c1JiuSSv-c54oH4jZhzQbyh8,`APP,`SUCCESS,`CFT,`CNY,`0.01,`0.00,`0,`0,`0.00,`0.00,`,`,`备注信息，充值抽奖送名著：三国演义,`上海店,`0.00000,`0.60%" +

                "总交易单数,总交易额,总退款金额,总企业红包退款金额,手续费总金额" +
                "`7,`0.07,`0.00,`0.00,`0.00000";


//        ExportToExcel.generateExcel(result);


    }

}
