package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzkg implements RemoteCall {
    private final /* synthetic */ zzkk zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ ListenerHolder zzc;

    public /* synthetic */ zzkg(zzkk r1, String r2, ListenerHolder r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r3, Object r4) {
        TaskCompletionSource r42 = (TaskCompletionSource) r4;
        zzkt r32 = (zzkt) ((zzku) r3).getService();
        zzka r43 = new zzka(this.zza, this.zzc);
        r32.zzj(this.zzb, r43);
    }
}
