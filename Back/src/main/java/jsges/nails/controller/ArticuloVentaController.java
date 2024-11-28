package jsges.nails.controller;

import jsges.nails.DTO.ArticuloVentaDTO;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.model.ArticuloVenta;
import jsges.nails.service.servicios_Interface.IArticuloVentaService;
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

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")

public class ArticuloVentaController {

    private static final Logger logger = LoggerFactory.getLogger(ArticuloVentaController.class);

    @Autowired
    private IArticuloVentaService modelService;

    @Autowired
    private ILineaService lineaService;

    public ArticuloVentaController() {

    }

    @GetMapping({"/articulos"})
    public ResponseEntity<List<ArticuloVentaDTO>> getAll() {
        logger.info("entra y trae todos los articulos");

        try {
            List<ArticuloVentaDTO> articulos = modelService.listar();
            return ResponseEntity.ok(articulos);
        } catch (Exception e) {
            logger.error("Error al obtener los artículos: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping({"/articulosPageQuery"})
    public ResponseEntity<Page<ArticuloVentaDTO>> getItems(
            @RequestParam(defaultValue = "") String consulta,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "${max_page}") int size) {

        Page<ArticuloVentaDTO> pageResult = modelService.listarPaginado(consulta, PageRequest.of(page, size));

        return ResponseEntity.ok().body(pageResult);
    }


    @PostMapping("/articulos")
    public ResponseEntity<ArticuloVentaDTO> agregar(@RequestBody ArticuloVentaDTO modelDTO) {
        logger.info("Creando un nuevo artículo de venta.");

        try {
            // Validación del DTO
            if (modelDTO.getLinea() == null || modelDTO.getDenominacion().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }

            // Crear y guardar el modelo
            ArticuloVenta newModel = modelService.crearDesdeDTO(modelDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ArticuloVentaDTO(newModel));
        } catch (NoSuchElementException e) {
            logger.error("Error al buscar la línea: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            logger.error("Error al guardar el artículo: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @DeleteMapping("/articuloEliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            modelService.eliminarArticulo(id);
            return ResponseEntity.noContent().build(); // Devuelve 204 No Content
        } catch (RecursoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Devuelve 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Manejo genérico de errores
        }
    }


    @GetMapping("/articulos/{id}")
    public ResponseEntity<ArticuloVentaDTO> getPorId(@PathVariable Integer id) {
        try {
            ArticuloVentaDTO articuloVentaDTO = modelService.obtenerArticuloPorId(id);
            return ResponseEntity.ok(articuloVentaDTO);
        } catch (RecursoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Devuelve 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Manejo genérico de errores
        }
    }


    @PutMapping("/articulos/{id}")
    public ResponseEntity<ArticuloVentaDTO> actualizar(@PathVariable Integer id, @RequestBody ArticuloVentaDTO modelRecibido) {

        try {
            ArticuloVentaDTO articuloActualizado = modelService.actualizarArticulo(id, modelRecibido);
            return ResponseEntity.ok(articuloActualizado);
        } catch (RecursoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Devuelve 404 si no existe el artículo
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Manejo genérico de errores
        }
    }


}

