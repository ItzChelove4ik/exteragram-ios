package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final /* synthetic */ class zzmx implements Supplier {
    private final /* synthetic */ zzmz zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;

    public /* synthetic */ zzmx(zzmz r1, String r2, String r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    @Override // com.google.common.base.Supplier
    public final /* synthetic */ Object get() {
        return this.zza.zzc(this.zzb, this.zzc);
    }
}
