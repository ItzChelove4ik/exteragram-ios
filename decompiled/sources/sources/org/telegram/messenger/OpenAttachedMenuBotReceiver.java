package org.telegram.messenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes.dex */
public class OpenAttachedMenuBotReceiver extends Activity {
    public static String ACTION = "com.tmessages.openshortcutbot";

    static {
    }

    public OpenAttachedMenuBotReceiver() {
    }

    @Override // android.app.Activity
    public void onCreate(Bundle r6) {
        super.onCreate(r6);
        Intent r62 = getIntent();
        if (r62 != null) goto L7;
        finish();
        return;
    L7:
        if (r62.getAction() != null) goto L9;
    L20:
        finish();
        return;
    L9:
        if (r62.getAction().startsWith(ACTION) == false) goto L20;
        if (r62.getLongExtra("botId", 0) != 0) goto L15;
        return;
    L15:
        Intent r0 = new Intent(this, LaunchActivity.class);
        r0.setAction(r62.getAction());
        r0.putExtras(r62);
        startActivity(r0);
        finish();
        return;
    L17:
        th = move-exception;
        FileLog.e(th);
    }
}
