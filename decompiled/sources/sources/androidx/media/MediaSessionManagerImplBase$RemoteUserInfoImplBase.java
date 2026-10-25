package androidx.media;

import android.text.TextUtils;
import androidx.core.util.ObjectsCompat;

/* loaded from: classes3.dex */
class MediaSessionManagerImplBase$RemoteUserInfoImplBase implements MediaSessionManager$RemoteUserInfoImpl {
    private String mPackageName;
    private int mPid;
    private int mUid;

    public MediaSessionManagerImplBase$RemoteUserInfoImplBase(String r1, int r2, int r3) {
        this.mPackageName = r1;
        this.mPid = r2;
        this.mUid = r3;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof MediaSessionManagerImplBase$RemoteUserInfoImplBase) == true) goto L8;
        return false;
    L8:
        MediaSessionManagerImplBase$RemoteUserInfoImplBase r52 = (MediaSessionManagerImplBase$RemoteUserInfoImplBase) r5;
        if (this.mPid < 0) goto L22;
        if (r52.mPid < 0) goto L22;
        if (TextUtils.equals(this.mPackageName, r52.mPackageName) == true) goto L16;
    L20:
        return false;
    L16:
        if (this.mPid != r52.mPid) goto L20;
        if (this.mUid != r52.mUid) goto L20;
        return true;
    L22:
        if (TextUtils.equals(this.mPackageName, r52.mPackageName) == true) goto L24;
    L26:
        return false;
    L24:
        if (this.mUid != r52.mUid) goto L26;
        return true;
    }

    public int hashCode() {
        return ObjectsCompat.hash(new Object[]{this.mPackageName, Integer.valueOf(this.mUid)});
    }
}
