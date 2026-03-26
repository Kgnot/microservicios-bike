package pro.ms.billetera.utils.mapper;

import pro.ms.billetera.domain.enums.TipoTransaccion;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleCobro;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleTransaccion;
import pro.ms.billetera.infrastructure.persistence.jpa.TransaccionCobroEntity;

public final class TransaccionCobroMapper {

    private TransaccionCobroMapper() {
    }

    public static Transaccion toDomain(TransaccionCobroEntity entity) {
        if (entity == null || entity.getTransaccion() == null) {
            return null;
        }

        return TransaccionMapper.toDomain(entity.getTransaccion(), TipoTransaccion.COBRO, null);
    }

    public static TransaccionCobroEntity toEntity(Transaccion domain) {
        if (domain == null) {
            return null;
        }
        DetalleCobro dt = (DetalleCobro) domain.getDetalle();

        TransaccionCobroEntity entity = new TransaccionCobroEntity();
        entity.setId(domain.getId());
        entity.setTransaccion(TransaccionMapper.toEntity(domain));
        entity.setRazon(dt.razon());
        return entity;
    }
}

