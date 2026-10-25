package org.telegram.ui.Components;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes3.dex */
public abstract class PermissionRequest {
    private static int lastId = 1500;

    /* renamed from: org.telegram.ui.Components.PermissionRequest$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 implements NotificationCenter.NotificationCenterDelegate {
        final /* synthetic */ int val$code;
        final /* synthetic */ NotificationCenter.NotificationCenterDelegate[] val$observer;
        final /* synthetic */ Utilities.Callback val$whenDone;

        public AnonymousClass1(int r1, Utilities.Callback r2, NotificationCenter.NotificationCenterDelegate[] r3) {
            this.val$code = r1;
            this.val$whenDone = r2;
            this.val$observer = r3;
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r3, int r4, Object... r5) {
            int r42 = NotificationCenter.activityPermissionsGranted;
            if (r3 != r42) goto L11;
            int r0 = ((Integer) r5[0]).intValue();
            String[] r1 = (String[]) r5[1];
            int[] r52 = (int[]) r5[2];
            if (r0 != this.val$code) goto L12;
            Utilities.Callback r02 = this.val$whenDone;
            if (r02 == null) goto L9;
            r02.run(r52);
        L9:
            NotificationCenter.getGlobalInstance().removeObserver(this.val$observer[0], r42);
            return;
        L12:
            return;
        }
    }

    /* renamed from: $r8$lambda$iRU0goqpim-QBvdfDKQXDlUe4eE, reason: not valid java name */
    public static /* synthetic */ void m11663$r8$lambda$iRU0goqpimQBvdfDKQXDlUe4eE(Utilities.Callback r3, int[] r4) {
        boolean r1 = false;
        if (r4.length >= 1) goto L5;
    L7:
        r3.run(Boolean.valueOf(r1));
        return;
    L5:
        if (r4[0] != 0) goto L7;
        r1 = true;
        goto L7
    }

    public static /* synthetic */ void $r8$lambda$q0hX_pJpuHZP4mTkT6vEQnDXWEE(Activity r1, AlertDialog r2, int r3) {
        Intent r32 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L5
        r32.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L5
        r1.startActivity(r32);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$q9ATOAOAN4Qvrdb6vy7_1KuGHfs(String[] r3, Activity r4, Utilities.Callback r5, int[] r6) {
        int r62 = r3.length;
        boolean r0 = false;
        int r1 = 0;
    L3:
        if (r1 >= r62) goto L8;
        if (r4.checkSelfPermission(r3[r1]) == 0) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        r0 = true;
    L8:
        if (r5 == null) goto L13;
        r5.run(Boolean.valueOf(r0));
        return;
    }

    /* renamed from: $r8$lambda$rqSIchz3-KrqjwF9F5tplg1vFfk, reason: not valid java name */
    public static /* synthetic */ void m11664$r8$lambda$rqSIchz3KrqjwF9F5tplg1vFfk(Activity r1, AlertDialog r2, int r3) {
        Intent r32 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L5
        r32.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L5
        r1.startActivity(r32);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$xa6dWFIyXHQ433VJXZn8tMIv4bE(String[] r3, Activity r4, Utilities.Callback r5, int[] r6) {
        int r62 = r3.length;
        boolean r0 = false;
        int r1 = 0;
    L3:
        if (r1 >= r62) goto L8;
        if (r4.checkSelfPermission(r3[r1]) != 0) goto L9;
        r1 = r1 + 1;
    L9:
        if (r5 == null) goto L14;
        r5.run(Boolean.valueOf(r0));
        return;
    L14:
        return;
    L8:
        r0 = true;
        goto L9
    }

    static {
    }

    public static boolean canAskPermission(String r1) {
        Activity r0 = LaunchActivity.instance;
        if (r0 != null) goto L5;
        r0 = AndroidUtilities.findActivity(ApplicationLoader.applicationContext);
    L5:
        if (r0 != null) goto L9;
        return false;
    L9:
        return r0.shouldShowRequestPermissionRationale(r1);
    }

    public static void ensureAllPermissions(int r0, int r1, String[] r2, Utilities.Callback<Boolean> r3) {
        ensureAllPermissions(r0, r1, r2, r2, r3);
    }

    public static void ensureAllPermissions(int r5, int r6, String[] r7, String[] r8, Utilities.Callback<Boolean> r9) {
        Activity r0 = LaunchActivity.instance;
        if (r0 != null) goto L5;
        r0 = AndroidUtilities.findActivity(ApplicationLoader.applicationContext);
    L5:
        if (r0 == null) goto L32;
        int r1 = r7.length;
        int r3 = 0;
    L8:
        if (r3 >= r1) goto L23;
        if (r0.checkSelfPermission(r7[r3]) != 0) goto L11;
        r3 = r3 + 1;
        goto L8
    L11:
        int r12 = r7.length;
        int r32 = 0;
    L12:
        if (r32 >= r12) goto L20;
        if (r0.shouldShowRequestPermissionRationale(r7[r32]) == true) goto L15;
        r32 = r32 + 1;
        goto L12
    L15:
        new AlertDialog.Builder(r0, null).setTopAnimation(r5, 72, false, Theme.getColor(Theme.key_dialogTopBackground)).setMessage(AndroidUtilities.replaceTags(LocaleController.getString(r6))).setPositiveButton(LocaleController.getString(R.string.PermissionOpenSettings), new PermissionRequest$$ExternalSyntheticLambda3(r0)).setNegativeButton(LocaleController.getString(R.string.ContactsPermissionAlertNotNow), null).create().show();
        if (r9 == null) goto L30;
        r9.run(Boolean.FALSE);
        return;
    L30:
        return;
    L20:
        requestPermissions(r8, new PermissionRequest$$ExternalSyntheticLambda4(r8, r0, r9));
        return;
    L23:
        if (r9 == null) goto L31;
        r9.run(Boolean.TRUE);
        return;
    L31:
        return;
    }

    public static void ensureEitherPermission(int r5, int r6, String[] r7, String[] r8, Utilities.Callback<Boolean> r9) {
        Activity r0 = LaunchActivity.instance;
        if (r0 != null) goto L5;
        r0 = AndroidUtilities.findActivity(ApplicationLoader.applicationContext);
    L5:
        if (r0 == null) goto L32;
        int r1 = r7.length;
        int r3 = 0;
    L8:
        if (r3 >= r1) goto L15;
        if (r0.checkSelfPermission(r7[r3]) == 0) goto L11;
        r3 = r3 + 1;
        goto L8
    L11:
        if (r9 == null) goto L30;
        r9.run(Boolean.TRUE);
        return;
    L30:
        return;
    L15:
        int r12 = r7.length;
        int r32 = 0;
    L16:
        if (r32 >= r12) goto L24;
        if (r0.shouldShowRequestPermissionRationale(r7[r32]) == false) goto L19;
        r32 = r32 + 1;
        goto L16
    L19:
        new AlertDialog.Builder(r0, null).setTopAnimation(r5, 72, false, Theme.getColor(Theme.key_dialogTopBackground)).setMessage(AndroidUtilities.replaceTags(LocaleController.getString(r6))).setPositiveButton(LocaleController.getString(R.string.PermissionOpenSettings), new PermissionRequest$$ExternalSyntheticLambda1(r0)).setNegativeButton(LocaleController.getString(R.string.ContactsPermissionAlertNotNow), null).create().show();
        if (r9 == null) goto L31;
        r9.run(Boolean.FALSE);
        return;
    L31:
        return;
    L24:
        requestPermissions(r8, new PermissionRequest$$ExternalSyntheticLambda2(r8, r0, r9));
        return;
    }

    public static void ensurePermission(int r1, int r2, String r3, Utilities.Callback<Boolean> r4) {
        ensureEitherPermission(r1, r2, new String[]{r3}, new String[]{r3}, r4);
    }

    public static boolean hasPermission(String r2) {
        Context r0 = LaunchActivity.instance;
        if (r0 != null) goto L6;
        r0 = AndroidUtilities.findActivity(ApplicationLoader.applicationContext);
    L6:
        if (r0 != null) goto L9;
        return false;
    L9:
        if (r0.checkSelfPermission(r2) != 0) goto L12;
        return true;
    L12:
        return false;
    }

    public static void requestPermission(String r1, Utilities.Callback<Boolean> r2) {
        String[] r12 = {r1};
        if (r2 == null) goto L5;
        PermissionRequest$$ExternalSyntheticLambda0 r0 = new PermissionRequest$$ExternalSyntheticLambda0(r2);
    L6:
        requestPermissions(r12, r0);
        return;
    L5:
        r0 = null;
        goto L6
    }

    public static void requestPermissions(String[] r4, Utilities.Callback<int[]> r5) {
        Activity r0 = LaunchActivity.instance;
        if (r0 != null) goto L5;
        r0 = AndroidUtilities.findActivity(ApplicationLoader.applicationContext);
    L5:
        if (r0 != null) goto L7;
        return;
    L7:
        int r1 = lastId;
        lastId = r1 + 1;
        NotificationCenter.NotificationCenterDelegate[] r2 = new NotificationCenter.NotificationCenterDelegate[1];
        r2[0] = new AnonymousClass1(r1, r5, r2);
        NotificationCenter.getGlobalInstance().addObserver(r2[0], NotificationCenter.activityPermissionsGranted);
        r0.requestPermissions(r4, r1);
    }

    public static void showPermissionSettings(String r3) {
        Activity r32 = LaunchActivity.instance;
        if (r32 != null) goto L5;
        r32 = AndroidUtilities.findActivity(ApplicationLoader.applicationContext);
    L5:
        if (r32 != null) goto L7;
        return;
    L7:
        Intent r0 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        r0.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));
        r32.startActivity(r0);     // Catch: Exception -> L10
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
    }
}
