package okhttp3.internal;

import kotlin.Metadata;
import okhttp3.Response;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001¨\u0006\u0002"}, d2 = {"stripBody", "Lokhttp3/Response;", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class UnreadableResponseBodyKt {
    public static final Response stripBody(Response r5) {
        return r5.newBuilder().body(new UnreadableResponseBody(r5.body().contentType(), r5.body().contentLength())).build();
    }
}
