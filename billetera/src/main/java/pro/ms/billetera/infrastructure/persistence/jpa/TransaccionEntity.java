package pro.ms.billetera.infrastructure.persistence.jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "transacciones")
@Entity
@Data
public class TransaccionEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    @JsonBackReference
    private CuentaEntity cuenta;

    private BigDecimal monto;

    @ManyToOne
    @JoinColumn(name = "moneda") // aqui deberia llamarse moneda id, ash, yo y mi pendejada en bd
    @JsonBackReference
    private MonedaEntity moneda;

    private String descripcion;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fecha;

}
