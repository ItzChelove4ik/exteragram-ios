package androidx.media3.session;

import android.os.Bundle;
import android.os.SystemClock;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;

/* loaded from: classes4.dex */
public final class SessionResult {
    private static final String FIELD_COMPLETION_TIME_MS = null;
    private static final String FIELD_EXTRAS = null;
    private static final String FIELD_RESULT_CODE = null;
    private static final String FIELD_SESSION_ERROR = null;
    public final long completionTimeMs;
    public final Bundle extras;
    public final int resultCode;
    public final SessionError sessionError;

    static {
        FIELD_RESULT_CODE = Util.intToStringMaxRadix(0);
        FIELD_EXTRAS = Util.intToStringMaxRadix(1);
        FIELD_COMPLETION_TIME_MS = Util.intToStringMaxRadix(2);
        FIELD_SESSION_ERROR = Util.intToStringMaxRadix(3);
    }

    public SessionResult(int r2) {
        this(r2, Bundle.EMPTY);
    }

    public SessionResult(int r7, Bundle r8) {
        this(r7, r8, SystemClock.elapsedRealtime(), null);
    }

    private SessionResult(int r2, Bundle r3, long r4, SessionError r6) {
        if (r6 == null) goto L7;
        if (r2 < 0) goto L7;
        boolean r0 = false;
    L8:
        Assertions.checkArgument(r0);
        this.resultCode = r2;
        this.extras = new Bundle(r3);
        this.completionTimeMs = r4;
        if (r6 != null) goto L12;
        if (r2 >= 0) goto L12;
        r6 = new SessionError(r2, "no error message provided");
    L12:
        this.sessionError = r6;
        return;
    L7:
        r0 = true;
        goto L8
    }

    public static SessionResult fromBundle(Bundle r8) {
        int r3 = r8.getInt(FIELD_RESULT_CODE, -1);
        Bundle r0 = r8.getBundle(FIELD_EXTRAS);
        long r5 = r8.getLong(FIELD_COMPLETION_TIME_MS, SystemClock.elapsedRealtime());
        Bundle r82 = r8.getBundle(FIELD_SESSION_ERROR);
        if (r82 == null) goto L6;
        SessionError r83 = SessionError.fromBundle(r82);
    L5:
        SessionError r7 = r83;
        if (r0 != null) goto L13;
        r0 = Bundle.EMPTY;
    L13:
        return new SessionResult(r3, r0, r5, r7);
    L6:
        if (r3 == 0) goto L8;
        r83 = new SessionError(r3, "no error message provided");
        goto L5
    L8:
        r83 = null;
        goto L5
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putInt(FIELD_RESULT_CODE, this.resultCode);
        r0.putBundle(FIELD_EXTRAS, this.extras);
        r0.putLong(FIELD_COMPLETION_TIME_MS, this.completionTimeMs);
        SessionError r4 = this.sessionError;
        if (r4 == null) goto L5;
        r0.putBundle(FIELD_SESSION_ERROR, r4.toBundle());
    L5:
        return r0;
    }
}
