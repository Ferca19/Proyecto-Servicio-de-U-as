package jsges.nails.service.servicios_Interface;

import jsges.nails.DTO.ArticuloVentaDTO;
import jsges.nails.DTO.LineaDTO;
import jsges.nails.model.ArticuloVenta;
import jsges.nails.model.Linea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILineaService {

    List<LineaDTO> listar();

    Page<LineaDTO> listarPaginado(String consulta, Pageable pageable);

    Linea crearDesdeDTO(LineaDTO modelDTO);

    void eliminarLinea(Integer id);

    LineaDTO obtenerLineaPorId(Integer id);

    LineaDTO actualizarLinea(Integer id, LineaDTO modelRecibido);

    Linea buscarPorId(Integer id);

    Linea guardar(Linea model);

    List<Linea> listar(String consulta);

    List<Linea> buscar(String consulta);

}
