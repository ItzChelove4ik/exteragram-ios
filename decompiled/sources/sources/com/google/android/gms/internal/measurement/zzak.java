package com.google.android.gms.internal.measurement;

import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public interface zzak {
    static zzao zzu(zzak r3, zzao r4, zzg r5, List r6) {
        if (r3.zzj(r4.zzc()) == false) goto L11;
        zzao r32 = r3.zzk(r4.zzc());
        if ((r32 instanceof zzai) == true) goto L7;
        FieldSet$$ExternalSyntheticBUOutline0.m("%s is not a function", new Object[]{r4.zzc()});
        return null;
    L7:
        return ((zzai) r32).zza(r5, r6);
    L11:
        if ("hasOwnProperty".equals(r4.zzc()) == false) goto L18;
        zzh.zza("hasOwnProperty", 1, r6);
        if (r3.zzj(r5.zza((zzao) r6.get(0)).zzc()) == false) goto L17;
        return zzao.zzk;
    L17:
        return zzao.zzl;
    L18:
        FieldSet$$ExternalSyntheticBUOutline0.m("Object has no function %s", new Object[]{r4.zzc()});
        return null;
    }

    static Iterator zzv(Map r1) {
        return new zzaj(r1.keySet().iterator());
    }

    boolean zzj(String r1);

    zzao zzk(String r1);

    void zzm(String r1, zzao r2);
}
