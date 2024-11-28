package jsges.nails.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Servicio {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private int estado;

        private Timestamp fechaRegistro;
        private Timestamp fechaRealizacion;
        private double total;

        @ManyToOne(cascade = CascadeType.ALL)
        private Cliente cliente;


        public void asEliminado() {
                this.setEstado(1);
        }

}
