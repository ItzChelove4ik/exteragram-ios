package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes4.dex */
abstract class zza extends zzc {
    public final int zza;
    public final Bundle zzb;
    final /* synthetic */ BaseGmsClient zzc;

    public zza(BaseGmsClient r2, int r3, Bundle r4) {
        java.util.Objects.requireNonNull(r2);
        this.zzc = r2;
        super(r2, Boolean.TRUE);
        this.zza = r3;
        this.zzb = r4;
    }

    public abstract boolean zza();

    public abstract void zzb(ConnectionResult r1);

    @Override // com.google.android.gms.common.internal.zzc
    public final /* bridge */ /* synthetic */ void zzc(Object r4) {
        Boolean r42 = (Boolean) r4;
        int r43 = this.zza;
        PendingIntent r1 = null;
        if (r43 == 0) goto L5;
        this.zzc.zzd(1, null);
        Bundle r0 = this.zzb;
        if (r0 == null) goto L11;
        r1 = (PendingIntent) r0.getParcelable(BaseGmsClient.KEY_PENDING_INTENT);
    L11:
        zzb(new ConnectionResult(r43, r1));
        return;
    L5:
        if (zza() == true) goto L13;
        this.zzc.zzd(1, null);
        zzb(new ConnectionResult(8, null));
        return;
    }
}
