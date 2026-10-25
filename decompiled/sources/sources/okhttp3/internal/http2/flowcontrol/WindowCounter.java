package okhttp3.internal.http2.flowcontrol;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\tJ\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\f¨\u0006\u0015"}, d2 = {"Lokhttp3/internal/http2/flowcontrol/WindowCounter;", _UrlKt.FRAGMENT_ENCODE_SET, "streamId", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "getStreamId", "()I", "value", _UrlKt.FRAGMENT_ENCODE_SET, "total", "getTotal", "()J", "acknowledged", "getAcknowledged", "unacknowledged", "getUnacknowledged", PluginsConstants.UPDATE, _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class WindowCounter {
    private long acknowledged;
    private final int streamId;
    private long total;

    public WindowCounter(int r1) {
        this.streamId = r1;
    }

    public static /* synthetic */ void update$default(WindowCounter r2, long r3, long r5, int r7, Object r8) {
        if ((r7 & 1) == 0) goto L6;
        r3 = 0;
    L6:
        if ((r7 & 2) == 0) goto L8;
        r5 = 0;
    L8:
        r2.update(r3, r5);
    }

    public final long getAcknowledged() {
        return this.acknowledged;
    }

    public final int getStreamId() {
        return this.streamId;
    }

    public final long getTotal() {
        return this.total;
    }

    public final synchronized long getUnacknowledged() {
        monitor-enter(this);
        long r0 = this.total - this.acknowledged;
        monitor-exit(this);
        return r0;
    L7:
        th = move-exception;
        throw th;
    }

    public String toString() {
        return "WindowCounter(streamId=" + this.streamId + ", total=" + this.total + ", acknowledged=" + this.acknowledged + ", unacknowledged=" + getUnacknowledged() + ')';
    }

    public final synchronized void update(long r4, long r6) {
        monitor-enter(this);
        if (r4 < 0) goto L19;
        if (r6 < 0) goto L17;
        long r0 = this.total + r4;     // Catch: Throwable -> L14
        this.total = r0;     // Catch: Throwable -> L14
        long r42 = this.acknowledged + r6;     // Catch: Throwable -> L14
        this.acknowledged = r42;     // Catch: Throwable -> L14
        if (r42 > r0) goto L13;
        monitor-exit(this);
        return;
    L13:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L14
    L17:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L14
    L19:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        throw th;
    }
}
