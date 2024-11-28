package jsges.nails.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cliente implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(columnDefinition = "TEXT")
        private String razonSocial;

        private int estado;

        @Column(columnDefinition = "TEXT")
        private String letra;

        @Column(columnDefinition = "TEXT")
        private String contacto;

        @Column(columnDefinition = "TEXT")
        private String celular;

        @Column(columnDefinition = "TEXT")
        private String mail;

        private Date fechaInicio;

        private Date fechaNacimiento;

        public void asEliminado() {
                this.setEstado(1);
        }

}
