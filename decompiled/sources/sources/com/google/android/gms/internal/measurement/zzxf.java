package com.google.android.gms.internal.measurement;

import android.os.SystemClock;
import com.google.common.base.Ticker;

/* loaded from: classes4.dex */
final class zzxf extends Ticker {
    public zzxf() {
    }

    @Override // com.google.common.base.Ticker
    public final long read() {
        return SystemClock.elapsedRealtimeNanos();
    }
}
