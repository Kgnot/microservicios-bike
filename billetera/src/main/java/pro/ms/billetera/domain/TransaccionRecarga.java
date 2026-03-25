package pro.ms.billetera.domain;

import lombok.*;

import java.util.UUID;

/*
 * Esta me especifíca cuando un usuario recarga
 * */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionRecarga extends Transaccion {

    private UUID id;
    private EntidadPago entidad;
    private String numeroCuenta;

}
