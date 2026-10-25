package androidx.media;

import android.media.session.MediaSessionManager;
import android.os.Build;
import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
public final class MediaSessionManager$RemoteUserInfo {
    MediaSessionManager$RemoteUserInfoImpl mImpl;

    public MediaSessionManager$RemoteUserInfo(MediaSessionManager.RemoteUserInfo r3) {
        String r0 = MediaSessionManagerImplApi28$RemoteUserInfoImplApi28.getPackageName(r3);
        if (r0 != null) goto L5;
        f$$ExternalSyntheticBUOutline2.m("package shouldn't be null");
        throw null;
    L5:
        if (TextUtils.isEmpty(r0) == true) goto L8;
        this.mImpl = new MediaSessionManagerImplApi28$RemoteUserInfoImplApi28(r3);
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("packageName should be nonempty");
        throw null;
    }

    public MediaSessionManager$RemoteUserInfo(String r3, int r4, int r5) {
        if (r3 != null) goto L5;
        f$$ExternalSyntheticBUOutline2.m("package shouldn't be null");
        throw null;
    L5:
        if (TextUtils.isEmpty(r3) == false) goto L7;
        f$$ExternalSyntheticBUOutline1.m("packageName should be nonempty");
        throw null;
    L7:
        if (Build.VERSION.SDK_INT < 28) goto L10;
        this.mImpl = new MediaSessionManagerImplApi28$RemoteUserInfoImplApi28(r3, r4, r5);
        return;
    L10:
        this.mImpl = new MediaSessionManagerImplBase$RemoteUserInfoImplBase(r3, r4, r5);
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof MediaSessionManager$RemoteUserInfo) == true) goto L10;
        return false;
    L10:
        return this.mImpl.equals(((MediaSessionManager$RemoteUserInfo) r2).mImpl);
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }
}
