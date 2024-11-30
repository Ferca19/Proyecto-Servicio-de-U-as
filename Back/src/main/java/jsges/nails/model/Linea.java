package jsges.nails.model;

import jakarta.persistence.*;
import jsges.nails.DTO.LineaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Linea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String observacion;

    private int codigo;

    @Column(columnDefinition = "TEXT")
    private String denominacion;

    private int estado;

    @Column(columnDefinition = "TEXT")
    private String detalle;


    public void asEliminado() {
        this.setEstado(1);
    }

}