package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Arrays;
import java.util.UUID;
import kotlin.jvm.internal.StringCompanionObject;

/* loaded from: classes4.dex */
public abstract class zzxh {
    public static final String zza() {
        zzxp r0 = zzxp.zza;
        long r02 = System.currentTimeMillis();
        String r2 = UUID.randomUUID().toString().substring(0, 8);
        StringCompanionObject r3 = StringCompanionObject.INSTANCE;
        return String.format("cache_%s_%s", Arrays.copyOf(new Object[]{Long.valueOf(r02), r2}, 2));
    }
}
