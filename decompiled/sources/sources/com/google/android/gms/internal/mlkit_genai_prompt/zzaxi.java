package com.google.android.gms.internal.mlkit_genai_prompt;

import androidx.core.app.NavUtils$$ExternalSyntheticBUOutline0;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public abstract class zzaxi {
    public static final byte[] zza = null;

    static {
        byte[] r2 = new byte[0];
        zza = r2;
        ByteBuffer.wrap(r2);
        int r1 = zzawe.$r8$clinit;
        new zzawd(r2, 0, 0, false, null).zza(0);     // Catch: zzaxm -> L5
        return;
    L5:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
    }

    public static int zza(boolean r0) {
        if (r0 == false) goto L5;
        return 1231;
    L5:
        return 1237;
    }

    public static int zzb(int r2, byte[] r3, int r4, int r5) {
        int r0 = r4;
    L4:
        if (r0 >= (r4 + r5)) goto L6;
        r2 = (r2 * 31) + r3[r0];
        r0 = r0 + 1;
        goto L4
    L6:
        return r2;
    }
}
