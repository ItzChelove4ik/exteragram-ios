package kotlin.random;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0080\u0004\u001a\u000e\u0010\u0003\u001a\u00020\u0002*\u00020\u0001H\u0087\u0080\u0004\u001a\n\u0010\u0004\u001a\u00020\u0002H\u0081\u0088\u0004\u001a\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0080\u0080\u0004¨\u0006\n"}, d2 = {"asJavaRandom", "Ljava/util/Random;", "Lkotlin/random/Random;", "asKotlinRandom", "defaultPlatformRandom", "doubleFromParts", _UrlKt.FRAGMENT_ENCODE_SET, "hi26", _UrlKt.FRAGMENT_ENCODE_SET, "low27", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class PlatformRandomKt {
    @SinceKotlin(version = "1.3")
    public static final java.util.Random asJavaRandom(Random r1) {
        if ((r1 instanceof AbstractPlatformRandom) == false) goto L5;
        AbstractPlatformRandom r0 = (AbstractPlatformRandom) r1;
    L6:
        if (r0 == null) goto L12;
        java.util.Random r02 = r0.getImpl();
        if (r02 == null) goto L12;
        return r02;
    L12:
        return new KotlinRandom(r1);
    L5:
        r0 = null;
        goto L6
    }

    @SinceKotlin(version = "1.3")
    public static final Random asKotlinRandom(java.util.Random r1) {
        if ((r1 instanceof KotlinRandom) == false) goto L5;
        KotlinRandom r0 = (KotlinRandom) r1;
    L6:
        if (r0 == null) goto L12;
        Random r02 = r0.getImpl();
        if (r02 == null) goto L12;
        return r02;
    L12:
        return new PlatformRandom(r1);
    L5:
        r0 = null;
        goto L6
    }

    @InlineOnly
    private static final Random defaultPlatformRandom() {
        return PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();
    }

    public static final double doubleFromParts(int r2, int r3) {
        return ((r2 << 27) + r3) / 9.007199254740992E15d;
    }
}
