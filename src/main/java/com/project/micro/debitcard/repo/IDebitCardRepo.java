package com.project.micro.debitcard.repo;

import com.project.micro.debitcard.model.DebitCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IDebitCardRepo extends ReactiveMongoRepository<DebitCard,String> {
}
