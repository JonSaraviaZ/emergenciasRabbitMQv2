package cl.duoc.emergencias.central_emergencias_producer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.FanoutExchange;

@Configuration
public class RabbitConfig{
    @Bean
    public FanoutExchange emergenciasExchange(){
        return new FanoutExchange(
            RabbitNames.EXCHANGE,
            true,
            false
        );
    }
}