package kotlin.io;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0086\u0080\u0004J\u0012\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\tH\u0082\u0080\u0004J\n\u0010\u001d\u001a\u00020\u0005H\u0082\u0080\u0004J\u001a\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0005H\u0082\u0080\u0004J\u0012\u0010!\u001a\u00020\"2\u0006\u0010\u0019\u001a\u00020\u001aH\u0082\u0080\u0004J\n\u0010#\u001a\u00020\"H\u0082\u0080\u0004J\n\u0010$\u001a\u00020\"H\u0082\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082®\b¢\u0006\u0002\n\u0000R\u000f\u0010\b\u001a\u00020\tX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\u000bX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\f\u001a\u00020\rX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000e\u001a\u00020\u000fX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0010\u001a\u00020\u0011X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\u0012\u001a\u00060\u0013j\u0002`\u0014X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lkotlin/io/LineReader;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "BUFFER_SIZE", _UrlKt.FRAGMENT_ENCODE_SET, "decoder", "Ljava/nio/charset/CharsetDecoder;", "directEOL", _UrlKt.FRAGMENT_ENCODE_SET, "bytes", _UrlKt.FRAGMENT_ENCODE_SET, "chars", _UrlKt.FRAGMENT_ENCODE_SET, "byteBuf", "Ljava/nio/ByteBuffer;", "charBuf", "Ljava/nio/CharBuffer;", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "readLine", _UrlKt.FRAGMENT_ENCODE_SET, "inputStream", "Ljava/io/InputStream;", "charset", "Ljava/nio/charset/Charset;", "decode", "endOfInput", "compactBytes", "decodeEndOfInput", "nBytes", "nChars", "updateCharset", _UrlKt.FRAGMENT_ENCODE_SET, "resetAll", "trimStringBuilder", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConsole.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Console.kt\nkotlin/io/LineReader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,302:1\n1#2:303\n*E\n"})
/* loaded from: classes5.dex */
public final class LineReader {
    private static final int BUFFER_SIZE = 32;
    public static final LineReader INSTANCE = null;
    private static final ByteBuffer byteBuf = null;
    private static final byte[] bytes = null;
    private static final CharBuffer charBuf = null;
    private static final char[] chars = null;
    private static CharsetDecoder decoder;
    private static boolean directEOL;
    private static final StringBuilder sb = null;

    static {
        INSTANCE = new LineReader();
        byte[] r1 = new byte[32];
        bytes = r1;
        char[] r0 = new char[32];
        chars = r0;
        byteBuf = ByteBuffer.wrap(r1);
        charBuf = CharBuffer.wrap(r0);
        sb = new StringBuilder();
    }

    private LineReader() {
    }

    private final int compactBytes() {
        ByteBuffer r2 = byteBuf;
        r2.compact();
        int r0 = r2.position();
        r2.position(0);
        return r0;
    }

    private final int decode(boolean r6) {
    L2:
        CharsetDecoder r0 = decoder;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        ByteBuffer r1 = byteBuf;
        CharBuffer r2 = charBuf;
        CoderResult r02 = r0.decode(r1, r2, r6);
        if (r02.isError() == false) goto L8;
        resetAll();
        r02.throwException();
    L8:
        int r12 = r2.position();
        if (r02.isOverflow() == false) goto L10;
        StringBuilder r03 = sb;
        char[] r3 = chars;
        int r13 = r12 - 1;
        r03.append(r3, 0, r13);
        r2.position(0);
        r2.limit(32);
        r2.put(r3[r13]);
        goto L2
    L10:
        return r12;
    }

    private final int decodeEndOfInput(int r2, int r3) {
        ByteBuffer r0 = byteBuf;
        r0.limit(r2);
        charBuf.position(r3);
        int r1 = decode(true);
        CharsetDecoder r22 = decoder;
        if (r22 != null) goto L5;
        r22 = null;
    L5:
        r22.reset();
        r0.position(0);
        return r1;
    }

    private final void resetAll() {
        CharsetDecoder r1 = decoder;
        if (r1 != null) goto L5;
        r1 = null;
    L5:
        r1.reset();
        byteBuf.position(0);
        sb.setLength(0);
    }

    private final void trimStringBuilder() {
        StringBuilder r1 = sb;
        r1.setLength(32);
        r1.trimToSize();
    }

    private final void updateCharset(Charset r5) {
        decoder = r5.newDecoder();
        ByteBuffer r52 = byteBuf;
        r52.clear();
        CharBuffer r0 = charBuf;
        r0.clear();
        r52.put((byte) 10);
        r52.flip();
        CharsetDecoder r2 = decoder;
        if (r2 != null) goto L5;
        r2 = null;
    L5:
        boolean r3 = false;
        r2.decode(r52, r0, false);
        if (r0.position() == 1) goto L8;
    L10:
        directEOL = r3;
        resetAll();
        return;
    L8:
        if (r0.get(0) != '\n') goto L10;
        r3 = true;
        goto L10
    }

    public final synchronized String readLine(InputStream r10, Charset r11) {
        monitor-enter(this);
        CharsetDecoder r0 = decoder;     // Catch: Throwable -> L10
        if (r0 == null) goto L12;
        if (r0 != null) goto L8;
        r0 = null;
    L8:
        if (Intrinsics.areEqual(r0.charset(), r11) == false) goto L12;
    L13:
        int r02 = 0;
        int r2 = 0;
    L14:
        int r3 = r10.read();     // Catch: Throwable -> L10
        if (r3 == (-1)) goto L17;
        int r7 = r02 + 1;     // Catch: Throwable -> L10
        bytes[r02] = (byte) r3;     // Catch: Throwable -> L10
        if (r3 == 10) goto L30;
        if (r7 == 32) goto L30;
        if (directEOL == false) goto L30;
        r02 = r7;
    L30:
        ByteBuffer r03 = byteBuf;     // Catch: Throwable -> L10
        r03.limit(r7);     // Catch: Throwable -> L10
        charBuf.position(r2);     // Catch: Throwable -> L10
        r2 = decode(false);     // Catch: Throwable -> L10
        if (r2 > 0) goto L33;
    L55:
        r02 = compactBytes();     // Catch: Throwable -> L10
        goto L14
    L33:
        if (chars[r2 - 1] != '\n') goto L55;
        r03.position(0);     // Catch: Throwable -> L10
        int r102 = r2;
    L35:
        if (r102 <= 0) goto L44;
        char[] r04 = chars;     // Catch: Throwable -> L10
        if (r04[r102 - 1] != '\n') goto L44;
        int r1 = r102 - 1;
        if (r1 > 0) goto L41;
    L43:
        r102 = r1;
        goto L44
    L41:
        if (r04[r102 - 2] != '\r') goto L43;
        r102 = r102 - 2;
    L44:
        StringBuilder r05 = sb;     // Catch: Throwable -> L10
        if (r05.length() != 0) goto L49;
        String r06 = new String(chars, 0, r102);     // Catch: Throwable -> L10
        monitor-exit(this);
        return r06;
    L49:
        r05.append(chars, 0, r102);     // Catch: Throwable -> L10
        String r103 = r05.toString();     // Catch: Throwable -> L10
        if (r05.length() <= 32) goto L52;
        trimStringBuilder();     // Catch: Throwable -> L10
    L52:
        r05.setLength(0);     // Catch: Throwable -> L10
        monitor-exit(this);
        return r103;
    L17:
        if (sb.length() != 0) goto L22;
        if (r02 != 0) goto L22;
        if (r2 != 0) goto L22;
        monitor-exit(this);
        return null;
    L22:
        r102 = decodeEndOfInput(r02, r2);     // Catch: Throwable -> L10
    L12:
        updateCharset(r11);     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }
}
