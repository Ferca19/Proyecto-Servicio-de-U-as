package jsges.nails.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
public class TipoServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_objeto_id_seq")
    @SequenceGenerator(name = "tipo_objeto_id_seq", sequenceName = "tipo_objeto_id_seq", allocationSize = 1)
    private Integer id;

    private String codigo;

    @Column(columnDefinition = "TEXT")
    private String denominacion;
    private int estado;

    @Column(columnDefinition = "TEXT")
    private String detalle;


    public void asEliminado() {
        this.setEstado(1);
    }



}
