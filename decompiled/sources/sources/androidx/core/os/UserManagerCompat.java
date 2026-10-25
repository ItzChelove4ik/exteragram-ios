package androidx.core.os;

import android.content.Context;
import android.os.UserManager;

/* loaded from: classes.dex */
public abstract class UserManagerCompat {

    public static class Api24Impl {
        public static boolean isUserUnlocked(Context r1) {
            return ((UserManager) r1.getSystemService(UserManager.class)).isUserUnlocked();
        }
    }

    public static boolean isUserUnlocked(Context r0) {
        return Api24Impl.isUserUnlocked(r0);
    }
}
