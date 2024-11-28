package jsges.nails.service.servicios;

import jsges.nails.DTO.ArticuloVentaDTO;
import jsges.nails.DTO.ClienteDTO;
import jsges.nails.DTO.ServicioDTO;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.model.ArticuloVenta;
import jsges.nails.model.Cliente;
import jsges.nails.model.Linea;
import jsges.nails.model.Servicio;
import jsges.nails.repository.ServicioRepository;
import jsges.nails.service.servicios_Interface.IClienteService;
import jsges.nails.service.servicios_Interface.IServicioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ServicioService implements IServicioService {

    @Autowired
    private ServicioRepository modelRepository;

    @Autowired
    private IClienteService clienteService;

    private static final Logger logger = LoggerFactory.getLogger(ServicioService.class);

    @Override
    public List<ServicioDTO> listar() {
        logger.info("Consultando artículos no eliminados.");
        try {
            List<Servicio> servicios = modelRepository.buscarNoEliminados();
            return servicios.stream()
                    .map(ServicioDTO::new)
                    .toList();
        } catch (Exception e) {
            logger.error("Error al consultar los artículos: {}", e.getMessage(), e);
            throw e; // Propaga la excepción para que sea manejada por el controlador.
        }
    }

    @Override
    public Servicio buscarPorId(Integer id) {
        return modelRepository.findById(id).orElse(null);
    }

    @Override
    public ServicioDTO obtenerServicioPorId(Integer id) {

        Servicio servicio = modelRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No se encontró el artículo con id: " + id));

        // Conversión a DTO
        return new ServicioDTO(servicio);
    }

    @Override
    public Page<ServicioDTO> listarPaginado(String consulta, Pageable pageable) {
        Page<Servicio> page = modelRepository.buscarNoEliminados(consulta, pageable);
        return page.map(ServicioDTO::new); // Convierte las entidades a DTO usando map()
    }



    @Override
    public Servicio crearDesdeDTO(ServicioDTO modelDTO) {

        // Construye el modelo Servicio desde el DTO
        Servicio model = new Servicio();
        model.setCliente(clienteService.buscarPorId(modelDTO.getCliente()));
        model.setTotal(modelDTO.getTotal());
        model.setFechaRealizacion(modelDTO.getFechaDocumento());
        model.setFechaRegistro(modelDTO.getFechaDocumento());

        // Guarda el modelo Servicio en la base de datos
        return modelRepository.save(model);  // Guarda el cliente en la base de datos
    }



    @Override
    public Servicio guardar(Servicio model) {
        return modelRepository.save(model);
    }


    @Override
    public void eliminarServicio(Integer id) {
        // Buscar el artículo por ID
        Servicio servicio = modelRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id));

        // Marcar como eliminado
        servicio.asEliminado();

        // Guardar el cambio
        modelRepository.save(servicio);
    }



    @Override
    public ServicioDTO actualizarServicio(Integer id, ServicioDTO modelRecibido) {
        // Buscar el artículo existente
        Servicio servicio = modelRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("El servicio con id " + id + " no existe."));

        // Actualizar los campos con los datos del DTO
        servicio.setCliente(clienteService.buscarPorId(modelRecibido.getCliente()));
        servicio.setFechaRegistro(modelRecibido.getFechaDocumento());
        servicio.setTotal(modelRecibido.getTotal());

        // Guardar el artículo actualizado
        Servicio servicioActualizado = modelRepository.save(servicio);

        // Retornar el DTO actualizado
        return new ServicioDTO(servicioActualizado);
    }


}
