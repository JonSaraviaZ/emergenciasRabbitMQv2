package cl.duoc.emergencias.central_emergencias_producer.controller;

import cl.duoc.emergencias.central_emergencias_producer.model.Emergencias;
import cl.duoc.emergencias.central_emergencias_producer.service.EmergenciasProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/emergencias")
public class EmergenciasController{
    private final EmergenciasProducer emergenciasProducer;

    public EmergenciasController(EmergenciasProducer emergenciasProducer){
        this.emergenciasProducer = emergenciasProducer;
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody Emergencias emergencias){
        emergenciasProducer.enviar(emergencias);
        return ResponseEntity.accepted().body("Emergencia Recibida");
    }

}