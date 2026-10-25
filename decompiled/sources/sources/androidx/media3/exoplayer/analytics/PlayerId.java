package androidx.media3.exoplayer.analytics;

import android.media.metrics.LogSessionId;
import android.os.Build;
import androidx.media3.common.util.Assertions;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class PlayerId {
    public static final PlayerId UNSET = null;
    private final Object equalityToken;
    private final LogSessionIdApi31 logSessionIdApi31;
    public final String name;

    public static final class LogSessionIdApi31 {
        public LogSessionId logSessionId;

        public LogSessionIdApi31() {
            this.logSessionId = PlayerId$LogSessionIdApi31$$ExternalSyntheticApiModelOutline0.m();
        }

        public void setLogSessionId(LogSessionId r3) {
            Assertions.checkState(this.logSessionId.equals(PlayerId$LogSessionIdApi31$$ExternalSyntheticApiModelOutline0.m()));
            this.logSessionId = r3;
        }
    }

    static {
        UNSET = new PlayerId(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    public PlayerId(String r2) {
        this.name = r2;
        if (Build.VERSION.SDK_INT < 31) goto L5;
        LogSessionIdApi31 r22 = new LogSessionIdApi31();
    L6:
        this.logSessionIdApi31 = r22;
        this.equalityToken = new Object();
        return;
    L5:
        r22 = null;
        goto L6
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof PlayerId) == true) goto L8;
        return false;
    L8:
        PlayerId r52 = (PlayerId) r5;
        if (Objects.equals(this.name, r52.name) == true) goto L11;
    L15:
        return false;
    L11:
        if (Objects.equals(this.logSessionIdApi31, r52.logSessionIdApi31) == false) goto L15;
        if (Objects.equals(this.equalityToken, r52.equalityToken) == false) goto L15;
        return true;
    }

    public synchronized LogSessionId getLogSessionId() {
        monitor-enter(this);
        LogSessionId r0 = ((LogSessionIdApi31) Assertions.checkNotNull(this.logSessionIdApi31)).logSessionId;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.logSessionIdApi31, this.equalityToken});
    }

    public synchronized void setLogSessionId(LogSessionId r2) {
        monitor-enter(this);
        ((LogSessionIdApi31) Assertions.checkNotNull(this.logSessionIdApi31)).setLogSessionId(r2);     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }
}
