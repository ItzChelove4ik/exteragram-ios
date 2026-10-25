package androidx.media3.datasource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public final class HttpDataSource$RequestProperties {
    private final Map<String, String> requestProperties;
    private Map<String, String> requestPropertiesSnapshot;

    public HttpDataSource$RequestProperties() {
        this.requestProperties = new HashMap();
    }

    public synchronized Map<String, String> getSnapshot() {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (this.requestPropertiesSnapshot != null) goto L8;
        this.requestPropertiesSnapshot = Collections.unmodifiableMap(new HashMap(this.requestProperties));     // Catch: Throwable -> L6
    L8:
        Map<String, String> r0 = this.requestPropertiesSnapshot;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    }
}
