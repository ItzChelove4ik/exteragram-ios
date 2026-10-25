package androidx.media;

import android.media.session.MediaSessionManager;

/* loaded from: classes3.dex */
final class MediaSessionManagerImplApi28$RemoteUserInfoImplApi28 extends MediaSessionManagerImplBase$RemoteUserInfoImplBase {
    final MediaSessionManager.RemoteUserInfo mObject;

    public MediaSessionManagerImplApi28$RemoteUserInfoImplApi28(MediaSessionManager.RemoteUserInfo r4) {
        super(r4.getPackageName(), r4.getPid(), r4.getUid());
        this.mObject = r4;
    }

    public MediaSessionManagerImplApi28$RemoteUserInfoImplApi28(String r1, int r2, int r3) {
        super(r1, r2, r3);
        this.mObject = MediaSessionManagerImplApi28$RemoteUserInfoImplApi28$$ExternalSyntheticApiModelOutline0.m(r1, r2, r3);
    }

    public static String getPackageName(MediaSessionManager.RemoteUserInfo r0) {
        return r0.getPackageName();
    }
}
