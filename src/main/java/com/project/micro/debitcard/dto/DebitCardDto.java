package com.project.micro.debitcard.dto;

import com.project.micro.debitcard.model.Account;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DebitCardDto {
    private String idDebitCard;
    private String idCustomer;
    private String idMainAccount;
    private List<Account> accounts;
}
