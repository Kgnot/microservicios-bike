package pro.ms.billetera.domain;

import com.google.type.Decimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaccion {

    private UUID id;
    private Cuenta cuenta;
    private Decimal monto;
    private Moneda moneda;
    private String descripcion;
    private LocalDateTime fecha;
}
