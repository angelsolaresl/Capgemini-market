package com.capgemini.market.domain.repository;

import com.capgemini.market.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurshaseRepository {
    //Aqui van los metodos
    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String clietnId);
    Purchase save(Purchase purchase);
}
