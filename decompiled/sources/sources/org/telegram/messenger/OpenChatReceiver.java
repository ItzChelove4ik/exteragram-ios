package org.telegram.messenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes.dex */
public class OpenChatReceiver extends Activity {
    public OpenChatReceiver() {
    }

    @Override // android.app.Activity
    public void onCreate(Bundle r8) {
        super.onCreate(r8);
        Intent r82 = getIntent();
        if (r82 != null) goto L7;
        finish();
        return;
    L7:
        if (r82.getAction() != null) goto L9;
    L24:
        finish();
        return;
    L9:
        if (r82.getAction().startsWith("com.tmessages.openchat") == false) goto L24;
        long r3 = r82.getLongExtra("chatId", r82.getIntExtra("chatId", 0));     // Catch: Throwable -> L21
        long r0 = r82.getLongExtra("userId", r82.getIntExtra("userId", 0));     // Catch: Throwable -> L21
        int r2 = r82.getIntExtra("encId", 0);     // Catch: Throwable -> L21
        if (r3 == 0) goto L16;
    L19:
        Intent r02 = new Intent(this, LaunchActivity.class);
        r02.setAction(r82.getAction());
        r02.putExtras(r82);
        startActivity(r02);
        finish();
        return;
    L16:
        if (r0 != 0) goto L19;
        if (r2 != 0) goto L19;
        return;
    L21:
        th = move-exception;
        FileLog.e(th);
    }
}
