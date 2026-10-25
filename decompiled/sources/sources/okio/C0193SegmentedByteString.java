package okio;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0012\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\u0001H\u0016¢\u0006\u0004\b\u0013\u0010\nJ\u0017\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\rH\u0010¢\u0006\u0004\b\u0015\u0010\u0016J#\u0010\u001b\u001a\u00020\u00012\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010!\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u0018H\u0010¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010$\u001a\u00020\u0018H\u0010¢\u0006\u0004\b\"\u0010#J\u000f\u0010%\u001a\u00020\u0003H\u0016¢\u0006\u0004\b%\u0010&J'\u0010.\u001a\u00020+2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020\u00182\u0006\u0010*\u001a\u00020\u0018H\u0010¢\u0006\u0004\b,\u0010-J/\u00102\u001a\u0002012\u0006\u0010)\u001a\u00020\u00182\u0006\u0010/\u001a\u00020\u00012\u0006\u00100\u001a\u00020\u00182\u0006\u0010*\u001a\u00020\u0018H\u0016¢\u0006\u0004\b2\u00103J/\u00102\u001a\u0002012\u0006\u0010)\u001a\u00020\u00182\u0006\u0010/\u001a\u00020\u00032\u0006\u00100\u001a\u00020\u00182\u0006\u0010*\u001a\u00020\u0018H\u0016¢\u0006\u0004\b2\u00104J!\u00106\u001a\u00020\u00182\u0006\u0010/\u001a\u00020\u00032\b\b\u0002\u00105\u001a\u00020\u0018H\u0016¢\u0006\u0004\b6\u00107J!\u00108\u001a\u00020\u00182\u0006\u0010/\u001a\u00020\u00032\b\b\u0002\u00105\u001a\u00020\u0018H\u0016¢\u0006\u0004\b8\u00107J\u000f\u0010:\u001a\u00020\u0003H\u0010¢\u0006\u0004\b9\u0010&J\u001a\u0010<\u001a\u0002012\b\u0010/\u001a\u0004\u0018\u00010;H\u0096\u0002¢\u0006\u0004\b<\u0010=J\u000f\u0010>\u001a\u00020\u0018H\u0016¢\u0006\u0004\b>\u0010#J\u000f\u0010?\u001a\u00020\rH\u0016¢\u0006\u0004\b?\u0010\u0011R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010@\u001a\u0004\bA\u0010BR\u001a\u0010\u0006\u001a\u00020\u00058\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0006\u0010C\u001a\u0004\bD\u0010E¨\u0006F"}, d2 = {"Lokio/SegmentedByteString;", "Lokio/ByteString;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "segments", _UrlKt.FRAGMENT_ENCODE_SET, "directory", "<init>", "([[B[I)V", "toByteString", "()Lokio/ByteString;", "Ljava/nio/charset/Charset;", "charset", _UrlKt.FRAGMENT_ENCODE_SET, "string", "(Ljava/nio/charset/Charset;)Ljava/lang/String;", "base64", "()Ljava/lang/String;", "hex", "toAsciiLowercase", "algorithm", "digest$okio", "(Ljava/lang/String;)Lokio/ByteString;", "digest", _UrlKt.FRAGMENT_ENCODE_SET, "beginIndex", "endIndex", "substring", "(II)Lokio/ByteString;", "pos", _UrlKt.FRAGMENT_ENCODE_SET, "internalGet$okio", "(I)B", "internalGet", "getSize$okio", "()I", "getSize", "toByteArray", "()[B", "Lokio/Buffer;", "buffer", "offset", "byteCount", _UrlKt.FRAGMENT_ENCODE_SET, "write$okio", "(Lokio/Buffer;II)V", "write", "other", "otherOffset", _UrlKt.FRAGMENT_ENCODE_SET, "rangeEquals", "(ILokio/ByteString;II)Z", "(I[BII)Z", "fromIndex", "indexOf", "([BI)I", "lastIndexOf", "internalArray$okio", "internalArray", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "hashCode", "toString", "[[B", "getSegments$okio", "()[[B", "[I", "getDirectory$okio", "()[I", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSegmentedByteString.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SegmentedByteString.kt\nokio/SegmentedByteString\n+ 2 SegmentedByteString.kt\nokio/internal/-SegmentedByteString\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,140:1\n63#2,12:141\n63#2,12:153\n104#2,2:165\n106#2,26:168\n135#2,5:194\n142#2:199\n145#2,3:200\n63#2,8:203\n148#2,8:211\n71#2,4:219\n156#2:223\n63#2,12:224\n160#2:236\n85#2,10:237\n161#2,9:247\n95#2,4:256\n170#2,2:260\n179#2,4:262\n85#2,10:266\n183#2,3:276\n95#2,4:279\n186#2:283\n195#2,8:284\n85#2,10:292\n203#2,3:302\n95#2,4:305\n206#2:309\n215#2,5:310\n85#2,10:315\n220#2,3:325\n95#2,4:328\n223#2:332\n226#2,4:333\n234#2,6:337\n63#2,8:343\n240#2,7:351\n71#2,4:358\n247#2,2:362\n1#3:167\n*S KotlinDebug\n*F\n+ 1 SegmentedByteString.kt\nokio/SegmentedByteString\n*L\n54#1:141,12\n66#1:153,12\n78#1:165,2\n78#1:168,26\n80#1:194,5\n82#1:199\n84#1:200,3\n84#1:203,8\n84#1:211,8\n84#1:219,4\n84#1:223\n90#1:224,12\n96#1:236\n96#1:237,10\n96#1:247,9\n96#1:256,4\n96#1:260,2\n103#1:262,4\n103#1:266,10\n103#1:276,3\n103#1:279,4\n103#1:283\n110#1:284,8\n110#1:292,10\n110#1:302,3\n110#1:305,4\n110#1:309\n117#1:310,5\n117#1:315,10\n117#1:325,3\n117#1:328,4\n117#1:332\n131#1:333,4\n133#1:337,6\n133#1:343,8\n133#1:351,7\n133#1:358,4\n133#1:362,2\n78#1:167\n*E\n"})
/* renamed from: okio.SegmentedByteString, reason: case insensitive filesystem */
/* loaded from: classes5.dex */
public final class C0193SegmentedByteString extends ByteString {
    private final transient int[] directory;
    private final transient byte[][] segments;

    public C0193SegmentedByteString(byte[][] r2, int[] r3) {
        super(ByteString.EMPTY.getData$okio());
        this.segments = r2;
        this.directory = r3;
    }

    private final ByteString toByteString() {
        return new ByteString(toByteArray());
    }

    @Override // okio.ByteString
    public String base64() {
        return toByteString().base64();
    }

    @Override // okio.ByteString
    public ByteString digest$okio(String r7) {
        MessageDigest r72 = MessageDigest.getInstance(r7);
        int r0 = getSegments$okio().length;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L6;
        int r3 = getDirectory$okio()[r0 + r1];
        int r4 = getDirectory$okio()[r1];
        r72.update(getSegments$okio()[r1], r3, r4 - r2);
        r1 = r1 + 1;
        r2 = r4;
        goto L3
    L6:
        return new ByteString(r72.digest());
    }

    @Override // okio.ByteString
    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof ByteString) == false) goto L12;
        ByteString r52 = (ByteString) r5;
        if (r52.size() != size()) goto L12;
        if (rangeEquals(0, r52, 0, size()) == false) goto L12;
        return true;
    L12:
        return false;
    }

    public final int[] getDirectory$okio() {
        return this.directory;
    }

    public final byte[][] getSegments$okio() {
        return this.segments;
    }

    @Override // okio.ByteString
    public int getSize$okio() {
        return getDirectory$okio()[getSegments$okio().length - 1];
    }

    @Override // okio.ByteString
    public int hashCode() {
        int r0 = getHashCode$okio();
        if (r0 == 0) goto L5;
        return r0;
    L5:
        int r02 = getSegments$okio().length;
        int r1 = 0;
        int r3 = 1;
        int r2 = 0;
    L6:
        if (r1 >= r02) goto L11;
        int r4 = getDirectory$okio()[r02 + r1];
        int r5 = getDirectory$okio()[r1];
        byte[] r6 = getSegments$okio()[r1];
        int r22 = (r5 - r2) + r4;
    L8:
        if (r4 >= r22) goto L10;
        r3 = (r3 * 31) + r6[r4];
        r4 = r4 + 1;
        goto L8
    L10:
        r1 = r1 + 1;
        r2 = r5;
        goto L6
    L11:
        setHashCode$okio(r3);
        return r3;
    }

    @Override // okio.ByteString
    public String hex() {
        return toByteString().hex();
    }

    @Override // okio.ByteString
    public int indexOf(byte[] r1, int r2) {
        return toByteString().indexOf(r1, r2);
    }

    @Override // okio.ByteString
    public byte[] internalArray$okio() {
        return toByteArray();
    }

    @Override // okio.ByteString
    public byte internalGet$okio(int r8) {
        SegmentedByteString.checkOffsetAndCount(getDirectory$okio()[getSegments$okio().length - 1], r8, 1);
        int r0 = okio.internal.SegmentedByteString.segment(this, r8);
        if (r0 != 0) goto L5;
        int r1 = 0;
    L7:
        return getSegments$okio()[r0][(r8 - r1) + getDirectory$okio()[getSegments$okio().length + r0]];
    L5:
        r1 = getDirectory$okio()[r0 - 1];
        goto L7
    }

    @Override // okio.ByteString
    public int lastIndexOf(byte[] r1, int r2) {
        return toByteString().lastIndexOf(r1, r2);
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int r7, ByteString r8, int r9, int r10) {
        if (r7 >= 0) goto L5;
    L18:
        return false;
    L5:
        if (r7 > (size() - r10)) goto L18;
        int r102 = r10 + r7;
        int r1 = okio.internal.SegmentedByteString.segment(this, r7);
    L8:
        if (r7 >= r102) goto L16;
        if (r1 != 0) goto L11;
        int r2 = 0;
    L12:
        int r3 = getDirectory$okio()[r1] - r2;
        int r4 = getDirectory$okio()[getSegments$okio().length + r1];
        int r32 = Math.min(r102, r3 + r2) - r7;
        if (r8.rangeEquals(r9, getSegments$okio()[r1], r4 + (r7 - r2), r32) == false) goto L14;
        r9 = r9 + r32;
        r7 = r7 + r32;
        r1 = r1 + 1;
        goto L8
    L14:
        return false;
    L11:
        r2 = getDirectory$okio()[r1 - 1];
        goto L12
    L16:
        return true;
    }

    @Override // okio.ByteString
    public boolean rangeEquals(int r7, byte[] r8, int r9, int r10) {
        if (r7 >= 0) goto L5;
    L21:
        return false;
    L5:
        if (r7 > (size() - r10)) goto L21;
        if (r9 < 0) goto L21;
        if (r9 > (r8.length - r10)) goto L21;
        int r102 = r10 + r7;
        int r1 = okio.internal.SegmentedByteString.segment(this, r7);
    L11:
        if (r7 >= r102) goto L19;
        if (r1 != 0) goto L14;
        int r2 = 0;
    L15:
        int r3 = getDirectory$okio()[r1] - r2;
        int r4 = getDirectory$okio()[getSegments$okio().length + r1];
        int r32 = Math.min(r102, r3 + r2) - r7;
        if (SegmentedByteString.arrayRangeEquals(getSegments$okio()[r1], r4 + (r7 - r2), r8, r9, r32) == false) goto L17;
        r9 = r9 + r32;
        r7 = r7 + r32;
        r1 = r1 + 1;
        goto L11
    L17:
        return false;
    L14:
        r2 = getDirectory$okio()[r1 - 1];
        goto L15
    L19:
        return true;
    }

    @Override // okio.ByteString
    public String string(Charset r1) {
        return toByteString().string(r1);
    }

    @Override // okio.ByteString
    public ByteString substring(int r11, int r12) {
        int r122 = SegmentedByteString.resolveDefaultParameter(this, r12);
        if (r11 >= 0) goto L5;
        SegmentedByteString$$ExternalSyntheticBUOutline0.m("beginIndex=", r11, " < 0");
        return null;
    L5:
        if (r122 > size()) goto L29;
        int r1 = r122 - r11;
        if (r1 < 0) goto L26;
        if (r11 == 0) goto L10;
    L12:
        if (r11 == r122) goto L14;
        int r0 = okio.internal.SegmentedByteString.segment(this, r11);
        int r123 = okio.internal.SegmentedByteString.segment(this, r122 - 1);
        byte[][] r2 = (byte[][]) ArraysKt.copyOfRange(getSegments$okio(), r0, r123 + 1);
        int[] r3 = new int[r2.length * 2];
        int r4 = 0;
        if (r0 > r123) goto L21;
        int r6 = r0;
        int r5 = 0;
    L18:
        r3[r5] = Math.min(getDirectory$okio()[r6] - r11, r1);
        int r7 = r5 + 1;
        r3[r5 + r2.length] = getDirectory$okio()[getSegments$okio().length + r6];
        if (r6 == r123) goto L21;
        r6 = r6 + 1;
        r5 = r7;
    L21:
        if (r0 == 0) goto L24;
        r4 = getDirectory$okio()[r0 - 1];
    L24:
        int r10 = r2.length;
        r3[r10] = r3[r10] + (r11 - r4);
        return new C0193SegmentedByteString(r2, r3);
    L14:
        return ByteString.EMPTY;
    L10:
        if (r122 != size()) goto L12;
        return this;
    L26:
        Utf8$$ExternalSyntheticBUOutline0.m("endIndex=", r122, " < beginIndex=", r11);
        return null;
    L29:
        throw new IllegalArgumentException(("endIndex=" + r122 + " > length(" + size() + ')').toString());
    }

    @Override // okio.ByteString
    public ByteString toAsciiLowercase() {
        return toByteString().toAsciiLowercase();
    }

    public byte[] toByteArray() {
        byte[] r0 = new byte[size()];
        int r1 = getSegments$okio().length;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r2 >= r1) goto L5;
        int r5 = getDirectory$okio()[r1 + r2];
        int r6 = getDirectory$okio()[r2];
        int r32 = r6 - r3;
        ArraysKt.copyInto(getSegments$okio()[r2], r0, r4, r5, r5 + r32);
        r4 = r4 + r32;
        r2 = r2 + 1;
        r3 = r6;
        goto L3
    L5:
        return r0;
    }

    @Override // okio.ByteString
    public String toString() {
        return toByteString().toString();
    }

    @Override // okio.ByteString
    public void write$okio(Buffer r12, int r13, int r14) {
        int r0 = r13 + r14;
        int r1 = okio.internal.SegmentedByteString.segment(this, r13);
    L3:
        if (r13 >= r0) goto L12;
        if (r1 != 0) goto L6;
        int r2 = 0;
    L7:
        int r3 = getDirectory$okio()[r1] - r2;
        int r4 = getDirectory$okio()[getSegments$okio().length + r1];
        int r32 = Math.min(r0, r3 + r2) - r13;
        int r7 = r4 + (r13 - r2);
        Segment r5 = new Segment(getSegments$okio()[r1], r7, r7 + r32, true, false);
        Segment r22 = r12.head;
        if (r22 != null) goto L10;
        r5.prev = r5;
        r5.next = r5;
        r12.head = r5;
    L11:
        r13 = r13 + r32;
        r1 = r1 + 1;
        goto L3
    L10:
        r22.prev.push(r5);
        goto L11
    L6:
        r2 = getDirectory$okio()[r1 - 1];
        goto L7
    L12:
        r12.setSize$okio(r12.size() + r14);
    }
}
