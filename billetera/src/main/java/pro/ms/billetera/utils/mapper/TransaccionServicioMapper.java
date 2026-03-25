package pro.ms.billetera.utils.mapper;

import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.infrastructure.persistence.jpa.TransaccionServicioEntity;

import java.util.UUID;

public final class TransaccionServicioMapper {

    private TransaccionServicioMapper() {
    }

    public static Transaccion toDomain(TransaccionServicioEntity entity) {
        throw new UnsupportedOperationException("No existe TipoTransaccion.SERVICIO en dominio para mapear esta entidad");
    }

    public static TransaccionServicioEntity toEntity(Transaccion domain, UUID servicioId) {
        if (domain == null) {
            return null;
        }

        TransaccionServicioEntity entity = new TransaccionServicioEntity();
        entity.setId(domain.getId());
        entity.setTransaccion(TransaccionMapper.toEntity(domain));
        entity.setServicioId(servicioId);
        return entity;
    }
}

