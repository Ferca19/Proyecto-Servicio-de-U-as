package jsges.nails.service.servicios;

import jsges.nails.DTO.ArticuloVentaDTO;
import jsges.nails.DTO.LineaDTO;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.model.ArticuloVenta;
import jsges.nails.model.Linea;
import jsges.nails.repository.LineaRepository;
import jsges.nails.service.servicios_Interface.ILineaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Line;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LineaService implements ILineaService {

    @Autowired
    private LineaRepository modelRepository;
    private static final Logger logger = LoggerFactory.getLogger(LineaService.class);

    @Override
    public List<LineaDTO> listar() {
        return modelRepository.buscarNoEliminados();
    }

    @Override
    public Page<LineaDTO> listarPaginado(String consulta, Pageable pageable) {
        Page<Linea> page = modelRepository.buscarNoEliminadoss(consulta, pageable);
        return page.map(LineaDTO::new); // Convierte las entidades a DTO usando map()
    }

    @Override
    public Linea crearDesdeDTO(LineaDTO modelDTO) {

        // Construye el modelo desde el DTO
        Linea model = new Linea();
        model.setDenominacion(modelDTO.getDenominacion());
        model.setObservacion(modelDTO.getObservacion());


        // Guarda el modelo
        return guardar(model);
    }

    @Override
    public LineaDTO obtenerLineaPorId(Integer id) {

        Linea linea = modelRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No se encontró la linea con id: " + id));

        // Conversión a DTO
        return new LineaDTO(linea);
    }

    @Override
    public void eliminarLinea(Integer id) {
        // Buscar el artículo por ID
        Linea linea = modelRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id));

        // Marcar como eliminado
        linea.asEliminado();

        // Guardar el cambio
        modelRepository.save(linea);
    }

    @Override
    public LineaDTO actualizarLinea(Integer id, LineaDTO modelRecibido) {
        // Buscar el artículo existente
        Linea linea = modelRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("La linea con id " + id + " no existe."));

        // Actualizar los campos con los datos del DTO
        linea.setDenominacion(modelRecibido.getDenominacion());
        linea.setObservacion(modelRecibido.getObservacion());

        // Guardar el artículo actualizado
        Linea lineaActualizada = modelRepository.save(linea);

        // Retornar el DTO actualizado
        return new LineaDTO(lineaActualizada);
    }

    @Override
    public Linea buscarPorId(Integer id) {
        return modelRepository.findById(id).orElse(null);
    }



    @Override
    public Linea guardar(Linea model) {
        return modelRepository.save(model);
    }

    @Override
    public List<Linea> listar(String consulta) {
        //logger.info("service " +consulta);
        return modelRepository.buscarNoEliminados(consulta);
    }

    public List<Linea> buscar(String consulta) {
        return modelRepository.buscarExacto(consulta);
    }


}
