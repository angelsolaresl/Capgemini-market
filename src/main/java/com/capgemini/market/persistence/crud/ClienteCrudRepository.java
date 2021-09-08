package com.capgemini.market.persistence.crud;

import com.capgemini.market.persistence.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteCrudRepository extends CrudRepository<Cliente, Integer> {

}
