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
    private UUID id;

    private UUID usuarioId;

    private BigDecimal monto;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "moneda")
    private MonedaEntity moneda;

    @UpdateTimestamp
    private LocalDateTime ultimaActualizacion;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime ultimaCreacion;

    // relaciones

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TransaccionEntity> transacciones = new ArrayList<>();

}
