package jsges.nails.model;

import jakarta.persistence.*;
import jsges.nails.DTO.LineaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Linea extends TipoObjeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String denominacion;

    private int estado;

    @Column(columnDefinition = "TEXT")
    private String observacion;

    public Linea(LineaDTO model) {
        this.denominacion = model.getDenominacion();
    }
}