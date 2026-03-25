package pro.ms.billetera.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pro.ms.billetera.application.dto.command_usecase.RecargarCommand;
import pro.ms.billetera.application.error.RecargarSaldoError;
import pro.ms.billetera.application.port.in.RecargarSaldoUseCase;
import pro.ms.billetera.application.port.out.CuentaRepository;
import pro.ms.billetera.application.port.out.EntidadPagoRepository;
import pro.ms.billetera.application.port.out.MonedaRepository;
import pro.ms.billetera.application.port.out.TransaccionRepository;
import pro.ms.billetera.domain.model.Cuenta;
import pro.ms.billetera.domain.model.EntidadPago;
import pro.ms.billetera.domain.model.Moneda;
import pro.ms.billetera.domain.model.Transaccion;
import pro.ms.billetera.domain.model.detalle_transaccion.DetalleRecarga;
import pro.ms.billetera.utils.shared.Result;

@Slf4j
@RequiredArgsConstructor
public class RecargarSaldoUseCaseImpl implements RecargarSaldoUseCase {

    private final CuentaRepository cuentaRepository;
    private final TransaccionRepository transaccionRepository;
    private final MonedaRepository monedaRepository;
    private final EntidadPagoRepository entidadPagoRepository;


    @Override
    public Result<Transaccion, RecargarSaldoError> ejecutar(RecargarCommand cmd) {
        Cuenta cuenta = cuentaRepository.findById(cmd.cuentaId());
        Moneda moneda = monedaRepository.findById(cmd.monedaId());
        EntidadPago ePago = entidadPagoRepository.findById(cmd.entidadPago());
        // validamos la cuenta
        if (cuenta == null) {
            log.warn("No existe el cuenta con el id: {}", cmd.cuentaId());
            return new Result.Failure<>(new RecargarSaldoError
                    .CuentaNoEncontrada("cuenta invalida"));
        }
        // validamos la entidad de pago
        if (ePago == null) {
            log.warn("No existe la entidad de pago con el id: {}", cmd.entidadPago());
            return new Result.Failure<>(new RecargarSaldoError.EntidadPagoNoEncontrada("tipo invalido"));
        }
        //validamos la moneda
        if (moneda == null) {
            log.warn("No existe el moneda con el id: {}", cmd.monedaId());
            return new Result.Failure<>(new RecargarSaldoError.MonedaNoEncontrada("tipo invalido"));
        }

        if (!cuenta.getMoneda().equals(moneda)) {
            return new Result.Failure<>(new RecargarSaldoError.MonedaInvalida(moneda.getDescripcion()));
        }
        var acreditar = cuenta.acreditar(cmd.monto());
        //acreditamos y verificamos que no haya error
        if (acreditar.isFailure()) {
            return new Result.Failure<>(new RecargarSaldoError
                    .ErrorDominioCuenta(acreditar.getError()));
        }
        // creamos el detalle
        DetalleRecarga detalle = new DetalleRecarga(
                ePago,
                cmd.numeroCuenta()
        );
        //creamos la transaccion
        Transaccion tx = Transaccion.recarga(
                cuenta.getId(),
                cmd.monto(),
                moneda,
                cmd.descripcion(),
                detalle);
        // Creamos la transaccion saldo
        cuentaRepository.save(cuenta);
        var savedTx = transaccionRepository.save(tx);

        return new Result.Success<>(savedTx);
    }
}
