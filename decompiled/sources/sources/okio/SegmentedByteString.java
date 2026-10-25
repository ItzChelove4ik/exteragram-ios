package okio;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import org.mvel2.MVEL;

@Metadata(d1 = {"\u0000L\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\u001a'\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0013\u0010\b\u001a\u00020\u0007*\u00020\u0007H\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u0013\u0010\b\u001a\u00020\n*\u00020\nH\u0000¢\u0006\u0004\b\b\u0010\u000b\u001a\u0013\u0010\b\u001a\u00020\u0000*\u00020\u0000H\u0000¢\u0006\u0004\b\b\u0010\f\u001a7\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\nH\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0013\u0010\u0017\u001a\u00020\u0016*\u00020\u0015H\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0013\u0010\u0017\u001a\u00020\u0016*\u00020\nH\u0000¢\u0006\u0004\b\u0017\u0010\u0019\u001a\u0017\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001b\u0010\u001c\u001a\u00020\n*\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\nH\u0000¢\u0006\u0004\b\u001c\u0010 \u001a\u001b\u0010\u001c\u001a\u00020\n*\u00020\r2\u0006\u0010!\u001a\u00020\nH\u0000¢\u0006\u0004\b\u001c\u0010\"\"\u001a\u0010#\u001a\u00020\u001a8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u001a\u0010'\u001a\u00020\n8\u0000X\u0080D¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*¨\u0006+"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "size", "offset", "byteCount", _UrlKt.FRAGMENT_ENCODE_SET, "checkOffsetAndCount", "(JJJ)V", _UrlKt.FRAGMENT_ENCODE_SET, "reverseBytes", "(S)S", _UrlKt.FRAGMENT_ENCODE_SET, "(I)I", "(J)J", _UrlKt.FRAGMENT_ENCODE_SET, "a", "aOffset", "b", "bOffset", _UrlKt.FRAGMENT_ENCODE_SET, "arrayRangeEquals", "([BI[BII)Z", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "toHexString", "(B)Ljava/lang/String;", "(I)Ljava/lang/String;", "Lokio/Buffer$UnsafeCursor;", "unsafeCursor", "resolveDefaultParameter", "(Lokio/Buffer$UnsafeCursor;)Lokio/Buffer$UnsafeCursor;", "Lokio/ByteString;", "position", "(Lokio/ByteString;I)I", "sizeParam", "([BI)I", "DEFAULT__new_UnsafeCursor", "Lokio/Buffer$UnsafeCursor;", "getDEFAULT__new_UnsafeCursor", "()Lokio/Buffer$UnsafeCursor;", "DEFAULT__ByteString_size", "I", "getDEFAULT__ByteString_size", "()I", "okio"}, k = 2, mv = {2, 2, 0}, xi = 48)
@JvmName(name = "-SegmentedByteString")
@SourceDebugExtension({"SMAP\nUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,185:1\n67#1:186\n73#1:187\n*S KotlinDebug\n*F\n+ 1 Util.kt\nokio/-SegmentedByteString\n*L\n105#1:186\n106#1:187\n*E\n"})
/* renamed from: okio.-SegmentedByteString, reason: invalid class name */
/* loaded from: classes.dex */
public abstract class SegmentedByteString {
    private static final int DEFAULT__ByteString_size = 0;
    private static final Buffer.UnsafeCursor DEFAULT__new_UnsafeCursor = null;

    static {
        DEFAULT__new_UnsafeCursor = new Buffer.UnsafeCursor();
        DEFAULT__ByteString_size = -1234567890;
    }

    public static final boolean arrayRangeEquals(byte[] r4, int r5, byte[] r6, int r7, int r8) {
        int r1 = 0;
    L3:
        if (r1 >= r8) goto L8;
        if (r4[r1 + r5] != r6[r1 + r7]) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return false;
    L8:
        return true;
    }

    public static final void checkOffsetAndCount(long r4, long r6, long r8) {
        if ((r6 | r8) < 0) goto L10;
        if (r6 > r4) goto L10;
        if ((r4 - r6) < r8) goto L10;
        return;
    L10:
        throw new ArrayIndexOutOfBoundsException("size=" + r4 + " offset=" + r6 + " byteCount=" + r8);
    }

    public static final int getDEFAULT__ByteString_size() {
        return DEFAULT__ByteString_size;
    }

    public static final Buffer.UnsafeCursor getDEFAULT__new_UnsafeCursor() {
        return DEFAULT__new_UnsafeCursor;
    }

    public static final int resolveDefaultParameter(ByteString r1, int r2) {
        if (r2 == DEFAULT__ByteString_size) goto L5;
        return r2;
    L5:
        return r1.size();
    }

    public static final int resolveDefaultParameter(byte[] r1, int r2) {
        if (r2 == DEFAULT__ByteString_size) goto L5;
        return r2;
    L5:
        return r1.length;
    }

    public static final Buffer.UnsafeCursor resolveDefaultParameter(Buffer.UnsafeCursor r1) {
        if (r1 == DEFAULT__new_UnsafeCursor) goto L5;
        return r1;
    L5:
        return new Buffer.UnsafeCursor();
    }

    public static final int reverseBytes(int r2) {
        int r0 = ((((-16777216) & r2) >>> 24) | ((16711680 & r2) >>> 8)) | ((65280 & r2) << 8);
        return ((r2 & 255) << 24) | r0;
    }

    public static final long reverseBytes(long r8) {
        long r0 = ((((((((-72057594037927936L) & r8) >>> 56) | ((71776119061217280L & r8) >>> 40)) | ((280375465082880L & r8) >>> 24)) | ((1095216660480L & r8) >>> 8)) | ((4278190080L & r8) << 8)) | ((16711680 & r8) << 24)) | ((65280 & r8) << 40);
        return ((r8 & 255) << 56) | r0;
    }

    public static final short reverseBytes(short r1) {
        int r0 = (65280 & r1) >>> 8;
        return (short) (((r1 & 255) << 8) | r0);
    }

    public static final String toHexString(byte r3) {
        return StringsKt.concatToString(new char[]{okio.internal.ByteString.getHEX_DIGIT_CHARS()[(r3 >> 4) & 15], okio.internal.ByteString.getHEX_DIGIT_CHARS()[r3 & 15]});
    }

    public static final String toHexString(int r10) {
        if (r10 != 0) goto L5;
        return MVEL.VERSION_SUB;
    L5:
        int r9 = 0;
        char[] r8 = {okio.internal.ByteString.getHEX_DIGIT_CHARS()[(r10 >> 28) & 15], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(r10 >> 24) & 15], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(r10 >> 20) & 15], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(r10 >> 16) & 15], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(r10 >> 12) & 15], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(r10 >> 8) & 15], okio.internal.ByteString.getHEX_DIGIT_CHARS()[(r10 >> 4) & 15], okio.internal.ByteString.getHEX_DIGIT_CHARS()[r10 & 15]};
    L6:
        if (r9 >= 8) goto L11;
        if (r8[r9] != '0') goto L11;
        r9 = r9 + 1;
    L11:
        return StringsKt.concatToString(r8, r9, 8);
    }
}
