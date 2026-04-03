package pro.ms.billetera.utils.mapper;

import pro.ms.billetera.domain.enums.TipoTransaccion;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleRecarga;
import pro.ms.billetera.infrastructure.persistence.jpa.TransaccionRecargaEntity;

public final class TransaccionRecargaMapper {

    private TransaccionRecargaMapper() {
    }

    public static Transaccion toDomain(TransaccionRecargaEntity entity) {
        if (entity == null || entity.getTransaccion() == null) {
            return null;
        }

        DetalleRecarga detalle = new DetalleRecarga(
                EntidadPagoMapper.toDomain(entity.getEntidadPago()),
                entity.getNumeroCuenta()
        );

        return TransaccionMapper.toDomain(entity.getTransaccion(), TipoTransaccion.RECARGA, detalle);
    }

    public static TransaccionRecargaEntity toEntity(Transaccion domain) {
        if (domain == null) {
            return null;
        }
        if (!(domain.getDetalle() instanceof DetalleRecarga detalleRecarga)) {
            throw new IllegalArgumentException("La transaccion no tiene detalle de recarga");
        }

        TransaccionRecargaEntity entity = new TransaccionRecargaEntity();
//        entity.setId(domain.getId());
        entity.setTransaccion(TransaccionMapper.toEntity(domain));
        entity.setEntidadPago(EntidadPagoMapper.toEntity(detalleRecarga.entidad()));
        entity.setNumeroCuenta(detalleRecarga.numeroCuenta());
        return entity;
    }
}

