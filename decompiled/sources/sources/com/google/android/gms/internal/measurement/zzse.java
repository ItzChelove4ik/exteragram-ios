package com.google.android.gms.internal.measurement;

import com.google.common.collect.Iterables;
import java.io.OutputStream;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzse implements zzro {
    private OutputStream zza;
    private zzsm zzb;

    public zzse() {
    }

    @Override // com.google.android.gms.internal.measurement.zzro
    public final void zza(List r3) {
        OutputStream r0 = (OutputStream) Iterables.getLast(r3);
        if ((r0 instanceof zzsm) == false) goto L6;
        this.zzb = (zzsm) r0;
        this.zza = (OutputStream) r3.get(0);
        return;
    }

    @Override // com.google.android.gms.internal.measurement.zzro
    public final void zzb() {
        zzc();
    }

    public final void zzc() {
        if (this.zzb == null) goto L7;
        this.zza.flush();
        this.zzb.zzb();
        return;
    L7:
        throw new zzsk("Cannot sync underlying stream");
    }
}
