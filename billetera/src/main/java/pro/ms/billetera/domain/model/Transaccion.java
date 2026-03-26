package pro.ms.billetera.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleRecarga;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleTransaccion;
import pro.ms.billetera.domain.enums.TipoTransaccion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
public class Transaccion {

    private final UUID id;
    private final UUID cuentaId;
    private final BigDecimal monto;
    private final Moneda moneda;
    private final String descripcion;
    private final LocalDateTime fecha;
    private final TipoTransaccion tipo;
    private final DetalleTransaccion detalle;

    private Transaccion(
            UUID id,
            UUID cuentaId,
            BigDecimal monto,
            Moneda moneda,
            String descripcion,
            LocalDateTime fecha,
            TipoTransaccion tipo,
            DetalleTransaccion detalle
    ) {
        this.id = id;
        this.cuentaId = cuentaId;
        this.monto = monto;
        this.moneda = moneda;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.tipo = tipo;
        this.detalle = detalle;
    }

    // Method para recarga
    public static Transaccion recarga(
            UUID cuentaId,
            BigDecimal monto,
            Moneda moneda,
            String descripcion,
            DetalleRecarga detalle
    ) {
        return new Transaccion(
                null,
                cuentaId,
                monto,
                moneda,
                descripcion,
                LocalDateTime.now(),
                TipoTransaccion.RECARGA,
                detalle
        );
    }

    // cobro
    public static Transaccion cobro(
            UUID cuentaId,
            BigDecimal monto,
            Moneda moneda,
            String descripcion,
            DetalleTransaccion detalle
    ) {
        return new Transaccion(
                null,
                cuentaId,
                monto,
                moneda,
                descripcion,
                LocalDateTime.now(),
                TipoTransaccion.COBRO,
                detalle
        );
    }

    // Reconstituye una transacción existente desde la capa de persistencia.
    public static Transaccion rehydrate(
            UUID id,
            UUID cuentaId,
            BigDecimal monto,
            Moneda moneda,
            String descripcion,
            LocalDateTime fecha,
            TipoTransaccion tipo,
            DetalleTransaccion detalle
    ) {
        return new Transaccion(
                id,
                cuentaId,
                monto,
                moneda,
                descripcion,
                fecha,
                tipo,
                detalle
        );
    }
}