package pro.ms.billetera.infrastructure.persistence.jpa;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Table(name = "moneda")
@Entity
@Data
public class MonedaEntity {
    @Id
    @Column(name = "id", length = 4)
    private String id;

    private String descripcion;


    @OneToMany(mappedBy = "moneda")
    @JsonManagedReference
    private List<CuentaEntity> cuentas;

    @OneToMany(mappedBy = "moneda")
    @JsonManagedReference
    private List<TransaccionEntity> transacciones = new ArrayList<>();
}
