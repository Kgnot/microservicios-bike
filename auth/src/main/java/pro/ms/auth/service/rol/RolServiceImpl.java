package pro.ms.auth.service.rol;

import org.springframework.stereotype.Service;
import pro.ms.auth.configuration.Result;
import pro.ms.auth.entity.Rol;
import pro.ms.auth.repository.RolRepository;
import pro.ms.auth.service.error.RolError;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository repository;

    public RolServiceImpl(RolRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result<Rol, RolError.RolNotFoundError> findById(Short id) {
        var rolOptional = repository.findById(id);
        return rolOptional.isEmpty() ?
                new Result.Failure<>(new RolError.RolNotFoundError(id)) :
                new Result.Success<>(rolOptional.get());
    }
}
