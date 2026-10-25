package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.os.Bundle;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.cast.zzeu;
import com.google.android.gms.internal.cast.zzez;
import com.google.android.gms.internal.cast.zzff;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzaa implements RemoteCall {
    private final /* synthetic */ CastRemoteDisplayClient zza;
    private final /* synthetic */ int zzb;
    private final /* synthetic */ PendingIntent zzc;
    private final /* synthetic */ CastDevice zzd;
    private final /* synthetic */ String zze;
    private final /* synthetic */ zzag zzf;

    public /* synthetic */ zzaa(CastRemoteDisplayClient r1, int r2, zzag r3, PendingIntent r4, CastDevice r5, String r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzf = r3;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r8, Object r9) {
        zzeu r82 = (zzeu) r8;
        Bundle r5 = new Bundle();
        r5.putInt("configuration", this.zzb);
        zzag r0 = this.zzf;
        CastRemoteDisplayClient r2 = this.zza;
        zzw r1 = new zzw(r2, (TaskCompletionSource) r9, r82, r0);
        zzez r02 = (zzez) r82.getService();
        String r3 = this.zzd.getDeviceId();
        ApiMetadata r6 = zzff.zza(r82.getContext());
        String r4 = this.zze;
        r02.zzg(r1, this.zzc, r3, r4, r5, r6);
    }
}
