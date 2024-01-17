package com.zerobase.convpay.service;

import com.zerobase.convpay.dto.*;
import com.zerobase.convpay.type.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConveniencePayService { // 편결이
    private final Map<PayMethodType, PaymentInterface> paymentInterfaceMap = new HashMap<>();
    private final DiscountInterface discountInterface;

    public ConveniencePayService(Set<PaymentInterface> paymentInterfaceSet, DiscountInterface discountInterface) {
        paymentInterfaceSet.forEach(
                paymentInterface -> paymentInterfaceMap.put(
                        paymentInterface.getPayMethodType(),
                        paymentInterface
                )
        );

        this.discountInterface = discountInterface;
    }

    //결제
    public PayResponse pay(PayRequest payRequest) {
        PaymentInterface paymentInterface = paymentInterfaceMap.get(payRequest.getPayMethodType());

        Integer discountedAmount = discountInterface.getDiscountedAmount(payRequest);
        PaymentResult paymentResult = paymentInterface.payment(discountedAmount);

        //FAIL Case
        if (paymentResult == PaymentResult.PAYMENT_FAIL) {
            return new PayResponse(PayResult.FAIL, 0);
        }

        //Success Case
        return new PayResponse(PayResult.SUCCESS, discountedAmount);

    }

    //결제취소
    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest) {
        PaymentInterface paymentInterface = paymentInterfaceMap.get(payCancelRequest.getPayMethodType());

        CancelPaymentResult cancelPaymentResult =
                paymentInterface.cancelpayment(payCancelRequest.getPayCancelAmount());


        //fail case
        if (CancelPaymentResult.CANCEL_PAYMENT_FAIL == cancelPaymentResult) {
            return new PayCancelResponse(PayCancelResult.PAY_CANCEL_FAIL, 0);
        }
        //success case
        return new PayCancelResponse(PayCancelResult.PAY_CANCEL_SUCCESS, payCancelRequest.getPayCancelAmount());
    }
}
