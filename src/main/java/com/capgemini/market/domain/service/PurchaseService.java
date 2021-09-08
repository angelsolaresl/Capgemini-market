package com.capgemini.market.domain.service;

import com.capgemini.market.domain.Purchase;
import com.capgemini.market.domain.repository.PurshaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private PurshaseRepository purshaseRepository;

    public List<Purchase> getAll(){
        return purshaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByClient(String clientId){
         return purshaseRepository.getByClient(clientId);
    }
    public Purchase save(Purchase purchase){
        return purshaseRepository.save(purchase);
    }
}

