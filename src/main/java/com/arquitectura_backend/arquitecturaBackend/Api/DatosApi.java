package com.arquitectura_backend.arquitecturaBackend.Api;

import com.arquitectura_backend.arquitecturaBackend.Bl.DatosBl;
import com.arquitectura_backend.arquitecturaBackend.Dto.DatosDto;
import com.arquitectura_backend.arquitecturaBackend.Dto.MessageDto;
import com.arquitectura_backend.arquitecturaBackend.Dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/datos")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class DatosApi {

    @Autowired
    private DatosBl datosBl;

    @GetMapping("/user/")
    public ResponseEntity<MessageDto<List<DatosDto>>> getDatosCuriososUser() {
        List<DatosDto> responseDtoList = datosBl.getall();
        try {
            return ResponseEntity.ok(new MessageDto<>(200, responseDtoList, "Datos retrieved successfully"));
        } catch (Exception e) {
            return ResponseEntity.ok(new MessageDto<>(500, null, "error"));
        }
    }

    @PostMapping("/")
    public ResponseEntity<MessageDto<ResponseDto>> saveDatosCuriosos(@RequestBody ResponseDto responseDto) {
        try {
            datosBl.guardar(responseDto);
            return ResponseEntity.ok(new MessageDto<>(200, null, "Datos retrieved successfully"));
        } catch (Exception e) {
            return ResponseEntity.ok(new MessageDto<>(500, null, "error"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDto<ResponseDto>> updateDatosCuriosos(@RequestBody ResponseDto responseDto, @PathVariable int id) {
        try {
            datosBl.modificar(responseDto, id);
            return ResponseEntity.ok(new MessageDto<>(200, null, "Datos retrieved successfully"));
        } catch (Exception e) {
            return ResponseEntity.ok(new MessageDto<>(500, null, "error"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto<ResponseDto>> deleteDatosCuriosos(@PathVariable int id) {
        try {
            datosBl.eliminar(id);
            return ResponseEntity.ok(new MessageDto<>(200, null, "Datos retrieved successfully"));
        } catch (Exception e) {
            return ResponseEntity.ok(new MessageDto<>(500, null, "error"));
        }
    }
}
