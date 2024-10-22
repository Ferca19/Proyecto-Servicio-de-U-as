package jsges.nails.service.servicios_Interface;


import jsges.nails.DTO.ServicioDTO;
import jsges.nails.model.Servicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServicioService {
    public List<Servicio> listar();

    public Servicio buscarPorId(Integer id);

    public Servicio guardar(Servicio model);

    public Page<ServicioDTO> findPaginated(Pageable pageable, List<ServicioDTO> servicios);

    public Page<Servicio> getServicios(Pageable pageable);

    public List<Servicio> listar(String consulta);

}
