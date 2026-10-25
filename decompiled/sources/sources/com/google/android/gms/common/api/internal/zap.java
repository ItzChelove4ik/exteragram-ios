package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes4.dex */
public abstract class zap extends LifecycleCallback implements DialogInterface.OnCancelListener {
    protected volatile boolean zaa;
    protected final AtomicReference zab;
    protected final GoogleApiAvailability zac;
    private final Handler zad;

    public zap(LifecycleFragment r2, GoogleApiAvailability r3) {
        super(r2);
        this.zab = new AtomicReference(null);
        this.zad = new com.google.android.gms.internal.base.zao(Looper.getMainLooper());
        this.zac = r3;
    }

    private final void zaa() {
        this.zab.set(null);
        zae();
    }

    private final void zab(ConnectionResult r3, int r4) {
        this.zab.set(null);
        zad(r3, r4);
    }

    private static final int zac(zam r0) {
        if (r0 != null) goto L6;
        return -1;
    L6:
        return r0.zaa();
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onActivityResult(int r3, int r4, Intent r5) {
        zam r0 = (zam) this.zab.get();
        if (r3 == 1) goto L17;
        if (r3 != 2) goto L27;
        int r32 = this.zac.isGooglePlayServicesAvailable(getActivity());
        if (r32 != 0) goto L11;
        zaa();
        return;
    L11:
        if (r0 != null) goto L13;
        return;
    L13:
        if (r0.zab().getErrorCode() != 18) goto L27;
        if (r32 != 18) goto L27;
        return;
    L27:
        if (r0 == null) goto L32;
        zab(r0.zab(), r0.zaa());
        return;
    L32:
        return;
    L17:
        if (r4 != (-1)) goto L20;
        zaa();
        return;
    L20:
        if (r4 != 0) goto L27;
        if (r0 == null) goto L31;
        int r33 = 13;
        if (r5 == null) goto L25;
        r33 = r5.getIntExtra("<<ResolutionFailureErrorDetail>>", 13);
    L25:
        zab(new ConnectionResult(r33, null, r0.zab().toString()), zac(r0));
        return;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface r3) {
        zab(new ConnectionResult(13, null), zac((zam) this.zab.get()));
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onCreate(Bundle r4) {
        super.onCreate(r4);
        if (r4 == null) goto L10;
        AtomicReference r3 = this.zab;
        if (r4.getBoolean("resolving_error", false) == false) goto L7;
        zam r1 = new zam(new ConnectionResult(r4.getInt("failed_status"), (PendingIntent) r4.getParcelable("failed_resolution")), r4.getInt("failed_client_id", -1));
    L8:
        r3.set(r1);
        return;
    L7:
        r1 = null;
        goto L8
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onSaveInstanceState(Bundle r3) {
        super.onSaveInstanceState(r3);
        zam r2 = (zam) this.zab.get();
        if (r2 != null) goto L5;
        return;
    L5:
        r3.putBoolean("resolving_error", true);
        r3.putInt("failed_client_id", r2.zaa());
        r3.putInt("failed_status", r2.zab().getErrorCode());
        r3.putParcelable("failed_resolution", r2.zab().getResolution());
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStart() {
        super.onStart();
        this.zaa = true;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public void onStop() {
        super.onStop();
        this.zaa = false;
    }

    public abstract void zad(ConnectionResult r1, int r2);

    public abstract void zae();

    public final void zaf(ConnectionResult r2, int r3) {
        zam r0 = new zam(r2, r3);
        if (AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(this.zab, null, r0) == false) goto L6;
        this.zad.post(new zao(this, r0));
        return;
    }

    public final /* synthetic */ void zag() {
        zaa();
    }

    public final /* synthetic */ void zah(ConnectionResult r1, int r2) {
        zab(r1, r2);
    }
}
