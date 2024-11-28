package jsges.nails.service.servicios_Interface;


import jsges.nails.DTO.ArticuloVentaDTO;
import jsges.nails.model.ArticuloVenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IArticuloVentaService {

    List<ArticuloVentaDTO> listar() ;

    ArticuloVenta guardar(ArticuloVenta model);

    ArticuloVentaDTO obtenerArticuloPorId(Integer id);

    ArticuloVentaDTO actualizarArticulo(Integer id, ArticuloVentaDTO modelRecibido);

    void eliminarArticulo(Integer id);

    ArticuloVenta crearDesdeDTO(ArticuloVentaDTO modelDTO);


    Page<ArticuloVentaDTO> listarPaginado(String consulta, Pageable pageable);
}
