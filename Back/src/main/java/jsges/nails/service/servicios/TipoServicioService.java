package jsges.nails.service.servicios;

import jsges.nails.DTO.ClienteDTO;
import jsges.nails.DTO.TipoObjetoDTO;
import jsges.nails.DTO.TipoServicioDTO;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.model.Cliente;
import jsges.nails.model.TipoServicio;
import jsges.nails.repository.TipoServicioRepository;
import jsges.nails.service.servicios_Interface.ITipoServicioService;
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

@Service
public class TipoServicioService implements ITipoServicioService {

    @Autowired
    private TipoServicioRepository modelRepository;
    private static final Logger logger = LoggerFactory.getLogger(TipoServicioService.class);

    @Override
    public List<TipoServicioDTO> listar() {
        logger.info("Listando tipos de servicio no eliminados");

        // Convertir directamente la lista de clientes a DTO en el servicio
        return modelRepository.buscarNoEliminados().stream()
                .map(TipoServicioDTO::new)  // Convertir cada Cliente a ClienteDTO
                .toList();  // Retornar la lista de DTOs
    }

    @Override
    public Page<TipoServicioDTO> listarPaginado(String consulta, Pageable pageable) {
        Page<TipoServicio> page = modelRepository.buscarNoEliminadoss(consulta, pageable);
        return page.map(TipoServicioDTO::new); // Convierte las entidades a DTO usando map()
    }

    @Override
    public TipoServicio crearDesdeDTO(TipoServicioDTO modelDTO) {
        // Si necesitas verificar alguna entidad relacionada, puedes hacerlo aquí (por ejemplo, buscando una entidad 'Estado' si fuera necesario).

        // Construye el modelo Cliente desde el DTO
        TipoServicio model = new TipoServicio();

        model.setDenominacion(modelDTO.getDenominacion());
        model.setCodigo(modelDTO.getCodigo());
        model.setDetalle(modelDTO.getDetalle());



        // Guarda el modelo Cliente en la base de datos
        return modelRepository.save(model);  // Guarda el cliente en la base de datos
    }

    @Override
    public void eliminarTipoServicio(Integer id) {
        // Buscar el artículo por ID
        TipoServicio tipoServicio = modelRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id));

        // Marcar como eliminado
        tipoServicio.asEliminado();

        // Guardar el cambio
        modelRepository.save(tipoServicio);
    }

    @Override
    public TipoServicioDTO obtenerTipoServicioPorId(Integer id) {

        TipoServicio tipoServicio = modelRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No se encontró el cliente con id: " + id));

        // Conversión a DTO
        return new TipoServicioDTO(tipoServicio);
    }


    @Override
    public TipoServicioDTO actualizarTipoServicio(Integer id, TipoServicioDTO modelRecibido) {

        // Buscar el cliente existente
        TipoServicio tipoServicio = modelRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("El tipo de servicio con id " + id + " no existe."));

        // Actualizar los campos del cliente con los valores recibidos
        tipoServicio.setCodigo(modelRecibido.getCodigo());
        tipoServicio.setDetalle(modelRecibido.getDetalle());
        tipoServicio.setDenominacion(modelRecibido.getDenominacion());

        // Guardar los cambios
        modelRepository.save(tipoServicio);

        // Devolver el DTO actualizado
        return new TipoServicioDTO(tipoServicio);
    }


    @Override
    public TipoServicio buscarPorId(Integer id) {
        return modelRepository.findById(id).orElse(null);
    }



    @Override
    public TipoServicio guardar(TipoServicio model) {
        return modelRepository.save(model);
    }



    @Override
    public List<TipoServicio> listar(String consulta) {
        //logger.info("service " +consulta);
        return modelRepository.buscarNoEliminados(consulta);
    }
    


}
