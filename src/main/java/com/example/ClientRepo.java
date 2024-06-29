package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ClientModel;

public interface ClientRepo extends JpaRepository<ClientModel, Long> {

}
