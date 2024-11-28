package jsges.nails.repository;

import jsges.nails.model.Cliente;
import jsges.nails.model.Servicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

    @Query("select p from Servicio p  where p.estado=0 ")
    List<Servicio> buscarNoEliminados();


    @Query("SELECT p FROM Servicio p WHERE p.estado = 0")
    Page<Servicio> buscarNoEliminados(@Param("consulta") String consulta, Pageable pageable);


}

