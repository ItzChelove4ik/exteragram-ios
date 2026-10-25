package com.google.android.play.core.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.ResultReceiver;

/* loaded from: classes5.dex */
public class PlayCoreDialogWrapperActivity extends Activity {
    private ResultReceiver zza;

    public PlayCoreDialogWrapperActivity() {
    }

    private final void zza() {
        ResultReceiver r2 = this.zza;
        if (r2 == null) goto L6;
        r2.send(3, new Bundle());
        return;
    }

    @Override // android.app.Activity
    public final void onActivityResult(int r1, int r2, Intent r3) {
        super.onActivityResult(r1, r2, r3);
        if (r1 != 0) goto L11;
        ResultReceiver r12 = this.zza;
        if (r12 == null) goto L11;
        if (r2 != (-1)) goto L9;
        r12.send(1, new Bundle());
        goto L11
    L9:
        if (r2 != 0) goto L11;
        r12.send(2, new Bundle());
    L11:
        finish();
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle r12) {
        int r0 = getIntent().getIntExtra("window_flags", 0);
        PendingIntent r1 = null;
        if (r0 == 0) goto L5;
        getWindow().getDecorView().setSystemUiVisibility(r0);
        Intent r3 = new Intent();
        r3.putExtra("window_flags", r0);
        Intent r7 = r3;
    L6:
        super.onCreate(r12);
        if (r12 != null) goto L23;
        this.zza = (ResultReceiver) getIntent().getParcelableExtra("result_receiver");
        Bundle r122 = getIntent().getExtras();
        if (r122 == null) goto L11;
        r1 = (PendingIntent) r122.get("confirmation_intent");
    L11:
        if (r122 == null) goto L21;
        if (r1 == null) goto L21;
        PlayCoreDialogWrapperActivity r4 = this;
        r4.startIntentSenderForResult(r1.getIntentSender(), 0, r7, 0, 0, 0);     // Catch: IntentSender.SendIntentException -> L25
        return;
    L19:
        r4.zza();
        r4.finish();
        return;
    L18:
        r4 = this;
    L21:
        zza();
        finish();
        return;
    L23:
        this.zza = (ResultReceiver) r12.getParcelable("result_receiver");
        return;
    L5:
        r7 = null;
        goto L6
    }

    @Override // android.app.Activity
    public final void onSaveInstanceState(Bundle r2) {
        r2.putParcelable("result_receiver", this.zza);
    }
}
