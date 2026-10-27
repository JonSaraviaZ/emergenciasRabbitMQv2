package cl.duoc.emergencias.central_emergencias_consumer.config;

public final class RabbitNames{

    private RabbitNames(){
    }
    public static final String EXCHANGE = "emergencias.exchange";
    public static final String POLICIA_QUEUE = "emergencias.policia";
    public static final String POLICIA_DLX = "emergencias.policia.dlx";
    public static final String POLICIA_DLQ = "emergencias.policia.dlq";
    public static final String BOMBEROS_QUEUE = "emergencias.bomberos";
    public static final String BOMBEROS_DLX = "emergencias.bomberos.dlx";
    public static final String BOMBEROS_DLQ = "emergencias.bomberos.dlq";
}