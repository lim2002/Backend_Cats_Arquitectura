package com.arquitectura_backend.arquitecturaBackend.Api;

import com.arquitectura_backend.arquitecturaBackend.Bl.DatosBl;
import com.arquitectura_backend.arquitecturaBackend.Dto.DatosDto;
import com.arquitectura_backend.arquitecturaBackend.Dto.MessageDto;
import com.arquitectura_backend.arquitecturaBackend.Dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
@RequestMapping("/api/v1/datos")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class DatosApi {
    private static final Logger logger = LoggerFactory.getLogger(DatosApi.class);
    @Autowired
    private DatosBl datosBl;

    @GetMapping("/user/")
    public ResponseEntity<MessageDto<Page<DatosDto>>> getDatosCuriososUser(@PageableDefault(size = 10) Pageable pageable) {
        logger.info("Solicitud para obtener datos curiosos del usuario recibida");
        try {
            Page<DatosDto> datosPage = datosBl.getall(pageable);
            logger.info("Datos curiosos obtenidos exitosamente");
            return ResponseEntity.ok(new MessageDto<>(200, datosPage, "Datos recuperados exitosamente"));
        } catch (Exception e) {
            logger.error("Error al obtener datos curiosos del usuario: {}", e.getMessage());
            return ResponseEntity.ok(new MessageDto<>(500, null, "error"));
        }
    }

    @PostMapping("/admin/add")
    public ResponseEntity<MessageDto<ResponseDto>> saveDatosCuriosos(@RequestBody ResponseDto responseDto) {
        logger.info("Solicitud para guardar datos curiosos recibida");
        try {
            datosBl.guardar(responseDto);
            logger.info("Datos curiosos guardados exitosamente");
            return ResponseEntity.ok(new MessageDto<>(200, null, "Datos recuperados exitosamente"));
        } catch (Exception e) {
            logger.error("Error al guardar datos curiosos: {}", e.getMessage());
            return ResponseEntity.ok(new MessageDto<>(500, null, "error"));
        }
    }

    @PutMapping("/admin/edit/{id}")
    public ResponseEntity<MessageDto<ResponseDto>> updateDatosCuriosos(@RequestBody ResponseDto responseDto, @PathVariable int id) {
        logger.info("Solicitud para actualizar datos curiosos con ID {} recibida", id);
        try {
            datosBl.modificar(responseDto, id);
            logger.info("Datos curiosos con ID {} actualizados exitosamente", id);
            return ResponseEntity.ok(new MessageDto<>(200, null, "Datos recuperados exitosamente"));
        } catch (Exception e) {
            logger.error("Error al actualizar datos curiosos con ID {}: {}", id, e.getMessage());
            return ResponseEntity.ok(new MessageDto<>(500, null, "error"));
        }
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<MessageDto<ResponseDto>> deleteDatosCuriosos(@PathVariable int id) {
        logger.info("Solicitud para eliminar datos curiosos con ID {} recibida", id);
        try {
            datosBl.eliminar(id);
            logger.info("Datos curiosos con ID {} eliminados exitosamente", id);
            return ResponseEntity.ok(new MessageDto<>(200, null, "Datos recuperados exitosamente"));
        } catch (Exception e) {
            logger.error("Error al eliminar datos curiosos con ID {}: {}", id, e.getMessage());
            return ResponseEntity.ok(new MessageDto<>(500, null, "error"));
        }
    }
}
