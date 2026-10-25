package com.google.android.gms.internal.cast;

import android.os.Bundle;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class zzaz {
    public static Map zza(Bundle r2, String r3) {
        Map r22 = (Map) r2.getSerializable(r3);
        if (r22 == null) goto L5;
        HashMap r32 = new HashMap();
        Iterator r23 = r22.entrySet().iterator();
    L8:
        if (r23.hasNext() == false) goto L17;
        Map.Entry r0 = (Map.Entry) r23.next();
        if (r0 == null) goto L8;
        if (r0.getKey() == null) goto L8;
        if (r0.getValue() == null) goto L8;
        r32.put((Integer) r0.getKey(), (Integer) r0.getValue());
        goto L8
    L17:
        return Collections.unmodifiableMap(r32);
    L5:
        return zzhy.zza();
    }
}
