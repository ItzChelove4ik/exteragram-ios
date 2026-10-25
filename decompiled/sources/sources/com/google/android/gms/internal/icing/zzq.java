package com.google.android.gms.internal.icing;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

@VisibleForTesting
/* loaded from: classes4.dex */
public abstract class zzq {

    @VisibleForTesting
    static final String[] zza = null;
    private static final Map<String, Integer> zzb = null;

    static {
        zza = new String[]{"text1", "text2", "icon", "intent_action", "intent_data", "intent_data_id", "intent_extra_data", "suggest_large_icon", "intent_activity", "thing_proto"};
        zzb = new HashMap(10);
        int r0 = 0;
    L3:
        String[] r2 = zza;
        int r3 = r2.length;
        if (r0 >= 10) goto L6;
        zzb.put(r2[r0], Integer.valueOf(r0));
        r0 = r0 + 1;
        goto L3
    }

    public static String zza(int r2) {
        if (r2 < 0) goto L8;
        String[] r0 = zza;
        int r1 = r0.length;
        if (r2 < 10) goto L7;
        return null;
    L7:
        return r0[r2];
    L8:
        return null;
    }
}
