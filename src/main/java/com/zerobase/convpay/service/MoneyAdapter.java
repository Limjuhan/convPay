package com.zerobase.convpay.service;

public class MoneyAdapter {
    //머니사용
    public MoneyUseResult use(Integer payAmount) {
        System.out.println("MoneyAdapter.use: " + payAmount);

        if (payAmount > 1000_000) {
            return MoneyUseResult.USE_FAIL;
        }

        return MoneyUseResult.USE_SUCCESS;
    }

    //머니취소
    public void useCancel() {

    }
}
