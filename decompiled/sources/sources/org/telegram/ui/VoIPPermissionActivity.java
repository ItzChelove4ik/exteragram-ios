package org.telegram.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import java.util.ArrayList;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.voip.VoIPPreNotificationService;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.Components.voip.VoIPHelper;

@TargetApi(23)
/* loaded from: classes6.dex */
public class VoIPPermissionActivity extends Activity {
    public VoIPPermissionActivity() {
    }

    @Override // android.app.Activity
    public void onCreate(Bundle r5) {
        super.onCreate(r5);
        VoIPService r52 = VoIPService.getSharedInstance();
        if (r52 == null) goto L10;
        TL_phone.PhoneCall r53 = r52.privateCall;
        if (r53 != null) goto L7;
    L9:
        boolean r54 = false;
    L11:
        ArrayList r1 = new ArrayList();
        if (checkSelfPermission("android.permission.RECORD_AUDIO") == 0) goto L14;
        r1.add("android.permission.RECORD_AUDIO");
    L14:
        if (r54 == false) goto L19;
        if (checkSelfPermission("android.permission.CAMERA") == 0) goto L19;
        r1.add("android.permission.CAMERA");
    L19:
        if (Build.VERSION.SDK_INT < 31) goto L26;
        if (checkSelfPermission("android.permission.BLUETOOTH_CONNECT") == 0) goto L26;
        if (shouldShowRequestPermissionRationale("android.permission.BLUETOOTH_CONNECT") == true) goto L26;
        r1.add("android.permission.BLUETOOTH_CONNECT");
    L26:
        if (r1.isEmpty() == true) goto L38;
        String[] r0 = (String[]) r1.toArray(new String[0]);     // Catch: Exception -> L33
        if (r54 == false) goto L30;
        int r55 = 102;
    L31:
        requestPermissions(r0, r55);     // Catch: Exception -> L33
        return;
    L30:
        r55 = 101;
    L33:
        e = move-exception;
        FileLog.e(e);
        return;
    L38:
        return;
    L7:
        if (r53.video == false) goto L9;
        r54 = true;
        goto L11
    L10:
        r54 = VoIPPreNotificationService.isVideo();
        goto L11
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int r4, String[] r5, int[] r6) {
        if (r4 != 101) goto L5;
    L8:
        boolean r52 = false;
        int r0 = 0;
    L10:
        if (r0 >= r6.length) goto L15;
        if (r6[r0] != 0) goto L17;
        r0 = r0 + 1;
    L17:
        if (r6.length <= 0) goto L26;
        if (r52 == false) goto L26;
        if (VoIPService.getSharedInstance() == null) goto L22;
        VoIPService.getSharedInstance().acceptIncomingCall();
    L23:
        finish();
        startActivity(new Intent(this, LaunchActivity.class).setAction("voip"));
        return;
    L22:
        VoIPPreNotificationService.answer(this);
    L26:
        if (shouldShowRequestPermissionRationale("android.permission.RECORD_AUDIO") == false) goto L28;
        finish();
        return;
    L28:
        if (VoIPService.getSharedInstance() == null) goto L30;
        VoIPService.getSharedInstance().declineIncomingCall();
    L31:
        VoIPHelper.permissionDenied(this, new VoIPPermissionActivity$$ExternalSyntheticLambda0(this), r4);
        return;
    L30:
        VoIPPreNotificationService.decline(this, 1);
        goto L31
    L15:
        r52 = true;
        goto L17
    L5:
        if (r4 == 102) goto L8;
    }
}
