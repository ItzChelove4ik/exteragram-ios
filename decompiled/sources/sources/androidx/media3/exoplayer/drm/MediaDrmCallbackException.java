package androidx.media3.exoplayer.drm;

import android.net.Uri;
import androidx.media3.datasource.DataSpec;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class MediaDrmCallbackException extends IOException {
    public final long bytesLoaded;
    public final DataSpec dataSpec;
    public final Map<String, List<String>> responseHeaders;
    public final Uri uriAfterRedirects;

    public MediaDrmCallbackException(DataSpec r1, Uri r2, Map<String, List<String>> r3, long r4, Throwable r6) {
        super(r6);
        this.dataSpec = r1;
        this.uriAfterRedirects = r2;
        this.responseHeaders = r3;
        this.bytesLoaded = r4;
    }
}
