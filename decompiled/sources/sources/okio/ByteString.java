package okio;

import c.f$$ExternalSyntheticBUOutline1;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.RandomKt$$ExternalSyntheticBUOutline0;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import okio.internal.ByteStringNonJs;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0000\n\u0002\b\u0013\b\u0016\u0018\u0000 V2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0001VB\u0011\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000e\u0010\tJ\r\u0010\u000f\u001a\u00020\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u0000¢\u0006\u0004\b\u0011\u0010\u0010J\r\u0010\u0012\u001a\u00020\u0000¢\u0006\u0004\b\u0012\u0010\u0010J\u0017\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0007H\u0010¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0017\u0010\tJ\u000f\u0010\u0018\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\u0018\u0010\u0010J#\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u0019H\u0017¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\"\u001a\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u0019H\u0010¢\u0006\u0004\b \u0010!J\u0018\u0010%\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\u0019H\u0087\u0002¢\u0006\u0004\b$\u0010!J\u000f\u0010(\u001a\u00020\u0019H\u0010¢\u0006\u0004\b&\u0010'J\u000f\u0010+\u001a\u00020\u0003H\u0010¢\u0006\u0004\b)\u0010*J'\u00103\u001a\u0002002\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020\u00192\u0006\u0010/\u001a\u00020\u0019H\u0010¢\u0006\u0004\b1\u00102J/\u00107\u001a\u0002062\u0006\u0010.\u001a\u00020\u00192\u0006\u00104\u001a\u00020\u00002\u0006\u00105\u001a\u00020\u00192\u0006\u0010/\u001a\u00020\u0019H\u0016¢\u0006\u0004\b7\u00108J/\u00107\u001a\u0002062\u0006\u0010.\u001a\u00020\u00192\u0006\u00104\u001a\u00020\u00032\u0006\u00105\u001a\u00020\u00192\u0006\u0010/\u001a\u00020\u0019H\u0016¢\u0006\u0004\b7\u00109J\u0015\u0010;\u001a\u0002062\u0006\u0010:\u001a\u00020\u0000¢\u0006\u0004\b;\u0010<J\u0015\u0010>\u001a\u0002062\u0006\u0010=\u001a\u00020\u0000¢\u0006\u0004\b>\u0010<J!\u0010@\u001a\u00020\u00192\u0006\u00104\u001a\u00020\u00002\b\b\u0002\u0010?\u001a\u00020\u0019H\u0007¢\u0006\u0004\b@\u0010AJ!\u0010@\u001a\u00020\u00192\u0006\u00104\u001a\u00020\u00032\b\b\u0002\u0010?\u001a\u00020\u0019H\u0017¢\u0006\u0004\b@\u0010BJ!\u0010C\u001a\u00020\u00192\u0006\u00104\u001a\u00020\u00002\b\b\u0002\u0010?\u001a\u00020\u0019H\u0007¢\u0006\u0004\bC\u0010AJ!\u0010C\u001a\u00020\u00192\u0006\u00104\u001a\u00020\u00032\b\b\u0002\u0010?\u001a\u00020\u0019H\u0017¢\u0006\u0004\bC\u0010BJ\u001a\u0010E\u001a\u0002062\b\u00104\u001a\u0004\u0018\u00010DH\u0096\u0002¢\u0006\u0004\bE\u0010FJ\u000f\u0010G\u001a\u00020\u0019H\u0016¢\u0006\u0004\bG\u0010'J\u0018\u0010H\u001a\u00020\u00192\u0006\u00104\u001a\u00020\u0000H\u0096\u0002¢\u0006\u0004\bH\u0010IJ\u000f\u0010J\u001a\u00020\u0007H\u0016¢\u0006\u0004\bJ\u0010\tR\u001a\u0010\u0004\u001a\u00020\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010K\u001a\u0004\bL\u0010*R\"\u0010G\u001a\u00020\u00198\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bG\u0010M\u001a\u0004\bN\u0010'\"\u0004\bO\u0010PR$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\b\u0010Q\u001a\u0004\bR\u0010\t\"\u0004\bS\u0010TR\u0011\u0010U\u001a\u00020\u00198G¢\u0006\u0006\u001a\u0004\bU\u0010'¨\u0006W"}, d2 = {"Lokio/ByteString;", "Ljava/io/Serializable;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "data", "<init>", "([B)V", _UrlKt.FRAGMENT_ENCODE_SET, "utf8", "()Ljava/lang/String;", "Ljava/nio/charset/Charset;", "charset", "string", "(Ljava/nio/charset/Charset;)Ljava/lang/String;", "base64", "md5", "()Lokio/ByteString;", "sha1", "sha256", "algorithm", "digest$okio", "(Ljava/lang/String;)Lokio/ByteString;", "digest", "hex", "toAsciiLowercase", _UrlKt.FRAGMENT_ENCODE_SET, "beginIndex", "endIndex", "substring", "(II)Lokio/ByteString;", "pos", _UrlKt.FRAGMENT_ENCODE_SET, "internalGet$okio", "(I)B", "internalGet", "index", "getByte", "get", "getSize$okio", "()I", "getSize", "internalArray$okio", "()[B", "internalArray", "Lokio/Buffer;", "buffer", "offset", "byteCount", _UrlKt.FRAGMENT_ENCODE_SET, "write$okio", "(Lokio/Buffer;II)V", "write", "other", "otherOffset", _UrlKt.FRAGMENT_ENCODE_SET, "rangeEquals", "(ILokio/ByteString;II)Z", "(I[BII)Z", "prefix", "startsWith", "(Lokio/ByteString;)Z", "suffix", "endsWith", "fromIndex", "indexOf", "(Lokio/ByteString;I)I", "([BI)I", "lastIndexOf", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "hashCode", "compareTo", "(Lokio/ByteString;)I", "toString", "[B", "getData$okio", "I", "getHashCode$okio", "setHashCode$okio", "(I)V", "Ljava/lang/String;", "getUtf8$okio", "setUtf8$okio", "(Ljava/lang/String;)V", "size", "Companion", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nByteString.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteString.kt\nokio/ByteString\n+ 2 ByteString.kt\nokio/internal/-ByteString\n+ 3 Util.kt\nokio/-SegmentedByteString\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,364:1\n42#2,7:365\n52#2:372\n55#2:373\n62#2,4:374\n66#2:379\n68#2:381\n74#2,23:382\n102#2,23:405\n129#2,2:428\n131#2,9:431\n143#2:440\n146#2:441\n149#2:442\n152#2:443\n160#2:444\n170#2,3:445\n169#2:448\n183#2,2:449\n188#2:451\n192#2:452\n196#2:453\n200#2:454\n204#2,7:455\n217#2:462\n221#2,8:463\n233#2,4:471\n242#2,5:475\n251#2,6:480\n257#2,9:487\n301#2,8:496\n129#2,2:504\n131#2,9:507\n312#2,9:516\n67#3:378\n73#3:380\n73#3:486\n1#4:430\n1#4:506\n*S KotlinDebug\n*F\n+ 1 ByteString.kt\nokio/ByteString\n*L\n66#1:365,7\n71#1:372\n108#1:373\n110#1:374,4\n110#1:379\n110#1:381\n112#1:382,23\n114#1:405,23\n118#1:428,2\n118#1:431,9\n120#1:440\n129#1:441\n131#1:442\n133#1:443\n152#1:444\n159#1:445,3\n159#1:448\n166#1:449,2\n168#1:451\n170#1:452\n172#1:453\n174#1:454\n180#1:455,7\n183#1:462\n186#1:463,8\n188#1:471,4\n190#1:475,5\n192#1:480,6\n192#1:487,9\n194#1:496,8\n194#1:504,2\n194#1:507,9\n194#1:516,9\n110#1:378\n110#1:380\n192#1:486\n118#1:430\n194#1:506\n*E\n"})
/* loaded from: classes.dex */
public class ByteString implements Serializable, Comparable<ByteString> {
    public static final Companion Companion = null;

    @JvmField
    public static final ByteString EMPTY = null;
    private final byte[] data;
    private transient int hashCode;
    private transient String utf8;

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\b\u001a\u00020\u00072\n\u0010\u0006\u001a\u00020\u0004\"\u00020\u0005H\u0007¢\u0006\u0004\b\b\u0010\tJ'\u0010\u000e\u001a\u00020\u0007*\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nH\u0007¢\u0006\u0004\b\b\u0010\rJ\u0013\u0010\u0010\u001a\u00020\u0007*\u00020\u000fH\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0016\u001a\u00020\u0007*\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u000fH\u0007¢\u0006\u0004\b\u0017\u0010\u0011J\u0013\u0010\u0018\u001a\u00020\u0007*\u00020\u000fH\u0007¢\u0006\u0004\b\u0018\u0010\u0011R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Lokio/ByteString$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "data", "Lokio/ByteString;", "of", "([B)Lokio/ByteString;", _UrlKt.FRAGMENT_ENCODE_SET, "offset", "byteCount", "([BII)Lokio/ByteString;", "toByteString", _UrlKt.FRAGMENT_ENCODE_SET, "encodeUtf8", "(Ljava/lang/String;)Lokio/ByteString;", "Ljava/nio/charset/Charset;", "charset", "encodeString", "(Ljava/lang/String;Ljava/nio/charset/Charset;)Lokio/ByteString;", "encode", "decodeBase64", "decodeHex", _UrlKt.FRAGMENT_ENCODE_SET, "serialVersionUID", "J", "EMPTY", "Lokio/ByteString;", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nByteString.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteString.kt\nokio/ByteString$Companion\n+ 2 ByteString.kt\nokio/internal/-ByteString\n+ 3 ByteStringNonJs.kt\nokio/internal/-ByteStringNonJs\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,364:1\n269#2:365\n273#2,3:366\n280#2,3:369\n287#2,2:372\n25#3:374\n27#3,7:376\n1#4:375\n1#4:383\n*S KotlinDebug\n*F\n+ 1 ByteString.kt\nokio/ByteString$Companion\n*L\n234#1:365\n239#1:366,3\n251#1:369,3\n259#1:372,2\n262#1:374\n262#1:376,7\n262#1:375\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static /* synthetic */ ByteString of$default(Companion r0, byte[] r1, int r2, int r3, int r4, Object r5) {
            if ((r4 & 1) == 0) goto L6;
            r2 = 0;
        L6:
            if ((r4 & 2) == 0) goto L9;
            r3 = SegmentedByteString.getDEFAULT__ByteString_size();
        L9:
            return r0.of(r1, r2, r3);
        }

        @JvmStatic
        public final ByteString decodeBase64(String r1) {
            byte[] r0 = Base64.decodeBase64ToArray(r1);
            if (r0 != null) goto L5;
            return null;
        L5:
            return new ByteString(r0);
        }

        @JvmStatic
        public final ByteString decodeHex(String r5) {
            if ((r5.length() % 2) != 0) goto L9;
            int r4 = r5.length() / 2;
            byte[] r0 = new byte[r4];
            int r1 = 0;
        L5:
            if (r1 >= r4) goto L8;
            int r2 = r1 * 2;
            r0[r1] = (byte) ((ByteStringNonJs.access$decodeHexDigit(r5.charAt(r2)) << 4) + ByteStringNonJs.access$decodeHexDigit(r5.charAt(r2 + 1)));
            r1 = r1 + 1;
            goto L5
        L8:
            return new ByteString(r0);
        L9:
            RandomKt$$ExternalSyntheticBUOutline0.m("Unexpected hex string: ".concat(r5));
            return null;
        }

        @JvmStatic
        @JvmName(name = "encodeString")
        public final ByteString encodeString(String r1, Charset r2) {
            return new ByteString(r1.getBytes(r2));
        }

        @JvmStatic
        public final ByteString encodeUtf8(String r2) {
            ByteString r1 = new ByteString(_JvmPlatformKt.asUtf8ToByteArray(r2));
            r1.setUtf8$okio(r2);
            return r1;
        }

        @JvmStatic
        public final ByteString of(byte... r2) {
            return new ByteString(Arrays.copyOf(r2, r2.length));
        }

        @JvmStatic
        @JvmName(name = "of")
        public final ByteString of(byte[] r7, int r8, int r9) {
            int r6 = SegmentedByteString.resolveDefaultParameter(r7, r9);
            SegmentedByteString.checkOffsetAndCount(r7.length, r8, r6);
            return new ByteString(ArraysKt.copyOfRange(r7, r8, r6 + r8));
        }
    }

    static {
        Companion = new Companion(null);
        EMPTY = new ByteString(new byte[0]);
    }

    public ByteString(byte[] r1) {
        this.data = r1;
    }

    public static /* synthetic */ int indexOf$default(ByteString r0, ByteString r1, int r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: indexOf");
        return 0;
    L4:
        if ((r3 & 2) == 0) goto L7;
        r2 = 0;
    L7:
        return r0.indexOf(r1, r2);
    }

    public static /* synthetic */ int lastIndexOf$default(ByteString r0, ByteString r1, int r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: lastIndexOf");
        return 0;
    L4:
        if ((r3 & 2) == 0) goto L7;
        r2 = SegmentedByteString.getDEFAULT__ByteString_size();
    L7:
        return r0.lastIndexOf(r1, r2);
    }

    public static /* synthetic */ ByteString substring$default(ByteString r0, int r1, int r2, int r3, Object r4) {
        if (r4 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: substring");
        return null;
    L4:
        if ((r3 & 1) == 0) goto L7;
        r1 = 0;
    L7:
        if ((r3 & 2) == 0) goto L10;
        r2 = SegmentedByteString.getDEFAULT__ByteString_size();
    L10:
        return r0.substring(r1, r2);
    }

    public String base64() {
        return Base64.encodeBase64$default(getData$okio(), null, 1, null);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(ByteString r1) {
        return compareTo2(r1);
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(ByteString r10) {
        int r0 = size();
        int r1 = r10.size();
        int r2 = Math.min(r0, r1);
        int r4 = 0;
    L4:
        if (r4 >= r2) goto L11;
        int r7 = getByte(r4) & UByte.MAX_VALUE;
        int r8 = r10.getByte(r4) & UByte.MAX_VALUE;
        if (r7 != r8) goto L8;
        r4 = r4 + 1;
        goto L4
    L8:
        if (r7 >= r8) goto L10;
        return -1;
    L10:
        return 1;
    L11:
        if (r0 != r1) goto L13;
        return 0;
    L13:
        if (r0 >= r1) goto L15;
        return -1;
    L15:
        return 1;
    }

    public ByteString digest$okio(String r3) {
        MessageDigest r32 = MessageDigest.getInstance(r3);
        r32.update(this.data, 0, size());
        return new ByteString(r32.digest());
    }

    public final boolean endsWith(ByteString r4) {
        return rangeEquals(size() - r4.size(), r4, 0, r4.size());
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof ByteString) == false) goto L12;
        ByteString r52 = (ByteString) r5;
        if (r52.size() != getData$okio().length) goto L12;
        if (r52.rangeEquals(0, getData$okio(), 0, getData$okio().length) == false) goto L12;
        return true;
    L12:
        return false;
    }

    @JvmName(name = "getByte")
    public final byte getByte(int r1) {
        return internalGet$okio(r1);
    }

    public final byte[] getData$okio() {
        return this.data;
    }

    public final int getHashCode$okio() {
        return this.hashCode;
    }

    public int getSize$okio() {
        return getData$okio().length;
    }

    public final String getUtf8$okio() {
        return this.utf8;
    }

    public int hashCode() {
        int r0 = getHashCode$okio();
        if (r0 == 0) goto L5;
        return r0;
    L5:
        int r02 = Arrays.hashCode(getData$okio());
        setHashCode$okio(r02);
        return r02;
    }

    public String hex() {
        char[] r0 = new char[getData$okio().length * 2];
        byte[] r8 = getData$okio();
        int r1 = r8.length;
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r2 >= r1) goto L6;
        byte r4 = r8[r2];
        int r5 = r3 + 1;
        r0[r3] = okio.internal.ByteString.getHEX_DIGIT_CHARS()[(r4 >> 4) & 15];
        r3 = r3 + 2;
        r0[r5] = okio.internal.ByteString.getHEX_DIGIT_CHARS()[r4 & 15];
        r2 = r2 + 1;
        goto L3
    L6:
        return StringsKt.concatToString(r0);
    }

    @JvmOverloads
    public final int indexOf(ByteString r1, int r2) {
        return indexOf(r1.internalArray$okio(), r2);
    }

    @JvmOverloads
    public int indexOf(byte[] r5, int r6) {
        int r0 = getData$okio().length - r5.length;
        int r62 = Math.max(r6, 0);
        if (r62 <= r0) goto L5;
        return -1;
    L5:
        if (SegmentedByteString.arrayRangeEquals(getData$okio(), r62, r5, 0, r5.length) == true) goto L6;
        if (r62 == r0) goto L13;
        r62 = r62 + 1;
        goto L5
    L13:
        return -1;
    L6:
        return r62;
    }

    public byte[] internalArray$okio() {
        return getData$okio();
    }

    public byte internalGet$okio(int r1) {
        return getData$okio()[r1];
    }

    @JvmOverloads
    public final int lastIndexOf(ByteString r1, int r2) {
        return lastIndexOf(r1.internalArray$okio(), r2);
    }

    @JvmOverloads
    public int lastIndexOf(byte[] r4, int r5) {
        int r52 = Math.min(SegmentedByteString.resolveDefaultParameter(this, r5), getData$okio().length - r4.length);
    L4:
        if ((-1) >= r52) goto L9;
        if (SegmentedByteString.arrayRangeEquals(getData$okio(), r52, r4, 0, r4.length) == true) goto L7;
        r52 = r52 - 1;
        goto L4
    L7:
        return r52;
    L9:
        return -1;
    }

    public final ByteString md5() {
        return digest$okio("MD5");
    }

    public boolean rangeEquals(int r1, ByteString r2, int r3, int r4) {
        return r2.rangeEquals(r3, getData$okio(), r1, r4);
    }

    public boolean rangeEquals(int r2, byte[] r3, int r4, int r5) {
        if (r2 >= 0) goto L4;
        return false;
    L4:
        if (r2 > (getData$okio().length - r5)) goto L14;
        if (r4 >= 0) goto L7;
        return false;
    L7:
        if (r4 <= (r3.length - r5)) goto L9;
        return false;
    L9:
        if (SegmentedByteString.arrayRangeEquals(getData$okio(), r2, r3, r4, r5) == false) goto L17;
        return true;
    L17:
        return false;
    L14:
        return false;
    }

    public final void setHashCode$okio(int r1) {
        this.hashCode = r1;
    }

    public final void setUtf8$okio(String r1) {
        this.utf8 = r1;
    }

    public final ByteString sha1() {
        return digest$okio("SHA-1");
    }

    public final ByteString sha256() {
        return digest$okio("SHA-256");
    }

    @JvmName(name = "size")
    public final int size() {
        return getSize$okio();
    }

    public final boolean startsWith(ByteString r3) {
        return rangeEquals(0, r3, 0, r3.size());
    }

    public String string(Charset r2) {
        return new String(this.data, r2);
    }

    @JvmOverloads
    public ByteString substring(int r2, int r3) {
        int r32 = SegmentedByteString.resolveDefaultParameter(this, r3);
        if (r2 >= 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("beginIndex < 0");
        return null;
    L5:
        if (r32 <= getData$okio().length) goto L7;
        ByteString$$ExternalSyntheticBUOutline1.m("endIndex > length(", getData$okio().length, 41);
        return null;
    L7:
        if ((r32 - r2) < 0) goto L14;
        if (r2 != 0) goto L13;
        if (r32 != getData$okio().length) goto L13;
        return this;
    L13:
        return new ByteString(ArraysKt.copyOfRange(getData$okio(), r2, r32));
    L14:
        f$$ExternalSyntheticBUOutline1.m("endIndex < beginIndex");
        return null;
    }

    public ByteString toAsciiLowercase() {
        int r0 = 0;
    L4:
        if (r0 >= getData$okio().length) goto L22;
        byte r1 = getData$okio()[r0];
        if (r1 < 65) goto L21;
        if (r1 > 90) goto L21;
        byte[] r5 = getData$okio();
        byte[] r52 = Arrays.copyOf(r5, r5.length);
        int r4 = r0 + 1;
        r52[r0] = (byte) (r1 + 32);
    L12:
        if (r4 >= r52.length) goto L20;
        byte r02 = r52[r4];
        if (r02 < 65) goto L18;
        if (r02 > 90) goto L18;
        r52[r4] = (byte) (r02 + 32);
    L18:
        r4 = r4 + 1;
        goto L12
    L20:
        return new ByteString(r52);
    L21:
        r0 = r0 + 1;
        goto L4
    L22:
        return this;
    }

    public String toString() {
        if (getData$okio().length != 0) goto L6;
        return "[size=0]";
    L6:
        int r0 = okio.internal.ByteString.access$codePointIndexToCharIndex(getData$okio(), 64);
        if (r0 == (-1)) goto L9;
        String r1 = utf8();
        String r4 = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(r1.substring(0, r0), "\\", "\\\\", false, 4, null), "\n", "\\n", false, 4, null), "\r", "\\r", false, 4, null);
        if (r0 >= r1.length()) goto L30;
        return "[size=" + getData$okio().length + " text=" + r4 + "…]";
    L30:
        return "[text=" + r4 + ']';
    L9:
        if (getData$okio().length <= 64) goto L11;
        StringBuilder r02 = new StringBuilder("[size=");
        r02.append(getData$okio().length);
        r02.append(" hex=");
        ByteString r2 = this;
        int r12 = SegmentedByteString.resolveDefaultParameter(r2, 64);
        if (r12 > r2.getData$okio().length) goto L23;
        if (r12 >= 0) goto L16;
        f$$ExternalSyntheticBUOutline1.m("endIndex < beginIndex");
        return null;
    L16:
        if (r12 == r2.getData$okio().length) goto L19;
        r2 = new ByteString(ArraysKt.copyOfRange(r2.getData$okio(), 0, r12));
    L19:
        r02.append(r2.hex());
        r02.append("…]");
        return r02.toString();
    L23:
        ByteString$$ExternalSyntheticBUOutline1.m("endIndex > length(", r2.getData$okio().length, 41);
        return null;
    L11:
        return "[hex=" + hex() + ']';
    }

    public String utf8() {
        String r0 = getUtf8$okio();
        if (r0 != null) goto L6;
        String r02 = _JvmPlatformKt.toUtf8String(internalArray$okio());
        setUtf8$okio(r02);
        return r02;
    L6:
        return r0;
    }

    public void write$okio(Buffer r1, int r2, int r3) {
        okio.internal.ByteString.commonWrite(this, r1, r2, r3);
    }
}
