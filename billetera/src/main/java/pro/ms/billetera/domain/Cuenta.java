package pro.ms.billetera.domain;

import com.google.type.Decimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {

    private UUID id;
    private UUID usuarioId;
    private Decimal saldo;
    private Moneda moneda;


}
