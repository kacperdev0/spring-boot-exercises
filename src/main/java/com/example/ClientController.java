package com.example;

import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/clients")
public class ClientController {
    private final ClientRepo clientRepo;

    public ClientController(ClientRepo cr) {
        this.clientRepo = cr;
    }

    @PostConstruct
    public void init() {
        clientRepo.save(new ClientModel("John Doe", "john.doe@baeldung.com"));
        clientRepo.save(new ClientModel("Jane Smith", "jane.smith@baeldung.com"));
    }

    @GetMapping
    public List<ClientModel> getClients() {
        return clientRepo.findAll();
    }

    @GetMapping("/{id}")
    public ClientModel getClient(Long id) {
        return clientRepo.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity addClient(@RequestBody ClientModel cm) throws URISyntaxException {
        ClientModel saved = clientRepo.save(cm);
        return ResponseEntity.created(new URI("/clients/" + saved.getId())).body(saved);
    }
}
