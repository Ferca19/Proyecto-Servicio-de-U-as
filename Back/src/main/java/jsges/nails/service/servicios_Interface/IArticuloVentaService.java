package jsges.nails.service.servicios_Interface;


import jsges.nails.DTO.ArticuloVentaDTO;
import jsges.nails.model.ArticuloVenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IArticuloVentaService {

    public List<ArticuloVenta> listar();

    public ArticuloVenta buscarPorId(Integer id);

    public ArticuloVenta guardar(ArticuloVenta model);

    public void eliminar(ArticuloVenta model);

    public List<ArticuloVenta> listar(String consulta);

    public Page<ArticuloVenta> getArticulos(Pageable pageable);

    public Page<ArticuloVentaDTO> findPaginated(Pageable pageable, List<ArticuloVentaDTO> list);
}
