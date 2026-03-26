package pro.ms.billetera.domain.model.detalle_transaccion;

import pro.ms.billetera.domain.model.EntidadPago;

public record DetalleRecarga(EntidadPago entidad, String numeroCuenta) implements DetalleTransaccion {

    public DetalleRecarga {
        if (entidad == null) {
            throw new IllegalArgumentException("Entidad requerida");
        }
        if (numeroCuenta == null || numeroCuenta.isBlank()) {
            throw new IllegalArgumentException("Número de cuenta requerido");
        }

    }
}
