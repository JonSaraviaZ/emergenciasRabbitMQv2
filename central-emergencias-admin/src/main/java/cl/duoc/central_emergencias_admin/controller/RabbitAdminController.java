package cl.duoc.central_emergencias_admin.controller;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import cl.duoc.central_emergencias_admin.service.RabbitAdminService;
import org.springframework.web.bind.annotation.GetMapping;
//importar ReponseEntity
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/admin")
public class RabbitAdminController {
    private final RabbitAdminService service;

    public RabbitAdminController(RabbitAdminService service) {
        this.service = service;
    }

    @GetMapping(value = "/colas", produces = MediaType.APPLICATION_JSON_VALUE)
    
    public ResponseEntity<String> listarColas() {
        return ResponseEntity.ok(service.listarColas());
    }


}
