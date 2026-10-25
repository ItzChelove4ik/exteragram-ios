package androidx.media3.session;

import android.media.session.MediaSession;
import android.os.Bundle;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Util;

/* loaded from: classes4.dex */
public final class SessionToken {
    private static final String FIELD_IMPL = null;
    private static final String FIELD_IMPL_TYPE = null;
    private final SessionTokenImpl impl;

    public interface SessionTokenImpl {
        Bundle getExtras();

        Bundle toBundle();
    }

    static {
        MediaLibraryInfo.registerModule("media3.session");
        FIELD_IMPL_TYPE = Util.intToStringMaxRadix(0);
        FIELD_IMPL = Util.intToStringMaxRadix(1);
    }

    public SessionToken(int r10, int r11, int r12, int r13, String r14, IMediaSession r15, Bundle r16, MediaSession.Token r17) {
        this.impl = new SessionTokenImplBase(r10, r11, r12, r13, r14, r15, r16, r17);
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof SessionToken) == true) goto L7;
        return false;
    L7:
        return this.impl.equals(((SessionToken) r2).impl);
    }

    public Bundle getExtras() {
        return this.impl.getExtras();
    }

    public int hashCode() {
        return this.impl.hashCode();
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        if ((this.impl instanceof SessionTokenImplBase) == false) goto L5;
        r0.putInt(FIELD_IMPL_TYPE, 0);
    L6:
        r0.putBundle(FIELD_IMPL, this.impl.toBundle());
        return r0;
    L5:
        r0.putInt(FIELD_IMPL_TYPE, 1);
        goto L6
    }

    public String toString() {
        return this.impl.toString();
    }
}
