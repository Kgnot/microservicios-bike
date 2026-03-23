package pro.ms.auth.controller.response.generic;

public record ApiError(
        String message,
        String details
) {
}