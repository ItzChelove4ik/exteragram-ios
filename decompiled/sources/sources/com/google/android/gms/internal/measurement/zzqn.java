package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes4.dex */
public final class zzqn {
    private final boolean zza;
    private final List zzb;
    private final zzacr zzc;
    private final String zzd;
    private final String zze;
    private final List zzf;
    private final List zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final zznf zzk;

    public zzqn(boolean r1, List r2, zzacr r3, String r4, String r5, List r6, List r7, boolean r8, boolean r9, boolean r10, zznf r11) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = r8;
        this.zzi = r9;
        this.zzj = r10;
        this.zzk = r11;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzqn) == true) goto L8;
        return false;
    L8:
        zzqn r52 = (zzqn) r5;
        if (this.zza == r52.zza) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.zzb, r52.zzb) == true) goto L15;
        return false;
    L15:
        if (Intrinsics.areEqual(this.zzc, r52.zzc) == true) goto L18;
        return false;
    L18:
        if (Intrinsics.areEqual(this.zzd, r52.zzd) == true) goto L21;
        return false;
    L21:
        if (Intrinsics.areEqual(this.zze, r52.zze) == true) goto L24;
        return false;
    L24:
        if (Intrinsics.areEqual(this.zzf, r52.zzf) == true) goto L27;
        return false;
    L27:
        if (Intrinsics.areEqual(this.zzg, r52.zzg) == true) goto L30;
        return false;
    L30:
        if (this.zzh == r52.zzh) goto L33;
        return false;
    L33:
        if (this.zzi == r52.zzi) goto L36;
        return false;
    L36:
        if (this.zzj == r52.zzj) goto L39;
        return false;
    L39:
        if (Intrinsics.areEqual(this.zzk, r52.zzk) == true) goto L41;
        return false;
    L41:
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{Boolean.valueOf(this.zza), this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, Boolean.valueOf(this.zzh), Boolean.valueOf(this.zzi), Boolean.valueOf(this.zzj)});
    }

    public final String toString() {
        boolean r1 = this.zza;
        int r2 = String.valueOf(r1).length();
        List r3 = this.zzb;
        int r4 = String.valueOf(r3).length();
        zzacr r5 = this.zzc;
        int r6 = String.valueOf(r5).length();
        String r7 = this.zzd;
        int r8 = String.valueOf(r7).length();
        String r9 = this.zze;
        int r10 = String.valueOf(r9).length();
        List r11 = this.zzf;
        int r12 = String.valueOf(r11).length();
        List r13 = this.zzg;
        int r14 = String.valueOf(r13).length();
        boolean r15 = this.zzh;
        int r16 = String.valueOf(r15).length();
        boolean r22 = this.zzi;
        int r18 = String.valueOf(r22).length();
        boolean r42 = this.zzj;
        int r20 = String.valueOf(r42).length();
        zznf r0 = this.zzk;
        int r17 = ((((((((((((((((((r2 + 59) + r4) + 9) + r6) + 10) + r8) + 17) + r10) + 30) + r12) + 30) + r14) + 24) + r16) + 26) + r18) + 20) + r20) + 14;
        StringBuilder r62 = new StringBuilder((r17 + String.valueOf(r0).length()) + 1);
        r62.append("SharedStorageInfo(shouldUseSharedStorage=");
        r62.append(r1);
        r62.append(", enabledBackings=");
        r62.append(r3);
        r62.append(", secret=");
        r62.append(r5);
        r62.append(", dirPath=");
        r62.append(r7);
        r62.append(", gmsCoreDirPath=");
        r62.append(r9);
        r62.append(", includeStaticConfigPackages=");
        r62.append(r11);
        r62.append(", excludeStaticConfigPackages=");
        r62.append(r13);
        r62.append(", hasStorageInfoFromGms=");
        r62.append(r15);
        r62.append(", allowEmptySnapshotToken=");
        r62.append(r22);
        r62.append(", enableCommitV2Api=");
        r62.append(r42);
        r62.append(", clientFlags=");
        r62.append(r0);
        r62.append(")");
        return r62.toString();
    }

    public final zzacr zza() {
        return this.zzc;
    }

    public final String zzb() {
        return this.zzd;
    }

    public final String zzc() {
        return this.zze;
    }

    public final boolean zzd() {
        return this.zzi;
    }

    public final boolean zze() {
        return this.zzj;
    }

    public final zznf zzf() {
        return this.zzk;
    }

    public final int zzg(zzabz r2, String r3) {
        if (this.zzh == true) goto L5;
        return 14;
    L5:
        if (this.zza == true) goto L7;
        return 3;
    L7:
        if (this.zzb.contains(r2) == true) goto L9;
        return 3;
    L9:
        if (this.zzc.zzb() == 0) goto L22;
        List r22 = this.zzf;
        if (r22.isEmpty() == true) goto L17;
        if (r22.contains(r3) == true) goto L17;
        return 5;
    L17:
        if (this.zzg.contains(r3) == false) goto L20;
        return 6;
    L20:
        return 0;
    L22:
        return 4;
    }
}
