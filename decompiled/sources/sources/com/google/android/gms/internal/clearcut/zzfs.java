package com.google.android.gms.internal.clearcut;

import com.android.dex.EncodedValueReader$$ExternalSyntheticBUOutline0;
import com.google.android.gms.cast.internal.zzar$$ExternalSyntheticBUOutline0;
import com.google.android.gms.cast.internal.zzar$$ExternalSyntheticBUOutline1;
import com.google.android.gms.vision.Frame$Builder$$ExternalSyntheticBUOutline0;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

/* loaded from: classes4.dex */
public final class zzfs {
    private final ByteBuffer zzgd;
    private zzbn zzrh;
    private int zzri;

    private zzfs(ByteBuffer r1) {
        this.zzgd = r1;
        r1.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzfs(byte[] r1, int r2, int r3) {
        this(ByteBuffer.wrap(r1, r2, r3));
    }

    private static int zza(CharSequence r9) {
        int r0 = r9.length();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L7;
        if (r9.charAt(r2) >= 128) goto L7;
        r2 = r2 + 1;
    L7:
        int r3 = r0;
    L8:
        if (r2 >= r0) goto L28;
        char r4 = r9.charAt(r2);
        if (r4 >= 2048) goto L12;
        r3 = r3 + ((127 - r4) >>> 31);
        r2 = r2 + 1;
        goto L8
    L12:
        int r42 = r9.length();
        int r6 = 0;
    L13:
        if (r2 >= r42) goto L27;
        char r7 = r9.charAt(r2);
        if (r7 >= 2048) goto L17;
        r6 = r6 + ((127 - r7) >>> 31);
    L26:
        r2 = r2 + 1;
        goto L13
    L17:
        r6 = r6 + 2;
        if (55296 > r7) goto L26;
        if (r7 > 57343) goto L26;
        if (Character.codePointAt(r9, r2) < 65536) goto L24;
        r2 = r2 + 1;
        goto L26
    L24:
        Frame$Builder$$ExternalSyntheticBUOutline0.m(39, "Unpaired surrogate at index ", r2);
        return 0;
    L27:
        r3 = r3 + r6;
    L28:
        if (r3 < r0) goto L30;
        return r3;
    L30:
        zzar$$ExternalSyntheticBUOutline0.m(54, "UTF-8 length does not fit in int: ", r3 + 4294967296L);
        return 0;
    }

    private final void zzao(int r3) {
        byte r32 = (byte) r3;
        boolean r0 = this.zzgd.hasRemaining();
        ByteBuffer r1 = this.zzgd;
        if (r0 == false) goto L7;
        r1.put(r32);
        return;
    L7:
        throw new zzft(r1.position(), this.zzgd.limit());
    }

    private final void zzap(int r2) {
    L3:
        if ((r2 & (-128)) == 0) goto L4;
        zzao((r2 & 127) | 128);
        r2 = r2 >>> 7;
        goto L3
    L4:
        zzao(r2);
    }

    public static int zzb(int r0, String r1) {
        return zzr(r0) + zzh(r1);
    }

    public static int zzb(int r0, byte[] r1) {
        return zzr(r0) + zzh(r1);
    }

    public static int zzd(int r0, long r1) {
        return zzr(r0) + zzo(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v20 */
    private static void zzd(CharSequence r17, ByteBuffer r18) {
        if (r18.isReadOnly() == true) goto L71;
        char r5 = 57343;
        int r8 = 0;
        if (r18.hasArray() == true) goto L72;
        int r2 = r17.length();
    L49:
        if (r8 >= r2) goto L69;
        char r52 = r17.charAt(r8);
        char r53 = r52;
        if (r52 >= 128) goto L53;
    L52:
        r18.put((byte) r53);
    L68:
        r8 = r8 + 1;
        goto L49
    L53:
        if (r52 >= 2048) goto L56;
        r18.put((byte) ((r52 >>> 6) | 960));
        r53 = (r52 & '?') | 128;
        goto L52
    L56:
        if (r52 < 55296) goto L67;
        if (57343 < r52) goto L67;
        int r11 = r8 + 1;
        if (r11 == r17.length()) goto L65;
        char r82 = r17.charAt(r11);
        if (Character.isSurrogatePair(r52, r82) == false) goto L64;
        int r54 = Character.toCodePoint(r52, r82);
        r18.put((byte) ((r54 >>> 18) | 240));
        r18.put((byte) (((r54 >>> 12) & 63) | 128));
        r18.put((byte) (((r54 >>> 6) & 63) | 128));
        r18.put((byte) ((r54 & 63) | 128));
        r8 = r11;
        goto L68
    L64:
        r8 = r11;
    L65:
        zzar$$ExternalSyntheticBUOutline1.m(39, "Unpaired surrogate at index ", r8 - 1);
        return;
    L67:
        r18.put((byte) ((r52 >>> '\f') | 480));
        r18.put((byte) (((r52 >>> 6) & 63) | 128));
        r18.put((byte) ((r52 & '?') | 128));
        goto L68
    L69:
        return;
    L72:
        byte[] r22 = r18.array();     // Catch: ArrayIndexOutOfBoundsException -> L45
        int r10 = r18.arrayOffset() + r18.position();     // Catch: ArrayIndexOutOfBoundsException -> L45
        int r112 = r18.remaining();     // Catch: ArrayIndexOutOfBoundsException -> L45
        int r12 = r17.length();     // Catch: ArrayIndexOutOfBoundsException -> L45
        int r113 = r112 + r10;     // Catch: ArrayIndexOutOfBoundsException -> L45
    L7:
        if (r8 >= r12) goto L13;
        int r13 = r8 + r10;     // Catch: ArrayIndexOutOfBoundsException -> L45
        if (r13 >= r113) goto L13;
        char r14 = r17.charAt(r8);     // Catch: ArrayIndexOutOfBoundsException -> L45
        if (r14 >= 128) goto L13;
        r22[r13] = (byte) r14;     // Catch: ArrayIndexOutOfBoundsException -> L45
        r8 = r8 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L45
    L13:
        if (r8 != r12) goto L15;
        int r102 = r10 + r12;     // Catch: ArrayIndexOutOfBoundsException -> L45
    L43:
        r18.position(r102 - r18.arrayOffset());     // Catch: ArrayIndexOutOfBoundsException -> L45
        return;
    L15:
        r102 = r10 + r8;     // Catch: ArrayIndexOutOfBoundsException -> L45
    L16:
        if (r8 >= r12) goto L43;
        char r132 = r17.charAt(r8);     // Catch: ArrayIndexOutOfBoundsException -> L45
        if (r132 >= 128) goto L21;
        if (r102 >= r113) goto L21;
        r22[r102] = (byte) r132;     // Catch: ArrayIndexOutOfBoundsException -> L45
        r102 = r102 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L45
    L37:
        r8 = r8 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L45
        r5 = 57343;
    L21:
        if (r132 < 2048) goto L23;
    L25:
        if (r132 < 55296) goto L28;
        if (r5 < r132) goto L28;
    L31:
        if (r102 > (r113 - 4)) goto L41;
        int r55 = r8 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L45
        if (r55 == r17.length()) goto L39;
        char r83 = r17.charAt(r55);     // Catch: ArrayIndexOutOfBoundsException -> L45
        if (Character.isSurrogatePair(r132, r83) == false) goto L38;
        int r84 = Character.toCodePoint(r132, r83);     // Catch: ArrayIndexOutOfBoundsException -> L45
        r22[r102] = (byte) ((r84 >>> 18) | 240);     // Catch: ArrayIndexOutOfBoundsException -> L45
        r22[r102 + 1] = (byte) (((r84 >>> 12) & 63) | 128);     // Catch: ArrayIndexOutOfBoundsException -> L45
        int r133 = r102 + 3;     // Catch: ArrayIndexOutOfBoundsException -> L45
        r22[r102 + 2] = (byte) (((r84 >>> 6) & 63) | 128);     // Catch: ArrayIndexOutOfBoundsException -> L45
        r102 = r102 + 4;     // Catch: ArrayIndexOutOfBoundsException -> L45
        r22[r133] = (byte) ((r84 & 63) | 128);     // Catch: ArrayIndexOutOfBoundsException -> L45
        r8 = r55;
        goto L37
    L38:
        r8 = r55;
    L39:
        StringBuilder r1 = new StringBuilder(39);     // Catch: ArrayIndexOutOfBoundsException -> L45
        r1.append("Unpaired surrogate at index ");     // Catch: ArrayIndexOutOfBoundsException -> L45
        r1.append(r8 - 1);     // Catch: ArrayIndexOutOfBoundsException -> L45
        throw new IllegalArgumentException(r1.toString());     // Catch: ArrayIndexOutOfBoundsException -> L45
    L41:
        StringBuilder r15 = new StringBuilder(37);     // Catch: ArrayIndexOutOfBoundsException -> L45
        r15.append("Failed writing ");     // Catch: ArrayIndexOutOfBoundsException -> L45
        r15.append(r132);     // Catch: ArrayIndexOutOfBoundsException -> L45
        r15.append(" at index ");     // Catch: ArrayIndexOutOfBoundsException -> L45
        r15.append(r102);     // Catch: ArrayIndexOutOfBoundsException -> L45
        throw new ArrayIndexOutOfBoundsException(r15.toString());     // Catch: ArrayIndexOutOfBoundsException -> L45
    L28:
        if (r102 > (r113 - 3)) goto L31;
        r22[r102] = (byte) ((r132 >>> '\f') | 480);     // Catch: ArrayIndexOutOfBoundsException -> L45
        int r152 = r102 + 2;     // Catch: ArrayIndexOutOfBoundsException -> L45
        r22[r102 + 1] = (byte) (((r132 >>> 6) & 63) | 128);     // Catch: ArrayIndexOutOfBoundsException -> L45
        r102 = r102 + 3;     // Catch: ArrayIndexOutOfBoundsException -> L45
        r22[r152] = (byte) ((r132 & '?') | 128);     // Catch: ArrayIndexOutOfBoundsException -> L45
        goto L37
    L23:
        if (r102 > (r113 - 2)) goto L25;
        int r142 = r102 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L45
        r22[r102] = (byte) ((r132 >>> 6) | 960);     // Catch: ArrayIndexOutOfBoundsException -> L45
        r102 = r102 + 2;     // Catch: ArrayIndexOutOfBoundsException -> L45
        r22[r142] = (byte) ((r132 & '?') | 128);     // Catch: ArrayIndexOutOfBoundsException -> L45
    L45:
        e = move-exception;
        BufferOverflowException r16 = new BufferOverflowException();
        r16.initCause(e);
        throw r16;
    L71:
        throw new ReadOnlyBufferException();
    }

    public static int zzh(String r1) {
        int r12 = zza(r1);
        return zzz(r12) + r12;
    }

    public static int zzh(byte[] r1) {
        return zzz(r1.length) + r1.length;
    }

    public static zzfs zzh(byte[] r1, int r2, int r3) {
        return new zzfs(r1, 0, r3);
    }

    public static long zzj(long r3) {
        long r0 = r3 << 1;
        return (r3 >> 63) ^ r0;
    }

    public static int zzo(long r4) {
        if (((-128) & r4) != 0) goto L7;
        return 1;
    L7:
        if (((-16384) & r4) != 0) goto L11;
        return 2;
    L11:
        if (((-2097152) & r4) != 0) goto L15;
        return 3;
    L15:
        if (((-268435456) & r4) != 0) goto L19;
        return 4;
    L19:
        if (((-34359738368L) & r4) != 0) goto L23;
        return 5;
    L23:
        if (((-4398046511104L) & r4) != 0) goto L27;
        return 6;
    L27:
        if (((-562949953421312L) & r4) != 0) goto L31;
        return 7;
    L31:
        if (((-72057594037927936L) & r4) != 0) goto L35;
        return 8;
    L35:
        if ((r4 & Long.MIN_VALUE) != 0) goto L38;
        return 9;
    L38:
        return 10;
    }

    public static int zzr(int r0) {
        return zzz(r0 << 3);
    }

    public static int zzs(int r0) {
        if (r0 >= 0) goto L4;
        return 10;
    L4:
        return zzz(r0);
    }

    private static int zzz(int r1) {
        if ((r1 & (-128)) != 0) goto L7;
        return 1;
    L7:
        if ((r1 & (-16384)) != 0) goto L11;
        return 2;
    L11:
        if (((-2097152) & r1) != 0) goto L15;
        return 3;
    L15:
        if ((r1 & (-268435456)) != 0) goto L18;
        return 4;
    L18:
        return 5;
    }

    public final void zza(int r4, String r5) {
        zzb(r4, 2);
        int r42 = zzz(r5.length());     // Catch: BufferOverflowException -> L9
        if (r42 != zzz(r5.length() * 3)) goto L13;
        int r0 = this.zzgd.position();     // Catch: BufferOverflowException -> L9
        if (this.zzgd.remaining() < r42) goto L12;
        this.zzgd.position(r0 + r42);     // Catch: BufferOverflowException -> L9
        zzd(r5, this.zzgd);     // Catch: BufferOverflowException -> L9
        int r52 = this.zzgd.position();     // Catch: BufferOverflowException -> L9
        this.zzgd.position(r0);     // Catch: BufferOverflowException -> L9
        zzap((r52 - r0) - r42);     // Catch: BufferOverflowException -> L9
        this.zzgd.position(r52);     // Catch: BufferOverflowException -> L9
        return;
    L12:
        throw new zzft(r0 + r42, this.zzgd.limit());     // Catch: BufferOverflowException -> L9
    L13:
        zzap(zza(r5));     // Catch: BufferOverflowException -> L9
        zzd(r5, this.zzgd);     // Catch: BufferOverflowException -> L9
        return;
    L9:
        e = move-exception;
        zzft r53 = new zzft(this.zzgd.position(), this.zzgd.limit());
        r53.initCause(e);
        throw r53;
    }

    public final void zza(int r3, byte[] r4) {
        zzb(r3, 2);
        zzap(r4.length);
        int r32 = r4.length;
        int r0 = this.zzgd.remaining();
        ByteBuffer r1 = this.zzgd;
        if (r0 < r32) goto L7;
        r1.put(r4, 0, r32);
        return;
    L7:
        throw new zzft(r1.position(), this.zzgd.limit());
    }

    public final void zzb(int r1, int r2) {
        zzap((r1 << 3) | r2);
    }

    public final void zzb(int r2, boolean r3) {
        zzb(25, 0);
        byte r22 = r3 ? 1 : 0;
        boolean r32 = this.zzgd.hasRemaining();
        ByteBuffer r0 = this.zzgd;
        if (r32 == false) goto L7;
        r0.put(r22);
        return;
    L7:
        throw new zzft(r0.position(), this.zzgd.limit());
    }

    public final void zzc(int r2, int r3) {
        zzb(r2, 0);
        if (r3 < 0) goto L6;
        zzap(r3);
        return;
    L6:
        zzn(r3);
    }

    public final void zze(int r6, zzdo r7) {
        if (this.zzrh != null) goto L7;
        this.zzrh = zzbn.zza(this.zzgd);
    L5:
        this.zzri = this.zzgd.position();
    L9:
        zzbn r0 = this.zzrh;
        r0.zza(r6, r7);
        r0.flush();
        this.zzri = this.zzgd.position();
        return;
    L7:
        if (this.zzri == this.zzgd.position()) goto L9;
        this.zzrh.write(this.zzgd.array(), this.zzri, this.zzgd.position() - this.zzri);
        goto L5
    }

    public final void zzem() {
        if (this.zzgd.remaining() != 0) goto L5;
        return;
    L5:
        EncodedValueReader$$ExternalSyntheticBUOutline0.m("Did not write as much data as expected, %s bytes remaining.", new Object[]{Integer.valueOf(this.zzgd.remaining())});
    }

    public final void zzi(int r2, long r3) {
        zzb(r2, 0);
        zzn(r3);
    }

    public final void zzn(long r5) {
    L3:
        if (((-128) & r5) == 0) goto L4;
        zzao((((int) r5) & 127) | 128);
        r5 = r5 >>> 7;
        goto L3
    L4:
        zzao((int) r5);
    }
}
