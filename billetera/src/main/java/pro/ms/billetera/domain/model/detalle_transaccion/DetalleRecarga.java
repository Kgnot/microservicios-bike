package pro.ms.billetera.domain.model.detalle_transaccion;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pro.ms.billetera.domain.model.EntidadPago;

@Getter
@ToString
@EqualsAndHashCode
public class DetalleRecarga implements DetalleTransaccion {

    private final EntidadPago entidad;
    private final String numeroCuenta;

    public DetalleRecarga(EntidadPago entidad, String numeroCuenta) {
        if (entidad == null) {
            throw new IllegalArgumentException("Entidad requerida");
        }
        if (numeroCuenta == null || numeroCuenta.isBlank()) {
            throw new IllegalArgumentException("Número de cuenta requerido");
        }

        this.entidad = entidad;
        this.numeroCuenta = numeroCuenta;
    }
}
