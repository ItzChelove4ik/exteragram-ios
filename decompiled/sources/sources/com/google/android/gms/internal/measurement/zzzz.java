package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
final class zzzz extends zzaaa {
    private final Map zza;

    public /* synthetic */ zzzz(zzzj r2, zzzj r3, byte[] r4) {
        super(null);
        LinkedHashMap r42 = new LinkedHashMap();
        zzd(r42, r2);
        zzd(r42, r3);
        Iterator r22 = r42.entrySet().iterator();
    L4:
        if (r22.hasNext() == false) goto L8;
        Map.Entry r32 = (Map.Entry) r22.next();
        if (((zzyl) r32.getKey()).zzf() == false) goto L4;
        r32.setValue(Collections.unmodifiableList((List) r32.getValue()));
        goto L4
    L8:
        this.zza = Collections.unmodifiableMap(r42);
    }

    private static void zzd(Map r4, zzzj r5) {
        int r0 = 0;
    L4:
        if (r0 >= r5.zza()) goto L13;
        zzyl r1 = r5.zzb(r0);
        Object r2 = r4.get(r1);
        if (r1.zzf() == false) goto L11;
        List r22 = (List) r2;
        if (r22 != null) goto L10;
        r22 = new ArrayList();
        r4.put(r1, r22);
    L10:
        r22.add(r1.zze(r5.zzc(r0)));
    L12:
        r0 = r0 + 1;
        goto L4
    L11:
        r4.put(r1, r1.zze(r5.zzc(r0)));
        goto L12
    }

    @Override // com.google.android.gms.internal.measurement.zzaaa
    public final void zza(zzzq r4, Object r5) {
        Iterator r3 = this.zza.entrySet().iterator();
    L4:
        if (r3.hasNext() == false) goto L9;
        Map.Entry r0 = (Map.Entry) r3.next();
        zzyl r1 = (zzyl) r0.getKey();
        Object r02 = r0.getValue();
        if (r1.zzf() == true) goto L7;
        r4.zza(r1, r02, r5);
        goto L4
    L7:
        r4.zzb(r1, ((List) r02).iterator(), r5);
        goto L4
    }

    @Override // com.google.android.gms.internal.measurement.zzaaa
    public final int zzb() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.measurement.zzaaa
    public final Set zzc() {
        return this.zza.keySet();
    }
}
