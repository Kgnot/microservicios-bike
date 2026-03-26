package pro.ms.billetera.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pro.ms.billetera.application.exception.*;
import pro.ms.billetera.application.port.out.repository.CuentaRepository;
import pro.ms.billetera.application.port.out.repository.EntidadPagoRepository;
import pro.ms.billetera.application.port.out.repository.MonedaRepository;
import pro.ms.billetera.domain.model.Cuenta;
import pro.ms.billetera.domain.model.EntidadPago;
import pro.ms.billetera.domain.model.Moneda;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class TransaccionEntityResolverService {

    private final CuentaRepository cuentaRepository;
    private final MonedaRepository monedaRepository;
    private final EntidadPagoRepository entidadPagoRepository;

    public Cuenta obtenerCuenta(UUID cuentaId) {
        log.debug("Buscando cuenta con id: {}", cuentaId);
        return cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> {
                    log.warn("Cuenta no encontrada: {}", cuentaId);
                    return new CuentaNoEncontradaException("Cuenta no encontrada: " + cuentaId);
                });
    }

    public Moneda obtenerMoneda(String monedaId) {
        log.debug("Buscando moneda con id: {}", monedaId);
        return monedaRepository.findById(monedaId)
                .orElseThrow(() -> {
                    log.warn("Moneda no encontrada: {}", monedaId);
                    return new MonedaNoEncontradaException(monedaId);
                });
    }

    public EntidadPago obtenerEntidadPago(Short entidadPagoId) {
        log.debug("Buscando entidad de pago con id: {}", entidadPagoId);
        return entidadPagoRepository.findById(entidadPagoId)
                .orElseThrow(() -> {
                    log.warn("Entidad de pago no encontrada: {}", entidadPagoId);
                    return new EntidadPagoNoEncontradaException(entidadPagoId);
                });
    }
}