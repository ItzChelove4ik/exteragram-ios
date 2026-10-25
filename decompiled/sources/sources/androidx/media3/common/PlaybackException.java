package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Util;

/* loaded from: classes3.dex */
public abstract class PlaybackException extends Exception {
    private static final String FIELD_BUNDLE_EXTRAS = null;
    private static final String FIELD_INT_ERROR_CODE = null;
    private static final String FIELD_LONG_TIMESTAMP_MS = null;
    private static final String FIELD_STRING_CAUSE_CLASS_NAME = null;
    private static final String FIELD_STRING_CAUSE_MESSAGE = null;
    private static final String FIELD_STRING_MESSAGE = null;
    public final int errorCode;
    public final Bundle extras;
    public final long timestampMs;

    static {
        FIELD_INT_ERROR_CODE = Util.intToStringMaxRadix(0);
        FIELD_LONG_TIMESTAMP_MS = Util.intToStringMaxRadix(1);
        FIELD_STRING_MESSAGE = Util.intToStringMaxRadix(2);
        FIELD_STRING_CAUSE_CLASS_NAME = Util.intToStringMaxRadix(3);
        FIELD_STRING_CAUSE_MESSAGE = Util.intToStringMaxRadix(4);
        FIELD_BUNDLE_EXTRAS = Util.intToStringMaxRadix(5);
    }

    public PlaybackException(String r1, Throwable r2, int r3, Bundle r4, long r5) {
        super(r1, r2);
        this.errorCode = r3;
        this.extras = r4;
        this.timestampMs = r5;
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putInt(FIELD_INT_ERROR_CODE, this.errorCode);
        r0.putLong(FIELD_LONG_TIMESTAMP_MS, this.timestampMs);
        r0.putString(FIELD_STRING_MESSAGE, getMessage());
        r0.putBundle(FIELD_BUNDLE_EXTRAS, this.extras);
        Throwable r4 = getCause();
        if (r4 == null) goto L5;
        r0.putString(FIELD_STRING_CAUSE_CLASS_NAME, r4.getClass().getName());
        r0.putString(FIELD_STRING_CAUSE_MESSAGE, r4.getMessage());
    L5:
        return r0;
    }
}
