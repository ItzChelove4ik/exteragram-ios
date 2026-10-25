package androidx.media3.exoplayer.drm;

import android.os.Looper;
import androidx.media3.common.Format;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;

/* loaded from: classes4.dex */
public interface DrmSessionManager {
    public static final DrmSessionManager DRM_UNSUPPORTED = null;

    /* renamed from: androidx.media3.exoplayer.drm.DrmSessionManager$1, reason: invalid class name */
    public class AnonymousClass1 implements DrmSessionManager {
        public AnonymousClass1() {
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionManager
        public DrmSession acquireSession(DrmSessionEventListener.EventDispatcher r2, Format r3) {
            if (r3.drmInitData != null) goto L7;
            return null;
        L7:
            return new ErrorStateDrmSession(new DrmSession.DrmSessionException(new UnsupportedDrmException(1), 6001));
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionManager
        public int getCryptoType(Format r1) {
            if (r1.drmInitData == null) goto L6;
            return 1;
        L6:
            return 0;
        }

        @Override // androidx.media3.exoplayer.drm.DrmSessionManager
        public void setPlayer(Looper r1, PlayerId r2) {
        }
    }

    public interface DrmSessionReference {
        public static final DrmSessionReference EMPTY = null;

        /* renamed from: $r8$lambda$DM-a17sm9MCvIJcj8WTjZ7ghCMI, reason: not valid java name */
        static /* synthetic */ void m829$r8$lambda$DMa17sm9MCvIJcj8WTjZ7ghCMI() {
        }

        static {
            EMPTY = new DrmSessionManager$DrmSessionReference$$ExternalSyntheticLambda0();
        }

        void release();
    }

    static {
        DRM_UNSUPPORTED = new AnonymousClass1();
    }

    DrmSession acquireSession(DrmSessionEventListener.EventDispatcher r1, Format r2);

    int getCryptoType(Format r1);

    default DrmSessionReference preacquireSession(DrmSessionEventListener.EventDispatcher r1, Format r2) {
        return DrmSessionReference.EMPTY;
    }

    default void prepare() {
    }

    default void release() {
    }

    void setPlayer(Looper r1, PlayerId r2);
}
