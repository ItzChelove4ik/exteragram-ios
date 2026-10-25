package com.google.android.recaptcha.internal;

import android.app.Application;

/* loaded from: classes5.dex */
final /* synthetic */ class zzij implements zzip {
    private final /* synthetic */ Application zza;
    private final /* synthetic */ zzhr zzb;

    public /* synthetic */ zzij(Application r1, zzhr r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.recaptcha.internal.zzip
    public final /* synthetic */ zzcl zza() {
        zzhr r1 = this.zzb;
        return new zzdy(this.zza, r1, null);
    }
}
