package com.google.android.gms.internal.measurement;

import java.util.function.Function;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final /* synthetic */ class zzpb implements Function {
    private final /* synthetic */ zzlk zza;
    private final /* synthetic */ zzon zzb;
    private final /* synthetic */ zzpd zzc;

    public /* synthetic */ zzpb(zzlk r1, zzon r2, String r3, zzpd r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
    }

    @Override // java.util.function.Function
    public final /* synthetic */ Object apply(Object r5) {
        String r52 = (String) r5;
        zzon r53 = this.zzb;
        zzoo r54 = new zzoo(new zzpg(this.zza, r53, _UrlKt.FRAGMENT_ENCODE_SET, null), null);
        this.zzc.zzb(true);
        return r54;
    }
}
