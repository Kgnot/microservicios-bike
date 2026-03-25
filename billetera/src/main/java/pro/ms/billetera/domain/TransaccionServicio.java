package pro.ms.billetera.domain;

import lombok.*;

import java.util.UUID;

/*
 * Esta me indica específicamente las transacciones de cada servicio del cliente
 * */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionServicio extends Transaccion {

    private UUID id;
    private UUID servicioId;
}
