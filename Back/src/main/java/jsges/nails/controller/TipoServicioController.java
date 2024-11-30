package jsges.nails.controller;

import jsges.nails.DTO.ClienteDTO;
import jsges.nails.DTO.TipoServicioDTO;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.model.Cliente;
import jsges.nails.model.TipoServicio;
import jsges.nails.service.servicios_Interface.ITipoServicioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value="${path_mapping}")
@CrossOrigin(value="${path_cross}")
public class TipoServicioController {
    private static final Logger logger = LoggerFactory.getLogger(TipoServicioController.class);
    @Autowired
    private ITipoServicioService modelService;

    public TipoServicioController() {

    }

    @GetMapping("/tiposServicios")
    public ResponseEntity<List<TipoServicioDTO>> getAll() {
        logger.info("Obteniendo todos los tipos de servicio");

        // Obtén la lista de ClienteDTO directamente del servicio
        List<TipoServicioDTO> listadoDTO = modelService.listar();

        logger.info("Se encontraron {} clientes", listadoDTO.size());
        return ResponseEntity.ok(listadoDTO);  // Retorna la lista de DTOs
    }


    @GetMapping({"/tiposServiciosPageQuery"})
    public ResponseEntity<Page<TipoServicioDTO>> getItems(
            @RequestParam(defaultValue = "") String consulta,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "${max_page}") int size) {

        Page<TipoServicioDTO> pageResult = modelService.listarPaginado(consulta, PageRequest.of(page, size));

        return ResponseEntity.ok().body(pageResult);
    }


    @PostMapping("/tiposServicios")
    public ResponseEntity<TipoServicioDTO> agregar(@RequestBody TipoServicioDTO modelDTO) {
        logger.info("Creando un nuevo cliente.");

        try {
            // Validación del DTO
            if (modelDTO.getDenominacion() == null ) {
                return ResponseEntity.badRequest().body(null);
            }

            // Crear y guardar el modelo
            TipoServicio newModel = modelService.crearDesdeDTO(modelDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new TipoServicioDTO(newModel));
        } catch (NoSuchElementException e) {
            logger.error("Error al buscar la línea: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            logger.error("Error al guardar el artículo: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/tipoServicioEliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            modelService.eliminarTipoServicio(id);
            return ResponseEntity.noContent().build(); // Devuelve 204 No Content
        } catch (RecursoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Devuelve 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Manejo genérico de errores
        }
    }

    @GetMapping("/tiposServicios/{id}")
    public ResponseEntity<TipoServicioDTO> getPorId(@PathVariable Integer id) {
        try {
            TipoServicioDTO tipoServicioDTO = modelService.obtenerTipoServicioPorId(id);
            return ResponseEntity.ok(tipoServicioDTO);
        } catch (RecursoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Devuelve 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Manejo genérico de errores
        }
    }


    @PutMapping("/tiposServicios/{id}")
    public ResponseEntity<TipoServicioDTO> actualizar(@PathVariable Integer id, @RequestBody TipoServicioDTO modelRecibido) {

        try {
            TipoServicioDTO tiposServicioActualizado = modelService.actualizarTipoServicio(id, modelRecibido);
            return ResponseEntity.ok(tiposServicioActualizado);
        } catch (RecursoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Devuelve 404 si no existe el artículo
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Manejo genérico de errores
        }
    }

}

