package retrofit2;

import java.util.Objects;

/* loaded from: classes7.dex */
public class HttpException extends RuntimeException {
    private final int code;
    private final String message;
    private final transient Response<?> response;

    public HttpException(Response<?> r2) {
        super(getMessage(r2));
        this.code = r2.code();
        this.message = r2.message();
        this.response = r2;
    }

    private static String getMessage(Response<?> r2) {
        Objects.requireNonNull(r2, "response == null");
        return "HTTP " + r2.code() + " " + r2.message();
    }
}
