package com.google.android.gms.cast.framework;

import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzbi extends zzba {
    final /* synthetic */ SessionProvider zza;

    public /* synthetic */ zzbi(SessionProvider r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.zzbb
    public final IObjectWrapper zzb(String r1) {
        Session r0 = this.zza.createSession(r1);
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.zzn();
    }

    @Override // com.google.android.gms.cast.framework.zzbb
    public final boolean zzc() {
        return this.zza.isSessionRecoverable();
    }

    @Override // com.google.android.gms.cast.framework.zzbb
    public final String zzd() {
        return this.zza.getCategory();
    }
}
