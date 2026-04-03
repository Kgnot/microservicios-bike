package pro.ms.billetera.infrastructure.persistence.jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cuentas")
@Data
public class CuentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID usuarioId;

    private BigDecimal saldo;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "moneda")
    private MonedaEntity moneda;

    @UpdateTimestamp
    private LocalDateTime ultimaActualizacion;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creadoEn;

    // relaciones

    @OneToMany(mappedBy = "cuenta")
    @JsonManagedReference
    private List<TransaccionEntity> transacciones = new ArrayList<>();

}
