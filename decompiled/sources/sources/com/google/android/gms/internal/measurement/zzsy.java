package com.google.android.gms.internal.measurement;

import android.net.Uri;
import java.io.OutputStream;

/* loaded from: classes4.dex */
public abstract class zzsy implements zzsx {
    public zzsy() {
    }

    public abstract zzsx zzb();

    public abstract Uri zzf(Uri r1);

    @Override // com.google.android.gms.internal.measurement.zzsx
    public final OutputStream zzj(Uri r2) {
        return zzb().zzj(zzf(r2));
    }

    @Override // com.google.android.gms.internal.measurement.zzsx
    public final void zzk(Uri r2) {
        zzb().zzk(zzf(r2));
    }

    @Override // com.google.android.gms.internal.measurement.zzsx
    public final void zzl(Uri r2, Uri r3) {
        zzb().zzl(zzf(r2), zzf(r3));
    }
}
