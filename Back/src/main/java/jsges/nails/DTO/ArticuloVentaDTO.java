package jsges.nails.DTO;


import jsges.nails.model.ArticuloVenta;
import lombok.Data;

@Data
public class ArticuloVentaDTO extends TipoObjetoDTO {

    public Integer id;
    public String denominacion;
    public Integer linea;

    public ArticuloVentaDTO( ArticuloVenta model) {
        this.id = model.getId();
        this.denominacion=model.getDenominacion();
        this.linea=model.getLinea().getId();
    }

    public ArticuloVentaDTO( ) {

    }
}
