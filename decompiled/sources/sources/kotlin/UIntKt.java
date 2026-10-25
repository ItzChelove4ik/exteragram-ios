package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.internal.IntrinsicConstEvaluation;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\u001a\u0013\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004¢\u0006\u0002\u0010\u0003\u001a\u0013\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0087\u0088\u0004¢\u0006\u0002\u0010\u0005\u001a\u0013\u0010\u0000\u001a\u00020\u0001*\u00020\u0006H\u0087\u0088\u0004¢\u0006\u0002\u0010\u0007\u001a\u0013\u0010\u0000\u001a\u00020\u0001*\u00020\bH\u0087\u0088\u0004¢\u0006\u0002\u0010\t\u001a\u0013\u0010\u0000\u001a\u00020\u0001*\u00020\nH\u0087\u0088\u0004¢\u0006\u0002\u0010\u000b\u001a\u0013\u0010\u0000\u001a\u00020\u0001*\u00020\fH\u0087\u0088\u0004¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"toUInt", "Lkotlin/UInt;", _UrlKt.FRAGMENT_ENCODE_SET, "(B)I", _UrlKt.FRAGMENT_ENCODE_SET, "(S)I", _UrlKt.FRAGMENT_ENCODE_SET, "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "(J)I", _UrlKt.FRAGMENT_ENCODE_SET, "(F)I", _UrlKt.FRAGMENT_ENCODE_SET, "(D)I", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class UIntKt {
    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int toUInt(byte r0) {
        return UInt.m2113constructorimpl(r0);
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int toUInt(double r0) {
        return UnsignedKt.doubleToUInt(r0);
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int toUInt(float r2) {
        return UnsignedKt.doubleToUInt(r2);
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int toUInt(int r0) {
        return UInt.m2113constructorimpl(r0);
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int toUInt(long r0) {
        return UInt.m2113constructorimpl((int) r0);
    }

    @SinceKotlin(version = "1.5")
    @IntrinsicConstEvaluation
    @InlineOnly
    private static final int toUInt(short r0) {
        return UInt.m2113constructorimpl(r0);
    }
}
