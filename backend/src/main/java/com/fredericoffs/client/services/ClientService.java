package com.fredericoffs.client.services;

import com.fredericoffs.client.dto.ClientDTO;
import com.fredericoffs.client.entities.Client;
import com.fredericoffs.client.repositories.ClientRepository;
import com.fredericoffs.client.services.exceptions.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

  @Autowired
  private ClientRepository repository;

  @Transactional(readOnly = true)
  public List<ClientDTO> findAll() {
    List<Client> list = repository.findAll();
    return list
        .stream()
        .map(ClientDTO::new)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
    Page<Client> list = repository.findAll(pageRequest);
    return list.map(ClientDTO::new);
  }

  @Transactional(readOnly = true)
  public ClientDTO findById(Long id) {
    Optional<Client> obj = repository.findById(id);
    Client entity = obj.orElseThrow(() -> new EntityNotFoundException("Client not found."));
    return new ClientDTO(entity);
  }

  @Transactional
  public ClientDTO insert(ClientDTO dto) {
    Client entity = new Client();
    entity.setName(dto.getName());
    entity.setCpf(dto.getCpf());
    entity.setChildren(dto.getChildren());
    entity.setBirthDate(dto.getBirthDate());
    entity.setIncome(dto.getIncome());
    entity = repository.save(entity);
    return new ClientDTO(entity);
  }
}
