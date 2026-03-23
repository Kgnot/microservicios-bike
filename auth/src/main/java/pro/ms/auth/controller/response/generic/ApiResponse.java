package pro.ms.auth.controller.response.generic;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private String status;
    private int code;
    private T data;
    private Meta meta;
    private ApiError error;

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> res = new ApiResponse<>();
        res.setStatus("success");
        res.setCode(200);
        res.setData(data);
        return res;
    }

    public static <T> ApiResponse<T> success(T data, Page<?> page) {
        ApiResponse<T> res = success(data);

        Meta meta = new Meta(
                page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages()
        );

        res.setMeta(meta);

        return res;
    }

    public static <T> ApiResponse<T> error(int code, String message, String details) {
        ApiResponse<T> res = new ApiResponse<>();
        res.setStatus("error");
        res.setCode(code);

        ApiError err = new ApiError(message, details);

        res.setError(err);

        return res;
    }
}
