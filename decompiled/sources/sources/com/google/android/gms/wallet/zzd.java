package com.google.android.gms.wallet;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.Task;

/* loaded from: classes5.dex */
public final class zzd extends Fragment {
    boolean zza;
    private int zzb;
    private zzc zzc;

    public zzd() {
    }

    public static /* bridge */ /* synthetic */ void zza(zzd r0, Task r1) {
        r0.zzb(r1);
    }

    private final void zzb(Task r3) {
        if (this.zza == true) goto L10;
        this.zza = true;
        Activity r0 = getActivity();
        r0.getFragmentManager().beginTransaction().remove(this).commit();
        int r2 = this.zzb;
        if (r3 == null) goto L8;
        AutoResolveHelper.zzc(r0, r2, r3);
        return;
    L8:
        AutoResolveHelper.zzb(r0, r2, 0, new Intent());
        return;
    }

    private final void zzc() {
        zzc r0 = this.zzc;
        if (r0 == null) goto L6;
        r0.zzb(this);
        return;
    }

    @Override // android.app.Fragment
    public final void onCreate(Bundle r5) {
        super.onCreate(r5);
        this.zzb = getArguments().getInt("requestCode");
        if (AutoResolveHelper.zza == getArguments().getLong("initializationElapsedRealtime")) goto L5;
        this.zzc = null;
    L6:
        boolean r0 = false;
        if (r5 != null) goto L9;
    L11:
        this.zza = r0;
        return;
    L9:
        if (r5.getBoolean("delivered") == false) goto L11;
        r0 = true;
        goto L11
    L5:
        int r02 = getArguments().getInt("resolveCallId");
        this.zzc = (zzc) zzc.zzb.get(r02);
        goto L6
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
        zzc();
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        zzc r0 = this.zzc;
        if (r0 == null) goto L7;
        r0.zzc(this);
        return;
    L7:
        if (Log.isLoggable("AutoResolveHelper", 5) == false) goto L9;
        Log.w("AutoResolveHelper", "Sending canceled result for garbage collected task!");
    L9:
        zzb(null);
    }

    @Override // android.app.Fragment
    public final void onSaveInstanceState(Bundle r3) {
        super.onSaveInstanceState(r3);
        r3.putBoolean("delivered", this.zza);
        zzc();
    }
}
