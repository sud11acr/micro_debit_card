package com.project.micro.debitcard.controller;

import com.project.micro.debitcard.integration.DebitCardRequest;
import com.project.micro.debitcard.integration.DebitCardResponse;
import com.project.micro.debitcard.service.IDebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
@RestController
@RequestMapping("/debitcard")
public class DebitCardController {

    @Autowired
    private IDebitCardService service;
    @GetMapping("/findById/{id}")
    public Mono<ResponseEntity<DebitCardResponse>> findById(@PathVariable String id){
        return service.findById(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @GetMapping("/findAll")
    public Mono<ResponseEntity<Flux<DebitCardResponse>>> findAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.findAll()));
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<DebitCardResponse>>save(@Validated @RequestBody Mono<DebitCardRequest> debitCardRequest){
        return service.save(debitCardRequest)
                .map(p -> ResponseEntity.created(URI.create("/create".concat("/").concat(p.getIdDebitCard())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<DebitCardResponse>>update(@PathVariable String id,@RequestBody Mono<DebitCardRequest> debitCardRequest ){
        return service.update(id,debitCardRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return service.delete(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
    }
}
