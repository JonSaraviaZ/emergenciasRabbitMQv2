package cl.duoc.emergencias.central_emergencias_consumer.config;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig{
    @Bean
    public FanoutExchange emergenciasExchange(){
        return new FanoutExchange(RabbitNames.EXCHANGE,true,false);
    }
    @Bean
    public Queue policiaQueue(){
        return QueueBuilder.durable(RabbitNames.POLICIA_QUEUE).
            withArgument("x-dead-letter-exchange",RabbitNames.POLICIA_DLX).
            withArgument("x-dead-letter-routing-key",RabbitNames.POLICIA_QUEUE)
            .build();
    }
    @Bean
    public Queue bomberosQueue(){
        return QueueBuilder.durable(RabbitNames.BOMBEROS_QUEUE).
            withArgument("x-dead-letter-exchange",RabbitNames.BOMBEROS_DLX).
            withArgument("x-dead-letter-routing-key",RabbitNames.BOMBEROS_QUEUE)
            .build();
    }
    @Bean
    public Binding policiaBinding(Queue policiaQueue, FanoutExchange emergenciasExchange){
        return BindingBuilder.bind(policiaQueue).to(emergenciasExchange);
    }
    @Bean
    public Binding bomberosBinding(Queue bomberosQueue, FanoutExchange emergenciasExchange){
        return BindingBuilder.bind(bomberosQueue).to(emergenciasExchange);
    }

    

    //actualizacion ACK y NACK
    @Bean
    public DirectExchange bomberosDlx(){
        return new DirectExchange(RabbitNames.BOMBEROS_DLX,true,false);
    }
    @Bean
    public Queue bomberosDlq(){
        return QueueBuilder.durable(RabbitNames.BOMBEROS_DLQ).build();
    }
    @Bean
    public Binding bomberosDlqBinding(Queue bomberosDlq, DirectExchange bomberosDlx){
        return BindingBuilder.bind(bomberosDlq).to(bomberosDlx).with(RabbitNames.BOMBEROS_QUEUE);
    }


    //
    @Bean
    public DirectExchange policiaDlx(){
        return new DirectExchange(RabbitNames.POLICIA_DLX,true,false);
    }
    @Bean
    public Queue policiaDlq(){
        return QueueBuilder.durable(RabbitNames.POLICIA_DLQ).build();
    }
    @Bean
    public Binding policiaDlqBinding(Queue policiaDlq, DirectExchange policiaDlx){
        return BindingBuilder.bind(policiaDlq).to(policiaDlx).with(RabbitNames.POLICIA_QUEUE);
    }
    
}