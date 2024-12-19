package jsges.nails.service.servicios_Interface;


import jsges.nails.DTO.ClienteDTO;
import jsges.nails.DTO.TipoObjetoDTO;
import jsges.nails.DTO.TipoServicioDTO;
import jsges.nails.model.TipoServicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITipoServicioService {

    List<TipoServicioDTO> listar();

    Page<TipoServicioDTO> listarPaginado(String consulta, Pageable pageable);

    TipoServicio crearDesdeDTO(TipoServicioDTO modelDTO);

    void eliminarTipoServicio(Integer id);

    TipoServicioDTO obtenerTipoServicioPorId(Integer id);

    TipoServicioDTO actualizarTipoServicio(Integer id, TipoServicioDTO modelRecibido);

    TipoServicio buscarPorId(Integer id);

    TipoServicio guardar(TipoServicio model);


    List<TipoServicio> listar(String consulta);


}
