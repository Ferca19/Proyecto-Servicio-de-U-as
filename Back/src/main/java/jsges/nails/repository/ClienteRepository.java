package jsges.nails.repository;

import jsges.nails.model.ArticuloVenta;
import jsges.nails.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("select p from Cliente p  where p.estado=0 order by p.razonSocial")
    List<Cliente> buscarNoEliminados();


    @Query("SELECT p FROM Cliente p WHERE p.estado = 0 AND p.razonSocial LIKE %:consulta% ORDER BY p.razonSocial")
    Page<Cliente> buscarNoEliminados(@Param("consulta") String consulta, Pageable pageable);




}
