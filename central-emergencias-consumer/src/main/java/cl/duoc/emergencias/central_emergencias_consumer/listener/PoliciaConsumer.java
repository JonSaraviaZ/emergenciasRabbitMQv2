package cl.duoc.emergencias.central_emergencias_consumer.listener;
import cl.duoc.emergencias.central_emergencias_consumer.config.RabbitNames;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;


@Component
public class PoliciaConsumer{
    @RabbitListener(queues = RabbitNames.POLICIA_QUEUE)
    public void recibir(String mensaje, 
        Message message,
        Channel channel) throws IOException{
        
        System.out.println("[POLICIA] Emergencia Recibida: " + mensaje);
        long deliveryTag = 0;

        try{
            deliveryTag = message.getMessageProperties().getDeliveryTag();
            //Simulacion de falla en el procesamiento
            if(mensaje.contains("ERROR")){
                throw new RuntimeException("No fue prosible el procesamiento");
            }
            System.out.println("[POLICIA] Emergencia fue procesada");
        }
        catch(RuntimeException ex){
            System.out.println("[POLICIA] Error: " + ex.getMessage());
            channel.basicNack(deliveryTag, false, false);
            System.out.println("[POLICIA] NACK enviado");
            return;
        }

        channel.basicAck(deliveryTag, false);

        System.out.println("[POLICIA] ACK enviado");
    }
}