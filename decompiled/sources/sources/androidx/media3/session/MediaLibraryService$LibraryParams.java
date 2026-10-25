package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.util.Util;

/* loaded from: classes4.dex */
public final class MediaLibraryService$LibraryParams {
    private static final String FIELD_EXTRAS = null;
    private static final String FIELD_OFFLINE = null;
    private static final String FIELD_RECENT = null;
    private static final String FIELD_SUGGESTED = null;
    public final Bundle extras;
    public final boolean isOffline;
    public final boolean isRecent;
    public final boolean isSuggested;

    static {
        FIELD_EXTRAS = Util.intToStringMaxRadix(0);
        FIELD_RECENT = Util.intToStringMaxRadix(1);
        FIELD_OFFLINE = Util.intToStringMaxRadix(2);
        FIELD_SUGGESTED = Util.intToStringMaxRadix(3);
    }

    private MediaLibraryService$LibraryParams(Bundle r2, boolean r3, boolean r4, boolean r5) {
        this.extras = new Bundle(r2);
        this.isRecent = r3;
        this.isOffline = r4;
        this.isSuggested = r5;
    }

    public static MediaLibraryService$LibraryParams fromBundle(Bundle r5) {
        Bundle r0 = r5.getBundle(FIELD_EXTRAS);
        boolean r1 = r5.getBoolean(FIELD_RECENT, false);
        boolean r3 = r5.getBoolean(FIELD_OFFLINE, false);
        boolean r52 = r5.getBoolean(FIELD_SUGGESTED, false);
        if (r0 != null) goto L6;
        r0 = Bundle.EMPTY;
    L6:
        return new MediaLibraryService$LibraryParams(r0, r1, r3, r52);
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putBundle(FIELD_EXTRAS, this.extras);
        r0.putBoolean(FIELD_RECENT, this.isRecent);
        r0.putBoolean(FIELD_OFFLINE, this.isOffline);
        r0.putBoolean(FIELD_SUGGESTED, this.isSuggested);
        return r0;
    }
}
