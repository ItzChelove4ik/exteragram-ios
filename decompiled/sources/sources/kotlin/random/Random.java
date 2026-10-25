package kotlin.random;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@SinceKotlin(version = "1.3")
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\b'\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H¦\u0080\u0004J\n\u0010\u0007\u001a\u00020\u0005H\u0096\u0080\u0004J\u0012\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0096\u0080\u0004J\u001a\u0010\u0007\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0096\u0080\u0004J\n\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004J\u0012\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u000bH\u0096\u0080\u0004J\u001a\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u000bH\u0096\u0080\u0004J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004J\u0012\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u000fH\u0096\u0080\u0004J\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u000fH\u0096\u0080\u0004J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004J&\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u0005H\u0097\u0080\bJ\u0012\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0097\u0080\bJ\u0012\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0005H\u0096\u0080\u0004¨\u0006\u0019"}, d2 = {"Lkotlin/random/Random;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "nextBits", _UrlKt.FRAGMENT_ENCODE_SET, "bitCount", "nextInt", "until", "from", "nextLong", _UrlKt.FRAGMENT_ENCODE_SET, "nextBoolean", _UrlKt.FRAGMENT_ENCODE_SET, "nextDouble", _UrlKt.FRAGMENT_ENCODE_SET, "nextFloat", _UrlKt.FRAGMENT_ENCODE_SET, "nextBytes", _UrlKt.FRAGMENT_ENCODE_SET, "array", "fromIndex", "toIndex", "size", "Default", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRandom.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Random.kt\nkotlin/random/Random\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,390:1\n1#2:391\n*E\n"})
/* loaded from: classes.dex */
public abstract class Random {
    public static final Default Default = null;
    private static final Random defaultRandom = null;

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001#B\t\bB¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0007\u001a\u00020\bH\u0082\u0080\u0004J\u001b\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u00060\fj\u0002`\rH\u0082\u0080\u0004¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0096\u0080\u0004J\n\u0010\u0012\u001a\u00020\u0010H\u0096\u0080\u0004J\u0012\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0010H\u0096\u0080\u0004J\u001a\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0010H\u0096\u0080\u0004J\n\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004J\u0012\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0016H\u0096\u0080\u0004J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0016H\u0096\u0080\u0004J\n\u0010\u0017\u001a\u00020\u0018H\u0096\u0080\u0004J\n\u0010\u0019\u001a\u00020\u001aH\u0096\u0080\u0004J\u0012\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u001aH\u0096\u0080\u0004J\u001a\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u001aH\u0096\u0080\u0004J\n\u0010\u001b\u001a\u00020\u001cH\u0096\u0080\u0004J\u0012\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0097\u0080\bJ\u0012\u0010\u001d\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u0010H\u0096\u0080\u0004J\"\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u0010H\u0096\u0080\bR\u000f\u0010\u0006\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lkotlin/random/Random$Default;", "Lkotlin/random/Random;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "<init>", "()V", "defaultRandom", "writeReplace", _UrlKt.FRAGMENT_ENCODE_SET, "readObject", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.TYPE_INPUT, "Ljava/io/ObjectInputStream;", "Lkotlin/internal/ReadObjectParameterType;", "(Ljava/io/ObjectInputStream;)V", "nextBits", _UrlKt.FRAGMENT_ENCODE_SET, "bitCount", "nextInt", "until", "from", "nextLong", _UrlKt.FRAGMENT_ENCODE_SET, "nextBoolean", _UrlKt.FRAGMENT_ENCODE_SET, "nextDouble", _UrlKt.FRAGMENT_ENCODE_SET, "nextFloat", _UrlKt.FRAGMENT_ENCODE_SET, "nextBytes", _UrlKt.FRAGMENT_ENCODE_SET, "array", "size", "fromIndex", "toIndex", "Serialized", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Default extends Random implements Serializable {

        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0000\bÂ\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\t\bB¢\u0006\u0004\b\u0003\u0010\u0004J\n\u0010\u0007\u001a\u00020\bH\u0082\u0080\u0004R\u000f\u0010\u0005\u001a\u00020\u0006X\u0082Ô\b¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlin/random/Random$Default$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "<init>", "()V", "serialVersionUID", _UrlKt.FRAGMENT_ENCODE_SET, "readResolve", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
        /* loaded from: classes5.dex */
        public static final class Serialized implements Serializable {
            public static final Serialized INSTANCE = null;
            private static final long serialVersionUID = 0;

            static {
                INSTANCE = new Serialized();
            }

            private Serialized() {
            }

            private final Object readResolve() {
                return Random.Default;
            }
        }

        private Default() {
        }

        public /* synthetic */ Default(DefaultConstructorMarker r1) {
            this();
        }

        private final void readObject(ObjectInputStream r1) {
            throw new InvalidObjectException("Deserialization is supported via proxy only");
        }

        private final Object writeReplace() {
            return Serialized.INSTANCE;
        }

        @Override // kotlin.random.Random
        public int nextBits(int r1) {
            return Random.access$getDefaultRandom$cp().nextBits(r1);
        }

        @Override // kotlin.random.Random
        public boolean nextBoolean() {
            return Random.access$getDefaultRandom$cp().nextBoolean();
        }

        @Override // kotlin.random.Random
        public byte[] nextBytes(int r1) {
            return Random.access$getDefaultRandom$cp().nextBytes(r1);
        }

        @Override // kotlin.random.Random
        @IgnorableReturnValue
        public byte[] nextBytes(byte[] r1) {
            return Random.access$getDefaultRandom$cp().nextBytes(r1);
        }

        @Override // kotlin.random.Random
        public byte[] nextBytes(byte[] r1, int r2, int r3) {
            return Random.access$getDefaultRandom$cp().nextBytes(r1, r2, r3);
        }

        @Override // kotlin.random.Random
        public double nextDouble() {
            return Random.access$getDefaultRandom$cp().nextDouble();
        }

        @Override // kotlin.random.Random
        public double nextDouble(double r1) {
            return Random.access$getDefaultRandom$cp().nextDouble(r1);
        }

        @Override // kotlin.random.Random
        public double nextDouble(double r1, double r3) {
            return Random.access$getDefaultRandom$cp().nextDouble(r1, r3);
        }

        @Override // kotlin.random.Random
        public float nextFloat() {
            return Random.access$getDefaultRandom$cp().nextFloat();
        }

        @Override // kotlin.random.Random
        public int nextInt() {
            return Random.access$getDefaultRandom$cp().nextInt();
        }

        @Override // kotlin.random.Random
        public int nextInt(int r1) {
            return Random.access$getDefaultRandom$cp().nextInt(r1);
        }

        @Override // kotlin.random.Random
        public int nextInt(int r1, int r2) {
            return Random.access$getDefaultRandom$cp().nextInt(r1, r2);
        }

        @Override // kotlin.random.Random
        public long nextLong() {
            return Random.access$getDefaultRandom$cp().nextLong();
        }

        @Override // kotlin.random.Random
        public long nextLong(long r1) {
            return Random.access$getDefaultRandom$cp().nextLong(r1);
        }

        @Override // kotlin.random.Random
        public long nextLong(long r1, long r3) {
            return Random.access$getDefaultRandom$cp().nextLong(r1, r3);
        }
    }

    static {
        Default = new Default(null);
        defaultRandom = PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();
    }

    public Random() {
    }

    public static final /* synthetic */ Random access$getDefaultRandom$cp() {
        return defaultRandom;
    }

    public static /* synthetic */ byte[] nextBytes$default(Random r0, byte[] r1, int r2, int r3, int r4, Object r5) {
        if (r5 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: nextBytes");
        return null;
    L4:
        if ((r4 & 2) == 0) goto L7;
        r2 = 0;
    L7:
        if ((r4 & 4) == 0) goto L10;
        r3 = r1.length;
    L10:
        return r0.nextBytes(r1, r2, r3);
    }

    public abstract int nextBits(int r1);

    public boolean nextBoolean() {
        if (nextBits(1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public byte[] nextBytes(int r1) {
        return nextBytes(new byte[r1]);
    }

    @IgnorableReturnValue
    public byte[] nextBytes(byte[] r3) {
        return nextBytes(r3, 0, r3.length);
    }

    @IgnorableReturnValue
    public byte[] nextBytes(byte[] r7, int r8, int r9) {
        if (r8 < 0) goto L20;
        if (r8 > r7.length) goto L20;
        if (r9 < 0) goto L20;
        if (r9 > r7.length) goto L20;
        if (r8 > r9) goto L18;
        int r0 = (r9 - r8) / 4;
        int r1 = 0;
        int r2 = 0;
    L11:
        if (r2 >= r0) goto L13;
        int r3 = nextInt();
        r7[r8] = (byte) r3;
        r7[r8 + 1] = (byte) (r3 >>> 8);
        r7[r8 + 2] = (byte) (r3 >>> 16);
        r7[r8 + 3] = (byte) (r3 >>> 24);
        r8 = r8 + 4;
        r2 = r2 + 1;
        goto L11
    L13:
        int r92 = r9 - r8;
        int r6 = nextBits(r92 * 8);
    L14:
        if (r1 >= r92) goto L16;
        r7[r8 + r1] = (byte) (r6 >>> (r1 * 8));
        r1 = r1 + 1;
        goto L14
    L16:
        return r7;
    L18:
        throw new IllegalArgumentException(("fromIndex (" + r8 + ") must be not greater than toIndex (" + r9 + ").").toString());
    L20:
        throw new IllegalArgumentException(("fromIndex (" + r8 + ") or toIndex (" + r9 + ") are out of range: 0.." + r7.length + '.').toString());
    }

    public double nextDouble() {
        return PlatformRandomKt.doubleFromParts(nextBits(26), nextBits(27));
    }

    public double nextDouble(double r3) {
        return nextDouble(0.0d, r3);
    }

    public double nextDouble(double r7, double r9) {
        RandomKt.checkRangeBounds(r7, r9);
        double r0 = r9 - r7;
        if (Double.isInfinite(r0) == true) goto L5;
    L9:
        double r72 = r7 + (nextDouble() * r0);
    L11:
        if (r72 >= r9) goto L13;
        return r72;
    L13:
        return Math.nextAfter(r9, Double.NEGATIVE_INFINITY);
    L5:
        if (Math.abs(r7) > Double.MAX_VALUE) goto L9;
        if (Math.abs(r9) > Double.MAX_VALUE) goto L9;
        double r02 = nextDouble() * ((r9 / 2.0d) - (r7 / 2.0d));
        r72 = (r7 + r02) + r02;
        goto L11
    }

    public float nextFloat() {
        return nextBits(24) / 1.6777216E7f;
    }

    public int nextInt() {
        return nextBits(32);
    }

    public int nextInt(int r2) {
        return nextInt(0, r2);
    }

    public int nextInt(int r4, int r5) {
        RandomKt.checkRangeBounds(r4, r5);
        int r0 = r5 - r4;
        if (r0 > 0) goto L12;
        if (r0 == Integer.MIN_VALUE) goto L12;
    L7:
        int r02 = nextInt();
        if (r4 > r02) goto L7;
        if (r02 >= r5) goto L7;
        return r02;
    L12:
        if (((-r0) & r0) != r0) goto L14;
        int r3 = nextBits(RandomKt.fastLog2(r0));
    L18:
        return r4 + r3;
    L14:
        int r52 = nextInt() >>> 1;
        int r1 = r52 % r0;
        if (((r52 - r1) + (r0 - 1)) < 0) goto L14;
        r3 = r1;
        goto L18
    }

    public long nextLong() {
        return (nextInt() << 32) + nextInt();
    }

    public long nextLong(long r3) {
        return nextLong(0, r3);
    }

    public long nextLong(long r10, long r12) {
        RandomKt.checkRangeBounds(r10, r12);
        long r0 = r12 - r10;
        if (r0 > 0) goto L5;
    L18:
        long r02 = nextLong();
        if (r10 > r02) goto L18;
        if (r02 >= r12) goto L18;
        return r02;
    L5:
        if (((-r0) & r0) != r0) goto L13;
        int r122 = (int) r0;
        int r03 = (int) (r0 >>> 32);
        if (r122 == 0) goto L10;
        int r9 = nextBits(RandomKt.fastLog2(r122));
    L9:
        long r123 = r9 & 4294967295L;
    L17:
        return r10 + r123;
    L10:
        if (r03 != 1) goto L12;
        r9 = nextInt();
        goto L9
    L12:
        r123 = (nextBits(RandomKt.fastLog2(r03)) << 32) + (nextInt() & 4294967295L);
    L13:
        long r124 = nextLong() >>> 1;
        long r5 = r124 % r0;
        if (((r124 - r5) + (r0 - 1)) < 0) goto L13;
        r123 = r5;
        goto L17
    }
}
