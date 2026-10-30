package cl.duoc.central_emergencias_admin.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service //La capa service toma todo lo de config y se conecta con Rabbit.
public class RabbitAdminService {
    //necesitamos un atributo de la clase
    private final RestClient client;

    public RabbitAdminService(RestClient rabbitAdminRestClient) {
        this.client = rabbitAdminRestClient;

    }
    //1 - Consultar todas las colas de RabbitMQ
    public String listarColas(){
        return client.get()
                .uri("/queues") //hacemos una solicitud GET a la API de administración de RabbitMQ para obtener la lista de colas
                .retrieve() //recuperamos la respuesta de la solicitud
                .body(String.class);

    }

    //2 - Consultar todos los exchanges de RabbitMQ

    public String listarExchanges(){
        return client.get()
                .uri("/exchanges") //hacemos una solicitud GET a la API de administración de RabbitMQ para obtener la lista de exchanges
                .retrieve() //recuperamos la respuesta de la solicitud
                .body(String.class);
    }




}
