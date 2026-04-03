package pro.ms.billetera.utils.mapper;

import pro.ms.billetera.domain.model.Cuenta;
import pro.ms.billetera.infrastructure.persistence.jpa.CuentaEntity;

public final class CuentaMapper {

    private CuentaMapper() {
    }

    public static Cuenta toDomain(CuentaEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Cuenta(
                entity.getId(),
                entity.getUsuarioId(),
                entity.getSaldo(),
                MonedaMapper.toDomain(entity.getMoneda())
        );
    }

    public static CuentaEntity toEntity(Cuenta domain) {
        if (domain == null) {
            return null;
        }

        CuentaEntity entity = new CuentaEntity();
        entity.setId(domain.getId());
        entity.setUsuarioId(domain.getUsuarioId());
        entity.setSaldo(domain.getSaldo());
        entity.setMoneda(MonedaMapper.toEntity(domain.getMoneda()));
        return entity;
    }
}

