package com.google.android.gms.internal.measurement;

import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzbi extends zzav {
    public zzbi() {
    }

    @Override // com.google.android.gms.internal.measurement.zzav
    public final zzao zza(String r3, zzg r4, List r5) {
        if (r3 != null) goto L5;
    L14:
        FieldSet$$ExternalSyntheticBUOutline0.m("Command not found: %s", new Object[]{r3});
        return null;
    L5:
        if (r3.isEmpty() == true) goto L14;
        if (r4.zzd(r3) == false) goto L14;
        zzao r0 = r4.zzh(r3);
        if ((r0 instanceof zzai) == true) goto L11;
        FieldSet$$ExternalSyntheticBUOutline0.m("Function %s is not defined", new Object[]{r3});
        return null;
    L11:
        return ((zzai) r0).zza(r4, r5);
    }
}
