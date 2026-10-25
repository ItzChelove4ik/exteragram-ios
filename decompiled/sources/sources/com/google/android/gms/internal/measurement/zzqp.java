package com.google.android.gms.internal.measurement;

import com.google.common.base.Function;
import java.util.Iterator;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final /* synthetic */ class zzqp implements Function {
    static final /* synthetic */ zzqp zza = null;

    static {
        zza = new zzqp();
    }

    private /* synthetic */ zzqp() {
    }

    @Override // com.google.common.base.Function
    public final /* synthetic */ Object apply(Object r7) {
        zzmg r72 = (zzmg) r7;
        zzqu r6 = zzqv.zzh();
        if (r72 == null) goto L5;
        Iterator r0 = r72.zze().iterator();
    L8:
        if (r0.hasNext() == false) goto L29;
        zzmi r1 = (zzmi) r0.next();
        zzqw r2 = zzqx.zzh();
        r2.zza(r1.zza());
        int r3 = r1.zzq();
        int r4 = r3 - 1;
        if (r3 == 0) goto L28;
        if (r4 != 0) goto L13;
        r2.zzb(r1.zzb());
    L27:
        r6.zze((zzqx) r2.zzbd());
        goto L8
    L13:
        if (r4 != 1) goto L15;
        r2.zzc(r1.zzc());
        goto L27
    L15:
        if (r4 != 2) goto L17;
        r2.zzd(r1.zzd());
        goto L27
    L17:
        if (r4 != 3) goto L19;
        r2.zze(r1.zze());
        goto L27
    L19:
        if (r4 != 4) goto L21;
        r2.zzf(r1.zzf());
        goto L27
    L21:
        Segment$$ExternalSyntheticBUOutline1.m("No known flag type");
        return null;
    L28:
        throw null;
    L29:
        r6.zzc(r72.zzd());
        r6.zza(r72.zza());
        r6.zzd(r72.zzf());
        if (r72.zzb() == false) goto L33;
        r6.zzb(r72.zzc());
    L33:
        return (zzqv) r6.zzbd();
    L5:
        return (zzqv) r6.zzbd();
    }
}
