package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.SessionState;
import com.google.android.gms.cast.framework.SessionTransferCallback;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class zzx extends SessionTransferCallback {
    final /* synthetic */ zzy zza;

    public zzx(zzy r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.SessionTransferCallback
    public final void onTransferFailed(int r2, int r3) {
        zzcr r22 = new zzcr(11);
        r22.zza(Integer.valueOf(r3));
        zzy r1 = this.zza;
        r22.zzb(Boolean.valueOf(r1.zzd().zze()));
        r1.zza(new zzcs(r22));
    }

    @Override // com.google.android.gms.cast.framework.SessionTransferCallback
    public final void onTransferred(int r1, SessionState r2) {
    }

    @Override // com.google.android.gms.cast.framework.SessionTransferCallback
    public final void onTransferring(int r3) {
        zzcr r0 = new zzcr(10);
        zzy r2 = this.zza;
        r0.zzb(Boolean.valueOf(r2.zzd().zze()));
        r2.zza(new zzcs(r0));
        r2.zzb().zzc(new zzac(new zzab(r3)));
    }
}
