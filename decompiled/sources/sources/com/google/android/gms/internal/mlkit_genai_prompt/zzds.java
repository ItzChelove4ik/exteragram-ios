package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import j$.time.Duration;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes4.dex */
final class zzds extends zzdn {
    private final Context zza;
    private final ExecutorService zzb;
    private final Executor zzc;
    private final boolean zzd;
    private final Duration zze;
    private final ScheduledExecutorService zzf;
    private final Optional zzg;

    public /* synthetic */ zzds(Context r1, ExecutorService r2, Executor r3, boolean r4, Duration r5, ScheduledExecutorService r6, Optional r7, byte[] r8) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzdn) == false) goto L22;
        zzdn r52 = (zzdn) r5;
        if (this.zza.equals(r52.zza()) == false) goto L22;
        if (this.zzb.equals(r52.zzb()) == false) goto L22;
        if (this.zzc.equals(r52.zzc()) == false) goto L22;
        if (this.zzd != r52.zzd()) goto L22;
        if (this.zze.equals(r52.zze()) == false) goto L22;
        if (this.zzf.equals(r52.zzf()) == false) goto L22;
        if (this.zzg.equals(r52.zzg()) == false) goto L22;
        return true;
    L22:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zza.hashCode() ^ 1000003;
        int r02 = (r0 * 1000003) ^ this.zzb.hashCode();
        int r03 = (r02 * 1000003) ^ this.zzc.hashCode();
        if (true == this.zzd) goto L5;
        int r2 = 1237;
    L6:
        int r04 = ((((((r03 * 1000003) ^ r2) * 1000003) ^ this.zze.hashCode()) * 1000003) ^ this.zzf.hashCode()) * 1000003;
        return this.zzg.hashCode() ^ r04;
    L5:
        r2 = 1231;
        goto L6
    }

    public final String toString() {
        String r0 = this.zza.toString();
        int r1 = r0.length();
        String r2 = this.zzb.toString();
        int r3 = r2.length();
        String r4 = this.zzc.toString();
        int r5 = r4.length();
        Optional r6 = this.zzg;
        ScheduledExecutorService r7 = this.zzf;
        String r8 = this.zze.toString();
        String r72 = r7.toString();
        String r62 = String.valueOf(r6);
        boolean r13 = this.zzd;
        int r9 = String.valueOf(r13).length();
        int r10 = r8.length();
        int r11 = r72.length();
        int r12 = ((((((((((r1 + 45) + r3) + 19) + r5) + 23) + r9) + 22) + r10) + 30) + r11) + 22;
        StringBuilder r32 = new StringBuilder((r12 + r62.length()) + 1);
        r32.append("AiCoreClientOptions{context=");
        r32.append(r0);
        r32.append(", workerExecutor=");
        r32.append(r2);
        r32.append(", callbackExecutor=");
        r32.append(r4);
        r32.append(", bindImportantEnabled=");
        r32.append(r13);
        r32.append(", autoUnbindTimeoutMs=");
        r32.append(r8);
        r32.append(", autoUnbindScheduledExecutor=");
        r32.append(r72);
        r32.append(", autoDownloadEnabled=");
        r32.append(r62);
        r32.append("}");
        return r32.toString();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdn
    public final Context zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdn
    public final ExecutorService zzb() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdn
    public final Executor zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdn
    public final boolean zzd() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdn
    public final Duration zze() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdn
    public final ScheduledExecutorService zzf() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdn
    public final Optional zzg() {
        return this.zzg;
    }
}
