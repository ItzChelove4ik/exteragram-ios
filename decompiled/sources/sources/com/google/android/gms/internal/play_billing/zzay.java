package com.google.android.gms.internal.play_billing;

import android.os.SystemClock;

/* loaded from: classes5.dex */
final class zzay extends zzbl {
    public zzay() {
    }

    @Override // com.google.android.gms.internal.play_billing.zzbl
    public final long zza() {
        return SystemClock.elapsedRealtime() * 1000000;
    }
}
