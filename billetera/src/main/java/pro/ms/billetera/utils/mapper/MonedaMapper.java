package pro.ms.billetera.utils.mapper;

import pro.ms.billetera.domain.model.Moneda;
import pro.ms.billetera.infrastructure.persistence.jpa.MonedaEntity;

public final class MonedaMapper {

    private MonedaMapper() {
    }

    public static Moneda toDomain(MonedaEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Moneda(entity.getId(), entity.getDescripcion());
    }

    public static MonedaEntity toEntity(Moneda domain) {
        if (domain == null) {
            return null;
        }

        MonedaEntity entity = new MonedaEntity();
        entity.setId(domain.getId());
        entity.setDescripcion(domain.getDescripcion());
        return entity;
    }
}

