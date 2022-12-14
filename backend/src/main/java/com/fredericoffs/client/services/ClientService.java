package com.fredericoffs.client.services;

import com.fredericoffs.client.dto.ClientDTO;
import com.fredericoffs.client.entities.Client;
import com.fredericoffs.client.repositories.ClientRepository;
import com.fredericoffs.client.services.exceptions.DatabaseException;
import com.fredericoffs.client.services.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Client not found."));
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

  @Transactional
  public ClientDTO update(Long id, ClientDTO dto) {
    try {
      Client entity = repository.getOne(id); //getReferenceById novas vers??es do SpringBoot
      entity.setName(dto.getName());
      entity.setCpf(dto.getCpf());
      entity.setIncome(dto.getIncome());
      entity.setChildren(dto.getChildren());
      entity.setBirthDate(dto.getBirthDate());
      entity = repository.save(entity);
      return new ClientDTO(entity);
    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Client id not found " + id);
    }
  }

  public void delete(Long id) {
    try {
      repository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException("Client id not found " + id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Integrity violation.");
    }
  }
}
