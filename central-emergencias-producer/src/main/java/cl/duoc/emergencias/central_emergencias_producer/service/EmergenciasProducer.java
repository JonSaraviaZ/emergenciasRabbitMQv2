package cl.duoc.emergencias.central_emergencias_producer.service;

import cl.duoc.emergencias.central_emergencias_producer.model.Emergencias;
import cl.duoc.emergencias.central_emergencias_producer.config.RabbitNames;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class EmergenciasProducer{

    private final RabbitTemplate rabbitTemplate;

    public EmergenciasProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }
    public void enviar(Emergencias emergencias){
        String mensaje = emergencias.tipo() + "|" +
                    emergencias.ubicacion() + "|" +
                    emergencias.descripcion();

        rabbitTemplate.convertAndSend(RabbitNames.EXCHANGE,
                                        "",
                                        mensaje);
        System.out.println("[PRODUCER] Emergencias publicada: " + mensaje);   
    }
}