package com.google.android.gms.internal.measurement;

import com.google.common.base.Function;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final /* synthetic */ class zzpt implements Function {
    private final /* synthetic */ String zza;

    public /* synthetic */ zzpt(String r1, String r2) {
        this.zza = r1;
    }

    @Override // com.google.common.base.Function
    public final /* synthetic */ Object apply(Object r4) {
        zznu r42 = (zznu) r4;
        int r0 = zzpu.$r8$clinit;
        String r3 = this.zza;
        zznq r02 = (zznq) r42.zza(r3, zznr.zzb()).zzco();
        if (r02.zza().contains(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L5;
        r02.zzb(_UrlKt.FRAGMENT_ENCODE_SET);
    L5:
        zznt r43 = (zznt) r42.zzco();
        r02.zzc(_UrlKt.FRAGMENT_ENCODE_SET);
        r43.zza(r3, (zznr) r02.zzbd());
        return (zznu) r43.zzbd();
    }
}
