package okio.internal;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.rop.code.RegisterSpec;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.Buffer$$ExternalSyntheticBUOutline3;
import okio.ByteString;
import okio.Options;
import okio.Segment;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.SegmentedByteString;
import okio._JvmPlatformKt;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0016\n\u0002\b\u0003\u001a7\u0010\t\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001b\u0010\u000f\u001a\u00020\u000e*\u00020\u000b2\u0006\u0010\r\u001a\u00020\fH\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a%\u0010\u0014\u001a\u00020\u0002*\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\bH\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0017\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001aA\u0010\u001d\u001a\u00020\f*\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\f2\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u001c\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u001b\u0010!\u001a\u00020\u001f*\u00020\u000b2\u0006\u0010 \u001a\u00020\u001fH\u0000¢\u0006\u0004\b!\u0010\"\"\u001a\u0010#\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)¨\u0006*"}, d2 = {"Lokio/Segment;", "segment", _UrlKt.FRAGMENT_ENCODE_SET, "segmentPos", _UrlKt.FRAGMENT_ENCODE_SET, "bytes", "bytesOffset", "bytesLimit", _UrlKt.FRAGMENT_ENCODE_SET, "rangeEquals", "(Lokio/Segment;I[BII)Z", "Lokio/Buffer;", _UrlKt.FRAGMENT_ENCODE_SET, "newline", _UrlKt.FRAGMENT_ENCODE_SET, "readUtf8Line", "(Lokio/Buffer;J)Ljava/lang/String;", "Lokio/Options;", "options", "selectTruncated", "selectPrefix", "(Lokio/Buffer;Lokio/Options;Z)I", RegisterSpec.PREFIX, "countDigitsIn", "(J)I", "Lokio/ByteString;", "fromIndex", "toIndex", "byteCount", "commonIndexOf", "(Lokio/Buffer;Lokio/ByteString;JJII)J", "Lokio/Buffer$UnsafeCursor;", "unsafeCursor", "commonReadAndWriteUnsafe", "(Lokio/Buffer;Lokio/Buffer$UnsafeCursor;)Lokio/Buffer$UnsafeCursor;", "HEX_DIGIT_BYTES", "[B", "getHEX_DIGIT_BYTES", "()[B", _UrlKt.FRAGMENT_ENCODE_SET, "DigitCountToLargestValue", "[J", "okio"}, k = 2, mv = {2, 2, 0}, xi = 48)
@JvmName(name = "-Buffer")
@SourceDebugExtension({"SMAP\nBuffer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Buffer.kt\nokio/internal/-Buffer\n+ 2 Util.kt\nokio/-SegmentedByteString\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1712:1\n110#1,20:1735\n110#1,20:1768\n110#1:1788\n112#1,18:1790\n110#1,20:1808\n73#2:1713\n73#2:1714\n73#2:1715\n73#2:1716\n73#2:1717\n73#2:1718\n73#2:1719\n73#2:1720\n73#2:1721\n73#2:1722\n73#2:1723\n73#2:1724\n82#2:1725\n82#2:1726\n76#2:1727\n76#2:1728\n76#2:1729\n76#2:1730\n76#2:1731\n76#2:1732\n76#2:1733\n76#2:1734\n85#2:1755\n88#2:1757\n73#2:1758\n73#2:1759\n73#2:1760\n73#2:1761\n73#2:1762\n73#2:1763\n73#2:1764\n73#2:1765\n73#2:1766\n73#2:1767\n88#2:1789\n85#2:1828\n1#3:1756\n*S KotlinDebug\n*F\n+ 1 Buffer.kt\nokio/internal/-Buffer\n*L\n413#1:1735,20\n1262#1:1768,20\n1305#1:1788\n1305#1:1790,18\n1341#1:1808,20\n176#1:1713\n200#1:1714\n319#1:1715\n324#1:1716\n347#1:1717\n348#1:1718\n349#1:1719\n350#1:1720\n356#1:1721\n357#1:1722\n358#1:1723\n359#1:1724\n383#1:1725\n384#1:1726\n390#1:1727\n391#1:1728\n392#1:1729\n393#1:1730\n394#1:1731\n395#1:1732\n396#1:1733\n397#1:1734\n425#1:1755\n858#1:1757\n876#1:1758\n878#1:1759\n882#1:1760\n884#1:1761\n888#1:1762\n890#1:1763\n894#1:1764\n896#1:1765\n916#1:1766\n919#1:1767\n1317#1:1789\n1658#1:1828\n*E\n"})
/* renamed from: okio.internal.-Buffer, reason: invalid class name */
/* loaded from: classes.dex */
public abstract class Buffer {
    private static final long[] DigitCountToLargestValue = null;
    private static final byte[] HEX_DIGIT_BYTES = null;

    static {
        HEX_DIGIT_BYTES = _JvmPlatformKt.asUtf8ToByteArray("0123456789abcdef");
        DigitCountToLargestValue = new long[]{-1, 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, 9999999999L, 99999999999L, 999999999999L, 9999999999999L, 99999999999999L, 999999999999999L, 9999999999999999L, 99999999999999999L, 999999999999999999L, LongCompanionObject.MAX_VALUE};
    }

    public static final /* synthetic */ int access$countDigitsIn(long r0) {
        return countDigitsIn(r0);
    }

    public static final long commonIndexOf(okio.Buffer r19, ByteString r20, long r21, long r23, int r25, int r26) {
        long r0 = r21;
        long r2 = r23;
        long r11 = r26;
        SegmentedByteString.checkOffsetAndCount(r20.size(), r25, r11);
        long r6 = 0;
        if (r26 > 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("byteCount == 0");
        return 0;
    L5:
        if (r0 >= 0) goto L7;
        Buffer$$ExternalSyntheticBUOutline3.m("fromIndex < 0: ", r0);
        return 0;
    L7:
        if (r0 > r2) goto L55;
        if (r2 <= r19.size()) goto L11;
        r2 = r19.size();
    L11:
        long r9 = -1;
        if (r0 != r2) goto L14;
        return -1;
    L14:
        Segment r13 = r19.head;
        if (r13 != null) goto L18;
        return -1;
    L18:
        if ((r19.size() - r0) >= r0) goto L38;
        r6 = r19.size();
    L21:
        if (r6 <= r0) goto L23;
        r13 = r13.prev;
        r6 = r6 - (r13.limit - r13.pos);
        r9 = r9;
        goto L21
    L23:
        long r232 = r9;
        byte[] r92 = r20.internalArray$okio();
        byte r10 = r92[r25];
        long r22 = Math.min(r2, (r19.size() - r11) + 1);
    L25:
        if (r6 >= r22) goto L36;
        byte[] r8 = r13.data;
        int r112 = (int) Math.min(r13.limit, (r13.pos + r22) - r6);
        int r02 = (int) ((r13.pos + r0) - r6);
    L27:
        if (r02 >= r112) goto L35;
        if (r8[r02] != r10) goto L34;
        if (rangeEquals(r13, r02 + 1, r92, r25 + 1, r26) == false) goto L34;
    L33:
        return (r02 - r13.pos) + r6;
    L34:
        r02 = r02 + 1;
        goto L27
    L35:
        r6 = r6 + (r13.limit - r13.pos);
        r13 = r13.next;
        r0 = r6;
        goto L25
    L36:
        return r232;
    L38:
        long r93 = (r13.limit - r13.pos) + r6;
        if (r93 > r0) goto L41;
        r13 = r13.next;
        r6 = r93;
        goto L38
    L41:
        byte[] r94 = r20.internalArray$okio();
        byte r102 = r94[r25];
        long r24 = Math.min(r2, (r19.size() - r11) + 1);
    L43:
        if (r6 >= r24) goto L53;
        byte[] r82 = r13.data;
        int r113 = (int) Math.min(r13.limit, (r13.pos + r24) - r6);
        r02 = (int) ((r13.pos + r0) - r6);
    L45:
        if (r02 >= r113) goto L52;
        if (r82[r02] != r102) goto L51;
        if (rangeEquals(r13, r02 + 1, r94, r25 + 1, r26) == true) goto L33;
    L51:
        r02 = r02 + 1;
        goto L45
    L52:
        r6 = r6 + (r13.limit - r13.pos);
        r13 = r13.next;
        r0 = r6;
        goto L43
    L53:
        return -1;
    L55:
        throw new IllegalArgumentException(("fromIndex > toIndex: " + r0 + " > " + r2).toString());
    }

    public static /* synthetic */ long commonIndexOf$default(okio.Buffer r8, ByteString r9, long r10, long r12, int r14, int r15, int r16, Object r17) {
        if ((r16 & 4) == 0) goto L5;
        r12 = LongCompanionObject.MAX_VALUE;
    L5:
        long r4 = r12;
        if ((r16 & 8) == 0) goto L8;
        int r6 = 0;
    L10:
        if ((r16 & 16) == 0) goto L13;
        int r7 = r9.size();
    L15:
        return commonIndexOf(r8, r9, r10, r4, r6, r7);
    L13:
        r7 = r15;
        goto L15
    L8:
        r6 = r14;
        goto L10
    }

    public static final Buffer.UnsafeCursor commonReadAndWriteUnsafe(okio.Buffer r1, Buffer.UnsafeCursor r2) {
        Buffer.UnsafeCursor r22 = SegmentedByteString.resolveDefaultParameter(r2);
        if (r22.buffer != null) goto L6;
        r22.buffer = r1;
        r22.readWrite = true;
        return r22;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("already attached to a buffer");
        return null;
    }

    private static final int countDigitsIn(long r4) {
        int r0 = ((64 - Long.numberOfLeadingZeros(r4)) * 10) >>> 5;
        if (r4 <= DigitCountToLargestValue[r0]) goto L5;
        int r42 = 1;
    L7:
        return r0 + r42;
    L5:
        r42 = 0;
        goto L7
    }

    public static final byte[] getHEX_DIGIT_BYTES() {
        return HEX_DIGIT_BYTES;
    }

    public static final boolean rangeEquals(Segment r5, int r6, byte[] r7, int r8, int r9) {
        int r0 = r5.limit;
        byte[] r1 = r5.data;
    L3:
        if (r8 >= r9) goto L11;
        if (r6 != r0) goto L7;
        r5 = r5.next;
        byte[] r62 = r5.data;
        int r02 = r5.pos;
        r1 = r62;
        r6 = r02;
        r0 = r5.limit;
    L7:
        if (r1[r6] != r7[r8]) goto L8;
        r6 = r6 + 1;
        r8 = r8 + 1;
        goto L3
    L8:
        return false;
    L11:
        return true;
    }

    public static final String readUtf8Line(okio.Buffer r6, long r7) {
        if (r7 <= 0) goto L8;
        long r3 = r7 - 1;
        if (r6.getByte(r3) != 13) goto L8;
        String r72 = r6.readUtf8(r3);
        r6.skip(2);
        return r72;
    L8:
        String r73 = r6.readUtf8(r7);
        r6.skip(1);
        return r73;
    }

    public static final int selectPrefix(okio.Buffer r17, Options r18, boolean r19) {
        Segment r0 = r17.head;
        if (r0 != null) goto L7;
        if (r19 == false) goto L6;
        return -2;
    L6:
        return -1;
    L7:
        byte[] r3 = r0.data;
        int r4 = r0.pos;
        int r5 = r0.limit;
        int[] r6 = r18.getTrie$okio();
        Segment r9 = r0;
        int r10 = -1;
        int r8 = 0;
    L8:
        int r11 = r8 + 1;
        int r12 = r6[r8];
        int r82 = r8 + 2;
        int r112 = r6[r11];
        if (r112 == (-1)) goto L11;
        r10 = r112;
    L11:
        if (r9 == null) goto L27;
        if (r12 >= 0) goto L34;
        int r13 = r82 + (r12 * (-1));
    L16:
        int r122 = r4 + 1;
        int r14 = r82 + 1;
        if ((r3[r4] & UByte.MAX_VALUE) != r6[r82]) goto L36;
        if (r14 != r13) goto L21;
        boolean r42 = true;
    L22:
        if (r122 != r5) goto L30;
        Segment r32 = r9.next;
        int r52 = r32.pos;
        byte[] r83 = r32.data;
        int r92 = r32.limit;
        if (r32 != r0) goto L29;
        if (r42 == false) goto L27;
        r3 = r83;
        Segment r84 = null;
    L31:
        if (r42 == true) goto L32;
        r4 = r52;
        r5 = r92;
        r9 = r84;
        r82 = r14;
        goto L16
    L32:
        int r43 = r6[r14];
        int r132 = r52;
        r5 = r92;
        r9 = r84;
    L44:
        if (r43 >= 0) goto L45;
        r8 = -r43;
        r4 = r132;
        goto L8
    L45:
        return r43;
    L29:
        r84 = r32;
        r3 = r83;
        goto L31
    L30:
        r84 = r9;
        r92 = r5;
        r52 = r122;
        goto L31
    L21:
        r42 = false;
    L36:
        return r10;
    L34:
        r132 = r4 + 1;
        int r44 = r3[r4] & UByte.MAX_VALUE;
        int r142 = r82 + r12;
    L35:
        if (r82 == r142) goto L36;
        if (r44 == r6[r82]) goto L39;
        r82 = r82 + 1;
        goto L35
    L39:
        r43 = r6[r82 + r12];
        if (r132 != r5) goto L44;
        r9 = r9.next;
        int r33 = r9.pos;
        r132 = r33;
        r3 = r9.data;
        r5 = r9.limit;
        if (r9 != r0) goto L44;
        r9 = null;
    L27:
        if (r19 == false) goto L36;
        return -2;
    }

    public static /* synthetic */ int selectPrefix$default(okio.Buffer r0, Options r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = false;
    L6:
        return selectPrefix(r0, r1, r2);
    }
}
