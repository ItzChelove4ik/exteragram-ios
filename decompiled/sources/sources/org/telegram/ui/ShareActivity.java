package org.telegram.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ShareAlert;

/* loaded from: classes6.dex */
public class ShareActivity extends Activity {
    private Dialog visibleDialog;

    public static /* synthetic */ void $r8$lambda$920XaPDIcpoOjP6P2_IL8SiRCFI(ShareActivity r0, DialogInterface r1) {
        r0.lambda$onCreate$0(r1);
    }

    public ShareActivity() {
    }

    private /* synthetic */ void lambda$onCreate$0(DialogInterface r1) {
        if (isFinishing() == true) goto L5;
        finish();
    L5:
        this.visibleDialog = null;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle r12) {
        ApplicationLoader.postInitApplication();
        AndroidUtilities.checkDisplaySize(this, getResources().getConfiguration());
        AndroidUtilities.setPreferredMaxRefreshRate(getWindow());
        requestWindowFeature(1);
        setTheme(R.style.Theme_TMessages_Transparent);
        super.onCreate(r12);
        setContentView(new View(this), new ViewGroup.LayoutParams(-1, -1));
        Intent r122 = getIntent();
        if (r122 == null) goto L32;
        if ("android.intent.action.VIEW".equals(r122.getAction()) == false) goto L32;
        if (r122.getData() == null) goto L32;
        Uri r123 = r122.getData();
        String r1 = r123.getScheme();
        String r2 = r123.toString();
        String r124 = r123.getQueryParameter("hash");
        if ("tgb".equals(r1) == false) goto L30;
        if (r2.toLowerCase().startsWith("tgb://share_game_score") == false) goto L30;
        if (TextUtils.isEmpty(r124) == true) goto L30;
        SharedPreferences r13 = ApplicationLoader.applicationContext.getSharedPreferences("botshare", 0);
        String r22 = r13.getString(r124 + "_m", null);
        if (TextUtils.isEmpty(r22) == false) goto L20;
        finish();
        return;
    L20:
        SerializedData r5 = new SerializedData(Utilities.hexToBytes(r22));
        TLRPC.Message r23 = TLRPC.Message.TLdeserialize(r5, r5.readInt32(false), false);
        if (r23 != null) goto L24;
        finish();
        return;
    L24:
        r23.readAttachPath(r5, 0);
        r5.cleanup();
        String r9 = r13.getString(r124 + "_link", null);
        MessageObject r6 = new MessageObject(UserConfig.selectedAccount, r23, false, true);
        r6.messageOwner.with_my_score = true;
        ShareAlert r11 = ShareAlert.createShareAlert(this, r6, null, false, r9, false);     // Catch: Exception -> L27
        this.visibleDialog = r11;     // Catch: Exception -> L27
        r11.setCanceledOnTouchOutside(true);     // Catch: Exception -> L27
        this.visibleDialog.setOnDismissListener(new ShareActivity$$ExternalSyntheticLambda0(this));     // Catch: Exception -> L27
        this.visibleDialog.show();     // Catch: Exception -> L27
        return;
    L27:
        e = move-exception;
        FileLog.e(e);
        finish();
        return;
    L30:
        finish();
        return;
    L32:
        finish();
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        Dialog r0 = this.visibleDialog;     // Catch: Exception -> L9
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.isShowing() == false) goto L15;
        this.visibleDialog.dismiss();     // Catch: Exception -> L9
        this.visibleDialog = null;     // Catch: Exception -> L9
        return;
    L15:
        return;
    L9:
        e = move-exception;
        FileLog.e(e);
    }
}
