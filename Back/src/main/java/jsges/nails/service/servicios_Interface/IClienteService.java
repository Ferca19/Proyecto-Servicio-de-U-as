package jsges.nails.service.servicios_Interface;


import jsges.nails.DTO.ClienteDTO;
import jsges.nails.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {
    public List<Cliente> listar();

    public Cliente buscarPorId(Integer id);

    public Cliente guardar(Cliente cliente);

    public void eliminar(Cliente cliente);

      public List<Cliente> listar(String consulta);

    public Page<Cliente> getClientes(Pageable pageable);

    public Page<ClienteDTO> findPaginated(Pageable pageable, List<ClienteDTO> clientes);
}
