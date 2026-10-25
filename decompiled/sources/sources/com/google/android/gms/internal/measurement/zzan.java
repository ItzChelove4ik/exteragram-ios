package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzan extends zzai implements zzak {
    protected final List zza;
    protected final List zzb;
    protected zzg zzc;

    private zzan(zzan r3) {
        super(r3.zzd);
        ArrayList r0 = new ArrayList(r3.zza.size());
        this.zza = r0;
        r0.addAll(r3.zza);
        ArrayList r02 = new ArrayList(r3.zzb.size());
        this.zzb = r02;
        r02.addAll(r3.zzb);
        this.zzc = r3.zzc;
    }

    public zzan(String r1, List r2, List r3, zzg r4) {
        super(r1);
        this.zza = new ArrayList();
        this.zzc = r4;
        if (r2.isEmpty() == true) goto L8;
        Iterator r12 = r2.iterator();
    L6:
        if (r12.hasNext() == false) goto L8;
        zzao r22 = (zzao) r12.next();
        this.zza.add(r22.zzc());
    L8:
        this.zzb = new ArrayList(r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzao zza(zzg r5, List r6) {
        zzg r0 = this.zzc.zzc();
        int r1 = 0;
    L3:
        List r2 = this.zza;
        if (r1 >= r2.size()) goto L10;
        if (r1 >= r6.size()) goto L8;
        r0.zzf((String) r2.get(r1), r5.zza((zzao) r6.get(r1)));
    L9:
        r1 = r1 + 1;
        goto L3
    L8:
        r0.zzf((String) r2.get(r1), zzao.zzf);
        goto L9
    L10:
        Iterator r4 = this.zzb.iterator();
    L12:
        if (r4.hasNext() == false) goto L21;
        zzao r52 = (zzao) r4.next();
        zzao r62 = r0.zza(r52);
        if ((r62 instanceof zzap) == false) goto L17;
        r62 = r0.zza(r52);
    L17:
        if ((r62 instanceof zzag) == false) goto L12;
        return ((zzag) r62).zzb();
    L21:
        return zzao.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzai, com.google.android.gms.internal.measurement.zzao
    public final zzao zzt() {
        return new zzan(this);
    }
}
