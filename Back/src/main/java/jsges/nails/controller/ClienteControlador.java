package jsges.nails.controller;

import jsges.nails.DTO.ArticuloVentaDTO;
import jsges.nails.DTO.ClienteDTO;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.model.ArticuloVenta;
import jsges.nails.model.Cliente;
import jsges.nails.service.servicios_Interface.IClienteService;
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

public class ClienteControlador {

    private static final Logger logger = LoggerFactory.getLogger(ClienteControlador.class);

    @Autowired
    private IClienteService modelService;


    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> getAll() {
        logger.info("Obteniendo todos los clientes");

        // Obtén la lista de ClienteDTO directamente del servicio
        List<ClienteDTO> listadoDTO = modelService.listar();

        logger.info("Se encontraron {} clientes", listadoDTO.size());
        return ResponseEntity.ok(listadoDTO);  // Retorna la lista de DTOs
    }


    @GetMapping({"/clientesPageQuery"})
    public ResponseEntity<Page<ClienteDTO>> getItems(
            @RequestParam(defaultValue = "") String consulta,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "${max_page}") int size) {

        Page<ClienteDTO> pageResult = modelService.listarPaginado(consulta, PageRequest.of(page, size));

        return ResponseEntity.ok().body(pageResult);
    }



    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> agregar(@RequestBody ClienteDTO modelDTO) {
        logger.info("Creando un nuevo cliente.");

        try {
            // Validación del DTO
            if (modelDTO.getRazonSocial() == null || modelDTO.getCelular().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(null);
            }

            // Crear y guardar el modelo
            Cliente newModel = modelService.crearDesdeDTO(modelDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ClienteDTO(newModel));
        } catch (NoSuchElementException e) {
            logger.error("Error al buscar la línea: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            logger.error("Error al guardar el artículo: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }




    @DeleteMapping("/clienteEliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            modelService.eliminarCliente(id);
            return ResponseEntity.noContent().build(); // Devuelve 204 No Content
        } catch (RecursoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Devuelve 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Manejo genérico de errores
        }
    }


    @GetMapping("/clientes/{id}")
    public ResponseEntity<ClienteDTO> getPorId(@PathVariable Integer id) {
        try {
            ClienteDTO clienteVentaDTO = modelService.obtenerClientePorId(id);
            return ResponseEntity.ok(clienteVentaDTO);
        } catch (RecursoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Devuelve 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Manejo genérico de errores
        }
    }


    @PutMapping("/clientes/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Integer id, @RequestBody ClienteDTO modelRecibido) {

        try {
            ClienteDTO clienteActualizado = modelService.actualizarCliente(id, modelRecibido);
            return ResponseEntity.ok(clienteActualizado);
        } catch (RecursoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Devuelve 404 si no existe el artículo
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Manejo genérico de errores
        }
    }


}
