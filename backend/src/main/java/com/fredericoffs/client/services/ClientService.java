package com.fredericoffs.client.services;

import com.fredericoffs.client.entities.Client;
import com.fredericoffs.client.repositories.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

  @Autowired
  private ClientRepository repository;

  public List<Client> findAll() {
    return repository.findAll();
  }
}
