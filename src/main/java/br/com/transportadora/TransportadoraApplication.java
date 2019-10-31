package br.com.transportadora;

import br.com.transportadora.config.property.DesafioApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DesafioApiProperty.class)
public class TransportadoraApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportadoraApplication.class, args);
    }

}
