package jsges.nails.DTO;


import jsges.nails.model.Linea;

public class LineaDTO extends TipoObjetoDTO {

    public LineaDTO() {
       super();
    }

    public LineaDTO(Linea linea) {
        this.id= linea.getId();
        this.denominacion= linea.getDenominacion();
    }
}
