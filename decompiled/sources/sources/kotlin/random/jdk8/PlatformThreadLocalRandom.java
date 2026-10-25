package kotlin.random.jdk8;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import kotlin.Metadata;
import kotlin.random.AbstractPlatformRandom;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0096\u0080\u0004J\u0012\u0010\f\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\rH\u0096\u0080\u0004J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\rH\u0096\u0080\u0004J\u0012\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\u000fH\u0096\u0080\u0004R\u0015\u0010\u0004\u001a\u00020\u00058VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lkotlin/random/jdk8/PlatformThreadLocalRandom;", "Lkotlin/random/AbstractPlatformRandom;", "<init>", "()V", "impl", "Ljava/util/Random;", "getImpl", "()Ljava/util/Random;", "nextInt", _UrlKt.FRAGMENT_ENCODE_SET, "from", "until", "nextLong", _UrlKt.FRAGMENT_ENCODE_SET, "nextDouble", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib-jdk8"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PlatformThreadLocalRandom extends AbstractPlatformRandom {
    public PlatformThreadLocalRandom() {
    }

    @Override // kotlin.random.AbstractPlatformRandom
    public Random getImpl() {
        return ThreadLocalRandom.current();
    }

    @Override // kotlin.random.Random
    public double nextDouble(double r1) {
        return ThreadLocalRandom.current().nextDouble(r1);
    }

    @Override // kotlin.random.Random
    public int nextInt(int r1, int r2) {
        return ThreadLocalRandom.current().nextInt(r1, r2);
    }

    @Override // kotlin.random.Random
    public long nextLong(long r1) {
        return ThreadLocalRandom.current().nextLong(r1);
    }

    @Override // kotlin.random.Random
    public long nextLong(long r1, long r3) {
        return ThreadLocalRandom.current().nextLong(r1, r3);
    }
}
