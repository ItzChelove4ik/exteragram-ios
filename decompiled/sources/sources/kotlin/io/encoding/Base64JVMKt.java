package kotlin.io.encoding;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.text.Charsets;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0081\u0088\u0004\u001a&\u0010\b\u001a\u00020\t*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0081\u0088\u0004\u001a6\u0010\n\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0081\u0088\u0004\u001a&\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0081\u0088\u0004¨\u0006\u000e"}, d2 = {"platformCharsToBytes", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/io/encoding/Base64;", "source", _UrlKt.FRAGMENT_ENCODE_SET, "startIndex", _UrlKt.FRAGMENT_ENCODE_SET, "endIndex", "platformEncodeToString", _UrlKt.FRAGMENT_ENCODE_SET, "platformEncodeIntoByteArray", "destination", "destinationOffset", "platformEncodeToByteArray", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class Base64JVMKt {
    @SinceKotlin(version = "1.8")
    @InlineOnly
    private static final byte[] platformCharsToBytes(Base64 r1, CharSequence r2, int r3, int r4) {
        if ((r2 instanceof String) == false) goto L7;
        String r22 = (String) r2;
        r1.checkSourceBounds$kotlin_stdlib(r22.length(), r3, r4);
        return r22.substring(r3, r4).getBytes(Charsets.ISO_8859_1);
    L7:
        return r1.charsToBytesImpl$kotlin_stdlib(r2, r3, r4);
    }

    @SinceKotlin(version = "1.8")
    @InlineOnly
    private static final int platformEncodeIntoByteArray(Base64 r0, byte[] r1, byte[] r2, int r3, int r4, int r5) {
        return r0.encodeIntoByteArrayImpl$kotlin_stdlib(r1, r2, r3, r4, r5);
    }

    @SinceKotlin(version = "1.8")
    @InlineOnly
    private static final byte[] platformEncodeToByteArray(Base64 r0, byte[] r1, int r2, int r3) {
        return r0.encodeToByteArrayImpl$kotlin_stdlib(r1, r2, r3);
    }

    @SinceKotlin(version = "1.8")
    @InlineOnly
    private static final String platformEncodeToString(Base64 r0, byte[] r1, int r2, int r3) {
        return new String(r0.encodeToByteArrayImpl$kotlin_stdlib(r1, r2, r3), Charsets.ISO_8859_1);
    }
}
