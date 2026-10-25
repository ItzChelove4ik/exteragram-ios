package com.google.android.recaptcha.internal;

import kotlin.comparisons.ComparisonsKt;
import kotlin.text.StringsKt;

/* loaded from: classes5.dex */
public final class zzhi implements Comparable {
    private int zza;
    private long zzb;
    private long zzc;

    public zzhi() {
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object r1) {
        return zzg((zzhi) r1);
    }

    public final String toString() {
        String r0 = StringsKt.padEnd$default(String.valueOf(this.zzb / this.zza), 10, 0, 2, null);
        String r5 = StringsKt.padEnd$default(String.valueOf(this.zzc), 10, 0, 2, null);
        String r1 = StringsKt.padEnd$default(String.valueOf(this.zzb), 10, 0, 2, null);
        String r8 = StringsKt.padEnd$default(String.valueOf(this.zza), 5, 0, 2, null);
        int r2 = String.valueOf(r0).length();
        int r3 = String.valueOf(r5).length();
        StringBuilder r32 = new StringBuilder((((((r2 + 41) + r3) + 16) + String.valueOf(r1).length()) + 14) + String.valueOf(r8).length());
        r32.append("avgExecutionTime: ");
        r32.append(r0);
        r32.append(" us| maxExecutionTime: ");
        r32.append(r5);
        r32.append(" us| totalTime: ");
        r32.append(r1);
        r32.append(" us| #Usages: ");
        r32.append(r8);
        return r32.toString();
    }

    public final int zza() {
        return this.zza;
    }

    public final void zzb(int r1) {
        this.zza = r1;
    }

    public final long zzc() {
        return this.zzb;
    }

    public final void zzd(long r1) {
        this.zzb = r1;
    }

    public final long zze() {
        return this.zzc;
    }

    public final void zzf(long r1) {
        this.zzc = r1;
    }

    public final int zzg(zzhi r3) {
        return ComparisonsKt.compareValues(Long.valueOf(this.zzb), Long.valueOf(r3.zzb));
    }
}
