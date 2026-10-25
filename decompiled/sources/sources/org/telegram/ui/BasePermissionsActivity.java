package org.telegram.ui;

import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.camera.CameraController;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes.dex */
public abstract class BasePermissionsActivity extends FragmentActivity {
    protected int currentAccount;

    public static /* synthetic */ void $r8$lambda$sX__QPVHjhHYLh_uJdXFNVC4zn8(BasePermissionsActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createPermissionErrorAlert$0(r1, r2);
    }

    public BasePermissionsActivity() {
        this.currentAccount = -1;
    }

    private /* synthetic */ void lambda$createPermissionErrorAlert$0(AlertDialog r2, int r3) {
        Intent r32 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L5
        r32.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L5
        startActivity(r32);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private void showPermissionErrorAlert(int r1, String r2) {
        createPermissionErrorAlert(r1, r2).show();
    }

    public boolean checkPermissionsResult(int r10, String[] r11, int[] r12) {
        if (r12 != null) goto L5;
        r12 = new int[0];
    L5:
        if (r11 != null) goto L8;
        r11 = new String[0];
    L8:
        if (r12.length > 0) goto L10;
    L12:
        boolean r1 = false;
    L14:
        if (r10 != 104) goto L21;
        if (r1 == false) goto L19;
        GroupCallActivity r9 = GroupCallActivity.groupCallInstance;
        if (r9 == null) goto L93;
        r9.enableCamera();
    L93:
        return true;
    L19:
        showPermissionErrorAlert(R.raw.permission_request_camera, LocaleController.getString(R.string.VoipNeedCameraPermission));
        goto L93
    L21:
        if (r10 == 4) goto L86;
        if (r10 == 151) goto L86;
        if (r10 != 5) goto L31;
        if (r1 == true) goto L29;
        showPermissionErrorAlert(R.raw.permission_request_contacts, LocaleController.getString(R.string.PermissionNoContactsSharing));
        return false;
    L29:
        ContactsController.getInstance(this.currentAccount).forceImportContacts();
        goto L93
    L31:
        if (r10 == 3) goto L59;
        if (r10 == 150) goto L59;
        if (r10 != 18) goto L37;
    L57:
        if (r1 == true) goto L93;
        showPermissionErrorAlert(R.raw.permission_request_camera, LocaleController.getString(R.string.PermissionNoCameraWithHint));
        goto L93
    L37:
        if (r10 == 19) goto L57;
        if (r10 == 20) goto L57;
        if (r10 == 22) goto L57;
        if (r10 != 2) goto L51;
        NotificationCenter r92 = NotificationCenter.getGlobalInstance();
        if (r1 == false) goto L48;
        int r102 = NotificationCenter.locationPermissionGranted;
    L49:
        r92.postNotificationName(r102, new Object[0]);
        goto L93
    L48:
        r102 = NotificationCenter.locationPermissionDenied;
        goto L49
    L51:
        if (r10 != 211) goto L93;
        NotificationCenter r93 = NotificationCenter.getGlobalInstance();
        if (r1 == false) goto L55;
        int r103 = NotificationCenter.locationPermissionGranted;
    L56:
        r93.postNotificationName(r103, new Object[]{1});
        goto L93
    L55:
        r103 = NotificationCenter.locationPermissionDenied;
    L59:
        int r13 = Math.min(r11.length, r12.length);
        int r3 = 0;
        boolean r5 = true;
        boolean r6 = true;
    L60:
        if (r3 >= r13) goto L74;
        if ("android.permission.RECORD_AUDIO".equals(r11[r3]) == false) goto L68;
        if (r12[r3] != 0) goto L66;
        r5 = true;
    L73:
        r3 = r3 + 1;
        goto L60
    L66:
        r5 = false;
        goto L73
    L68:
        if ("android.permission.CAMERA".equals(r11[r3]) == false) goto L73;
        if (r12[r3] != 0) goto L72;
        r6 = true;
        goto L73
    L72:
        r6 = false;
        goto L73
    L74:
        if (r10 != 150) goto L78;
        if (r5 == false) goto L77;
        if (r6 == true) goto L78;
    L77:
        showPermissionErrorAlert(R.raw.permission_request_camera, LocaleController.getString(R.string.PermissionNoCameraMicVideo));
    L78:
        if (r5 == true) goto L80;
        showPermissionErrorAlert(R.raw.permission_request_microphone, LocaleController.getString(R.string.PermissionNoAudioWithHint));
        goto L93
    L80:
        if (r6 == true) goto L83;
        showPermissionErrorAlert(R.raw.permission_request_camera, LocaleController.getString(R.string.PermissionNoCameraWithHint));
        goto L93
    L83:
        if (SharedConfig.inAppCamera == false) goto L85;
        CameraController.getInstance().initCamera(null);
    L85:
        return false;
    L86:
        if (r1 == true) goto L92;
        int r112 = R.raw.permission_request_folder;
        if (r10 != 151) goto L90;
        String r104 = LocaleController.getString(R.string.PermissionNoStorageAvatar);
    L91:
        showPermissionErrorAlert(r112, r104);
        goto L93
    L90:
        r104 = LocaleController.getString(R.string.PermissionStorageWithHint);
        goto L91
    L92:
        ImageLoader.getInstance().checkMediaPaths();
        goto L93
    L10:
        if (r12[0] != 0) goto L12;
        r1 = true;
        goto L14
    }

    public AlertDialog createPermissionErrorAlert(int r5, String r6) {
        return new AlertDialog.Builder(this).setTopAnimation(r5, 72, false, Theme.getColor(Theme.key_dialogTopBackground)).setMessage(AndroidUtilities.replaceTags(r6)).setPositiveButton(LocaleController.getString(R.string.PermissionOpenSettings), new BasePermissionsActivity$$ExternalSyntheticLambda0(this)).setNegativeButton(LocaleController.getString(R.string.ContactsPermissionAlertNotNow), null).create();
    }
}
