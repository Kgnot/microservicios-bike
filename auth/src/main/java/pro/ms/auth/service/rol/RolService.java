package pro.ms.auth.service.rol;

import pro.ms.auth.configuration.Result;
import pro.ms.auth.entity.Rol;
import pro.ms.auth.service.error.RolError;

public interface RolService {

    Result<Rol, RolError.RolNotFoundError> findById(Short id);
}
