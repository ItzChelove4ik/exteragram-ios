package com.google.android.gms.internal.measurement;

import java.io.File;
import java.io.FileOutputStream;

/* loaded from: classes4.dex */
public final class zzsm extends zzso implements zzsf {
    private final FileOutputStream zza;
    private final File zzb;

    public zzsm(FileOutputStream r1, File r2) {
        super(r1);
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.measurement.zzsf
    public final File zza() {
        return this.zzb;
    }

    public final void zzb() {
        this.zza.getFD().sync();
    }
}
