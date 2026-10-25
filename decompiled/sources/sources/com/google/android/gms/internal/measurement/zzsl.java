package com.google.android.gms.internal.measurement;

import java.io.File;
import java.io.FileInputStream;

/* loaded from: classes4.dex */
public final class zzsl extends zzsn implements zzsf {
    private final FileInputStream zza;
    private final File zzb;

    private zzsl(FileInputStream r1, File r2) {
        super(r1);
        this.zza = r1;
        this.zzb = r2;
    }

    public static zzsl zzb(File r2) {
        return new zzsl(new FileInputStream(r2), r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzsf
    public final File zza() {
        return this.zzb;
    }
}
