package cl.duoc.emergencias.central_emergencias_consumer.listener;
import cl.duoc.emergencias.central_emergencias_consumer.config.RabbitNames;

import java.io.IOException;

import javax.management.RuntimeErrorException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
public class BomberosConsumer{
    @RabbitListener(queues = RabbitNames.BOMBEROS_QUEUE)
    public void recibir(String mensaje,Message message ,Channel channel)
        throws IOException{

        System.out.println("[BOMBEROS] Emergencia Recibida: " + mensaje);
        long deliveryTag = 0;
        try{
            deliveryTag = message.getMessageProperties().getDeliveryTag();
            //Simulacion de falla en el procesamiento
            if(mensaje.contains("ERROR")){
                throw new RuntimeException("No fue prosible el procesamiento");
            }
            System.out.println("[BOMBEROS] Emergencia fue procesada");
        }
        catch(RuntimeException ex){
            System.out.println("[BOMBEROS] Error: " + ex.getMessage());
            channel.basicNack(deliveryTag, false, false);
            System.out.println("[BOMBEROS] NACK enviado");
            return;
        }
        //solo confirmamos si el procesamiento fue exitoso
        channel.basicAck(deliveryTag, false);
        System.out.println("[BOMBEROS] ACK enviado");

    }
}