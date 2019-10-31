package br.com.transportadora.repository;

import br.com.transportadora.model.Transportadora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Transportadoras extends JpaRepository<Transportadora, UUID> {

}
