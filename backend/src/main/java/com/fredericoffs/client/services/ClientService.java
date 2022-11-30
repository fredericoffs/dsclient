package com.fredericoffs.client.services;

import com.fredericoffs.client.dto.ClientDTO;
import com.fredericoffs.client.entities.Client;
import com.fredericoffs.client.repositories.ClientRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

  @Autowired
  private ClientRepository repository;

  public List<ClientDTO> findAll() {
    List<Client> list = repository.findAll();
    return list
        .stream()
        .map(ClientDTO::new)
        .collect(Collectors.toList());
  }
}
