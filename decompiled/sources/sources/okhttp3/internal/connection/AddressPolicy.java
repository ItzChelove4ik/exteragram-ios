package okhttp3.internal.connection;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bR\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lokhttp3/internal/connection/AddressPolicy;", _UrlKt.FRAGMENT_ENCODE_SET, "minimumConcurrentCalls", _UrlKt.FRAGMENT_ENCODE_SET, "backoffDelayMillis", _UrlKt.FRAGMENT_ENCODE_SET, "backoffJitterMillis", "<init>", "(IJI)V", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class AddressPolicy {

    @JvmField
    public final long backoffDelayMillis;

    @JvmField
    public final int backoffJitterMillis;

    @JvmField
    public final int minimumConcurrentCalls;

    public AddressPolicy() {
        this(0, 0, 0, 7, null);
    }

    public AddressPolicy(int r1, long r2, int r4) {
        this.minimumConcurrentCalls = r1;
        this.backoffDelayMillis = r2;
        this.backoffJitterMillis = r4;
    }

    public /* synthetic */ AddressPolicy(int r1, long r2, int r4, int r5, DefaultConstructorMarker r6) {
        if ((r5 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        if ((r5 & 2) == 0) goto L9;
        r2 = 60000;
    L9:
        if ((r5 & 4) == 0) goto L11;
        r4 = 100;
    L11:
        this(r1, r2, r4);
    }
}
