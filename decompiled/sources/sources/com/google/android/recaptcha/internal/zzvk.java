package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public final class zzvk {
    private final Map zza;

    public zzvk() {
        this.zza = new HashMap();
    }

    public final zzvk zza(zzady r3, Object r4) {
        if (r3.zzd() != 0) goto L5;
    L9:
        Map r0 = this.zza;
        if (r0.containsKey(r3) == false) goto L12;
        List r32 = (List) r0.get(r3);
    L13:
        r32.add(r4);
        return this;
    L12:
        ArrayList r1 = new ArrayList();
        r0.put(r3, r1);
        r32 = r1;
        goto L13
    L5:
        if (r3.zzd() == 5) goto L9;
        zzsj$$ExternalSyntheticBUOutline1.m("PrefixMap only supports 0 and 5 byte prefixes");
        return null;
    }

    public final zzvm zzb() {
        return new zzvm(this.zza, null);
    }
}
