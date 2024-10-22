package jsges.nails.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@ToString
public class Servicio {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private int estado;

        @ManyToOne(cascade = CascadeType.ALL)
        private Cliente cliente;

        private Timestamp fechaRegistro;
        private Timestamp fechaRealizacion;
        private double total;


    public Servicio() {

    }



}