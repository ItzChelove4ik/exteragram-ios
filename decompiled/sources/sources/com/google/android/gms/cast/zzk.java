package com.google.android.gms.cast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final class zzk extends com.google.android.gms.cast.internal.zzae {
    public zzk(zzm r1, GoogleApiClient r2) {
        Objects.requireNonNull(r1);
        super(r2);
    }

    @Override // com.google.android.gms.cast.internal.zzae, com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient r1) {
        zza((com.google.android.gms.cast.internal.zzx) r1);
    }

    @Override // com.google.android.gms.cast.internal.zzae
    public final void zza(com.google.android.gms.cast.internal.zzx r2) {
        r2.zzt(_UrlKt.FRAGMENT_ENCODE_SET, this);     // Catch: IllegalStateException -> L4
        return;
    L4:
        zzc(CastStatusCodes.INVALID_REQUEST);
    }
}
