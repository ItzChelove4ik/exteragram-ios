package retrofit2;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Objects;
import okhttp3.Headers;
import okhttp3.ResponseBody;

/* loaded from: classes3.dex */
public final class Response<T> {
    private final T body;
    private final ResponseBody errorBody;
    private final okhttp3.Response rawResponse;

    private Response(okhttp3.Response r1, T r2, ResponseBody r3) {
        this.rawResponse = r1;
        this.body = r2;
        this.errorBody = r3;
    }

    public static <T> Response<T> error(ResponseBody r2, okhttp3.Response r3) {
        Objects.requireNonNull(r2, "body == null");
        Objects.requireNonNull(r3, "rawResponse == null");
        if (r3.isSuccessful() == false) goto L5;
        f$$ExternalSyntheticBUOutline1.m("rawResponse should not be successful response");
        return null;
    L5:
        return new Response(r3, null, r2);
    }

    public static <T> Response<T> success(T r2, okhttp3.Response r3) {
        Objects.requireNonNull(r3, "rawResponse == null");
        if (r3.isSuccessful() == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("rawResponse must be successful response");
        return null;
    L5:
        return new Response(r3, r2, null);
    }

    public T body() {
        return this.body;
    }

    public int code() {
        return this.rawResponse.code();
    }

    public Headers headers() {
        return this.rawResponse.headers();
    }

    public boolean isSuccessful() {
        return this.rawResponse.isSuccessful();
    }

    public String message() {
        return this.rawResponse.message();
    }

    public String toString() {
        return this.rawResponse.toString();
    }
}
