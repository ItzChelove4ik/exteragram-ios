package com.google.android.gms.internal.mlkit_vision_label;

import com.google.firebase.encoders.ObjectEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzcr {
    private final Map zza;
    private final Map zzb;
    private final ObjectEncoder zzc;

    public zzcr(Map r1, Map r2, ObjectEncoder r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public final byte[] zza(Object r5) {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();
        new zzco(r0, this.zza, this.zzb, this.zzc).zzf(r5);     // Catch: IOException -> L6
    L5:
        return r0.toByteArray();
    }
}
