package com.google.android.recaptcha.internal;

import androidx.core.app.NavUtils$$ExternalSyntheticBUOutline0;
import java.io.IOException;
import okio.Buffer$$ExternalSyntheticBUOutline2;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes5.dex */
public abstract class zzrv {
    private static final zzrv zza = null;
    private static final zzrv zzb = null;

    static {
        Character r1 = Character.valueOf(SignatureVisitor.INSTANCEOF);
        zza = new zzrs("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", r1);
        zzb = new zzrs("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", r1);
        new zzru("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", r1);
        new zzru("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", r1);
        int r0 = zzrr.$r8$clinit;
        zzrq r02 = new zzrq("base16()", "0123456789ABCDEF".toCharArray());
        new zzru(r02, null);
        int r3 = 0;
        if (r02.zze().length != 16) goto L5;
        boolean r12 = true;
    L6:
        char[] r2 = new char[512];
        zzoq.zza(r12);
    L8:
        if (r3 >= 256) goto L10;
        r2[r3] = r02.zza(r3 >>> 4);
        r2[r3 | 256] = r02.zza(r3 & 15);
        r3 = r3 + 1;
        goto L8
    L10:
        return;
    L5:
        r12 = false;
        goto L6
    }

    public zzrv() {
    }

    public static zzrv zzi() {
        return zza;
    }

    public static zzrv zzj() {
        return zzb;
    }

    public abstract void zza(Appendable r1, byte[] r2, int r3, int r4);

    public abstract int zzb(byte[] r1, CharSequence r2);

    public abstract int zzc(int r1);

    public abstract int zze(int r1);

    public abstract CharSequence zzf(CharSequence r1);

    public final String zzg(byte[] r3, int r4, int r5) {
        zzoq.zzg(0, r5, r3.length);
        StringBuilder r42 = new StringBuilder(zzc(r5));
        zza(r42, r3, 0, r5);     // Catch: IOException -> L6
        return r42.toString();
    L6:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    }

    public final byte[] zzh(CharSequence r3) {
        CharSequence r32 = zzf(r3);     // Catch: zzrt -> L7
        int r0 = zze(r32.length());     // Catch: zzrt -> L7
        byte[] r1 = new byte[r0];     // Catch: zzrt -> L7
        int r2 = zzb(r1, r32);     // Catch: zzrt -> L7
        if (r2 != r0) goto L5;
        return r1;
    L5:
        byte[] r33 = new byte[r2];     // Catch: zzrt -> L7
        System.arraycopy(r1, 0, r33, 0, r2);     // Catch: zzrt -> L7
        return r33;
    L7:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }
}
