package pro.ms.auth.controller.response.generic;

public record Meta(
        int page,
        int size,
        long totalElements,
        int totalPages
) {
}
