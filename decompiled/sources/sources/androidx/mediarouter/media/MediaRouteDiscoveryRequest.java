package androidx.mediarouter.media;

import android.os.Bundle;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class MediaRouteDiscoveryRequest {
    private static final String KEY_ACTIVE_SCAN = "activeScan";
    private static final String KEY_SELECTOR = "selector";
    private final Bundle mBundle;
    private MediaRouteSelector mSelector;

    private MediaRouteDiscoveryRequest(Bundle r1) {
        this.mBundle = r1;
    }

    public MediaRouteDiscoveryRequest(MediaRouteSelector r2, boolean r3) {
        if (r2 == null) goto L6;
        Bundle r0 = new Bundle();
        this.mBundle = r0;
        this.mSelector = r2;
        r0.putBundle("selector", r2.asBundle());
        r0.putBoolean(KEY_ACTIVE_SCAN, r3);
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("selector must not be null");
        throw null;
    }

    private void ensureSelector() {
        if (this.mSelector != null) goto L8;
        MediaRouteSelector r0 = MediaRouteSelector.fromBundle(this.mBundle.getBundle("selector"));
        this.mSelector = r0;
        if (r0 != null) goto L9;
        this.mSelector = MediaRouteSelector.EMPTY;
        return;
    L9:
        return;
    }

    public static MediaRouteDiscoveryRequest fromBundle(Bundle r1) {
        if (r1 != null) goto L4;
        return null;
    L4:
        return new MediaRouteDiscoveryRequest(r1);
    }

    public Bundle asBundle() {
        return this.mBundle;
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof MediaRouteDiscoveryRequest) == false) goto L10;
        MediaRouteDiscoveryRequest r42 = (MediaRouteDiscoveryRequest) r4;
        if (getSelector().equals(r42.getSelector()) == false) goto L10;
        if (isActiveScan() != r42.isActiveScan()) goto L10;
        return true;
    L10:
        return false;
    }

    public MediaRouteSelector getSelector() {
        ensureSelector();
        return this.mSelector;
    }

    public int hashCode() {
        int r0 = getSelector().hashCode();
        return isActiveScan() ^ r0;
    }

    public boolean isActiveScan() {
        return this.mBundle.getBoolean(KEY_ACTIVE_SCAN);
    }

    public boolean isValid() {
        ensureSelector();
        return this.mSelector.isValid();
    }

    public String toString() {
        return "DiscoveryRequest{ selector=" + getSelector() + ", activeScan=" + isActiveScan() + ", isValid=" + isValid() + " }";
    }
}
