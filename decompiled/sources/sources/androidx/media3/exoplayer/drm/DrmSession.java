package androidx.media3.exoplayer.drm;

import androidx.media3.decoder.CryptoConfig;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes4.dex */
public interface DrmSession {

    public static class DrmSessionException extends IOException {
        public final int errorCode;

        public DrmSessionException(Throwable r1, int r2) {
            super(r1);
            this.errorCode = r2;
        }
    }

    static void replaceSession(DrmSession r1, DrmSession r2) {
        if (r1 != r2) goto L5;
        return;
    L5:
        if (r2 == null) goto L7;
        r2.acquire(null);
    L7:
        if (r1 == null) goto L10;
        r1.release(null);
        return;
    }

    void acquire(DrmSessionEventListener.EventDispatcher r1);

    CryptoConfig getCryptoConfig();

    DrmSessionException getError();

    UUID getSchemeUuid();

    int getState();

    boolean playClearSamplesWithoutKeys();

    Map<String, String> queryKeyStatus();

    void release(DrmSessionEventListener.EventDispatcher r1);

    boolean requiresSecureDecoder(String r1);
}
