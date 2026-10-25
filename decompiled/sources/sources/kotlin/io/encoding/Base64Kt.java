package kotlin.io.encoding;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0012\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0081\u0080\u0004\"\u000f\u0010\u0000\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u0004\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000\"\u000f\u0010\u0005\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"base64EncodeMap", _UrlKt.FRAGMENT_ENCODE_SET, "base64DecodeMap", _UrlKt.FRAGMENT_ENCODE_SET, "base64UrlEncodeMap", "base64UrlDecodeMap", "isInMimeAlphabet", _UrlKt.FRAGMENT_ENCODE_SET, "symbol", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBase64.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Base64.kt\nkotlin/io/encoding/Base64Kt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,844:1\n14123#2,3:845\n14123#2,3:848\n*S KotlinDebug\n*F\n+ 1 Base64.kt\nkotlin/io/encoding/Base64Kt\n*L\n786#1:845,3\n802#1:848,3\n*E\n"})
/* loaded from: classes5.dex */
public final class Base64Kt {
    private static final int[] base64DecodeMap = null;
    private static final byte[] base64EncodeMap = null;
    private static final int[] base64UrlDecodeMap = null;
    private static final byte[] base64UrlEncodeMap = null;

    static {
        byte[] r0 = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        base64EncodeMap = r0;
        int[] r2 = new int[256];
        ArraysKt.fill$default(r2, -1, 0, 0, 6, null);
        r2[61] = -2;
        int r5 = r0.length;
        int r6 = 0;
        int r7 = 0;
        int r8 = 0;
    L3:
        if (r7 >= r5) goto L5;
        r2[r0[r7]] = r8;
        r7 = r7 + 1;
        r8 = r8 + 1;
        goto L3
    L5:
        base64DecodeMap = r2;
        byte[] r02 = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        base64UrlEncodeMap = r02;
        int[] r72 = new int[256];
        ArraysKt.fill$default(r72, -1, 0, 0, 6, null);
        r72[61] = -2;
        int r1 = r02.length;
        int r22 = 0;
    L6:
        if (r6 >= r1) goto L8;
        r72[r02[r6]] = r22;
        r6 = r6 + 1;
        r22 = r22 + 1;
        goto L6
    L8:
        base64UrlDecodeMap = r72;
    }

    public static final /* synthetic */ int[] access$getBase64DecodeMap$p() {
        return base64DecodeMap;
    }

    public static final /* synthetic */ byte[] access$getBase64EncodeMap$p() {
        return base64EncodeMap;
    }

    public static final /* synthetic */ int[] access$getBase64UrlDecodeMap$p() {
        return base64UrlDecodeMap;
    }

    public static final /* synthetic */ byte[] access$getBase64UrlEncodeMap$p() {
        return base64UrlEncodeMap;
    }

    @SinceKotlin(version = "1.8")
    public static final boolean isInMimeAlphabet(int r2) {
        if (r2 < 0) goto L9;
        int[] r0 = base64DecodeMap;
        if (r2 < r0.length) goto L6;
        return false;
    L6:
        if (r0[r2] == (-1)) goto L12;
        return true;
    L12:
        return false;
    L9:
        return false;
    }
}
