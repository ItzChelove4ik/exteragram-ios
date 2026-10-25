package com.google.android.gms.vision.face;

import android.graphics.PointF;
import androidx.annotation.RecentlyNonNull;

/* loaded from: classes5.dex */
public final class Landmark {
    private final PointF zza;
    private final int zzb;

    public Landmark(@RecentlyNonNull PointF r1, int r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @RecentlyNonNull
    public final PointF getPosition() {
        return this.zza;
    }

    public final int getType() {
        return this.zzb;
    }
}
