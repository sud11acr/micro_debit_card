package com.project.micro.debitcard.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "debitcard")
public class DebitCard {

    @Id
    private String idDebitCard;
    private String idCustomer;
    private String idMainAccount;
    private List<Account> accounts;
    private Date registrationDate;
    private Date modificationDate;
    private Boolean status;



}
