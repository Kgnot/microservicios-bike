package pro.ms.billetera.infrastructure.persistence.jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Table(name = "transaccion_recarga")
@Entity
@Data
public class TransaccionRecargaEntity {

    @Id
    private UUID id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "transaccion_id")
    private TransaccionEntity transaccion;

    @ManyToOne
    @JoinColumn(name = "entidad_id")
    @JsonBackReference
    private EntidadPagoEntity entidadPagoEntity;

    @Column(name = "numero_cuenta", nullable = false, length = 30)
    private String numeroCuenta;
}
