package jsges.nails.service.servicios;

import jsges.nails.DTO.ArticuloVentaDTO;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.model.ArticuloVenta;
import jsges.nails.model.Linea;
import jsges.nails.repository.ArticuloVentaRepository;
import jsges.nails.service.servicios_Interface.IArticuloVentaService;
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
public class ArticuloVentaService implements IArticuloVentaService {

    @Autowired
    private ArticuloVentaRepository modelRepository;

    @Autowired
    private LineaService lineaService;

    private static final Logger logger = LoggerFactory.getLogger(ArticuloVentaService.class);



    @Override
    public List<ArticuloVentaDTO> listar() {
        logger.info("Consultando artículos no eliminados.");
        try {
            List<ArticuloVenta> articulos = modelRepository.buscarNoEliminados();
            return articulos.stream()
                    .map(ArticuloVentaDTO::new)
                    .toList();
        } catch (Exception e) {
            logger.error("Error al consultar los artículos: {}", e.getMessage(), e);
            throw e; // Propaga la excepción para que sea manejada por el controlador.
        }
    }

    @Override
    public Page<ArticuloVentaDTO> listarPaginado(String consulta, Pageable pageable) {
        Page<ArticuloVenta> page = modelRepository.buscarNoEliminados(consulta, pageable);
        return page.map(ArticuloVentaDTO::new); // Convierte las entidades a DTO usando map()
    }



    @Override
    public ArticuloVentaDTO obtenerArticuloPorId(Integer id) {

        ArticuloVenta articulo = modelRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No se encontró el artículo con id: " + id));

        // Conversión a DTO
        return new ArticuloVentaDTO(articulo);
    }




    @Override
    public ArticuloVenta crearDesdeDTO(ArticuloVentaDTO modelDTO) {
        // Busca la línea asociada
        Linea linea = lineaService.buscarPorId(modelDTO.getLinea());
        if (linea == null) {
            throw new NoSuchElementException("Línea no encontrada con ID: " + modelDTO.getLinea());
        }

        // Construye el modelo desde el DTO
        ArticuloVenta model = new ArticuloVenta();
        model.setDenominacion(modelDTO.getDenominacion());
        model.setLinea(linea);

        // Guarda el modelo
        return guardar(model);
    }

    @Override
    public ArticuloVenta guardar(ArticuloVenta model) {
        return modelRepository.save(model);
    }


    @Override
    public void eliminarArticulo(Integer id) {
        // Buscar el artículo por ID
        ArticuloVenta articulo = modelRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id));

        // Marcar como eliminado
        articulo.asEliminado();

        // Guardar el cambio
        modelRepository.save(articulo);
    }



    @Override
    public ArticuloVentaDTO actualizarArticulo(Integer id, ArticuloVentaDTO modelRecibido) {
        // Buscar el artículo existente
        ArticuloVenta articulo = modelRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("El artículo con id " + id + " no existe."));

        // Actualizar los campos con los datos del DTO
        articulo.setDenominacion(modelRecibido.getDenominacion());
        articulo.setLinea(lineaService.buscarPorId(modelRecibido.getLinea()));

        // Guardar el artículo actualizado
        ArticuloVenta articuloActualizado = modelRepository.save(articulo);

        // Retornar el DTO actualizado
        return new ArticuloVentaDTO(articuloActualizado);
    }








}
