package kotlin.random;

import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\u0080\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0004H\u0087\u0080\u0004\u001a\u0016\u0010\u0005\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0087\u0080\u0004\u001a\u0016\u0010\b\u001a\u00020\u0004*\u00020\u00012\u0006\u0010\u0006\u001a\u00020\tH\u0087\u0080\u0004\u001a\u0012\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0003H\u0080\u0080\u0004\u001a\u0016\u0010\f\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003H\u0080\u0080\u0004\u001a\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003H\u0080\u0080\u0004\u001a\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0080\u0080\u0004\u001a\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012H\u0080\u0080\u0004\u001a\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0015H\u0080\u0080\u0004¨\u0006\u0016"}, d2 = {"Random", "Lkotlin/random/Random;", "seed", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "nextInt", "range", "Lkotlin/ranges/IntRange;", "nextLong", "Lkotlin/ranges/LongRange;", "fastLog2", "value", "takeUpperBits", "bitCount", "checkRangeBounds", _UrlKt.FRAGMENT_ENCODE_SET, "from", "until", _UrlKt.FRAGMENT_ENCODE_SET, "boundsErrorMessage", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRandom.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Random.kt\nkotlin/random/RandomKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,390:1\n1#2:391\n*E\n"})
/* loaded from: classes5.dex */
public final class RandomKt {
    @SinceKotlin(version = "1.3")
    public static final Random Random(int r2) {
        return new XorWowRandom(r2, r2 >> 31);
    }

    @SinceKotlin(version = "1.3")
    public static final Random Random(long r3) {
        return new XorWowRandom((int) r3, (int) (r3 >> 32));
    }

    public static final String boundsErrorMessage(Object r2, Object r3) {
        return "Random range is empty: [" + r2 + ", " + r3 + ").";
    }

    public static final void checkRangeBounds(double r1, double r3) {
        if (r3 <= r1) goto L5;
        return;
    L5:
        RandomKt$$ExternalSyntheticBUOutline0.m(boundsErrorMessage(Double.valueOf(r1), Double.valueOf(r3)));
    }

    public static final void checkRangeBounds(int r0, int r1) {
        if (r1 <= r0) goto L4;
        return;
    L4:
        RandomKt$$ExternalSyntheticBUOutline0.m(boundsErrorMessage(Integer.valueOf(r0), Integer.valueOf(r1)));
    }

    public static final void checkRangeBounds(long r1, long r3) {
        if (r3 <= r1) goto L5;
        return;
    L5:
        RandomKt$$ExternalSyntheticBUOutline0.m(boundsErrorMessage(Long.valueOf(r1), Long.valueOf(r3)));
    }

    public static final int fastLog2(int r0) {
        return 31 - Integer.numberOfLeadingZeros(r0);
    }

    @SinceKotlin(version = "1.3")
    public static final int nextInt(Random r2, IntRange r3) {
        if (r3.isEmpty() == false) goto L5;
        Native$$ExternalSyntheticBUOutline5.m("Cannot get random in empty range: ", r3);
        return 0;
    L5:
        if (r3.getLast() >= Integer.MAX_VALUE) goto L9;
        return r2.nextInt(r3.getFirst(), r3.getLast() + 1);
    L9:
        if (r3.getFirst() <= Integer.MIN_VALUE) goto L13;
        return r2.nextInt(r3.getFirst() - 1, r3.getLast()) + 1;
    L13:
        return r2.nextInt();
    }

    @SinceKotlin(version = "1.3")
    public static final long nextLong(Random r7, LongRange r8) {
        if (r8.isEmpty() == false) goto L5;
        Native$$ExternalSyntheticBUOutline5.m("Cannot get random in empty range: ", r8);
        return 0;
    L5:
        if (r8.getLast() >= LongCompanionObject.MAX_VALUE) goto L9;
        return r7.nextLong(r8.getFirst(), r8.getLast() + 1);
    L9:
        if (r8.getFirst() <= Long.MIN_VALUE) goto L13;
        return r7.nextLong(r8.getFirst() - 1, r8.getLast()) + 1;
    L13:
        return r7.nextLong();
    }

    public static final int takeUpperBits(int r1, int r2) {
        return (r1 >>> (32 - r2)) & ((-r2) >> 31);
    }
}
