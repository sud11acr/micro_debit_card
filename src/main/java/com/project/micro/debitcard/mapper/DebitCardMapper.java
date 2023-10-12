package com.project.micro.debitcard.mapper;

import com.project.micro.debitcard.integration.DebitCardRequest;
import com.project.micro.debitcard.integration.DebitCardResponse;
import com.project.micro.debitcard.model.DebitCard;
import org.springframework.beans.BeanUtils;

public class DebitCardMapper {

    public static DebitCard toDebitCardModelReq(DebitCardRequest debitCardRequest){

        DebitCard debitCard=new DebitCard();
        BeanUtils.copyProperties(debitCardRequest,debitCard);
        return debitCard;
    }

    public static DebitCardResponse toDebitCardModelRes(DebitCard debitCard){

        DebitCardResponse debitCardResponse=new DebitCardResponse();
        BeanUtils.copyProperties(debitCard,debitCardResponse);
        return debitCardResponse;
    }
}
