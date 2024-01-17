package com.zerobase.convpay;

import com.zerobase.convpay.dto.PayCancelRequest;
import com.zerobase.convpay.dto.PayCancelResponse;
import com.zerobase.convpay.dto.PayRequest;
import com.zerobase.convpay.dto.PayResponse;
import com.zerobase.convpay.service.ConveniencePayService;
import com.zerobase.convpay.type.ConvenienceType;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;

public class UserClient {
    public static void main(String[] args) {
        // '사용자'->편결이->머니

        ConveniencePayService conveniencePayService = new ConveniencePayService();
        
        // 결제 1000원
        PayRequest payRequest = new PayRequest(ConvenienceType.G25, 1000);
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        System.out.println("payResponse = " + payResponse);

        // 취소 500원

        PayCancelRequest payCancelRequest = new PayCancelRequest(ConvenienceType.G25, 300);
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(payCancelRequest);

        System.out.println("payCancelResponse = " + payCancelResponse);

    }
}
