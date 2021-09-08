package com.capgemini.market.domain.repository;

import com.capgemini.market.domain.Client;
import com.capgemini.market.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<Client> getAll();
    Optional<Client> getClient(Integer id);
    Client save(Client client);



}
