package androidx.media3.session.legacy;

import android.os.Bundle;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class MediaBrowserServiceCompat$BrowserRoot {
    private final Bundle extras;
    private final String rootId;

    public MediaBrowserServiceCompat$BrowserRoot(String r1, Bundle r2) {
        if (r1 == null) goto L6;
        this.rootId = r1;
        this.extras = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead");
        throw null;
    }
}
