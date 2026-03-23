package pro.ms.auth.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "credenciales")
@Entity
@Data
public class Credenciales {

    @Id
    private UUID id;

    private UUID usuarioId;
    private String email;
    private String passwordHash;
    @ManyToOne
    @JoinColumn(name = "rol_id")
    @JsonBackReference
    private Rol rol;
    private Boolean activo;
    private LocalDateTime ultimoLogin;
    @CreationTimestamp
    private LocalDateTime creadoEn;
    @UpdateTimestamp
    private LocalDateTime actualizadoEn;


}
