package com.google.android.gms.internal.cast;

import android.hardware.display.VirtualDisplay;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.cast.CastRemoteDisplayApi;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

@Deprecated
/* loaded from: classes4.dex */
public final class zzet implements CastRemoteDisplayApi {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Logger zzb = null;
    private final Api zzc;
    private VirtualDisplay zzd;
    private final zzfb zze;

    static {
        zzb = new Logger("CastRemoteDisplayApiImpl");
    }

    public zzet(Api r2) {
        this.zze = new zzel(this);
        this.zzc = r2;
    }

    public static /* synthetic */ Logger zzb() {
        return zzb;
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplayApi
    public final PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult> startRemoteDisplay(GoogleApiClient r4, String r5) {
        zzb.d("startRemoteDisplay", new Object[0]);
        return r4.execute(new zzem(this, r4, r5));
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplayApi
    public final PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult> stopRemoteDisplay(GoogleApiClient r4) {
        zzb.d("stopRemoteDisplay", new Object[0]);
        return r4.execute(new zzen(this, r4));
    }

    public final /* synthetic */ void zza() {
        VirtualDisplay r0 = this.zzd;
        if (r0 != null) goto L5;
    L8:
        this.zzd = null;
        return;
    L5:
        if (r0.getDisplay() == null) goto L7;
        Logger r1 = zzb;
        int r2 = r0.getDisplay().getDisplayId();
        StringBuilder r4 = new StringBuilder(String.valueOf(r2).length() + 27);
        r4.append("releasing virtual display: ");
        r4.append(r2);
        r1.d(r4.toString(), new Object[0]);
    L7:
        r0.release();
        goto L8
    }

    public final /* synthetic */ Api zzc() {
        return this.zzc;
    }

    public final /* synthetic */ VirtualDisplay zzd() {
        return this.zzd;
    }

    public final /* synthetic */ void zze(VirtualDisplay r1) {
        this.zzd = r1;
    }

    public final /* synthetic */ zzfb zzf() {
        return this.zze;
    }
}
