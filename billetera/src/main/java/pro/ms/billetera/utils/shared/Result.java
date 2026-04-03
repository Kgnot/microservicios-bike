package pro.ms.billetera.utils.shared;

public sealed interface Result<V, E> {

    record Success<V, E>(V value) implements Result<V, E> {
    }

    record Failure<V, E>(E error) implements Result<V, E> {
    }

    default boolean isSuccess() {
        return this instanceof Success;
    }

    default boolean isFailure() {
        return this instanceof Failure;
    }

    default V getValue() {
        return switch (this) {
            case Success<V, E> value -> value.value;
            case Failure<V, E> failure ->
                    throw new RuntimeException("Cannot unwrap a Failure result: " + failure.error);
        };
    }

    default E getError() {
        return switch (this) {
            case Success<V, E> success ->
                    throw new RuntimeException("Cannot unwrapError a Success result: " + success.value);
            case Failure<V, E> error -> error.error;
        };
    }


}
