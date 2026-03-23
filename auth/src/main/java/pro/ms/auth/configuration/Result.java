package pro.ms.auth.configuration;


import java.util.function.Function;

public sealed interface Result<V, E> {

    record Success<V, E>(V value) implements Result<V, E> {
    }

    record Failure<V, E>(E error) implements Result<V, E> {
    }

    // métodos para mapear el error
    default boolean isSuccess() {
        return this instanceof Success<V, E>;
    }

    default boolean isFailure() {
        return this instanceof Failure<V, E>;
    }

    // intentamos obtener el valor
    default V unwrap() {
        return switch (this) {
            case Success<V, E>(V value) -> value;
            case Failure<V, E>(E error) -> throw new RuntimeException("Error de unwrap: " + error);
        };
    }

    default <U> Result<U, E> map(Function<? super V, ? extends U> mapper) {
        return switch (this) {
            case Success<V, E>(V value) -> new Success<>(mapper.apply(value));
            case Failure<V, E>(E error) -> new Failure<>(error);
        };
    }

    default <F> Result<V, F> mapError(Function<? super E, ? extends F> mapper) {
        return switch (this) {
            case Success<V, E>(V value) -> new Success<>(value);
            case Failure<V, E>(E error) -> new Failure<>(mapper.apply(error));
        };
    }

    default E unwrapError() {
        return switch (this) {
            case Success<V, E>(V value) -> throw new RuntimeException("No error present");
            case Failure<V, E>(E error) -> error;
        };
    }

}