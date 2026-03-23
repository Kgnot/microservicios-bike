package pro.ms.auth.service.credenciales;

import pro.ms.auth.configuration.Result;
import pro.ms.auth.entity.Credenciales;
import pro.ms.auth.service.error.CredencialesError;

public interface CredencialesService {

    Result<Credenciales, CredencialesError.NoSaveCredencialesError> save(Credenciales credenciales);
}
