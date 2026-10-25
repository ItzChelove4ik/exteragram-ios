package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import c.f$$ExternalSyntheticBUOutline2;
import j$.time.Duration;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzdr extends zzdk {
    private Context zza;
    private ExecutorService zzb;
    private Executor zzc;
    private boolean zzd;
    private Duration zze;
    private ScheduledExecutorService zzf;
    private Optional zzg;
    private byte zzh;

    public zzdr() {
        this.zzg = Optional.empty();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdk
    public final zzdk zza(ExecutorService r1) {
        if (r1 == null) goto L5;
        this.zzb = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null workerExecutor");
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdk
    public final zzdk zzb(Executor r1) {
        if (r1 == null) goto L5;
        this.zzc = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null callbackExecutor");
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdk
    public final zzdk zzc(boolean r1) {
        this.zzd = true;
        this.zzh = 1;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdk
    public final zzdk zzd(Duration r1) {
        if (r1 == null) goto L5;
        this.zze = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null autoUnbindTimeoutMs");
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdk
    public final zzdk zze(ScheduledExecutorService r1) {
        if (r1 == null) goto L5;
        this.zzf = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null autoUnbindScheduledExecutor");
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdk
    public final zzdk zzf(Optional r1) {
        if (r1 == null) goto L5;
        this.zzg = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null autoDownloadEnabled");
        return null;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdk
    public final Optional zzg() {
        ExecutorService r0 = this.zzb;
        if (r0 != null) goto L7;
        return Optional.empty();
    L7:
        return Optional.of(r0);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdk
    public final Optional zzh() {
        ScheduledExecutorService r0 = this.zzf;
        if (r0 != null) goto L7;
        return Optional.empty();
    L7:
        return Optional.of(r0);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdk
    public final zzdn zzj() {
        if (this.zzh != 1) goto L17;
        Context r3 = this.zza;
        if (r3 == null) goto L17;
        ExecutorService r4 = this.zzb;
        if (r4 == null) goto L17;
        Executor r5 = this.zzc;
        if (r5 == null) goto L17;
        Duration r7 = this.zze;
        if (r7 == null) goto L17;
        ScheduledExecutorService r8 = this.zzf;
        if (r8 == null) goto L17;
        return new zzds(r3, r4, r5, this.zzd, r7, r8, this.zzg, null);
    L17:
        StringBuilder r0 = new StringBuilder();
        if (this.zza != null) goto L21;
        r0.append(" context");
    L21:
        if (this.zzb != null) goto L24;
        r0.append(" workerExecutor");
    L24:
        if (this.zzc != null) goto L27;
        r0.append(" callbackExecutor");
    L27:
        if (this.zzh != 0) goto L30;
        r0.append(" bindImportantEnabled");
    L30:
        if (this.zze != null) goto L33;
        r0.append(" autoUnbindTimeoutMs");
    L33:
        if (this.zzf != null) goto L35;
        r0.append(" autoUnbindScheduledExecutor");
    L35:
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r0.toString()));
        return null;
    }

    public final zzdk zzk(Context r1) {
        if (r1 == null) goto L5;
        this.zza = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null context");
        return null;
    }
}
