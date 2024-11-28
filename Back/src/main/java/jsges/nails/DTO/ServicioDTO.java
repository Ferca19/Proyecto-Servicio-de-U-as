package jsges.nails.DTO;



import jsges.nails.model.ItemServicio;
import jsges.nails.model.Servicio;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class ServicioDTO{

    private Integer id;
    @NotNull
    private Integer cliente;
    private Timestamp fechaDocumento;
    private Set<ItemServicioDTO> listaItems = new HashSet<>();

    private Double total;
    private String clienteRazonSocial;

    public ServicioDTO(Servicio elemento, List<ItemServicio>list) {

        this.id = elemento.getId();
        this.cliente = elemento.getCliente().getId();
        this.clienteRazonSocial = elemento.getCliente().getRazonSocial();
        this.fechaDocumento = elemento.getFechaRealizacion();
        this.total= elemento.getTotal();

        list.forEach((model) -> {
           listaItems.add(new ItemServicioDTO(model));
        });
    }

    public ServicioDTO(Servicio elemento){

        this.id = elemento.getId();
        this.cliente = elemento.getCliente().getId();
        this.clienteRazonSocial = elemento.getCliente().getRazonSocial();
        this.fechaDocumento = elemento.getFechaRealizacion();
        this.total= elemento.getTotal();


    }
}
