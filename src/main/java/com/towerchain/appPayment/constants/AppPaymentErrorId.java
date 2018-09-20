package com.towerchain.appPayment.constants;

public interface AppPaymentErrorId {
	int ERROR_VERIFY_FAILED = 105; /// 验证失败
	int ERROR_DECRYPT_FAILED = 106; // 解密失败
	int ERROR_VERIFY_SIGN_FAILED = 107; // 验证签名失败
	int ERROR_UNKNOWN = 999999;
	int ERROR_PARAMETER = 2;		//参数值错误
}
