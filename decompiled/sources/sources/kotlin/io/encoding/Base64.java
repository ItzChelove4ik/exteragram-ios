package kotlin.io.encoding;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dex.Dex$$ExternalSyntheticBUOutline0;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.WasExperimental;
import kotlin.collections.AbstractList;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

@SinceKotlin(version = "2.2")
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\t\b\u0017\u0018\u0000 B2\u00020\u0001:\u0002ABB)\bB\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\bH\u0087\u0080\u0004J&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u0006H\u0086\u0080\u0004J8\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u0006H\u0086\u0080\u0004J&\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u0006H\u0086\u0080\u0004JA\u0010\u001f\u001a\u0002H \"\f\b\u0000\u0010 *\u00060!j\u0002`\"2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u0002H 2\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u0006H\u0087\u0080\b¢\u0006\u0002\u0010#J&\u0010$\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u0006H\u0086\u0080\u0004J8\u0010%\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u0006H\u0086\u0080\u0004J&\u0010$\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020&2\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u0006H\u0086\u0080\u0004J8\u0010%\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020&2\u0006\u0010\u001b\u001a\u00020\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u0006H\u0086\u0080\u0004J'\u0010'\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0080\u0080\u0004¢\u0006\u0002\b(J7\u0010)\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0080\u0080\u0004¢\u0006\u0002\b*J\u0017\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u0006H\u0080\u0080\u0004¢\u0006\u0002\b-J\n\u0010.\u001a\u00020\u0003H\u0082\u0080\u0004J2\u0010/\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0082\u0080\u0004J'\u00100\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0080\u0080\u0004¢\u0006\u0002\b1J'\u00102\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020&2\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0080\u0080\u0004¢\u0006\u0002\b3J\u0017\u00104\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u0016H\u0080\u0080\u0004¢\u0006\u0002\b5J*\u00106\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u00107\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u00108\u001a\u00020\u0006H\u0082\u0080\u0004J\u0012\u00109\u001a\u00020:2\u0006\u00107\u001a\u00020\u0006H\u0082\u0080\u0004J\"\u0010;\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0082\u0080\u0004J'\u0010<\u001a\u00020:2\u0006\u0010,\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u0006H\u0080\u0080\u0004¢\u0006\u0002\b=J\"\u0010>\u001a\u00020:2\u0006\u0010?\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010@\u001a\u00020\u0006H\u0082\u0080\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u00020\u0003X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0015\u0010\u0005\u001a\u00020\u0006X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0007\u001a\u00020\bX\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000f\u0010\u0012\u001a\u00020\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lkotlin/io/encoding/Base64;", _UrlKt.FRAGMENT_ENCODE_SET, "isUrlSafe", _UrlKt.FRAGMENT_ENCODE_SET, "isMimeScheme", "mimeLineLength", _UrlKt.FRAGMENT_ENCODE_SET, "paddingOption", "Lkotlin/io/encoding/Base64$PaddingOption;", "<init>", "(ZZILkotlin/io/encoding/Base64$PaddingOption;)V", "isUrlSafe$kotlin_stdlib", "()Z", "isMimeScheme$kotlin_stdlib", "getMimeLineLength$kotlin_stdlib", "()I", "getPaddingOption$kotlin_stdlib", "()Lkotlin/io/encoding/Base64$PaddingOption;", "mimeGroupsPerLine", "withPadding", "option", "encodeToByteArray", _UrlKt.FRAGMENT_ENCODE_SET, "source", "startIndex", "endIndex", "encodeIntoByteArray", "destination", "destinationOffset", "encode", _UrlKt.FRAGMENT_ENCODE_SET, "encodeToAppendable", "A", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "([BLjava/lang/Appendable;II)Ljava/lang/Appendable;", "decode", "decodeIntoByteArray", _UrlKt.FRAGMENT_ENCODE_SET, "encodeToByteArrayImpl", "encodeToByteArrayImpl$kotlin_stdlib", "encodeIntoByteArrayImpl", "encodeIntoByteArrayImpl$kotlin_stdlib", "encodeSize", "sourceSize", "encodeSize$kotlin_stdlib", "shouldPadOnEncode", "decodeImpl", "decodeSize", "decodeSize$kotlin_stdlib", "charsToBytesImpl", "charsToBytesImpl$kotlin_stdlib", "bytesToStringImpl", "bytesToStringImpl$kotlin_stdlib", "handlePaddingSymbol", "padIndex", "byteStart", "checkPaddingIsAllowed", _UrlKt.FRAGMENT_ENCODE_SET, "skipIllegalSymbolsIfMime", "checkSourceBounds", "checkSourceBounds$kotlin_stdlib", "checkDestinationBounds", "destinationSize", "capacityNeeded", "PaddingOption", "Default", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@WasExperimental(markerClass = {ExperimentalEncodingApi.class})
/* loaded from: classes5.dex */
public class Base64 {
    public static final Default Default = null;
    private static final Base64 Mime = null;
    private static final Base64 Pem = null;
    private static final Base64 UrlSafe = null;
    private static final int bitsPerByte = 8;
    private static final int bitsPerSymbol = 6;
    public static final int bytesPerGroup = 3;
    private static final int lineLengthMime = 76;
    private static final int lineLengthPem = 64;
    private static final byte[] mimeLineSeparatorSymbols = null;
    public static final byte padSymbol = 61;
    public static final int symbolsPerGroup = 4;
    private final boolean isMimeScheme;
    private final boolean isUrlSafe;
    private final int mimeGroupsPerLine;
    private final int mimeLineLength;
    private final PaddingOption paddingOption;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\u0005X\u0080Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\b\u001a\u00020\u0005X\u0080Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\t\u001a\u00020\nX\u0080Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000b\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\f\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u0015\u0010\r\u001a\u00020\u000eX\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0011\u001a\u00020\u0001X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0014\u001a\u00020\u0001X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0015\u0010\u0016\u001a\u00020\u0001X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013¨\u0006\u0018"}, d2 = {"Lkotlin/io/encoding/Base64$Default;", "Lkotlin/io/encoding/Base64;", "<init>", "()V", "bitsPerByte", _UrlKt.FRAGMENT_ENCODE_SET, "bitsPerSymbol", "bytesPerGroup", "symbolsPerGroup", "padSymbol", _UrlKt.FRAGMENT_ENCODE_SET, "lineLengthMime", "lineLengthPem", "mimeLineSeparatorSymbols", _UrlKt.FRAGMENT_ENCODE_SET, "getMimeLineSeparatorSymbols$kotlin_stdlib", "()[B", "UrlSafe", "getUrlSafe", "()Lkotlin/io/encoding/Base64;", "Mime", "getMime", "Pem", "getPem", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Default extends Base64 {
        private Default() {
            super(false, false, -1, PaddingOption.PRESENT, null);
        }

        public /* synthetic */ Default(DefaultConstructorMarker r1) {
            this();
        }

        public final Base64 getMime() {
            return Base64.access$getMime$cp();
        }

        public final byte[] getMimeLineSeparatorSymbols$kotlin_stdlib() {
            return Base64.access$getMimeLineSeparatorSymbols$cp();
        }

        public final Base64 getPem() {
            return Base64.access$getPem$cp();
        }

        public final Base64 getUrlSafe() {
            return Base64.access$getUrlSafe$cp();
        }
    }

    @SinceKotlin(version = "2.0")
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lkotlin/io/encoding/Base64$PaddingOption;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "PRESENT", "ABSENT", "PRESENT_OPTIONAL", "ABSENT_OPTIONAL", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public enum PaddingOption extends Enum<PaddingOption> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ PaddingOption[] $VALUES = null;
        public static final PaddingOption ABSENT = null;
        public static final PaddingOption ABSENT_OPTIONAL = null;
        public static final PaddingOption PRESENT = null;
        public static final PaddingOption PRESENT_OPTIONAL = null;

        private static final /* synthetic */ PaddingOption[] $values() {
            return new PaddingOption[]{PRESENT, ABSENT, PRESENT_OPTIONAL, ABSENT_OPTIONAL};
        }

        static {
            PRESENT = new PaddingOption("PRESENT", 0);
            ABSENT = new PaddingOption("ABSENT", 1);
            PRESENT_OPTIONAL = new PaddingOption("PRESENT_OPTIONAL", 2);
            ABSENT_OPTIONAL = new PaddingOption("ABSENT_OPTIONAL", 3);
            PaddingOption[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        PaddingOption(String r1, int r2) {
        }

        public static EnumEntries<PaddingOption> getEntries() {
            return $ENTRIES;
        }

        public static PaddingOption valueOf(String r1) {
            return (PaddingOption) Enum.valueOf(PaddingOption.class, r1);
        }

        public static PaddingOption[] values() {
            return (PaddingOption[]) $VALUES.clone();
        }
    }

    static {
        Default = new Default(null);
        mimeLineSeparatorSymbols = new byte[]{13, 10};
        PaddingOption r1 = PaddingOption.PRESENT;
        UrlSafe = new Base64(true, false, -1, r1);
        Mime = new Base64(false, true, 76, r1);
        Pem = new Base64(false, true, 64, r1);
    }

    private Base64(boolean r1, boolean r2, int r3, PaddingOption r4) {
        this.isUrlSafe = r1;
        this.isMimeScheme = r2;
        this.mimeLineLength = r3;
        this.paddingOption = r4;
        if (r1 == false) goto L8;
        if (r2 == false) goto L8;
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        throw null;
    L8:
        this.mimeGroupsPerLine = r3 / 4;
    }

    public /* synthetic */ Base64(boolean r1, boolean r2, int r3, PaddingOption r4, DefaultConstructorMarker r5) {
        this(r1, r2, r3, r4);
    }

    public static final /* synthetic */ Base64 access$getMime$cp() {
        return Mime;
    }

    public static final /* synthetic */ byte[] access$getMimeLineSeparatorSymbols$cp() {
        return mimeLineSeparatorSymbols;
    }

    public static final /* synthetic */ Base64 access$getPem$cp() {
        return Pem;
    }

    public static final /* synthetic */ Base64 access$getUrlSafe$cp() {
        return UrlSafe;
    }

    private final void checkDestinationBounds(int r7, int r8, int r9) {
        if (r8 < 0) goto L11;
        if (r8 > r7) goto L11;
        int r6 = r8 + r9;
        if (r6 < 0) goto L9;
        if (r6 > r7) goto L9;
        return;
    L9:
        Base64$$ExternalSyntheticBUOutline1.m("The destination array does not have enough capacity, destination offset: ", r8, ", destination size: ", r7, ", capacity needed: ", r9);
        return;
    L11:
        Dex$$ExternalSyntheticBUOutline0.m("destination offset: ", r8, ", destination size: ", r7);
    }

    private final void checkPaddingIsAllowed(int r2) {
        if (this.paddingOption == PaddingOption.ABSENT) goto L5;
        return;
    L5:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("The padding option is set to ABSENT, but the input has a pad character at index ", r2);
    }

    public static /* synthetic */ byte[] decode$default(Base64 r0, CharSequence r1, int r2, int r3, int r4, Object r5) {
        if (r5 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: decode");
        return null;
    L4:
        if ((r4 & 2) == 0) goto L7;
        r2 = 0;
    L7:
        if ((r4 & 4) == 0) goto L10;
        r3 = r1.length();
    L10:
        return r0.decode(r1, r2, r3);
    }

    public static /* synthetic */ byte[] decode$default(Base64 r0, byte[] r1, int r2, int r3, int r4, Object r5) {
        if (r5 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: decode");
        return null;
    L4:
        if ((r4 & 2) == 0) goto L7;
        r2 = 0;
    L7:
        if ((r4 & 4) == 0) goto L10;
        r3 = r1.length;
    L10:
        return r0.decode(r1, r2, r3);
    }

    private final int decodeImpl(byte[] r21, byte[] r22, int r23, int r24, int r25) {
        if (this.isUrlSafe == false) goto L5;
        int[] r3 = Base64Kt.access$getBase64UrlDecodeMap$p();
    L6:
        int r9 = r23;
        int r6 = r24;
        int r7 = -8;
        int r8 = 0;
    L8:
        if (r6 >= r25) goto L29;
        if (r7 != (-8)) goto L15;
        if ((r6 + 3) >= r25) goto L15;
        int r16 = 0;
        int r242 = 8;
        int r17 = r6 + 4;
        int r19 = 1;
        int r4 = (((r3[r21[r6] & UByte.MAX_VALUE] << 18) | (r3[r21[r6 + 1] & UByte.MAX_VALUE] << 12)) | (r3[r21[r6 + 2] & UByte.MAX_VALUE] << 6)) | r3[r21[r6 + 3] & UByte.MAX_VALUE];
        if (r4 >= 0) goto L14;
    L16:
        int r42 = r21[r6] & UByte.MAX_VALUE;
        int r13 = r3[r42];
        if (r13 < 0) goto L18;
        r6 = r6 + 1;
        r8 = (r8 << 6) | r13;
        int r43 = r7 + 6;
        if (r43 >= 0) goto L27;
        r7 = r43;
        goto L8
    L27:
        r22[r9] = (byte) (r8 >>> r43);
        r8 = r8 & ((r19 << r43) - 1);
        r7 = r7 - 2;
        r9 = r9 + 1;
        goto L8
    L18:
        if (r13 == (-2)) goto L19;
        if (this.isMimeScheme == false) goto L24;
        r6 = r6 + 1;
        goto L8
    L24:
        throw new IllegalArgumentException("Invalid symbol '" + ((char) r42) + "'(" + Integer.toString(r42, CharsKt.checkRadix(r242)) + ") at index " + r6);
    L19:
        r6 = handlePaddingSymbol(r21, r6, r25, r7);
        int r32 = r19;
    L30:
        if (r7 == (-2)) goto L47;
        if (r7 == (-8)) goto L38;
        if (r32 != 0) goto L38;
        if (this.paddingOption != PaddingOption.PRESENT) goto L38;
        f$$ExternalSyntheticBUOutline1.m("The padding option is set to PRESENT, but the input is not properly padded");
        return r16;
    L38:
        if (r8 != 0) goto L45;
        int r0 = skipIllegalSymbolsIfMime(r21, r6, r25);
        if (r0 >= r25) goto L42;
        int r1 = r21[r0] & UByte.MAX_VALUE;
        char r33 = (char) r1;
        String r12 = Integer.toString(r1, CharsKt.checkRadix(r242));
        StringBuilder r44 = new StringBuilder("Symbol '");
        r44.append(r33);
        r44.append("'(");
        r44.append(r12);
        r44.append(") at index ");
        r44.append(r0 - 1);
        r44.append(" is prohibited after the pad character");
        throw new IllegalArgumentException(r44.toString());
    L42:
        return r9 - r23;
    L45:
        f$$ExternalSyntheticBUOutline1.m("The pad bits must be zeros");
        return r16;
    L47:
        f$$ExternalSyntheticBUOutline1.m("The last unit of input does not have enough bits");
        return r16;
    L14:
        r22[r9] = (byte) (r4 >> 16);
        int r10 = r9 + 2;
        r22[r9 + 1] = (byte) (r4 >> 8);
        r9 = r9 + 3;
        r22[r10] = (byte) r4;
        r6 = r17;
    L15:
        r242 = 8;
        r16 = 0;
        r19 = 1;
        goto L16
    L29:
        r242 = 8;
        r16 = 0;
        r32 = 0;
        goto L30
    L5:
        r3 = Base64Kt.access$getBase64DecodeMap$p();
        goto L6
    }

    public static /* synthetic */ int decodeIntoByteArray$default(Base64 r1, CharSequence r2, byte[] r3, int r4, int r5, int r6, int r7, Object r8) {
        if (r8 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: decodeIntoByteArray");
        return 0;
    L4:
        if ((r7 & 4) == 0) goto L7;
        r4 = 0;
    L7:
        if ((r7 & 8) == 0) goto L10;
        r5 = 0;
    L10:
        if ((r7 & 16) == 0) goto L13;
        r6 = r2.length();
    L13:
        return r1.decodeIntoByteArray(r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ int decodeIntoByteArray$default(Base64 r1, byte[] r2, byte[] r3, int r4, int r5, int r6, int r7, Object r8) {
        if (r8 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: decodeIntoByteArray");
        return 0;
    L4:
        if ((r7 & 4) == 0) goto L7;
        r4 = 0;
    L7:
        if ((r7 & 8) == 0) goto L10;
        r5 = 0;
    L10:
        if ((r7 & 16) == 0) goto L13;
        r6 = r2.length;
    L13:
        return r1.decodeIntoByteArray(r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ String encode$default(Base64 r0, byte[] r1, int r2, int r3, int r4, Object r5) {
        if (r5 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: encode");
        return null;
    L4:
        if ((r4 & 2) == 0) goto L7;
        r2 = 0;
    L7:
        if ((r4 & 4) == 0) goto L10;
        r3 = r1.length;
    L10:
        return r0.encode(r1, r2, r3);
    }

    public static /* synthetic */ int encodeIntoByteArray$default(Base64 r1, byte[] r2, byte[] r3, int r4, int r5, int r6, int r7, Object r8) {
        if (r8 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: encodeIntoByteArray");
        return 0;
    L4:
        if ((r7 & 4) == 0) goto L7;
        r4 = 0;
    L7:
        if ((r7 & 8) == 0) goto L10;
        r5 = 0;
    L10:
        if ((r7 & 16) == 0) goto L13;
        r6 = r2.length;
    L13:
        return r1.encodeIntoByteArray(r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ Appendable encodeToAppendable$default(Base64 r0, byte[] r1, Appendable r2, int r3, int r4, int r5, Object r6) {
        if (r6 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: encodeToAppendable");
        return null;
    L4:
        if ((r5 & 4) == 0) goto L7;
        r3 = 0;
    L7:
        if ((r5 & 8) == 0) goto L10;
        r4 = r1.length;
    L10:
        return r0.encodeToAppendable(r1, r2, r3, r4);
    }

    public static /* synthetic */ byte[] encodeToByteArray$default(Base64 r0, byte[] r1, int r2, int r3, int r4, Object r5) {
        if (r5 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: encodeToByteArray");
        return null;
    L4:
        if ((r4 & 2) == 0) goto L7;
        r2 = 0;
    L7:
        if ((r4 & 4) == 0) goto L10;
        r3 = r1.length;
    L10:
        return r0.encodeToByteArray(r1, r2, r3);
    }

    private final int handlePaddingSymbol(byte[] r2, int r3, int r4, int r5) {
        if (r5 != (-8)) goto L5;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Redundant pad character at index ", r3);
        return 0;
    L5:
        if (r5 != (-6)) goto L7;
        checkPaddingIsAllowed(r3);
        return r3 + 1;
    L7:
        if (r5 != (-4)) goto L9;
        checkPaddingIsAllowed(r3);
        int r1 = skipIllegalSymbolsIfMime(r2, r3 + 1, r4);
        if (r1 != r4) goto L18;
    L21:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Missing one pad character at index ", r1);
        return 0;
    L18:
        if (r2[r1] != 61) goto L21;
        return r1 + 1;
    L9:
        if (r5 == (-2)) goto L11;
        Segment$$ExternalSyntheticBUOutline1.m("Unreachable");
        return 0;
    L11:
        return r3 + 1;
    }

    private final boolean shouldPadOnEncode() {
        PaddingOption r1 = this.paddingOption;
        if (r1 != PaddingOption.PRESENT) goto L5;
        return true;
    L5:
        if (r1 == PaddingOption.PRESENT_OPTIONAL) goto L11;
        return false;
    L11:
        return true;
    }

    private final int skipIllegalSymbolsIfMime(byte[] r2, int r3, int r4) {
        if (this.isMimeScheme == true) goto L5;
        return r3;
    L5:
        if (r3 >= r4) goto L10;
        int r1 = r2[r3] & UByte.MAX_VALUE;
        if (Base64Kt.access$getBase64DecodeMap$p()[r1] != (-1)) goto L10;
        r3 = r3 + 1;
    L10:
        return r3;
    }

    public final String bytesToStringImpl$kotlin_stdlib(byte[] r4) {
        StringBuilder r3 = new StringBuilder(r4.length);
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L6;
        r3.append((char) r4[r1]);
        r1 = r1 + 1;
        goto L3
    L6:
        return r3.toString();
    }

    public final byte[] charsToBytesImpl$kotlin_stdlib(CharSequence r4, int r5, int r6) {
        checkSourceBounds$kotlin_stdlib(r4.length(), r5, r6);
        byte[] r3 = new byte[r6 - r5];
        int r0 = 0;
    L3:
        if (r5 >= r6) goto L9;
        char r1 = r4.charAt(r5);
        if (r1 > 255) goto L7;
        r3[r0] = (byte) r1;
        r0 = r0 + 1;
    L8:
        r5 = r5 + 1;
        goto L3
    L7:
        r3[r0] = 63;
        r0 = r0 + 1;
        goto L8
    L9:
        return r3;
    }

    public final void checkSourceBounds$kotlin_stdlib(int r1, int r2, int r3) {
        AbstractList.Companion.checkBoundsIndexes$kotlin_stdlib(r2, r3, r1);
    }

    public final byte[] decode(CharSequence r7, int r8, int r9) {
        if ((r7 instanceof String) == false) goto L6;
        String r72 = (String) r7;
        checkSourceBounds$kotlin_stdlib(r72.length(), r8, r9);
        byte[] r73 = r72.substring(r8, r9).getBytes(Charsets.ISO_8859_1);
    L8:
        return decode$default(this, r73, 0, 0, 6, null);
    L6:
        r73 = charsToBytesImpl$kotlin_stdlib(r7, r8, r9);
        goto L8
    }

    public final byte[] decode(byte[] r8, int r9, int r10) {
        checkSourceBounds$kotlin_stdlib(r8.length, r9, r10);
        int r0 = decodeSize$kotlin_stdlib(r8, r9, r10);
        byte[] r3 = new byte[r0];
        if (decodeImpl(r8, r3, 0, r9, r10) != r0) goto L5;
        return r3;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return null;
    }

    public final int decodeIntoByteArray(CharSequence r9, byte[] r10, int r11, int r12, int r13) {
        if ((r9 instanceof String) == false) goto L6;
        String r92 = (String) r9;
        checkSourceBounds$kotlin_stdlib(r92.length(), r12, r13);
        byte[] r93 = r92.substring(r12, r13).getBytes(Charsets.ISO_8859_1);
    L8:
        return decodeIntoByteArray$default(this, r93, r10, r11, 0, 0, 24, null);
    L6:
        r93 = charsToBytesImpl$kotlin_stdlib(r9, r12, r13);
        goto L8
    }

    public final int decodeIntoByteArray(byte[] r3, byte[] r4, int r5, int r6, int r7) {
        checkSourceBounds$kotlin_stdlib(r3.length, r6, r7);
        checkDestinationBounds(r4.length, r5, decodeSize$kotlin_stdlib(r3, r6, r7));
        return decodeImpl(r3, r4, r5, r6, r7);
    }

    public final int decodeSize$kotlin_stdlib(byte[] r4, int r5, int r6) {
        int r0 = r6 - r5;
        if (r0 != 0) goto L6;
        return 0;
    L6:
        if (r0 != 1) goto L8;
        Base64$$ExternalSyntheticBUOutline0.m("Input should have at least 2 symbols for Base64 decoding, startIndex: ", r5, ", endIndex: ", r6);
        return 0;
    L8:
        if (this.isMimeScheme == false) goto L18;
    L9:
        if (r5 >= r6) goto L24;
        int r3 = Base64Kt.access$getBase64DecodeMap$p()[r4[r5] & UByte.MAX_VALUE];
        if (r3 >= 0) goto L16;
        if (r3 == (-2)) goto L14;
        r0 = r0 - 1;
        goto L16
    L14:
        r0 = r0 - (r6 - r5);
    L16:
        r5 = r5 + 1;
    L24:
        return (int) ((r0 * 6) / 8);
    L18:
        if (r4[r6 - 1] != 61) goto L24;
        int r32 = r0 - 1;
        if (r4[r6 - 2] != 61) goto L22;
        r0 = r0 - 2;
        goto L24
    L22:
        r0 = r32;
        goto L24
    }

    public final String encode(byte[] r1, int r2, int r3) {
        return new String(encodeToByteArrayImpl$kotlin_stdlib(r1, r2, r3), Charsets.ISO_8859_1);
    }

    public final int encodeIntoByteArray(byte[] r1, byte[] r2, int r3, int r4, int r5) {
        return encodeIntoByteArrayImpl$kotlin_stdlib(r1, r2, r3, r4, r5);
    }

    public final int encodeIntoByteArrayImpl$kotlin_stdlib(byte[] r18, byte[] r19, int r20, int r21, int r22) {
        int r4 = r21;
        checkSourceBounds$kotlin_stdlib(r18.length, r4, r22);
        checkDestinationBounds(r19.length, r20, encodeSize$kotlin_stdlib(r22 - r4));
        if (this.isUrlSafe == false) goto L5;
        byte[] r6 = Base64Kt.access$getBase64UrlEncodeMap$p();
    L7:
        if (this.isMimeScheme == false) goto L9;
        int r7 = this.mimeGroupsPerLine;
    L10:
        int r8 = r20;
    L12:
        if ((r4 + 2) >= r22) goto L19;
        int r9 = Math.min((r22 - r4) / 3, r7);
        int r12 = 0;
    L14:
        if (r12 >= r9) goto L16;
        int r14 = r18[r4] & UByte.MAX_VALUE;
        int r15 = r4 + 2;
        int r13 = r18[r4 + 1] & UByte.MAX_VALUE;
        r4 = r4 + 3;
        int r132 = ((r13 << 8) | (r14 << 16)) | (r18[r15] & UByte.MAX_VALUE);
        r19[r8] = r6[r132 >>> 18];
        r19[r8 + 1] = r6[(r132 >>> 12) & 63];
        int r142 = r8 + 3;
        r19[r8 + 2] = r6[(r132 >>> 6) & 63];
        r8 = r8 + 4;
        r19[r142] = r6[r132 & 63];
        r12 = r12 + 1;
        goto L14
    L16:
        if (r9 != r7) goto L12;
        if (r4 == r22) goto L12;
        int r92 = r8 + 1;
        byte[] r122 = mimeLineSeparatorSymbols;
        r19[r8] = r122[0];
        r8 = r8 + 2;
        r19[r92] = r122[1];
        goto L12
    L19:
        int r72 = r22 - r4;
        if (r72 != 1) goto L22;
        int r73 = r4 + 1;
        int r1 = (r18[r4] & UByte.MAX_VALUE) << 4;
        r19[r8] = r6[r1 >>> 6];
        int r11 = r8 + 2;
        r19[r8 + 1] = r6[r1 & 63];
        if (shouldPadOnEncode() == false) goto L31;
        int r0 = r8 + 3;
        r19[r11] = padSymbol;
        r8 = r8 + 4;
        r19[r0] = padSymbol;
        r4 = r73;
    L32:
        if (r4 == r22) goto L34;
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return 0;
    L34:
        return r8 - r20;
    L31:
        r4 = r73;
        r8 = r11;
        goto L32
    L22:
        if (r72 != 2) goto L32;
        int r74 = r4 + 1;
        int r123 = r18[r4] & UByte.MAX_VALUE;
        r4 = r4 + 2;
        int r16 = ((r18[r74] & UByte.MAX_VALUE) << 2) | (r123 << 10);
        r19[r8] = r6[r16 >>> 12];
        r19[r8 + 1] = r6[(r16 >>> 6) & 63];
        int r75 = r8 + 3;
        r19[r8 + 2] = r6[r16 & 63];
        if (shouldPadOnEncode() == false) goto L27;
        r8 = r8 + 4;
        r19[r75] = padSymbol;
        goto L32
    L27:
        r8 = r75;
        goto L32
    L9:
        r7 = Integer.MAX_VALUE;
        goto L10
    L5:
        r6 = Base64Kt.access$getBase64EncodeMap$p();
        goto L7
    }

    public final int encodeSize$kotlin_stdlib(int r4) {
        int r0 = r4 / 3;
        int r42 = r4 % 3;
        int r1 = 4;
        int r02 = r0 * 4;
        if (r42 == 0) goto L10;
        if (shouldPadOnEncode() == true) goto L8;
        r1 = r42 + 1;
    L8:
        r02 = r02 + r1;
    L10:
        if (r02 >= 0) goto L12;
        f$$ExternalSyntheticBUOutline1.m("Input is too big");
        return 0;
    L12:
        if (this.isMimeScheme == false) goto L14;
        r02 = r02 + (((r02 - 1) / this.mimeLineLength) * 2);
    L14:
        if (r02 < 0) goto L16;
        return r02;
    L16:
        f$$ExternalSyntheticBUOutline1.m("Input is too big");
        return 0;
    }

    @IgnorableReturnValue
    public final <A extends Appendable> A encodeToAppendable(byte[] r1, A r2, int r3, int r4) {
        r2.append(new String(encodeToByteArrayImpl$kotlin_stdlib(r1, r3, r4), Charsets.ISO_8859_1));
        return r2;
    }

    public final byte[] encodeToByteArray(byte[] r1, int r2, int r3) {
        return encodeToByteArrayImpl$kotlin_stdlib(r1, r2, r3);
    }

    public final byte[] encodeToByteArrayImpl$kotlin_stdlib(byte[] r8, int r9, int r10) {
        checkSourceBounds$kotlin_stdlib(r8.length, r9, r10);
        byte[] r3 = new byte[encodeSize$kotlin_stdlib(r10 - r9)];
        encodeIntoByteArrayImpl$kotlin_stdlib(r8, r3, 0, r9, r10);
        return r3;
    }

    public final int getMimeLineLength$kotlin_stdlib() {
        return this.mimeLineLength;
    }

    public final PaddingOption getPaddingOption$kotlin_stdlib() {
        return this.paddingOption;
    }

    public final boolean isMimeScheme$kotlin_stdlib() {
        return this.isMimeScheme;
    }

    public final boolean isUrlSafe$kotlin_stdlib() {
        return this.isUrlSafe;
    }

    @SinceKotlin(version = "2.0")
    public final Base64 withPadding(PaddingOption r4) {
        if (this.paddingOption != r4) goto L6;
        return this;
    L6:
        return new Base64(this.isUrlSafe, this.isMimeScheme, this.mimeLineLength, r4);
    }
}
