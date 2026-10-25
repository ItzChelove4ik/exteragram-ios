package com.google.android.gms.cloudmessaging;

import android.os.Bundle;

/* loaded from: classes.dex */
final class zzu extends zzs {
    public zzu(int r1, int r2, Bundle r3) {
        super(r1, r2, r3);
    }

    @Override // com.google.android.gms.cloudmessaging.zzs
    public final boolean zza() {
        return false;
    }

    @Override // com.google.android.gms.cloudmessaging.zzs
    public final void zzb(Bundle r2) {
        Bundle r22 = r2.getBundle("data");
        if (r22 != null) goto L5;
        r22 = Bundle.EMPTY;
    L5:
        zzc(r22);
    }
}
