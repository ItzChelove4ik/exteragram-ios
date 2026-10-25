package com.google.android.gms.internal.fido;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: classes4.dex */
public abstract class zzhc {
    static final Charset zza = null;
    static final Charset zzb = null;
    static final Charset zzc = null;
    public static final byte[] zzd = null;
    public static final ByteBuffer zze = null;
    public static final zzhb zzf = null;

    static {
        zza = Charset.forName("US-ASCII");
        zzb = Charset.forName("UTF-8");
        zzc = Charset.forName("ISO-8859-1");
        byte[] r2 = new byte[0];
        zzd = r2;
        zze = ByteBuffer.wrap(r2);
        int r1 = zzhb.$r8$clinit;
        zzgz r12 = new zzgz(r2, 0, 0, false, null);
        r12.zza(0);
        zzf = r12;
    }
}
