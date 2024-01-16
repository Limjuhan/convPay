package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.dto.PayResult;

public class ConveniencePayService { // 편결이
    private final MoneyAdapter moneyAdapter = new MoneyAdapter();
    //결제
    public PayResponse pay(PayRequest payRequest) {
        moneyAdapter.use(payRequest.getPayAmount());
        return new PayResponse(PayResult.SUCCESS, 100);
    }
    //결제취소
    public void payCancel() {

    }
}
