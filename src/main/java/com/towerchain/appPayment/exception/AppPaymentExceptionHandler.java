package com.towerchain.appPayment.exception;

import com.towerchain.appPayment.constants.AppPaymentErrorId;
import com.towerchain.appPayment.entity.ErrorEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by TowerChain_T01 on 2018/9/19.
 */
@ControllerAdvice
public class AppPaymentExceptionHandler {
    //声明要捕捉的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorEntity paymentExceptionHandler(HttpServletRequest request, Exception e) {
        ErrorEntity errorEntity = new ErrorEntity();
        //TODO
        if (e instanceof ParamException) {
            errorEntity.setCode(((ParamException) e).getError_code());
            errorEntity.setMsg(e.getMessage());
        } else {
            errorEntity.setCode(AppPaymentErrorId.ERROR_UNKNOWN);
            errorEntity.setMsg(e.getMessage());
        }
        return errorEntity;
    }
}
