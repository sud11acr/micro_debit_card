package com.project.micro.debitcard.service;

import com.project.micro.debitcard.integration.DebitCardRequest;
import com.project.micro.debitcard.integration.DebitCardResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IDebitCardService {

    Mono<DebitCardResponse> save(Mono<DebitCardRequest> debitCardRequest);
    Mono<DebitCardResponse> update(String id,Mono<DebitCardRequest> debitCardRequest);
    Flux<DebitCardResponse> findAll();
    Mono<DebitCardResponse> findById(String id);
    Mono<Void> delete(String id);
}
