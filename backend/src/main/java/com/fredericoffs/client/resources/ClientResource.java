package com.fredericoffs.client.resources;

import com.fredericoffs.client.entities.Client;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

  @GetMapping
  public ResponseEntity<List<Client>> findAll() {
    List<Client> list = new ArrayList<>();
    list.add(new Client(1L, "Frederico Silva", "010101010000", 1000.00, Instant.parse("1979-08-31T12:51:00Z"), 0));
    return ResponseEntity.ok().body(list);
  }
}
