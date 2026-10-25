package androidx.media3.exoplayer.source;

import android.net.Uri;
import androidx.media3.datasource.DataSpec;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes4.dex */
public final class LoadEventInfo {
    private static final AtomicLong idSource = null;
    public final long bytesLoaded;
    public final DataSpec dataSpec;
    public final long elapsedRealtimeMs;
    public final long loadDurationMs;
    public final long loadTaskId;
    public final Map<String, List<String>> responseHeaders;
    public final Uri uri;

    static {
        idSource = new AtomicLong();
    }

    public LoadEventInfo(long r13, DataSpec r15, long r16) {
        this(r13, r15, r15.uri, Collections.EMPTY_MAP, r16, 0, 0);
    }

    public LoadEventInfo(long r1, DataSpec r3, Uri r4, Map<String, List<String>> r5, long r6, long r8, long r10) {
        this.loadTaskId = r1;
        this.dataSpec = r3;
        this.uri = r4;
        this.responseHeaders = r5;
        this.elapsedRealtimeMs = r6;
        this.loadDurationMs = r8;
        this.bytesLoaded = r10;
    }

    public static long getNewId() {
        return idSource.getAndIncrement();
    }
}
