package com.google.android.gms.internal.clearcut;

import com.android.dx.io.Opcodes;
import com.google.android.gms.internal.vision.zzmj$$ExternalSyntheticBUOutline1;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
abstract class zzfg {
    public zzfg() {
    }

    public static void zzc(CharSequence r8, ByteBuffer r9) {
        int r0 = r8.length();
        int r1 = r9.position();
        int r2 = 0;
    L4:
        if (r2 >= r0) goto L8;
        char r4 = r8.charAt(r2);     // Catch: IndexOutOfBoundsException -> L56
        if (r4 >= 128) goto L8;
        r9.put(r1 + r2, (byte) r4);     // Catch: IndexOutOfBoundsException -> L56
        r2 = r2 + 1;     // Catch: IndexOutOfBoundsException -> L56
    L53:
        int r02 = r9.position() + Math.max(r2, (r1 - r9.position()) + 1);
        zzmj$$ExternalSyntheticBUOutline1.m(r8.charAt(r2), r02);
        return;
    L8:
        if (r2 != r0) goto L11;
        r9.position(r1 + r2);     // Catch: IndexOutOfBoundsException -> L56
        return;
    L11:
        r1 = r1 + r2;     // Catch: IndexOutOfBoundsException -> L56
    L12:
        if (r2 >= r0) goto L51;
        char r42 = r8.charAt(r2);     // Catch: IndexOutOfBoundsException -> L56
        if (r42 >= 128) goto L18;
        r9.put(r1, (byte) r42);     // Catch: IndexOutOfBoundsException -> L56
    L50:
        r2 = r2 + 1;     // Catch: IndexOutOfBoundsException -> L56
        r1 = r1 + 1;     // Catch: IndexOutOfBoundsException -> L56
        goto L12
    L18:
        if (r42 >= 2048) goto L24;
        int r5 = r1 + 1;
        r9.put(r1, (byte) ((r42 >>> 6) | 192));     // Catch: IndexOutOfBoundsException -> L22
        r9.put(r5, (byte) ((r42 & '?') | 128));     // Catch: IndexOutOfBoundsException -> L22
        r1 = r5;
    L22:
        r1 = r5;
        goto L53
    L24:
        if (r42 >= 55296) goto L26;
    L46:
        int r52 = r1 + 1;
        r9.put(r1, (byte) ((r42 >>> '\f') | Opcodes.SHL_INT_LIT8));     // Catch: IndexOutOfBoundsException -> L22
        r1 = r1 + 2;
        r9.put(r52, (byte) (((r42 >>> 6) & 63) | 128));     // Catch: IndexOutOfBoundsException -> L56
        r9.put(r1, (byte) ((r42 & '?') | 128));     // Catch: IndexOutOfBoundsException -> L56
        goto L50
    L26:
        if (57343 < r42) goto L46;
        int r53 = r2 + 1;
        if (r53 == r0) goto L45;
        char r22 = r8.charAt(r53);     // Catch: IndexOutOfBoundsException -> L55
        if (Character.isSurrogatePair(r42, r22) == false) goto L43;
        int r23 = Character.toCodePoint(r42, r22);     // Catch: IndexOutOfBoundsException -> L55
        int r43 = r1 + 1;
        r9.put(r1, (byte) ((r23 >>> 18) | 240));     // Catch: IndexOutOfBoundsException -> L42
        int r6 = r1 + 2;
        r9.put(r43, (byte) (((r23 >>> 12) & 63) | 128));     // Catch: IndexOutOfBoundsException -> L41
        r1 = r1 + 3;
        r9.put(r6, (byte) (((r23 >>> 6) & 63) | 128));     // Catch: IndexOutOfBoundsException -> L55
        r9.put(r1, (byte) ((r23 & 63) | 128));     // Catch: IndexOutOfBoundsException -> L55
        r2 = r53;
    L41:
        r2 = r53;
        r1 = r6;
    L42:
        r1 = r43;
        goto L40
    L43:
        r2 = r53;
    L40:
        r2 = r53;
    L45:
        throw new zzfi(r2, r0);     // Catch: IndexOutOfBoundsException -> L56
    L51:
        r9.position(r1);     // Catch: IndexOutOfBoundsException -> L56
    }

    public abstract int zzb(int r1, byte[] r2, int r3, int r4);

    public abstract int zzb(CharSequence r1, byte[] r2, int r3, int r4);

    public abstract void zzb(CharSequence r1, ByteBuffer r2);

    public final boolean zze(byte[] r2, int r3, int r4) {
        if (zzb(0, r2, r3, r4) != 0) goto L6;
        return true;
    L6:
        return false;
    }
}
