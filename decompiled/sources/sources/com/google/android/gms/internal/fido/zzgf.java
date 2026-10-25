package com.google.android.gms.internal.fido;

import java.io.IOException;
import okio.Buffer$$ExternalSyntheticBUOutline2;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes4.dex */
public abstract class zzgf {
    private static final zzgf zza = null;
    private static final zzgf zzb = null;
    private static final zzgf zzc = null;
    private static final zzgf zzd = null;
    private static final zzgf zze = null;

    static {
        Character r1 = Character.valueOf(SignatureVisitor.INSTANCEOF);
        zza = new zzgd("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", r1);
        zzb = new zzgd("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", r1);
        zzc = new zzge("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", r1);
        zzd = new zzge("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", r1);
        zze = new zzgc("base16()", "0123456789ABCDEF");
    }

    public zzgf() {
    }

    public static zzgf zzf() {
        return zze;
    }

    public abstract void zzb(Appendable r1, byte[] r2, int r3, int r4);

    public abstract int zzc(int r1);

    public abstract zzgf zzd();

    public final String zzg(byte[] r3, int r4, int r5) {
        zzbm.zze(0, r5, r3.length);
        StringBuilder r42 = new StringBuilder(zzc(r5));
        zzb(r42, r3, 0, r5);     // Catch: IOException -> L6
        return r42.toString();
    L6:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    }
}
