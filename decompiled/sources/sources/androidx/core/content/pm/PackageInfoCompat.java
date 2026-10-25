package androidx.core.content.pm;

import android.content.pm.PackageInfo;
import android.os.Build;

/* loaded from: classes3.dex */
public abstract class PackageInfoCompat {

    public static class Api28Impl {
        public static long getLongVersionCode(PackageInfo r2) {
            return r2.getLongVersionCode();
        }
    }

    public static long getLongVersionCode(PackageInfo r2) {
        if (Build.VERSION.SDK_INT < 28) goto L7;
        return Api28Impl.getLongVersionCode(r2);
    L7:
        return r2.versionCode;
    }
}
