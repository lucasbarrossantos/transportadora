package br.com.transportadora.service;

import br.com.transportadora.model.Transportadora;
import br.com.transportadora.repository.Transportadoras;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransportadoraService {

    private final Transportadoras transportadoras;

    public TransportadoraService(Transportadoras transportadoras) {
        this.transportadoras = transportadoras;
    }

    public Transportadora update(UUID id, Transportadora transportadora) {
        Optional<Transportadora> transportadoraSalvar = findByCodigo(id);

        BeanUtils.copyProperties(transportadora, transportadoraSalvar.get(), "id");
        return transportadoras.save(transportadoraSalvar.get());
    }

    private Optional<Transportadora> findByCodigo(UUID codigo) {
        Optional<Transportadora> transportadoraSalvar = transportadoras.findById(codigo);

        if (!transportadoraSalvar.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        return transportadoraSalvar;
    }
}
