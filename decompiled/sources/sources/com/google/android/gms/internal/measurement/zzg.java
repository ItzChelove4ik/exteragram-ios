package com.google.android.gms.internal.measurement;

import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzg {
    public final zzg zza;
    final zzaw zzb;
    final Map zzc;
    final Map zzd;

    public zzg(zzg r2, zzaw r3) {
        this.zzc = new HashMap();
        this.zzd = new HashMap();
        this.zza = r2;
        this.zzb = r3;
    }

    public final zzao zza(zzao r2) {
        return this.zzb.zzb(this, r2);
    }

    public final zzao zzb(zzae r4) {
        zzao r0 = zzao.zzf;
        Iterator r1 = r4.zzg();
    L4:
        if (r1.hasNext() == false) goto L7;
        int r02 = ((Integer) r1.next()).intValue();
        r0 = this.zzb.zzb(this, r4.zzl(r02));
        if ((r0 instanceof zzag) == false) goto L4;
    L7:
        return r0;
    }

    public final zzg zzc() {
        return new zzg(this, this.zzb);
    }

    public final boolean zzd(String r2) {
        if (this.zzc.containsKey(r2) == false) goto L6;
        return true;
    L6:
        zzg r1 = this.zza;
        if (r1 != null) goto L9;
        return false;
    L9:
        return r1.zzd(r2);
    }

    public final void zze(String r4, zzao r5) {
        Map r0 = this.zzc;
        if (r0.containsKey(r4) == true) goto L11;
        zzg r1 = this.zza;
        if (r1 == null) goto L11;
        if (r1.zzd(r4) == false) goto L11;
        r1.zze(r4, r5);
        return;
    L11:
        if (this.zzd.containsKey(r4) == false) goto L13;
        return;
    L13:
        if (r5 != null) goto L16;
        r0.remove(r4);
        return;
    L16:
        r0.put(r4, r5);
    }

    public final void zzf(String r2, zzao r3) {
        if (this.zzd.containsKey(r2) == false) goto L5;
        return;
    L5:
        Map r1 = this.zzc;
        if (r3 != null) goto L9;
        r1.remove(r2);
        return;
    L9:
        r1.put(r2, r3);
    }

    public final void zzg(String r1, zzao r2) {
        zzf(r1, r2);
        this.zzd.put(r1, Boolean.TRUE);
    }

    public final zzao zzh(String r3) {
        Map r0 = this.zzc;
        if (r0.containsKey(r3) == true) goto L5;
        zzg r2 = this.zza;
        if (r2 != null) goto L9;
        FieldSet$$ExternalSyntheticBUOutline0.m("%s is not defined", new Object[]{r3});
        return null;
    L9:
        return r2.zzh(r3);
    L5:
        return (zzao) r0.get(r3);
    }
}
