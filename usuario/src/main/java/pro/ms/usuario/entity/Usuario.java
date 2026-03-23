package pro.ms.usuario.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    private UUID id;

    private String nombre;
    private String nickname;
    private String tarjetaCredito;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String fotoPerfilUrl;
    @CreationTimestamp
    private LocalDateTime creadoEn;
    @UpdateTimestamp
    private LocalDateTime actualizadoEn;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Fidelizacion fidelizacion;
}
