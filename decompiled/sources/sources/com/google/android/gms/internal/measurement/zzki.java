package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzki implements RemoteCall {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String[] zzb;

    public /* synthetic */ zzki(String r1, int r2, String[] r3, byte[] r4) {
        this.zza = r1;
        this.zzb = r3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r8, Object r9) {
        int r0 = zzkk.$r8$clinit;
        zzkj r2 = new zzkj((TaskCompletionSource) r9, null);
        zzkt r1 = (zzkt) ((zzku) r8).getService();
        String[] r5 = this.zzb;
        r1.zze(r2, this.zza, 0, r5, null);
    }
}
