package com.google.android.gms.internal.mlkit_genai_prompt;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.text.Typography;

/* loaded from: classes4.dex */
public final class zzmp {
    private final List zza;

    public zzmp() {
        this.zza = new ArrayList();
    }

    public final zzmp zza(zzne r2) {
        if (r2.zzb() == true) goto L6;
        this.zza.add(r2);
        return this;
    L6:
        f$$ExternalSyntheticBUOutline1.m(zzlb.zzd("range must not be empty, but was %s", new Object[]{r2}));
        return null;
    }

    public final zzmp zzb(zzmp r2) {
        Iterator r22 = r2.zza.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        zza((zzne) r22.next());
        goto L4
    L6:
        return this;
    }

    public final zzmq zzc() {
        List r5 = this.zza;
        zzmh r0 = new zzmh(r5.size());
        int r1 = zzne.$r8$clinit;
        Collections.sort(r5, zznd.zza);
        Iterator r52 = r5.iterator();
        if ((r52 instanceof zzmv) == false) goto L5;
        zzmv r53 = (zzmv) r52;
    L7:
        if (r53.hasNext() == false) goto L17;
        zzne r12 = (zzne) r53.next();
    L10:
        if (r53.hasNext() == false) goto L16;
        zzne r2 = (zzne) r53.zza();
        if (r12.zza.zzc(r2.zzb) > 0) goto L16;
        if (r2.zza.zzc(r12.zzb) > 0) goto L16;
        zzla.zzb(r12.zzc(r2).zzb(), "Overlapping ranges not permitted but found %s overlapping %s", r12, r2);
        r12 = r12.zzd((zzne) r53.next());
    L16:
        r0.zzd(r12);
        goto L7
    L17:
        zzml r54 = r0.zze();
        if (r54.isEmpty() == false) goto L22;
        return zzmq.zza();
    L22:
        if (r54.size() != 1) goto L42;
        int r02 = 0;
        zznt r13 = r54.zzs(0);
        Object r22 = r13.next();
        if (r13.hasNext() == false) goto L26;
        StringBuilder r55 = new StringBuilder("expected one element but was: <");
        r55.append(r22);
    L31:
        if (r02 >= 4) goto L36;
        if (r13.hasNext() == false) goto L36;
        r55.append(", ");
        r55.append(r13.next());
        r02 = r02 + 1;
    L36:
        if (r13.hasNext() == false) goto L39;
        r55.append(", ...");
    L39:
        r55.append(Typography.greater);
        throw new IllegalArgumentException(r55.toString());
    L26:
        if (((zzne) r22).equals(zzne.zza()) == false) goto L42;
        return zzmq.zzb();
    L42:
        return new zzmq(r54);
    L5:
        r53 = new zzmv(r52);
        goto L7
    }
}
