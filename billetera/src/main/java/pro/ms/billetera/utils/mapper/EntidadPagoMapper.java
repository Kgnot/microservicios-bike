package pro.ms.billetera.utils.mapper;

import pro.ms.billetera.domain.model.EntidadPago;
import pro.ms.billetera.infrastructure.persistence.jpa.EntidadPagoEntity;

public final class EntidadPagoMapper {

    private EntidadPagoMapper() {
    }

    public static EntidadPago toDomain(EntidadPagoEntity entity) {
        if (entity == null) {
            return null;
        }

        return new EntidadPago(entity.getId(), entity.getNombre());
    }

    public static EntidadPagoEntity toEntity(EntidadPago domain) {
        if (domain == null) {
            return null;
        }

        EntidadPagoEntity entity = new EntidadPagoEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        return entity;
    }
}

