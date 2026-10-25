package com.google.android.recaptcha.internal;

import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public abstract class zzvc {
    public static final zzady zza = null;

    static {
        zza = zzady.zza(new byte[0]);
    }

    public static final zzady zza(int r2) {
        return zzady.zza(ByteBuffer.allocate(5).put((byte) 0).putInt(r2).array());
    }

    public static final zzady zzb(int r2) {
        return zzady.zza(ByteBuffer.allocate(5).put((byte) 1).putInt(r2).array());
    }
}
