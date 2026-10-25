package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import kotlin.time.DurationKt;

/* loaded from: classes5.dex */
final class zzog {
    private final Clock zza;
    private long zzb;

    public zzog(Clock r1) {
        Preconditions.checkNotNull(r1);
        this.zza = r1;
    }

    public final void zza() {
        this.zzb = this.zza.elapsedRealtime();
    }

    public final void zzb() {
        this.zzb = 0;
    }

    public final boolean zzc(long r3) {
        if (this.zzb != 0) goto L6;
        return true;
    L6:
        if ((this.zza.elapsedRealtime() - this.zzb) < DurationKt.MILLIS_IN_HOUR) goto L8;
        return true;
    L8:
        return false;
    }
}
