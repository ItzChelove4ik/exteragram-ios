package okhttp3.internal.connection;

import java.io.IOException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import okhttp3.internal.connection.RoutePlanner;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lokhttp3/internal/connection/SequentialExchangeFinder;", "Lokhttp3/internal/connection/ExchangeFinder;", "routePlanner", "Lokhttp3/internal/connection/RoutePlanner;", "<init>", "(Lokhttp3/internal/connection/RoutePlanner;)V", "getRoutePlanner", "()Lokhttp3/internal/connection/RoutePlanner;", "find", "Lokhttp3/internal/connection/RealConnection;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SequentialExchangeFinder implements ExchangeFinder {
    private final RoutePlanner routePlanner;

    public SequentialExchangeFinder(RoutePlanner r1) {
        this.routePlanner = r1;
    }

    @Override // okhttp3.internal.connection.ExchangeFinder
    public RealConnection find() {
        IOException r1 = null;
    L4:
        if (getRoutePlanner().isCanceled() == true) goto L26;
        RoutePlanner.Plan r2 = getRoutePlanner().plan();     // Catch: IOException -> L10
        if (r2.isReady() == true) goto L18;
        RoutePlanner.ConnectResult r3 = r2.mo3750connectTcp();     // Catch: IOException -> L10
        if (r3.isSuccess() == false) goto L12;
        r3 = r2.mo3751connectTlsEtc();     // Catch: IOException -> L10
    L12:
        RoutePlanner.Plan r4 = r3.component2();     // Catch: IOException -> L10
        Throwable r32 = r3.component3();     // Catch: IOException -> L10
        if (r32 != null) goto L16;
        if (r4 == null) goto L18;
        getRoutePlanner().getDeferredPlans().addFirst(r4);     // Catch: IOException -> L10
        goto L4
    L16:
        throw r32;     // Catch: IOException -> L10
    L18:
        return r2.mo3747handleSuccess();
    L10:
        e = move-exception;
        if (r1 != null) goto L21;
        r1 = e;
    L23:
        if (RoutePlanner.hasNext$default(getRoutePlanner(), null, 1, null) == true) goto L4;
        throw r1;
    L21:
        ExceptionsKt.addSuppressed(r1, e);
        goto L23
    L26:
        Model$$ExternalSyntheticBUOutline0.m("Canceled");
        return null;
    }

    @Override // okhttp3.internal.connection.ExchangeFinder
    public RoutePlanner getRoutePlanner() {
        return this.routePlanner;
    }
}
