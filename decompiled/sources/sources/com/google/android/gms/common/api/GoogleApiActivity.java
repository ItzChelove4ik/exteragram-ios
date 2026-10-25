package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.Preconditions;

@KeepName
/* loaded from: classes4.dex */
public class GoogleApiActivity extends Activity implements DialogInterface.OnCancelListener {
    protected int zaa;

    public GoogleApiActivity() {
        this.zaa = 0;
    }

    public static Intent zaa(Context r2, PendingIntent r3, int r4, boolean r5) {
        Intent r0 = new Intent(r2, GoogleApiActivity.class);
        r0.putExtra("pending_intent", r3);
        r0.putExtra("failing_client_id", r4);
        r0.putExtra("notify_manager", r5);
        return r0;
    }

    private final void zab() {
        Bundle r1 = getIntent().getExtras();
        if (r1 != null) goto L6;
        Log.e("GoogleApiActivity", "Activity started without extras");
        finish();
        return;
    L6:
        PendingIntent r3 = (PendingIntent) r1.get("pending_intent");
        Integer r0 = (Integer) r1.get("error_code");
        if (r3 != null) goto L13;
        if (r0 != null) goto L13;
        Log.e("GoogleApiActivity", "Activity started without resolution");
        finish();
        return;
    L13:
        if (r3 != null) goto L41;
        int r12 = ((Integer) Preconditions.checkNotNull(r0)).intValue();
        GoogleApiAvailability.getInstance().showErrorDialogFragment(this, r12, 2, this);
        this.zaa = 1;
        return;
    L41:
        GoogleApiActivity r5 = this;
        r5.startIntentSenderForResult(r3.getIntentSender(), 1, null, 0, 0, 0);     // Catch: IntentSender.SendIntentException -> L18 ActivityNotFoundException -> L20
        r5.zaa = 1;     // Catch: IntentSender.SendIntentException -> L18 ActivityNotFoundException -> L20
        return;
    L20:
        e = e;
    L21:
        ActivityNotFoundException r122 = e;
        if (r1.getBoolean("notify_manager", true) == false) goto L31;
        GoogleApiManager.zaa(r5).zaq(new ConnectionResult(22, null), r5.getIntent().getIntExtra("failing_client_id", -1));
    L35:
        r5.zaa = 1;
        r5.finish();
        return;
    L31:
        String r02 = r3.toString();
        StringBuilder r32 = new StringBuilder(r02.length() + 36);
        r32.append("Activity not found while launching ");
        r32.append(r02);
        r32.append(".");
        String r03 = r32.toString();
        if (Build.FINGERPRINT.contains("generic") == false) goto L34;
        r03 = r03.concat(" This may occur when resolving Google Play services connection issues on emulators with Google APIs but not Google Play Store.");
    L34:
        Log.e("GoogleApiActivity", r03, r122);
    L18:
        e = e;
    L24:
        Log.e("GoogleApiActivity", "Failed to launch pendingIntent", e);
        r5.finish();
        return;
    L26:
        e = e;
        r5 = this;
    L22:
        e = e;
        r5 = this;
        goto L24
    }

    @Override // android.app.Activity
    public final void onActivityResult(int r4, int r5, Intent r6) {
        super.onActivityResult(r4, r5, r6);
        if (r4 != 1) goto L13;
        boolean r42 = getIntent().getBooleanExtra("notify_manager", true);
        this.zaa = 0;
        setResult(r5, r6);
        if (r42 == false) goto L15;
        GoogleApiManager r43 = GoogleApiManager.zaa(this);
        if (r5 == (-1)) goto L11;
        if (r5 != 0) goto L15;
        r43.zaq(new ConnectionResult(13, null), getIntent().getIntExtra("failing_client_id", -1));
        goto L15
    L11:
        r43.zai();
    L15:
        finish();
        return;
    L13:
        if (r4 != 2) goto L15;
        this.zaa = 0;
        setResult(r5, r6);
        goto L15
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface r1) {
        this.zaa = 0;
        setResult(0);
        finish();
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle r2) {
        super.onCreate(r2);
        if (r2 == null) goto L6;
        this.zaa = r2.getInt("resolution");
    L6:
        if (this.zaa == 1) goto L9;
        zab();
        return;
    }

    @Override // android.app.Activity
    public final void onSaveInstanceState(Bundle r3) {
        r3.putInt("resolution", this.zaa);
        super.onSaveInstanceState(r3);
    }
}
