package com.google.android.gms.internal.play_billing;

import androidx.core.app.NavUtils$$ExternalSyntheticBUOutline0;
import c.f$$ExternalSyntheticBUOutline2;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: classes5.dex */
public abstract class zzfo {
    static final Charset zza = null;
    public static final byte[] zzb = null;

    static {
        Charset.forName("US-ASCII");
        zza = Charset.forName("UTF-8");
        Charset.forName("ISO-8859-1");
        byte[] r2 = new byte[0];
        zzb = r2;
        ByteBuffer.wrap(r2);
        int r1 = zzel.$r8$clinit;
        new zzej(r2, 0, 0, false, null).zza(0);     // Catch: zzfq -> L5
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

    public static int zzb(int r1, byte[] r2, int r3, int r4) {
        int r32 = 0;
    L3:
        if (r32 >= r4) goto L5;
        r1 = (r1 * 31) + r2[r32];
        r32 = r32 + 1;
        goto L3
    L5:
        return r1;
    }

    public static Object zzc(Object r0, String r1) {
        if (r0 == null) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline2.m("messageType");
        return null;
    }
}
