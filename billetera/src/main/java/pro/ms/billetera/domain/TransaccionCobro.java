package pro.ms.billetera.domain;

import lombok.*;

import java.util.UUID;

/*
 * Esta me especifíca cuando se le hace un cobro a un usuario
 * */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class TransaccionCobro extends Transaccion {

    private UUID id;
    private String razon;

}
