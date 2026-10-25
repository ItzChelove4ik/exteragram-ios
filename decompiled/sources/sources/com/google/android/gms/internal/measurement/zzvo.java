package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.common.collect.ImmutableList;
import java.util.UUID;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzvo extends zzwv {
    private ImmutableList zza;
    private ImmutableList zzb;
    private UUID zzc;
    private long zzd;
    private byte zze;

    public zzvo() {
    }

    @Override // com.google.android.gms.internal.measurement.zzwv
    public final zzwv zza(ImmutableList r1) {
        if (r1 == null) goto L5;
        this.zza = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null spansNames");
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzwv
    public final zzwv zzb(ImmutableList r1) {
        if (r1 == null) goto L5;
        this.zzb = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null extras");
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzwv
    public final zzwv zzc(UUID r1) {
        if (r1 == null) goto L5;
        this.zzc = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline2.m("Null rootTraceId");
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzwv
    public final zzwv zzd(long r1) {
        this.zzd = -1;
        this.zze = 1;
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zzwv
    public final zzww zze() {
        if (this.zze != 1) goto L13;
        ImmutableList r3 = this.zza;
        if (r3 == null) goto L13;
        ImmutableList r4 = this.zzb;
        if (r4 == null) goto L13;
        UUID r5 = this.zzc;
        if (r5 == null) goto L13;
        return new zzvp(r3, r4, r5, this.zzd, null);
    L13:
        StringBuilder r0 = new StringBuilder();
        if (this.zza != null) goto L17;
        r0.append(" spansNames");
    L17:
        if (this.zzb != null) goto L20;
        r0.append(" extras");
    L20:
        if (this.zzc != null) goto L23;
        r0.append(" rootTraceId");
    L23:
        if (this.zze != 0) goto L25;
        r0.append(" rootDurationMs");
    L25:
        Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r0.toString()));
        return null;
    }
}
