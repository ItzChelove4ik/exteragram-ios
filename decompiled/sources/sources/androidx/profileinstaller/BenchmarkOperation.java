package androidx.profileinstaller;

import android.content.Context;
import android.os.Build;
import androidx.profileinstaller.ProfileInstallReceiver;
import java.io.File;

/* loaded from: classes4.dex */
abstract class BenchmarkOperation {

    public static class Api21ContextHelper {
        public static File getCodeCacheDir(Context r0) {
            return r0.getCodeCacheDir();
        }
    }

    public static class Api24ContextHelper {
        public static Context createDeviceProtectedStorageContext(Context r0) {
            return r0.createDeviceProtectedStorageContext();
        }
    }

    public static boolean deleteFilesRecursively(File r6) {
        if (r6.isDirectory() == false) goto L16;
        File[] r62 = r6.listFiles();
        if (r62 != null) goto L7;
        return false;
    L7:
        int r2 = r62.length;
        int r3 = 0;
        boolean r4 = true;
    L8:
        if (r3 >= r2) goto L15;
        if (deleteFilesRecursively(r62[r3]) == false) goto L13;
        if (r4 == false) goto L13;
        r4 = true;
    L14:
        r3 = r3 + 1;
    L13:
        r4 = false;
        goto L14
    L15:
        return r4;
    L16:
        r6.delete();
        return true;
    }

    public static void dropShaderCache(Context r2, ProfileInstallReceiver.ResultDiagnostics r3) {
        if (Build.VERSION.SDK_INT < 34) goto L5;
        File r22 = Api24ContextHelper.createDeviceProtectedStorageContext(r2).getCacheDir();
    L7:
        if (deleteFilesRecursively(r22) == false) goto L10;
        r3.onResultReceived(14, null);
        return;
    L10:
        r3.onResultReceived(15, null);
        return;
    L5:
        r22 = Api21ContextHelper.getCodeCacheDir(Api24ContextHelper.createDeviceProtectedStorageContext(r2));
        goto L7
    }
}
