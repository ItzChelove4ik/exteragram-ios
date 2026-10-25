package androidx.core.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.sun.jna.Native$$ExternalSyntheticBUOutline2;
import java.util.Arrays;
import java.util.HashSet;

/* loaded from: classes3.dex */
public abstract class ActivityCompat extends ContextCompat {

    public static class Api31Impl {
        @SuppressLint({"BanUncheckedReflection"})
        public static boolean shouldShowRequestPermissionRationale(Activity r4, String r5) {
            PackageManager r0 = r4.getApplication().getPackageManager();     // Catch: Throwable -> L4
            return ((Boolean) PackageManager.class.getMethod("shouldShowRequestPermissionRationale", new Class[]{String.class}).invoke(r0, new Object[]{r5})).booleanValue();
        L5:
            return r4.shouldShowRequestPermissionRationale(r5);
        }
    }

    public static class Api32Impl {
        public static boolean shouldShowRequestPermissionRationale(Activity r0, String r1) {
            return r0.shouldShowRequestPermissionRationale(r1);
        }
    }

    /* loaded from: classes.dex */
    public interface RequestPermissionsRequestCodeValidator {
        void validateRequestPermissionsRequestCode(int r1);
    }

    public static /* synthetic */ void $r8$lambda$PEvuXAyy2Ld2s8FnpyspwjlByy4(Activity r1) {
        if (r1.isFinishing() == false) goto L5;
        return;
    L5:
        if (ActivityRecreator.recreate(r1) == true) goto L9;
        r1.recreate();
        return;
    }

    public static void finishAffinity(Activity r0) {
        r0.finishAffinity();
    }

    public static void finishAfterTransition(Activity r0) {
        r0.finishAfterTransition();
    }

    public static void postponeEnterTransition(Activity r0) {
        r0.postponeEnterTransition();
    }

    public static void recreate(Activity r2) {
        if (Build.VERSION.SDK_INT < 28) goto L6;
        r2.recreate();
        return;
    L6:
        new Handler(r2.getMainLooper()).post(new ActivityCompat$$ExternalSyntheticLambda0(r2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void requestPermissions(Activity r6, String[] r7, int r8) {
        HashSet r0 = new HashSet();
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r2 >= r7.length) goto L15;
        if (TextUtils.isEmpty(r7[r2]) == true) goto L13;
        if (Build.VERSION.SDK_INT >= 33) goto L12;
        if (TextUtils.equals(r7[r2], "android.permission.POST_NOTIFICATIONS") == false) goto L12;
        r0.add(Integer.valueOf(r2));
    L12:
        r2 = r2 + 1;
        goto L4
    L13:
        Native$$ExternalSyntheticBUOutline2.m("Permission request for permissions ", Arrays.toString(r7), " must not contain null or empty values");
        return;
    L15:
        int r22 = r0.size();
        if (r22 <= 0) goto L18;
        String[] r3 = new String[r7.length - r22];
    L19:
        if (r22 <= 0) goto L31;
        if (r22 != r7.length) goto L23;
        return;
    L23:
        int r23 = 0;
    L25:
        if (r1 >= r7.length) goto L31;
        if (r0.contains(Integer.valueOf(r1)) == true) goto L29;
        r3[r23] = r7[r1];
        r23 = r23 + 1;
    L29:
        r1 = r1 + 1;
    L31:
        if ((r6 instanceof RequestPermissionsRequestCodeValidator) == false) goto L33;
        ((RequestPermissionsRequestCodeValidator) r6).validateRequestPermissionsRequestCode(r8);
    L33:
        r6.requestPermissions(r7, r8);
        return;
    L18:
        r3 = r7;
        goto L19
    }

    public static void setEnterSharedElementCallback(Activity r0, SharedElementCallback r1) {
        r0.setEnterSharedElementCallback(null);
    }

    public static void setExitSharedElementCallback(Activity r0, SharedElementCallback r1) {
        r0.setExitSharedElementCallback(null);
    }

    public static boolean shouldShowRequestPermissionRationale(Activity r2, String r3) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 >= 33) goto L9;
        if (TextUtils.equals("android.permission.POST_NOTIFICATIONS", r3) == false) goto L9;
        return false;
    L9:
        if (r0 < 32) goto L13;
        return Api32Impl.shouldShowRequestPermissionRationale(r2, r3);
    L13:
        if (r0 != 31) goto L17;
        return Api31Impl.shouldShowRequestPermissionRationale(r2, r3);
    L17:
        return r2.shouldShowRequestPermissionRationale(r3);
    }

    public static void startActivityForResult(Activity r0, Intent r1, int r2, Bundle r3) {
        r0.startActivityForResult(r1, r2, r3);
    }

    public static void startIntentSenderForResult(Activity r0, IntentSender r1, int r2, Intent r3, int r4, int r5, int r6, Bundle r7) {
        r0.startIntentSenderForResult(r1, r2, r3, r4, r5, r6, r7);
    }

    public static void startPostponedEnterTransition(Activity r0) {
        r0.startPostponedEnterTransition();
    }
}
