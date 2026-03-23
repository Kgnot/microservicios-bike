package pro.ms.auth.service.credenciales;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.ms.auth.configuration.Result;
import pro.ms.auth.entity.Credenciales;
import pro.ms.auth.repository.CredencialesRepository;
import pro.ms.auth.service.error.CredencialesError;

@Service
public class CredencialesServiceImpl implements CredencialesService {

    private final CredencialesRepository credencialesRepository;
    private final PasswordEncoder passwordEncoder;

    public CredencialesServiceImpl(CredencialesRepository credencialesRepository,
                                   PasswordEncoder passwordEncoder) {
        this.credencialesRepository = credencialesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Result<Credenciales, CredencialesError.NoSaveCredencialesError> save(Credenciales credenciales) {
        try {
            String password = credenciales.getPasswordHash();
            String encodedPassword = passwordEncoder.encode(password);
            credenciales.setPasswordHash(encodedPassword);
            // Guardo ya hashing
            var credencialSave = credencialesRepository.save(credenciales);
            credencialesRepository.flush();
            return new Result.Success<>(credencialSave);
        } catch (Exception e) {
            return new Result.Failure<>(new CredencialesError.NoSaveCredencialesError());
        }
    }
}
