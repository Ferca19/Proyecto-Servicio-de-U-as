package jsges.nails.service.servicios_Interface;


import jsges.nails.DTO.ArticuloVentaDTO;
import jsges.nails.DTO.ServicioDTO;
import jsges.nails.model.Servicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServicioService {
    List<ServicioDTO> listar();

    Servicio buscarPorId(Integer id);

    Servicio guardar(Servicio model);

    Servicio crearDesdeDTO(ServicioDTO modelDTO);

    void eliminarServicio(Integer id);

    ServicioDTO actualizarServicio(Integer id, ServicioDTO modelRecibido);


    Page<ServicioDTO> listarPaginado(String consulta, Pageable pageable);

    ServicioDTO obtenerServicioPorId(Integer id);

}
