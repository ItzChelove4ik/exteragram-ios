package j$.sun.nio.cs;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class b extends CharsetEncoder {

    /* renamed from: a, reason: collision with root package name */
    public final e f273a;

    static {
    }

    public b(c r2) {
        super(r2, 1.0f, 1.0f);
        e r22 = new e();
        r22.f276a = CoderResult.UNDERFLOW;
        this.f273a = r22;
    }

    public static int a(char[] r3, int r4, byte[] r5, int r6, int r7) {
        int r0 = 0;
        if (r7 > 0) goto L5;
        return 0;
    L5:
        Objects.requireNonNull(r3);
        Objects.requireNonNull(r5);
        if (r4 < 0) goto L33;
        if (r4 >= r3.length) goto L33;
        if (r6 < 0) goto L31;
        if (r6 >= r5.length) goto L31;
        int r1 = (r4 + r7) - 1;
        if (r1 < 0) goto L29;
        if (r1 >= r3.length) goto L29;
        int r12 = (r6 + r7) - 1;
        if (r12 < 0) goto L27;
        if (r12 >= r5.length) goto L27;
    L20:
        if (r0 >= r7) goto L25;
        int r13 = r4 + 1;
        char r42 = r3[r4];
        if (r42 > 255) goto L25;
        r5[r6] = (byte) r42;
        r0 = r0 + 1;
        r4 = r13;
        r6 = r6 + 1;
    L25:
        return r0;
    L27:
        throw new ArrayIndexOutOfBoundsException(r12);
    L29:
        throw new ArrayIndexOutOfBoundsException(r1);
    L31:
        throw new ArrayIndexOutOfBoundsException(r6);
    L33:
        throw new ArrayIndexOutOfBoundsException(r4);
    }

    @Override // java.nio.charset.CharsetEncoder
    public final boolean canEncode(char r1) {
        if (r1 > 255) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.nio.charset.CharsetEncoder
    public final CoderResult encodeLoop(CharBuffer r12, ByteBuffer r13) {
        int r1 = 2;
        if (r12.hasArray() == true) goto L5;
    L44:
        int r0 = r12.position();
    L76:
    L54:
        th = move-exception;
        CharBuffer r122 = (CharBuffer) r12.position(r0);
        throw th;
    L46:
        if (r12.hasRemaining() == false) goto L71;
        char r3 = r12.get();     // Catch: Throwable -> L54
        if (r3 > 255) goto L58;
        if (r13.hasRemaining() == false) goto L51;
        r13.put((byte) r3);     // Catch: Throwable -> L54
        r0 = r0 + 1;     // Catch: Throwable -> L54
        goto L76
    L51:
        CoderResult r11 = CoderResult.OVERFLOW;     // Catch: Throwable -> L54
        CharBuffer r123 = (CharBuffer) r12.position(r0);
        return r11;
    L58:
        int r132 = this.f273a.a(r3, r12);     // Catch: Throwable -> L54
        e r112 = this.f273a;
        if (r132 >= 0) goto L65;
        CoderResult r113 = r112.f276a;     // Catch: Throwable -> L54
        CharBuffer r124 = (CharBuffer) r12.position(r0);
        return r113;
    L65:
        if (r112.f277b == true) goto L68;
        r1 = 1;
    L68:
        CoderResult r114 = CoderResult.unmappableForLength(r1);     // Catch: Throwable -> L54
        CharBuffer r125 = (CharBuffer) r12.position(r0);
        return r114;
    L71:
        CoderResult r115 = CoderResult.UNDERFLOW;     // Catch: Throwable -> L54
        CharBuffer r126 = (CharBuffer) r12.position(r0);
        return r115;
    L5:
        if (r13.hasArray() == false) goto L44;
        char[] r02 = r12.array();
        int r32 = r12.arrayOffset();
        int r4 = r12.position() + r32;
        int r5 = r12.limit() + r32;
        if (r4 <= r5) goto L10;
        r4 = r5;
    L10:
        byte[] r6 = r13.array();
        int r7 = r13.arrayOffset();
        int r8 = r13.position() + r7;
        int r9 = r13.limit() + r7;
        if (r8 <= r9) goto L14;
        r8 = r9;
    L14:
        int r92 = r9 - r8;
        int r10 = r5 - r4;
        if (r92 < r10) goto L77;
        r92 = r10;
    L77:
        int r62 = a(r02, r4, r6, r8, r92);     // Catch: Throwable -> L26
        int r42 = r4 + r62;     // Catch: Throwable -> L26
        int r82 = r8 + r62;     // Catch: Throwable -> L26
        if (r62 == r92) goto L35;
        int r03 = this.f273a.b(r02[r42], r02, r42, r5);     // Catch: Throwable -> L26
        e r116 = this.f273a;
        if (r03 >= 0) goto L29;
        CoderResult r117 = r116.f276a;     // Catch: Throwable -> L26
        CharBuffer r127 = (CharBuffer) r12.position(r42 - r32);
        ByteBuffer r128 = (ByteBuffer) r13.position(r82 - r7);
        return r117;
    L29:
        if (r116.f277b == true) goto L32;
        r1 = 1;
    L32:
        CoderResult r118 = CoderResult.unmappableForLength(r1);     // Catch: Throwable -> L26
        CharBuffer r129 = (CharBuffer) r12.position(r42 - r32);
        ByteBuffer r1210 = (ByteBuffer) r13.position(r82 - r7);
        return r118;
    L35:
        if (r92 >= r10) goto L39;
        CoderResult r119 = CoderResult.OVERFLOW;     // Catch: Throwable -> L26
        CharBuffer r1211 = (CharBuffer) r12.position(r42 - r32);
        ByteBuffer r1212 = (ByteBuffer) r13.position(r82 - r7);
        return r119;
    L39:
        CoderResult r1110 = CoderResult.UNDERFLOW;     // Catch: Throwable -> L26
        CharBuffer r1213 = (CharBuffer) r12.position(r42 - r32);
        ByteBuffer r1214 = (ByteBuffer) r13.position(r82 - r7);
        return r1110;
    L26:
        th = move-exception;
        CharBuffer r1215 = (CharBuffer) r12.position(r4 - r32);
        ByteBuffer r1216 = (ByteBuffer) r13.position(r8 - r7);
        throw th;
    }

    @Override // java.nio.charset.CharsetEncoder
    public final boolean isLegalReplacement(byte[] r1) {
        return true;
    }
}
