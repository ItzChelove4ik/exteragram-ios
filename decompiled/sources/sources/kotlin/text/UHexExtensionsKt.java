package kotlin.text;

import kotlin.ExperimentalStdlibApi;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.WasExperimental;
import kotlin.internal.InlineOnly;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004¢\u0006\u0004\b\u0005\u0010\u0006\u001a3\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004¢\u0006\u0004\b\n\u0010\u000b\u001a\u001d\u0010\f\u001a\u00020\u0002*\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004¢\u0006\u0002\u0010\r\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u000e2\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001d\u0010\u0011\u001a\u00020\u000e*\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004¢\u0006\u0002\u0010\u0012\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00132\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u001d\u0010\u0016\u001a\u00020\u0013*\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004¢\u0006\u0002\u0010\u0017\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00182\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u001d\u0010\u001b\u001a\u00020\u0018*\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004¢\u0006\u0002\u0010\u001c\u001a\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u001d2\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001d\u0010 \u001a\u00020\u001d*\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004¢\u0006\u0002\u0010!¨\u0006\""}, d2 = {"toHexString", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/UByteArray;", "format", "Lkotlin/text/HexFormat;", "toHexString-zHuV2wU", "([BLkotlin/text/HexFormat;)Ljava/lang/String;", "startIndex", _UrlKt.FRAGMENT_ENCODE_SET, "endIndex", "toHexString-lZCiFrA", "([BIILkotlin/text/HexFormat;)Ljava/lang/String;", "hexToUByteArray", "(Ljava/lang/String;Lkotlin/text/HexFormat;)[B", "Lkotlin/UByte;", "toHexString-ZQbaR00", "(BLkotlin/text/HexFormat;)Ljava/lang/String;", "hexToUByte", "(Ljava/lang/String;Lkotlin/text/HexFormat;)B", "Lkotlin/UShort;", "toHexString-r3ox_E0", "(SLkotlin/text/HexFormat;)Ljava/lang/String;", "hexToUShort", "(Ljava/lang/String;Lkotlin/text/HexFormat;)S", "Lkotlin/UInt;", "toHexString-8M7LxHw", "(ILkotlin/text/HexFormat;)Ljava/lang/String;", "hexToUInt", "(Ljava/lang/String;Lkotlin/text/HexFormat;)I", "Lkotlin/ULong;", "toHexString-8UJCm-I", "(JLkotlin/text/HexFormat;)Ljava/lang/String;", "hexToULong", "(Ljava/lang/String;Lkotlin/text/HexFormat;)J", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class UHexExtensionsKt {
    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final byte hexToUByte(String r0, HexFormat r1) {
        return UByte.m2036constructorimpl(HexExtensionsKt.hexToByte(r0, r1));
    }

    public static /* synthetic */ byte hexToUByte$default(String r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return UByte.m2036constructorimpl(HexExtensionsKt.hexToByte(r0, r1));
    }

    @SinceKotlin(version = "2.2")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    private static final byte[] hexToUByteArray(String r0, HexFormat r1) {
        return UByteArray.m2089constructorimpl(HexExtensionsKt.hexToByteArray(r0, r1));
    }

    public static /* synthetic */ byte[] hexToUByteArray$default(String r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return UByteArray.m2089constructorimpl(HexExtensionsKt.hexToByteArray(r0, r1));
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final int hexToUInt(String r0, HexFormat r1) {
        return UInt.m2113constructorimpl(HexExtensionsKt.hexToInt(r0, r1));
    }

    public static /* synthetic */ int hexToUInt$default(String r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return UInt.m2113constructorimpl(HexExtensionsKt.hexToInt(r0, r1));
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final long hexToULong(String r0, HexFormat r1) {
        return ULong.m2192constructorimpl(HexExtensionsKt.hexToLong(r0, r1));
    }

    public static /* synthetic */ long hexToULong$default(String r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return ULong.m2192constructorimpl(HexExtensionsKt.hexToLong(r0, r1));
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    private static final short hexToUShort(String r0, HexFormat r1) {
        return UShort.m2299constructorimpl(HexExtensionsKt.hexToShort(r0, r1));
    }

    public static /* synthetic */ short hexToUShort$default(String r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return UShort.m2299constructorimpl(HexExtensionsKt.hexToShort(r0, r1));
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: toHexString-8M7LxHw, reason: not valid java name */
    private static final String m3347toHexString8M7LxHw(int r0, HexFormat r1) {
        return HexExtensionsKt.toHexString(r0, r1);
    }

    /* renamed from: toHexString-8M7LxHw$default, reason: not valid java name */
    public static /* synthetic */ String m3348toHexString8M7LxHw$default(int r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return HexExtensionsKt.toHexString(r0, r1);
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: toHexString-8UJCm-I, reason: not valid java name */
    private static final String m3349toHexString8UJCmI(long r0, HexFormat r2) {
        return HexExtensionsKt.toHexString(r0, r2);
    }

    /* renamed from: toHexString-8UJCm-I$default, reason: not valid java name */
    public static /* synthetic */ String m3350toHexString8UJCmI$default(long r0, HexFormat r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r2 = HexFormat.Companion.getDefault();
    L6:
        return HexExtensionsKt.toHexString(r0, r2);
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: toHexString-ZQbaR00, reason: not valid java name */
    private static final String m3351toHexStringZQbaR00(byte r0, HexFormat r1) {
        return HexExtensionsKt.toHexString(r0, r1);
    }

    /* renamed from: toHexString-ZQbaR00$default, reason: not valid java name */
    public static /* synthetic */ String m3352toHexStringZQbaR00$default(byte r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return HexExtensionsKt.toHexString(r0, r1);
    }

    @SinceKotlin(version = "2.2")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: toHexString-lZCiFrA, reason: not valid java name */
    private static final String m3353toHexStringlZCiFrA(byte[] r0, int r1, int r2, HexFormat r3) {
        return HexExtensionsKt.toHexString(r0, r1, r2, r3);
    }

    /* renamed from: toHexString-lZCiFrA$default, reason: not valid java name */
    public static /* synthetic */ String m3354toHexStringlZCiFrA$default(byte[] r0, int r1, int r2, HexFormat r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = UByteArray.m2095getSizeimpl(r0);
    L9:
        if ((r4 & 4) == 0) goto L12;
        r3 = HexFormat.Companion.getDefault();
    L12:
        return HexExtensionsKt.toHexString(r0, r1, r2, r3);
    }

    @SinceKotlin(version = "2.2")
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @InlineOnly
    /* renamed from: toHexString-r3ox_E0, reason: not valid java name */
    private static final String m3355toHexStringr3ox_E0(short r0, HexFormat r1) {
        return HexExtensionsKt.toHexString(r0, r1);
    }

    /* renamed from: toHexString-r3ox_E0$default, reason: not valid java name */
    public static /* synthetic */ String m3356toHexStringr3ox_E0$default(short r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return HexExtensionsKt.toHexString(r0, r1);
    }

    @SinceKotlin(version = "2.2")
    @ExperimentalUnsignedTypes
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    /* renamed from: toHexString-zHuV2wU, reason: not valid java name */
    private static final String m3357toHexStringzHuV2wU(byte[] r0, HexFormat r1) {
        return HexExtensionsKt.toHexString(r0, r1);
    }

    /* renamed from: toHexString-zHuV2wU$default, reason: not valid java name */
    public static /* synthetic */ String m3358toHexStringzHuV2wU$default(byte[] r0, HexFormat r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = HexFormat.Companion.getDefault();
    L6:
        return HexExtensionsKt.toHexString(r0, r1);
    }
}
