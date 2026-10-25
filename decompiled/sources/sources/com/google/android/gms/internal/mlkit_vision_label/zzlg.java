package com.google.android.gms.internal.mlkit_vision_label;

import com.google.android.gms.common.internal.Objects;

/* loaded from: classes4.dex */
public final class zzlg {
    private final Integer zza;
    private final Float zzb;

    public /* synthetic */ zzlg(zzle r1, zzlf r2) {
        this.zza = null;
        this.zzb = zzle.zzc(r1);
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzlg) == true) goto L8;
        return false;
    L8:
        zzlg r52 = (zzlg) r5;
        if (Objects.equal(null, null) == true) goto L11;
    L15:
        return false;
    L11:
        if (Objects.equal(this.zzb, r52.zzb) == false) goto L15;
        if (Objects.equal(null, null) == false) goto L15;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{null, this.zzb, null});
    }

    @zzcm(zza = 2)
    public final Float zza() {
        return this.zzb;
    }
}
