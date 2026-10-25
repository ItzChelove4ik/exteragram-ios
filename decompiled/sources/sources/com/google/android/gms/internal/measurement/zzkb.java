package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final /* synthetic */ class zzkb implements RemoteCall {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;

    public /* synthetic */ zzkb(String r1, String r2, String r3) {
        this.zza = r1;
        this.zzb = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r3, Object r4) {
        int r0 = zzkk.$r8$clinit;
        zzkj r02 = new zzkj((TaskCompletionSource) r4, null);
        ((zzkt) ((zzku) r3).getService()).zzf(r02, this.zza, this.zzb, null);
    }
}
