package com.capgemini.market.persistence;

import com.capgemini.market.domain.Client;
import com.capgemini.market.domain.repository.ClientRepository;
import com.capgemini.market.persistence.crud.ClienteCrudRepository;
import com.capgemini.market.persistence.entity.Cliente;
import com.capgemini.market.persistence.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository implements ClientRepository {
   @Autowired
   private ClienteCrudRepository clienteCrudRepository;
   @Autowired
   private ClientMapper mapper;

    @Override
    public List<Client> getAll() {
        List<Cliente> clientes = (List<Cliente>) clienteCrudRepository.findAll();
        return mapper.toClients(clientes);
    }

    @Override
    public Optional<Client> getClient(Integer id) {
        return clienteCrudRepository.findById(id).map(cliente -> mapper.toClient(cliente));
    }

    @Override
    public Client save(Client client) {
        Cliente cliente = mapper.toCliente(client);//Se invoca el mapo inverso
        return mapper.toClient(clienteCrudRepository.save(cliente));
    }

}
