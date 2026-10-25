package org.telegram.messenger;

import android.app.IntentService;
import android.content.Intent;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes5.dex */
public class BringAppForegroundService extends IntentService {
    public BringAppForegroundService() {
        super("BringAppForegroundService");
    }

    @Override // android.app.IntentService
    public void onHandleIntent(Intent r2) {
        Intent r22 = new Intent(this, LaunchActivity.class);
        r22.setFlags(268435456);
        r22.setAction("android.intent.action.MAIN");
        startActivity(r22);
    }
}
