package pro.ms.billetera.infrastructure.persistence.jpa;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Table(name = "transacciones_servicio")
@Entity
@Data
public class TransaccionServicioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    @JoinColumn(name = "transaccion_id")
    @MapsId
    private TransaccionEntity transaccion;

    private UUID servicioId;

}
