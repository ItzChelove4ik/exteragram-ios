package okhttp3.internal.cache;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.internal.http.HttpMethod;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0002¨\u0006\u0002"}, d2 = {"requestForCache", "Lokhttp3/Request;", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class CacheInterceptorKt {
    public static final /* synthetic */ Request access$requestForCache(Request r0) {
        return requestForCache(r0);
    }

    private static final Request requestForCache(Request r3) {
        HttpUrl r0 = r3.cacheUrlOverride();
        if (r0 != null) goto L5;
        return r3;
    L5:
        if (HttpMethod.INSTANCE.isCacheable(r3.method()) == true) goto L9;
        if (Intrinsics.areEqual(r3.method(), "POST") == true) goto L9;
        return r3;
    L9:
        return r3.newBuilder().get().url(r0).cacheUrlOverride(null).build();
    }
}
