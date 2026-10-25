package kotlin.uuid;

import androidx.collection.MutableScatterMap$$ExternalSyntheticBackport0;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Comparator;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.ULong;
import kotlin.comparisons.ComparisonsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import kotlin.time.Clock;
import kotlin.time.Instant;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

@SinceKotlin(version = "2.0")
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 02\b\u0012\u0004\u0012\u00020\u00000\u00012\u00060\u0002j\u0002`\u0003:\u00010B\u0019\bB\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ]\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u001026\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u0002H\u00100\u0012H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0015J]\u0010\u0016\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u001026\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u0002H\u00100\u0012H\u0087\u0088\u0004ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0015J\n\u0010\u0018\u001a\u00020\u0019H\u0096\u0080\u0004J\n\u0010\u001a\u001a\u00020\u0019H\u0087\u0080\u0004J\n\u0010\u001b\u001a\u00020\u0019H\u0086\u0080\u0004J\n\u0010\u001c\u001a\u00020\u001dH\u0086\u0080\u0004J\u0011\u0010\u001e\u001a\u00020\u001fH\u0087\u0080\u0004¢\u0006\u0004\b \u0010!J\u0014\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0096\u0082\u0004J\u0012\u0010&\u001a\u00020'2\u0006\u0010$\u001a\u00020\u0000H\u0097\u0082\u0004J\n\u0010(\u001a\u00020'H\u0096\u0080\u0004J\n\u0010)\u001a\u00020%H\u0082\u0080\u0004J\u001b\u0010*\u001a\u00020+2\n\u0010,\u001a\u00060-j\u0002`.H\u0082\u0080\u0004¢\u0006\u0002\u0010/R\u001d\u0010\u0004\u001a\u00020\u00058\u0000X\u0081\u0084\b¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u0006\u001a\u00020\u00058\u0000X\u0081\u0084\b¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00061"}, d2 = {"Lkotlin/uuid/Uuid;", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "mostSignificantBits", _UrlKt.FRAGMENT_ENCODE_SET, "leastSignificantBits", "<init>", "(JJ)V", "getMostSignificantBits$annotations", "()V", "getMostSignificantBits", "()J", "getLeastSignificantBits$annotations", "getLeastSignificantBits", "toLongs", "T", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "(Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "toULongs", "Lkotlin/ULong;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "toHexDashString", "toHexString", "toByteArray", _UrlKt.FRAGMENT_ENCODE_SET, "toUByteArray", "Lkotlin/UByteArray;", "toUByteArray-TcUX1vc", "()[B", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "compareTo", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "writeReplace", "readObject", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.TYPE_INPUT, "Ljava/io/ObjectInputStream;", "Lkotlin/internal/ReadObjectParameterType;", "(Ljava/io/ObjectInputStream;)V", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ExperimentalUuidApi
/* loaded from: classes5.dex */
public final class Uuid implements Comparable<Uuid>, Serializable {
    public static final Companion Companion = null;
    private static final Uuid NIL = null;
    public static final int SIZE_BITS = 128;
    public static final int SIZE_BYTES = 16;
    private final long leastSignificantBits;
    private final long mostSignificantBits;

    @Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0086\u0080\u0004J!\u0010\u000f\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u0010H\u0086\u0080\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\u0012\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\u0086\u0080\u0004J\u0019\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0018H\u0087\u0080\u0004¢\u0006\u0004\b\u0019\u0010\u001aJ\u0012\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001dH\u0086\u0080\u0004J\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001c\u001a\u00020\u001dH\u0087\u0080\u0004J\u0012\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u001dH\u0087\u0080\u0004J\u0014\u0010!\u001a\u0004\u0018\u00010\u00052\u0006\u0010 \u001a\u00020\u001dH\u0087\u0080\u0004J\u0012\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u001dH\u0086\u0080\u0004J\u0014\u0010$\u001a\u0004\u0018\u00010\u00052\u0006\u0010#\u001a\u00020\u001dH\u0087\u0080\u0004J\n\u0010%\u001a\u00020\u0005H\u0086\u0080\u0004J\n\u0010&\u001a\u00020\u0005H\u0087\u0080\u0004J\n\u0010'\u001a\u00020\u0005H\u0087\u0080\u0004J\u0012\u0010(\u001a\u00020\u00052\u0006\u0010)\u001a\u00020*H\u0087\u0080\u0004J\u0017\u0010'\u001a\u00020\u00052\u0006\u0010+\u001a\u00020,H\u0080\u0080\u0004¢\u0006\u0002\b-R\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000f\u0010\b\u001a\u00020\tX\u0086Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\tX\u0086Ô\b¢\u0006\u0002\n\u0000R+\u0010.\u001a\u0012\u0012\u0004\u0012\u00020\u00050/j\b\u0012\u0004\u0012\u00020\u0005`08FX\u0087\u0084\b¢\u0006\f\u0012\u0004\b1\u0010\u0003\u001a\u0004\b2\u00103¨\u00064"}, d2 = {"Lkotlin/uuid/Uuid$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "NIL", "Lkotlin/uuid/Uuid;", "getNIL", "()Lkotlin/uuid/Uuid;", "SIZE_BYTES", _UrlKt.FRAGMENT_ENCODE_SET, "SIZE_BITS", "fromLongs", "mostSignificantBits", _UrlKt.FRAGMENT_ENCODE_SET, "leastSignificantBits", "fromULongs", "Lkotlin/ULong;", "fromULongs-eb3DHEI", "(JJ)Lkotlin/uuid/Uuid;", "fromByteArray", "byteArray", _UrlKt.FRAGMENT_ENCODE_SET, "fromUByteArray", "ubyteArray", "Lkotlin/UByteArray;", "fromUByteArray-GBYM_sE", "([B)Lkotlin/uuid/Uuid;", "parse", "uuidString", _UrlKt.FRAGMENT_ENCODE_SET, "parseOrNull", "parseHexDash", "hexDashString", "parseHexDashOrNull", "parseHex", "hexString", "parseHexOrNull", "random", "generateV4", "generateV7", "generateV7NonMonotonicAt", "timestamp", "Lkotlin/time/Instant;", "clock", "Lkotlin/time/Clock;", "generateV7$kotlin_stdlib", "LEXICAL_ORDER", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "getLEXICAL_ORDER$annotations", "getLEXICAL_ORDER", "()Ljava/util/Comparator;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @Deprecated(message = "Use naturalOrder<Uuid>() instead", replaceWith = @ReplaceWith(expression = "naturalOrder<Uuid>()", imports = {"kotlin.comparisons.naturalOrder"}))
        @DeprecatedSinceKotlin(warningSince = "2.1")
        public static /* synthetic */ void getLEXICAL_ORDER$annotations() {
        }

        public final Uuid fromByteArray(byte[] r5) {
            if (r5.length == 16) goto L5;
            Uuid$Companion$$ExternalSyntheticBUOutline0.m("Expected exactly 16 bytes, but was ", UuidKt__UuidKt.access$truncateForErrorMessage(r5, 32), " of size ", r5.length);
            return null;
        L5:
            return fromLongs(UuidKt__UuidJVMKt.getLongAt(r5, 0), UuidKt__UuidJVMKt.getLongAt(r5, 8));
        }

        public final Uuid fromLongs(long r7, long r9) {
            if (r7 != 0) goto L9;
            if (r9 != 0) goto L9;
            return getNIL();
        L9:
            return new Uuid(r7, r9, null);
        }

        @SinceKotlin(version = "2.1")
        @ExperimentalUnsignedTypes
        /* renamed from: fromUByteArray-GBYM_sE, reason: not valid java name */
        public final Uuid m3518fromUByteArrayGBYM_sE(byte[] r1) {
            return fromByteArray(r1);
        }

        /* renamed from: fromULongs-eb3DHEI, reason: not valid java name */
        public final Uuid m3519fromULongseb3DHEI(long r1, long r3) {
            return fromLongs(r1, r3);
        }

        @SinceKotlin(version = MVEL.VERSION)
        public final Uuid generateV4() {
            return UuidKt__UuidKt.secureRandomUuid();
        }

        @SinceKotlin(version = MVEL.VERSION)
        public final Uuid generateV7() {
            return generateV7$kotlin_stdlib(Clock.System.INSTANCE);
        }

        public final Uuid generateV7$kotlin_stdlib(Clock r1) {
            return UuidV7Generator.INSTANCE.generate(r1);
        }

        @SinceKotlin(version = MVEL.VERSION)
        public final Uuid generateV7NonMonotonicAt(Instant r7) {
            byte[] r0 = new byte[10];
            UuidKt__UuidJVMKt.secureRandomBytes(r0);
            long r1 = (r7.toEpochMilliseconds() << 16) | ((((r0[8] & 15) | 112) << 8) | (r0[9] & UByte.MAX_VALUE));
            r0[0] = (byte) (((byte) (r0[0] & 63)) | ByteCompanionObject.MIN_VALUE);
            return fromLongs(r1, UuidKt__UuidJVMKt.getLongAt(r0, 0));
        }

        public final Comparator<Uuid> getLEXICAL_ORDER() {
            return ComparisonsKt.naturalOrder();
        }

        public final Uuid getNIL() {
            return Uuid.access$getNIL$cp();
        }

        public final Uuid parse(String r4) {
            int r3 = r4.length();
            if (r3 == 32) goto L11;
            if (r3 != 36) goto L9;
            return UuidKt__UuidJVMKt.uuidParseHexDash(r4);
        L9:
            throw new IllegalArgumentException("Expected either a 36-char string in the standard hex-and-dash UUID format or a 32-char hexadecimal string, but was \"" + UuidKt__UuidKt.access$truncateForErrorMessage(r4, 64) + "\" of length " + r4.length());
        L11:
            return UuidKt__UuidJVMKt.uuidParseHex(r4);
        }

        public final Uuid parseHex(String r3) {
            if (r3.length() == 32) goto L5;
            Uuid$Companion$$ExternalSyntheticBUOutline0.m("Expected a 32-char hexadecimal string, but was \"", UuidKt__UuidKt.access$truncateForErrorMessage(r3, 64), "\" of length ", r3.length());
            return null;
        L5:
            return UuidKt__UuidJVMKt.uuidParseHex(r3);
        }

        @SinceKotlin(version = "2.1")
        public final Uuid parseHexDash(String r3) {
            if (r3.length() == 36) goto L5;
            Uuid$Companion$$ExternalSyntheticBUOutline0.m("Expected a 36-char string in the standard hex-and-dash UUID format, but was \"", UuidKt__UuidKt.access$truncateForErrorMessage(r3, 64), "\" of length ", r3.length());
            return null;
        L5:
            return UuidKt__UuidJVMKt.uuidParseHexDash(r3);
        }

        @SinceKotlin(version = MVEL.VERSION)
        public final Uuid parseHexDashOrNull(String r2) {
            if (r2.length() == 36) goto L7;
            return null;
        L7:
            return UuidKt__UuidJVMKt.uuidParseHexDashOrNull(r2);
        }

        @SinceKotlin(version = MVEL.VERSION)
        public final Uuid parseHexOrNull(String r2) {
            if (r2.length() == 32) goto L7;
            return null;
        L7:
            return UuidKt__UuidJVMKt.uuidParseHexOrNull(r2);
        }

        @SinceKotlin(version = MVEL.VERSION)
        public final Uuid parseOrNull(String r3) {
            int r0 = r3.length();
            if (r0 == 32) goto L11;
            if (r0 == 36) goto L9;
            return null;
        L9:
            return parseHexDashOrNull(r3);
        L11:
            return parseHexOrNull(r3);
        }

        public final Uuid random() {
            return generateV4();
        }
    }

    static {
        Companion = new Companion(null);
        NIL = new Uuid(0, 0);
    }

    private Uuid(long r1, long r3) {
        this.mostSignificantBits = r1;
        this.leastSignificantBits = r3;
    }

    public /* synthetic */ Uuid(long r1, long r3, DefaultConstructorMarker r5) {
        this(r1, r3);
    }

    public static final /* synthetic */ Uuid access$getNIL$cp() {
        return NIL;
    }

    @PublishedApi
    public static /* synthetic */ void getLeastSignificantBits$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getMostSignificantBits$annotations() {
    }

    private final void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization is supported via proxy only");
    }

    @InlineOnly
    private final <T> T toLongs(Function2<? super Long, ? super Long, ? extends T> r4) {
        return r4.invoke(Long.valueOf(getMostSignificantBits()), Long.valueOf(getLeastSignificantBits()));
    }

    @InlineOnly
    private final <T> T toULongs(Function2<? super ULong, ? super ULong, ? extends T> r4) {
        return r4.invoke(ULong.m2186boximpl(ULong.m2192constructorimpl(getMostSignificantBits())), ULong.m2186boximpl(ULong.m2192constructorimpl(getLeastSignificantBits())));
    }

    private final Object writeReplace() {
        return UuidKt__UuidJVMKt.serializedUuid(this);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Uuid r1) {
        return compareTo2(r1);
    }

    @SinceKotlin(version = "2.1")
    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(Uuid r5) {
        long r0 = this.mostSignificantBits;
        if (r0 == r5.mostSignificantBits) goto L7;
        return MutableScatterMap$$ExternalSyntheticBackport0.m(ULong.m2192constructorimpl(r0), ULong.m2192constructorimpl(r5.mostSignificantBits));
    L7:
        return MutableScatterMap$$ExternalSyntheticBackport0.m(ULong.m2192constructorimpl(this.leastSignificantBits), ULong.m2192constructorimpl(r5.leastSignificantBits));
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof Uuid) == true) goto L8;
        return false;
    L8:
        Uuid r82 = (Uuid) r8;
        if (this.mostSignificantBits == r82.mostSignificantBits) goto L11;
    L13:
        return false;
    L11:
        if (this.leastSignificantBits != r82.leastSignificantBits) goto L13;
        return true;
    }

    public final long getLeastSignificantBits() {
        return this.leastSignificantBits;
    }

    public final long getMostSignificantBits() {
        return this.mostSignificantBits;
    }

    public int hashCode() {
        return Long.hashCode(this.mostSignificantBits ^ this.leastSignificantBits);
    }

    public final byte[] toByteArray() {
        byte[] r0 = new byte[16];
        UuidKt__UuidJVMKt.setLongAt(r0, 0, this.mostSignificantBits);
        UuidKt__UuidJVMKt.setLongAt(r0, 8, this.leastSignificantBits);
        return r0;
    }

    @SinceKotlin(version = "2.1")
    public final String toHexDashString() {
        byte[] r3 = new byte[36];
        UuidKt__UuidJVMKt.formatBytesInto(this.mostSignificantBits, r3, 0, 0, 4);
        r3[8] = 45;
        UuidKt__UuidJVMKt.formatBytesInto(this.mostSignificantBits, r3, 9, 4, 6);
        r3[13] = 45;
        UuidKt__UuidJVMKt.formatBytesInto(this.mostSignificantBits, r3, 14, 6, 8);
        r3[18] = 45;
        UuidKt__UuidJVMKt.formatBytesInto(this.leastSignificantBits, r3, 19, 0, 2);
        r3[23] = 45;
        UuidKt__UuidJVMKt.formatBytesInto(this.leastSignificantBits, r3, 24, 2, 8);
        return StringsKt.decodeToString(r3);
    }

    public final String toHexString() {
        byte[] r3 = new byte[32];
        UuidKt__UuidJVMKt.formatBytesInto(this.mostSignificantBits, r3, 0, 0, 8);
        UuidKt__UuidJVMKt.formatBytesInto(this.leastSignificantBits, r3, 16, 0, 8);
        return StringsKt.decodeToString(r3);
    }

    public String toString() {
        return toHexDashString();
    }

    @SinceKotlin(version = "2.1")
    @ExperimentalUnsignedTypes
    /* renamed from: toUByteArray-TcUX1vc, reason: not valid java name */
    public final byte[] m3517toUByteArrayTcUX1vc() {
        return UByteArray.m2089constructorimpl(toByteArray());
    }
}
