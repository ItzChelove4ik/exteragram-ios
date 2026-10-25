package androidx.core.content;

import android.content.Context;
import android.os.Binder;
import android.os.Process;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.util.ObjectsCompat;

/* loaded from: classes3.dex */
public abstract class PermissionChecker {
    public static int checkCallingOrSelfPermission(Context r3, String r4) {
        if (Binder.getCallingPid() != Process.myPid()) goto L5;
        String r0 = r3.getPackageName();
    L7:
        return checkPermission(r3, r4, Binder.getCallingPid(), Binder.getCallingUid(), r0);
    L5:
        r0 = null;
        goto L7
    }

    public static int checkPermission(Context r2, String r3, int r4, int r5, String r6) {
        if (r2.checkPermission(r3, r4, r5) != (-1)) goto L5;
        return -1;
    L5:
        String r32 = AppOpsManagerCompat.permissionToOp(r3);
        if (r32 != null) goto L8;
        return 0;
    L8:
        if (r6 != null) goto L16;
        String[] r62 = r2.getPackageManager().getPackagesForUid(r5);
        if (r62 != null) goto L12;
    L15:
        return -1;
    L12:
        if (r62.length <= 0) goto L15;
        r6 = r62[0];
    L16:
        int r0 = Process.myUid();
        String r1 = r2.getPackageName();
        if (r0 == r5) goto L19;
    L21:
        int r22 = AppOpsManagerCompat.noteProxyOpNoThrow(r2, r32, r6);
    L22:
        if (r22 != 0) goto L24;
        return 0;
    L24:
        return -2;
    L19:
        if (ObjectsCompat.equals(r1, r6) == false) goto L21;
        r22 = AppOpsManagerCompat.checkOrNoteProxyOp(r2, r5, r32, r6);
        goto L22
    }

    public static int checkSelfPermission(Context r3, String r4) {
        return checkPermission(r3, r4, Process.myPid(), Process.myUid(), r3.getPackageName());
    }
}
