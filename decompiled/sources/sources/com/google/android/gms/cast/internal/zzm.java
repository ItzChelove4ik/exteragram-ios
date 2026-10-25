package com.google.android.gms.cast.internal;

import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.cast.zzff;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

/* loaded from: classes4.dex */
final /* synthetic */ class zzm implements RemoteCall {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ String[] zzb;
    private final /* synthetic */ String zzc;

    public /* synthetic */ zzm(zzn r1, String[] r2, String r3, List r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r8, Object r9) {
        zzo r82 = (zzo) r8;
        zzf r2 = new zzf(this.zza, (TaskCompletionSource) r9);
        zzak r1 = (zzak) r82.getService();
        ApiMetadata r6 = zzff.zza(r82.getContext());
        r1.zze(r2, this.zzb, this.zzc, null, r6);
    }
}
