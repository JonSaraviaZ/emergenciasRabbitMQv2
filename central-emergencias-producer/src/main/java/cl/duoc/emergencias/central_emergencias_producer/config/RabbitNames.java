package cl.duoc.emergencias.central_emergencias_producer.config;

public final class RabbitNames{

    private RabbitNames(){
    }
    public static final String EXCHANGE = "emergencias.exchange";
    public static final String POLICIA_QUEUE = "emergencias.policia";
    public static final String BOMBEROS_QUEUE = "emergencias.bomberos";
}