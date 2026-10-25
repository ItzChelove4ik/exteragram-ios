package com.google.android.gms.internal.measurement;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zznd {
    private static final zznd zza = null;
    private final zzmw zzb;
    private final zzmq zzc;

    static {
        zza = new zznd(zzmw.zzb(), zzmq.zzi());
    }

    private zznd(zzmw r1, zzmq r2) {
        this.zzb = (zzmw) Preconditions.checkNotNull(r1);
        this.zzc = r2;
    }

    public static zznd zza() {
        return zza;
    }

    public static zznd zzb(zzacv r4, boolean r5) {
        int r0 = r4.zzr();
        if (r0 > 1) goto L21;
        r4.zzr();
        int r02 = r4.zzD(r4.zzp());
        zzmq r1 = zzmq.zzh(r4, zzadf.zza());
        r4.zzE(r02);
        zzmu r03 = zzmu.zza();
        if (r5 == false) goto L13;
        int r52 = r4.zzD(r4.zzp());     // Catch: Throwable -> L9
        zzmw r2 = (zzmw) r03.zzc(r4, zznb.zza);     // Catch: Throwable -> L9
        if (r4.zzF() != 0) goto L12;
        r4.zzE(r52);     // Catch: Throwable -> L9
    L14:
        r03.close();
        return new zznd(r2, r1);
    L12:
        throw new zzaeh("Unexpected bytes remaining after FlagsBlob parsing.");     // Catch: Throwable -> L9
    L13:
        r2 = (zzmw) r03.zzb(r4.zzo(), zznc.zza);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        r03.close();     // Catch: Throwable -> L18
    L20:
        throw th;
    L18:
        th = move-exception;
        th.addSuppressed(th);
        goto L20
    L21:
        StringBuilder r12 = new StringBuilder(String.valueOf(r0).length() + 44);
        r12.append("Unsupported version: ");
        r12.append(r0);
        r12.append(". Current version is: 1");
        throw new zzaeh(r12.toString());
    }

    public final zzmw zzc() {
        zzmq r0 = this.zzc;
        int r1 = r0.zze();
        zzmw r6 = this.zzb;
        if (r1 <= 0) goto L49;
        Collection r02 = r0.zzf().values();
        if (r02 != null) goto L7;
        ImmutableMap r03 = ImmutableMap.of();
    L31:
        return zzmw.zza(r6, r03);
    L7:
        ImmutableMap.Builder r12 = ImmutableMap.builder();
        Iterator r04 = r02.iterator();
    L9:
        if (r04.hasNext() == false) goto L29;
        zzmi r2 = (zzmi) r04.next();
        int r3 = r2.zzq();
        int r4 = r3 - 1;
        if (r3 == 0) goto L28;
        if (r4 != 0) goto L14;
        r12.put(r2.zza(), Long.valueOf(r2.zzb()));
        goto L9
    L14:
        if (r4 != 1) goto L16;
        r12.put(r2.zza(), Boolean.valueOf(r2.zzc()));
        goto L9
    L16:
        if (r4 != 2) goto L18;
        r12.put(r2.zza(), Double.valueOf(r2.zzd()));
        goto L9
    L18:
        if (r4 != 3) goto L20;
        r12.put(r2.zza(), r2.zze());
        goto L9
    L20:
        if (r4 != 4) goto L22;
        r12.put(r2.zza(), r2.zzf().zzm());
        goto L9
    L22:
        Segment$$ExternalSyntheticBUOutline1.m("Could not serialize Flag for override: ".concat(String.valueOf(r2.zza())));
        return null;
    L28:
        throw null;
    L29:
        r03 = r12.buildKeepingLast();
        goto L31
    L49:
        return r6;
    }

    public final String zzd() {
        return this.zzc.zza();
    }

    public final String zze() {
        return this.zzc.zzc();
    }

    public final long zzf() {
        return this.zzc.zzd();
    }

    public final zzacr zzg() {
        return this.zzc.zzb();
    }

    public final Map zzh() {
        zzmq r1 = this.zzc;
        if (r1.zze() != 0) goto L7;
        return null;
    L7:
        return r1.zzf();
    }

    public final boolean zzi() {
        if (this.zzb.zze().isEmpty() == false) goto L8;
        zzmq r1 = this.zzc;
        if (zzmq.zzi().equals(r1) == false) goto L10;
        return true;
    L10:
        return false;
    L8:
        return false;
    }
}
