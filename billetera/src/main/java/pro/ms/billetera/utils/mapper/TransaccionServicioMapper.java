package pro.ms.billetera.utils.mapper;

import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleServicio;
import pro.ms.billetera.infrastructure.persistence.jpa.TransaccionServicioEntity;

public final class TransaccionServicioMapper {

    private TransaccionServicioMapper() {
    }

    public static Transaccion toDomain(TransaccionServicioEntity entity) {
        throw new UnsupportedOperationException("No existe TipoTransaccion.SERVICIO en dominio para mapear esta entidad");
    }

    public static TransaccionServicioEntity toEntity(Transaccion domain) {
        if (domain == null) {
            return null;
        }
        DetalleServicio dt = (DetalleServicio) domain.getDetalle();
        // todo revisar el detalle no sea nullo los atributos
        TransaccionServicioEntity entity = new TransaccionServicioEntity();
        entity.setId(domain.getId());
        entity.setTransaccion(TransaccionMapper.toEntity(domain));
        entity.setServicioId(dt.servicioId());
        return entity;
    }
}

