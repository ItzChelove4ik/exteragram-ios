package j$.sun.nio.cs;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import kotlin.UByte;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class a extends CharsetDecoder {
    static {
    }

    public a(c r2) {
        super(r2, 1.0f, 1.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.nio.ByteBuffer] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.nio.CharBuffer] */
    /* JADX WARN: Type inference failed for: r8v7, types: [java.nio.ByteBuffer] */
    @Override // java.nio.charset.CharsetDecoder
    public final CoderResult decodeLoop(ByteBuffer r8, CharBuffer r9) {
        if (r8.hasArray() == true) goto L5;
    L31:
        int r7 = r8.position();
    L47:
    L39:
        th = move-exception;
        ByteBuffer r72 = (ByteBuffer) r8.position(r7);
        throw th;
    L33:
        if (r8.hasRemaining() == false) goto L43;
        byte r0 = r8.get();     // Catch: Throwable -> L39
        if (r9.hasRemaining() == false) goto L36;
        r9.put((char) (r0 & UByte.MAX_VALUE));     // Catch: Throwable -> L39
        r7 = r7 + 1;     // Catch: Throwable -> L39
        goto L47
    L36:
        CoderResult r92 = CoderResult.OVERFLOW;     // Catch: Throwable -> L39
    L37:
        ByteBuffer r73 = (ByteBuffer) r8.position(r7);
        return r92;
    L43:
        r92 = CoderResult.UNDERFLOW;     // Catch: Throwable -> L39
        goto L37
    L5:
        if (r9.hasArray() == false) goto L31;
        byte[] r74 = r8.array();
        int r02 = r8.arrayOffset();
        int r1 = r8.position() + r02;
        int r03 = r8.arrayOffset();
        int r2 = r8.limit() + r03;
        if (r1 <= r2) goto L10;
        r1 = r2;
    L10:
        char[] r04 = r9.array();
        int r3 = r9.arrayOffset();
        int r4 = r9.position() + r3;
        int r32 = r9.arrayOffset();
        int r5 = r9.limit() + r32;
        if (r4 <= r5) goto L14;
        r4 = r5;
    L14:
        if (r1 >= r2) goto L27;
        byte r33 = r74[r1];     // Catch: Throwable -> L20
        if (r4 >= r5) goto L17;
        int r6 = r4 + 1;
        r04[r4] = (char) (r33 & UByte.MAX_VALUE);     // Catch: Throwable -> L25
        r1 = r1 + 1;
        r4 = r6;
    L25:
        th = th;
        r4 = r6;
    L29:
        ByteBuffer r82 = (ByteBuffer) r8.position(r1 - r8.arrayOffset());
        CharBuffer r83 = (CharBuffer) r9.position(r4 - r9.arrayOffset());
        throw th;
    L17:
        CoderResult r75 = CoderResult.OVERFLOW;     // Catch: Throwable -> L20
    L18:
        r1 = r1 - r8.arrayOffset();
        ByteBuffer r84 = (ByteBuffer) r8.position(r1);
        r4 = r4 - r9.arrayOffset();
        r8 = (CharBuffer) r9.position(r4);
        return r75;
    L20:
        th = th;
        goto L29
    L27:
        r75 = CoderResult.UNDERFLOW;     // Catch: Throwable -> L20
        goto L18
    }
}
