package br.com.transportadora.controller;

import br.com.transportadora.event.RecursoCriadoEvent;
import br.com.transportadora.model.Transportadora;
import br.com.transportadora.repository.Transportadoras;
import br.com.transportadora.service.TransportadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/transportadoras")
public class TransportadoraController {

    private final Transportadoras transportadoras;
    private final ApplicationEventPublisher publisher;
    private final TransportadoraService transportadoraService;



    @Autowired
    public TransportadoraController(Transportadoras transportadoras,
                                    ApplicationEventPublisher publisher,
                                    TransportadoraService transportadoraService) {
        this.transportadoras = transportadoras;
        this.publisher = publisher;
        this.transportadoraService = transportadoraService;
    }

    @GetMapping
    public Page<Transportadora> getAll(Pageable pageable) {
        return transportadoras.findAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Transportadora> save(@Valid @RequestBody Transportadora transportadora,
                                               HttpServletResponse response) {
        Transportadora transportadoraSalvar = transportadoras.save(transportadora);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, transportadoraSalvar.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(transportadoraSalvar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @Valid
                                    @RequestBody Transportadora transportadora) {
        return ResponseEntity.ok(transportadoraService.update(id, transportadora));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        transportadoras.deleteById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transportadora> findById(@PathVariable("id") UUID id) {
        Optional<Transportadora> transportadora = transportadoras.findById(id);
        if (transportadora.isPresent()) {
            return ResponseEntity.ok().body(transportadora.get());
        } else
            return ResponseEntity.notFound().build();
    }

}
