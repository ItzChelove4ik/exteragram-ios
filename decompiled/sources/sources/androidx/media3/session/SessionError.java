package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class SessionError {
    private static final String FIELD_CODE = null;
    private static final String FIELD_EXTRAS = null;
    private static final String FIELD_MESSAGE = null;
    public int code;
    public Bundle extras;
    public String message;

    static {
        FIELD_CODE = Util.intToStringMaxRadix(0);
        FIELD_MESSAGE = Util.intToStringMaxRadix(1);
        FIELD_EXTRAS = Util.intToStringMaxRadix(2);
    }

    public SessionError(int r2, String r3) {
        this(r2, r3, Bundle.EMPTY);
    }

    public SessionError(int r2, String r3, Bundle r4) {
        boolean r0 = true;
        if (r2 < 0) goto L7;
        if (r2 == 1) goto L7;
        r0 = false;
    L7:
        Assertions.checkArgument(r0);
        this.code = r2;
        this.message = r3;
        this.extras = r4;
    }

    public static SessionError fromBundle(Bundle r3) {
        int r0 = r3.getInt(FIELD_CODE, 1000);
        String r1 = r3.getString(FIELD_MESSAGE, _UrlKt.FRAGMENT_ENCODE_SET);
        Bundle r32 = r3.getBundle(FIELD_EXTRAS);
        if (r32 != null) goto L6;
        r32 = Bundle.EMPTY;
    L6:
        return new SessionError(r0, r1, r32);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof SessionError) == true) goto L8;
        return false;
    L8:
        SessionError r52 = (SessionError) r5;
        if (this.code == r52.code) goto L11;
    L13:
        return false;
    L11:
        if (Objects.equals(this.message, r52.message) == false) goto L13;
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.code), this.message});
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putInt(FIELD_CODE, this.code);
        r0.putString(FIELD_MESSAGE, this.message);
        if (this.extras.isEmpty() == true) goto L5;
        r0.putBundle(FIELD_EXTRAS, this.extras);
    L5:
        return r0;
    }
}
