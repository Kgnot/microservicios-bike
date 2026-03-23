package pro.ms.usuario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "fidelizacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fidelizacion {

    @Id
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Usuario usuario;

    private Integer puntos;
    private Integer puntosCanjeados;
    @UpdateTimestamp
    private LocalDateTime actualizadoEn;
}
