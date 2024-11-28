package jsges.nails.service.servicios_Interface;


import jsges.nails.DTO.ArticuloVentaDTO;
import jsges.nails.DTO.ClienteDTO;
import jsges.nails.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {
    List<ClienteDTO> listar();

    Cliente buscarPorId(Integer id);

    Cliente guardar(Cliente cliente);


    Page<ClienteDTO> listarPaginado(String consulta, Pageable pageable);

    Cliente crearDesdeDTO(ClienteDTO modelDTO);


    ClienteDTO obtenerClientePorId(Integer id);

    ClienteDTO actualizarCliente(Integer id, ClienteDTO modelRecibido);

    void eliminarCliente(Integer id);


}
