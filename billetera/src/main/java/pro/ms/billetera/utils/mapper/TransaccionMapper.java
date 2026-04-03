package pro.ms.billetera.utils.mapper;

import pro.ms.billetera.domain.enums.TipoTransaccion;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleTransaccion;
import pro.ms.billetera.infrastructure.persistence.jpa.CuentaEntity;
import pro.ms.billetera.infrastructure.persistence.jpa.TransaccionEntity;

public final class TransaccionMapper {

    private TransaccionMapper() {
    }

    public static Transaccion   toDomain(TransaccionEntity entity, TipoTransaccion tipo, DetalleTransaccion detalle) {
        if (entity == null) {
            return null;
        }

        return Transaccion.rehydrate(
                entity.getId(),
                entity.getCuenta() != null ? entity.getCuenta().getId() : null,
                entity.getMonto(),
                MonedaMapper.toDomain(entity.getMoneda()),
                entity.getDescripcion(),
                entity.getFecha(),
                tipo,
                detalle
        );
    }

    public static TransaccionEntity toEntity(Transaccion domain) {
        if (domain == null) {
            return null;
        }

        TransaccionEntity entity = new TransaccionEntity();
        entity.setId(domain.getId());
        entity.setMonto(domain.getMonto());
        entity.setDescripcion(domain.getDescripcion());
        entity.setFecha(domain.getFecha());
        entity.setMoneda(MonedaMapper.toEntity(domain.getMoneda()));

        if (domain.getCuentaId() != null) {
            CuentaEntity cuenta = new CuentaEntity();
            cuenta.setId(domain.getCuentaId());
            entity.setCuenta(cuenta);
        }

        return entity;
    }
}

