package jsges.nails.service.servicios;

import jsges.nails.DTO.ArticuloVentaDTO;
import jsges.nails.DTO.ClienteDTO;
import jsges.nails.excepcion.RecursoNoEncontradoExcepcion;
import jsges.nails.model.ArticuloVenta;
import jsges.nails.model.Cliente;
import jsges.nails.model.Linea;
import jsges.nails.repository.ClienteRepository;
import jsges.nails.service.servicios_Interface.IClienteService;
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
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository modelRepository;

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Override
    public List<ClienteDTO> listar() {
        logger.info("Listando clientes no eliminados");

        // Convertir directamente la lista de clientes a DTO en el servicio
        return modelRepository.buscarNoEliminados().stream()
                .map(ClienteDTO::new)  // Convertir cada Cliente a ClienteDTO
                .toList();  // Retornar la lista de DTOs
    }

    @Override
    public Page<ClienteDTO> listarPaginado(String consulta, Pageable pageable) {
        Page<Cliente> page = modelRepository.buscarNoEliminados(consulta, pageable);
        return page.map(ClienteDTO::new); // Convierte las entidades a DTO usando map()
    }



    @Override
    public Cliente buscarPorId(Integer id) {
        Cliente model = modelRepository.findById(id).orElse(null);
        return model;
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return modelRepository.save(cliente);
    }

    @Override
    public Cliente crearDesdeDTO(ClienteDTO modelDTO) {
        // Si necesitas verificar alguna entidad relacionada, puedes hacerlo aquí (por ejemplo, buscando una entidad 'Estado' si fuera necesario).

        // Construye el modelo Cliente desde el DTO
        Cliente model = new Cliente();
        model.setRazonSocial(modelDTO.getRazonSocial());
        model.setLetra(modelDTO.getLetra());
        model.setContacto(modelDTO.getContacto());
        model.setCelular(modelDTO.getCelular());
        model.setMail(modelDTO.getMail());
        model.setFechaInicio(modelDTO.getFechaInicio());
        model.setFechaNacimiento(modelDTO.getFechaNacimiento());


        // Guarda el modelo Cliente en la base de datos
        return modelRepository.save(model);  // Guarda el cliente en la base de datos
    }

    @Override
    public ClienteDTO obtenerClientePorId(Integer id) {

        Cliente cliente = modelRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("No se encontró el cliente con id: " + id));

        // Conversión a DTO
        return new ClienteDTO(cliente);
    }

    @Override
    public void eliminarCliente(Integer id) {
        // Buscar el artículo por ID
        Cliente cliente = modelRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id));

        // Marcar como eliminado
        cliente.asEliminado();

        // Guardar el cambio
        modelRepository.save(cliente);
    }



    @Override
    public ClienteDTO actualizarCliente(Integer id, ClienteDTO modelRecibido) {

        // Buscar el cliente existente
        Cliente cliente = modelRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExcepcion("El cliente con id " + id + " no existe."));

        // Actualizar los campos del cliente con los valores recibidos
        cliente.setRazonSocial(modelRecibido.getRazonSocial());
        cliente.setLetra(modelRecibido.getLetra());
        cliente.setContacto(modelRecibido.getContacto());
        cliente.setCelular(modelRecibido.getCelular());
        cliente.setMail(modelRecibido.getMail());
        cliente.setFechaInicio(modelRecibido.getFechaInicio());
        cliente.setFechaNacimiento(modelRecibido.getFechaNacimiento());

        // Guardar los cambios
        modelRepository.save(cliente);

        // Devolver el DTO actualizado
        return new ClienteDTO(cliente);
    }


}
