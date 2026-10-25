package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
final class zzvd extends zzve {
    private final zzafc zza;
    private final zzadf zzb;

    public zzvd(zzafc r1, zzadf r2) {
        this.zza = r1;
        if (r2 == null) goto L6;
        this.zzb = r2;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null extensionRegistryLite");
        throw null;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzve) == false) goto L12;
        zzve r52 = (zzve) r5;
        if (this.zza.equals(r52.zzb()) == false) goto L12;
        if (this.zzb.equals(r52.zzc()) == false) goto L12;
        return true;
    L12:
        return false;
    }

    public final int hashCode() {
        int r0 = this.zza.hashCode() ^ 1000003;
        int r2 = this.zzb.hashCode();
        return r2 ^ (r0 * 1000003);
    }

    public final String toString() {
        String r0 = this.zza.toString();
        int r1 = r0.length();
        String r3 = this.zzb.toString();
        int r12 = r1 + 53;
        StringBuilder r2 = new StringBuilder((r12 + r3.length()) + 1);
        r2.append("ProtoSerializer{defaultValue=");
        r2.append(r0);
        r2.append(", extensionRegistryLite=");
        r2.append(r3);
        r2.append("}");
        return r2.toString();
    }

    @Override // com.google.android.gms.internal.measurement.zztv
    public final /* synthetic */ Object zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzve
    public final zzafc zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzve
    public final zzadf zzc() {
        return this.zzb;
    }
}
