package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzl extends com.google.android.gms.cast.internal.zzae {
    final /* synthetic */ String zza;

    public zzl(zzm r1, GoogleApiClient r2, String r3) {
        this.zza = r3;
        Objects.requireNonNull(r1);
        super(r2);
    }

    @Override // com.google.android.gms.cast.internal.zzae, com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient r1) {
        zza((com.google.android.gms.cast.internal.zzx) r1);
    }

    @Override // com.google.android.gms.cast.internal.zzae
    public final void zza(com.google.android.gms.cast.internal.zzx r4) {
        String r0 = this.zza;
        if (TextUtils.isEmpty(r0) == false) goto L10;
        setResult(new Status(CastStatusCodes.INVALID_REQUEST, "IllegalArgument: sessionId cannot be null or empty", null));
        return;
    L10:
        r4.zzt(r0, this);     // Catch: IllegalStateException -> L6
        return;
    L6:
        zzc(CastStatusCodes.INVALID_REQUEST);
    }
}
