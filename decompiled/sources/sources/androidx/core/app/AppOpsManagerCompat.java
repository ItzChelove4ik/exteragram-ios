package androidx.core.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;

/* loaded from: classes3.dex */
public abstract class AppOpsManagerCompat {

    public static class Api29Impl {
        public static int checkOpNoThrow(AppOpsManager r0, String r1, int r2, String r3) {
            if (r0 != null) goto L6;
            return 1;
        L6:
            return r0.checkOpNoThrow(r1, r2, r3);
        }

        public static String getOpPackageName(Context r0) {
            return r0.getOpPackageName();
        }

        public static AppOpsManager getSystemService(Context r1) {
            return (AppOpsManager) r1.getSystemService(AppOpsManager.class);
        }
    }

    public static int checkOrNoteProxyOp(Context r2, int r3, String r4, String r5) {
        if (Build.VERSION.SDK_INT < 29) goto L10;
        AppOpsManager r0 = Api29Impl.getSystemService(r2);
        int r52 = Api29Impl.checkOpNoThrow(r0, r4, Binder.getCallingUid(), r5);
        if (r52 == 0) goto L8;
        return r52;
    L8:
        return Api29Impl.checkOpNoThrow(r0, r4, r3, Api29Impl.getOpPackageName(r2));
    L10:
        return noteProxyOpNoThrow(r2, r4, r5);
    }

    public static int noteProxyOpNoThrow(Context r1, String r2, String r3) {
        return ((AppOpsManager) r1.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(r2, r3);
    }

    public static String permissionToOp(String r0) {
        return AppOpsManager.permissionToOp(r0);
    }
}
