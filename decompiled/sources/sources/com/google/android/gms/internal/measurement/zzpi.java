package com.google.android.gms.internal.measurement;

import com.google.common.base.Preconditions;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final class zzpi implements zzph {
    private volatile zzon zza;
    private zzpg zzb;

    public /* synthetic */ zzpi(zzon r1, byte[] r2) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.measurement.zzph
    public final zzpg zza(zzlk r4, String r5) {
        Preconditions.checkArgument(true);
        zzon r52 = this.zza;
        zzon r0 = zzpg.zza;
        if (r52 == r0) goto L6;
        this.zzb = zzpg.zzd().zzc(r4, r52, _UrlKt.FRAGMENT_ENCODE_SET).zza(r4, _UrlKt.FRAGMENT_ENCODE_SET);
        this.zza = r0;
    L6:
        return this.zzb;
    }
}
