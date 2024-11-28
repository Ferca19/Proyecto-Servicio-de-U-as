package jsges.nails.controller;

import jsges.nails.DTO.ArticuloVentaDTO;
import jsges.nails.DTO.LineaDTO;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.model.ArticuloVenta;
import jsges.nails.model.Linea;
import jsges.nails.service.servicios_Interface.ILineaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class LineaController {
    private static final Logger logger = LoggerFactory.getLogger(LineaController.class);
    @Autowired
    private ILineaService modelService;

    public LineaController() {

    }

    @GetMapping({"/lineas"})
    public ResponseEntity<List<LineaDTO>> getAll() {
        logger.info("entra y trae todos las lineas");

        try {
            List<LineaDTO> lineas = modelService.listar();
            return ResponseEntity.ok(lineas);
        } catch (Exception e) {
            logger.error("Error al obtener las lineas: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping({"/lineasPageQuery"})
    public ResponseEntity<Page<LineaDTO>> getItems(
            @RequestParam(defaultValue = "") String consulta,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "${max_page}") int size) {

        Page<LineaDTO> pageResult = modelService.listarPaginado(consulta, PageRequest.of(page, size));

        return ResponseEntity.ok().body(pageResult);
    }


    @PostMapping("/linea")
    public ResponseEntity<LineaDTO> agregar(@RequestBody LineaDTO modelDTO) {
        logger.info("Creando una nueva linea.");

        try {
            // Validación del DTO
            if (modelDTO.getObservacion() == null || modelDTO.getDenominacion().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }

            // Crear y guardar el modelo
            Linea newModel = modelService.crearDesdeDTO(modelDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new LineaDTO(newModel));
        } catch (NoSuchElementException e) {
            logger.error("Error al buscar la observacion: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            logger.error("Error al guardar la linea: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @DeleteMapping("/lineaEliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            modelService.eliminarLinea(id);
            return ResponseEntity.noContent().build(); // Devuelve 204 No Content
        } catch (RecursoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Devuelve 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Manejo genérico de errores
        }
    }

    @GetMapping("/linea/{id}")
    public ResponseEntity<LineaDTO> getPorId(@PathVariable Integer id) {
        try {
            LineaDTO lineaDto = modelService.obtenerLineaPorId(id);
            return ResponseEntity.ok(lineaDto);
        } catch (RecursoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Devuelve 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Manejo genérico de errores
        }
    }


    @PutMapping("/linea/{id}")
    public ResponseEntity<LineaDTO> actualizar(@PathVariable Integer id, @RequestBody LineaDTO modelRecibido) {

        try {
            LineaDTO lineaActualizada = modelService.actualizarLinea(id, modelRecibido);
            return ResponseEntity.ok(lineaActualizada);
        } catch (RecursoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Devuelve 404 si no existe el artículo
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Manejo genérico de errores
        }
    }

}

