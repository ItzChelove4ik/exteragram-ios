package com.google.android.gms.internal.measurement;

import com.google.common.annotations.GoogleInternal;
import java.io.File;

/* loaded from: classes4.dex */
final class zzyb extends zzyc {
    private final String zzb;
    private final String zzc;
    private final int zzd;
    private final String zze;
    private int zzf;

    public /* synthetic */ zzyb(String r1, String r2, int r3, String r4, byte[] r5) {
        this.zzf = 0;
        this.zzb = "com/google/android/libraries/phenotype/client/Phlogger";
        this.zzc = "logInternal";
        this.zzd = 44;
        this.zze = "Phlogger.java";
    }

    public final boolean equals(Object r8) {
        if ((r8 instanceof zzyb) == false) goto L23;
        zzyb r82 = (zzyb) r8;
        if (this.zzc.equals(r82.zzc) == false) goto L23;
        String r7 = this.zzb;
        String r83 = r82.zzb;
        if (r7 != r83) goto L10;
    L22:
        return true;
    L10:
        if (r7.length() != r83.length()) goto L23;
        int r2 = 0;
    L13:
        if (r2 >= r7.length()) goto L22;
        char r3 = r7.charAt(r2);
        char r4 = r83.charAt(r2);
        if (r3 == r4) goto L21;
        if ((r3 & 65534) != 46) goto L23;
        if ((r3 ^ r4) != 1) goto L23;
    L21:
        r2 = r2 + 1;
    L23:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zzf;
        if (r0 != 0) goto L6;
        int r02 = ((this.zzc.hashCode() + 4867) * 31) + 44;
        this.zzf = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // com.google.android.gms.internal.measurement.zzyc
    public final String zza() {
        return this.zzb.replace('/', '.');
    }

    @Override // com.google.android.gms.internal.measurement.zzyc
    public final String zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzyc
    public final int zzc() {
        return 44;
    }

    @Override // com.google.android.gms.internal.measurement.zzyc
    public final String zzd() {
        String r1 = this.zze;
        return r1.substring(r1.lastIndexOf(File.separatorChar) + 1);
    }

    @Override // com.google.android.gms.internal.measurement.zzyc
    @GoogleInternal
    public final String zze() {
        return this.zze;
    }
}
