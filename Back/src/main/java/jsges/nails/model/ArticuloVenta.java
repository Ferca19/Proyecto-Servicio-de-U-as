package jsges.nails.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticuloVenta {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(columnDefinition = "TEXT")
        @NotNull
        private String denominacion;

        private int estado;

        @Column(columnDefinition = "TEXT")
        private String observacion;

        @ManyToOne(cascade = CascadeType.ALL)
        private Linea linea;


        public void asEliminado() {
               this.setEstado(1);
        }
}

