package jsges.nails.controller;

import jsges.nails.DTO.ArticuloVentaDTO;
import jsges.nails.DTO.ItemServicioDTO;
import jsges.nails.DTO.ServicioDTO;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.model.ArticuloVenta;
import jsges.nails.model.ItemServicio;
import jsges.nails.model.Servicio;
import jsges.nails.model.TipoServicio;
import jsges.nails.service.servicios_Interface.IClienteService;
import jsges.nails.service.servicios_Interface.IItemServicioService;
import jsges.nails.service.servicios_Interface.IServicioService;
import jsges.nails.service.servicios_Interface.ITipoServicioService;
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
public class ServicioController {
    private static final Logger logger = LoggerFactory.getLogger(ServicioController.class);
    @Autowired
    private IServicioService modelService;
    @Autowired
    private IClienteService clienteService;

    @Autowired
    private ITipoServicioService tipoServicioService;

    @Autowired
    private IItemServicioService itemServicioService;

    public ServicioController() {

    }


    @GetMapping({"/servicios"})
    public ResponseEntity<List<ServicioDTO>> getAll() {
        logger.info("entra y trae todos los articulos");

        try {
            List<ServicioDTO> servicios = modelService.listar();
            return ResponseEntity.ok(servicios);
        } catch (Exception e) {
            logger.error("Error al obtener los artículos: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/servicio/{id}")
    public ResponseEntity<ServicioDTO> getPorId(@PathVariable Integer id) {
        try {
            ServicioDTO servicioDTO = modelService.obtenerServicioPorId(id);
            return ResponseEntity.ok(servicioDTO);
        } catch (RecursoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Devuelve 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Manejo genérico de errores
        }
    }


    @GetMapping({"/serviciosPageQuery"})
    public ResponseEntity<Page<ServicioDTO>> getItems(
            @RequestParam(defaultValue = "") String consulta,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "${max_page}") int size) {

        Page<ServicioDTO> pageResult = modelService.listarPaginado(consulta, PageRequest.of(page, size));

        return ResponseEntity.ok().body(pageResult);
    }



    @PostMapping("/servicios")
    public ResponseEntity<ServicioDTO> agregar(@RequestBody ServicioDTO modelDTO) {

        logger.info("Creando un nuevo servicio.");

        try {
            // Validación del DTO
            if (modelDTO.getCliente() == null || modelDTO.getClienteRazonSocial().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }

            // Crear y guardar el modelo
            Servicio newModel = modelService.crearDesdeDTO(modelDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ServicioDTO(newModel));
        } catch (NoSuchElementException e) {
            logger.error("Error al buscar la línea: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            logger.error("Error al guardar el artículo: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @DeleteMapping("/servicioEliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            modelService.eliminarServicio(id);
            return ResponseEntity.noContent().build(); // Devuelve 204 No Content
        } catch (RecursoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Devuelve 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Manejo genérico de errores
        }
    }



    @PutMapping("/servicios/{id}")
    public ResponseEntity<ServicioDTO> actualizar(@PathVariable Integer id, @RequestBody ServicioDTO modelRecibido) {

        try {
            ServicioDTO servicioActualizado = modelService.actualizarServicio(id, modelRecibido);
            return ResponseEntity.ok(servicioActualizado);
        } catch (RecursoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Devuelve 404 si no existe el artículo
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Manejo genérico de errores
        }
    }

}

