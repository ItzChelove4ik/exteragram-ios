package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzkn implements zzpo {
    final /* synthetic */ zzlj zza;

    public zzkn(zzlj r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.measurement.internal.zzpo
    public final void zza(String r3, String r4, Bundle r5) {
        boolean r42 = TextUtils.isEmpty(r3);
        zzlj r2 = this.zza;
        if (r42 == true) goto L6;
        r2.zzH("auto", "_err", r5, r3);
        return;
    L6:
        r2.zzA("auto", "_err", r5);
    }
}
