package pro.ms.auth.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rol")
@Data
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    private String nombre;

    @OneToMany(mappedBy = "rol")
    @JsonManagedReference
    private List<Credenciales> credenciales = new ArrayList<>();

}
