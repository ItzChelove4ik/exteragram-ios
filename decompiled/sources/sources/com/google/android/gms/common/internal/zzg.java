package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes4.dex */
public final class zzg extends zza {
    final /* synthetic */ BaseGmsClient zze;

    public zzg(BaseGmsClient r1, int r2, Bundle r3) {
        java.util.Objects.requireNonNull(r1);
        this.zze = r1;
        super(r1, r2, r3);
    }

    @Override // com.google.android.gms.common.internal.zza
    public final boolean zza() {
        this.zze.zzc.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
        return true;
    }

    @Override // com.google.android.gms.common.internal.zza
    public final void zzb(ConnectionResult r2) {
        BaseGmsClient r1 = this.zze;
        if (r1.enableLocalFallback() == true) goto L5;
    L8:
        r1.zzc.onReportServiceBinding(r2);
        r1.onConnectionFailed(r2);
        return;
    L5:
        if (r1.zzg() == false) goto L8;
        r1.zzf(16);
    }
}
