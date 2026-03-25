package pro.ms.billetera.infrastructure.persistence.jpa;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entidad_pago")
@Data
public class EntidadPagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "nombre", nullable = false, length = 100, unique = true)
    private String nombre;

    //
    @OneToMany(mappedBy = "entidadPago", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TransaccionRecargaEntity> transaccionesRecarga = new ArrayList<>();
}
