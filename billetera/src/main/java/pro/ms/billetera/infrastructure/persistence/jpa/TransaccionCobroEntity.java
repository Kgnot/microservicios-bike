package pro.ms.billetera.infrastructure.persistence.jpa;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Table(name = "transaccion_cobro")
@Entity
@Data
public class TransaccionCobroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    @JoinColumn(name = "transaccion_id")
    @MapsId
    private TransaccionEntity transaccion;

    private String razon;

}
