package pro.ms.billetera.utils.mapper;

import pro.ms.billetera.domain.enums.TipoTransaccion;
import pro.ms.billetera.domain.model.Transaccion;
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

    public static TransaccionCobroEntity toEntity(Transaccion domain, String razon) {
        if (domain == null) {
            return null;
        }

        TransaccionCobroEntity entity = new TransaccionCobroEntity();
        entity.setId(domain.getId());
        entity.setTransaccion(TransaccionMapper.toEntity(domain));
        entity.setRazon(razon);
        return entity;
    }
}

