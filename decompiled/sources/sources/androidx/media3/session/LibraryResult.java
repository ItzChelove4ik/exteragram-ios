package androidx.media3.session;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.core.app.BundleCompat;
import androidx.media3.common.BundleListRetriever;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableList;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class LibraryResult<V> {
    private static final String FIELD_COMPLETION_TIME_MS = null;
    private static final String FIELD_PARAMS = null;
    private static final String FIELD_RESULT_CODE = null;
    private static final String FIELD_SESSION_ERROR = null;
    private static final String FIELD_VALUE = null;
    private static final String FIELD_VALUE_TYPE = null;
    public final long completionTimeMs;
    public final MediaLibraryService$LibraryParams params;
    public final int resultCode;
    public final SessionError sessionError;
    public final V value;
    private final int valueType;

    static {
        FIELD_RESULT_CODE = Util.intToStringMaxRadix(0);
        FIELD_COMPLETION_TIME_MS = Util.intToStringMaxRadix(1);
        FIELD_PARAMS = Util.intToStringMaxRadix(2);
        FIELD_VALUE = Util.intToStringMaxRadix(3);
        FIELD_VALUE_TYPE = Util.intToStringMaxRadix(4);
        FIELD_SESSION_ERROR = Util.intToStringMaxRadix(5);
    }

    private LibraryResult(int r1, long r2, MediaLibraryService$LibraryParams r4, SessionError r5, V r6, int r7) {
        this.resultCode = r1;
        this.completionTimeMs = r2;
        this.params = r4;
        this.sessionError = r5;
        this.value = r6;
        this.valueType = r7;
    }

    @SuppressLint({"WrongConstant"})
    public static <V> LibraryResult<V> ofError(int r3) {
        return ofError(new SessionError(r3, "no error message provided", Bundle.EMPTY));
    }

    @SuppressLint({"WrongConstant"})
    public static <V> LibraryResult<V> ofError(SessionError r8) {
        return new LibraryResult(r8.code, SystemClock.elapsedRealtime(), null, r8, null, 4);
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putInt(FIELD_RESULT_CODE, this.resultCode);
        r0.putLong(FIELD_COMPLETION_TIME_MS, this.completionTimeMs);
        MediaLibraryService$LibraryParams r1 = this.params;
        if (r1 == null) goto L5;
        r0.putBundle(FIELD_PARAMS, r1.toBundle());
    L5:
        SessionError r12 = this.sessionError;
        if (r12 == null) goto L8;
        r0.putBundle(FIELD_SESSION_ERROR, r12.toBundle());
    L8:
        r0.putInt(FIELD_VALUE_TYPE, this.valueType);
        V r13 = this.value;
        if (r13 == null) goto L19;
        int r2 = this.valueType;
        if (r2 != 1) goto L14;
    L24:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    L14:
        if (r2 != 2) goto L16;
        r0.putBundle(FIELD_VALUE, ((MediaItem) r13).toBundle());
        return r0;
    L16:
        if (r2 != 3) goto L18;
        BundleCompat.putBinder(r0, FIELD_VALUE, new BundleListRetriever(BundleCollectionUtil.toBundleList((ImmutableList) this.value, new LibraryResult$$ExternalSyntheticLambda0())));
        return r0;
    L18:
        if (r2 == 4) goto L24;
    L19:
        return r0;
    }
}
