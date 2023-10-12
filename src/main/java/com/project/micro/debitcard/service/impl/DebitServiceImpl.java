package com.project.micro.debitcard.service.impl;

import com.project.micro.debitcard.integration.DebitCardRequest;
import com.project.micro.debitcard.integration.DebitCardResponse;
import com.project.micro.debitcard.mapper.DebitCardMapper;
import com.project.micro.debitcard.model.DebitCard;
import com.project.micro.debitcard.repo.IDebitCardRepo;
import com.project.micro.debitcard.service.IDebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class DebitServiceImpl implements IDebitCardService {

    @Autowired
    private IDebitCardRepo repo;
    @Override
    public Mono<DebitCardResponse> save(Mono<DebitCardRequest> debitCardRequest) {
        return debitCardRequest.map(DebitCardMapper::toDebitCardModelReq)
                .flatMap(
                        p->{
                            p.setRegistrationDate(new Date());
                            p.setModificationDate(new Date());
                            p.setStatus(true);
                            return repo.save(p);
                        })
                .map(DebitCardMapper::toDebitCardModelRes);
    }

    @Override
    public Mono<DebitCardResponse> update(String id, Mono<DebitCardRequest> debitCardRequest) {
        Mono<DebitCard> monoBody = debitCardRequest.map(DebitCardMapper::toDebitCardModelReq);
        Mono<DebitCard> monoBD = repo.findById(id);

        return monoBD.zipWith(monoBody,(bd,pl)->{
                    bd.setModificationDate(new Date());
                    bd.setAccounts(pl.getAccounts());
                    bd.setIdMainAccount(pl.getIdMainAccount());
                    return bd;
                }).flatMap(p->repo.save(p))
                .map(DebitCardMapper::toDebitCardModelRes);
    }

    @Override
    public Flux<DebitCardResponse> findAll() {
        return repo.findAll().map(DebitCardMapper::toDebitCardModelRes);
    }

    @Override
    public Mono<DebitCardResponse> findById(String id) {
        return repo.findById(id).map(DebitCardMapper::toDebitCardModelRes);
    }

    @Override
    public Mono<Void> delete(String id) {
        return repo.deleteById(id);
    }
}
