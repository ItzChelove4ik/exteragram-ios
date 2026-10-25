package com.google.android.gms.internal.cast;

import android.view.animation.Interpolator;
import androidx.core.view.animation.PathInterpolatorCompat;

/* loaded from: classes4.dex */
abstract class zzgx {
    private static final Interpolator zza = null;
    private static final Interpolator zzb = null;
    private static final Interpolator zzc = null;

    static {
        zza = PathInterpolatorCompat.create(0.0f, 0.0f, 0.2f, 1.0f);
        zzb = PathInterpolatorCompat.create(0.4f, 0.0f, 1.0f, 1.0f);
        zzc = PathInterpolatorCompat.create(0.4f, 0.0f, 0.2f, 1.0f);
    }

    public static /* synthetic */ Interpolator zza() {
        return zza;
    }

    public static /* synthetic */ Interpolator zzb() {
        return zzb;
    }

    public static /* synthetic */ Interpolator zzc() {
        return zzc;
    }
}
