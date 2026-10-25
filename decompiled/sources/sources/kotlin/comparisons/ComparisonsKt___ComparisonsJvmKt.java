package kotlin.comparisons;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000J\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\u0007\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u0017\n\u0002\u0010\u0015\n\u0002\u0010\u0016\n\u0002\u0010\u0014\n\u0002\u0010\u0013\n\u0002\b\u0002\u001a/\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u0001H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0005\u001a\u001a\u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0006H\u0087\u0088\u0004\u001a\u001a\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0087\u0088\u0004\u001a\u001a\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0087\u0088\u0004\u001a\u001a\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\tH\u0087\u0088\u0004\u001a\u001a\u0010\u0000\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\nH\u0087\u0088\u0004\u001a\u001a\u0010\u0000\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000bH\u0087\u0088\u0004\u001a7\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u0006\u0010\f\u001a\u0002H\u0001H\u0087\u0080\u0004¢\u0006\u0002\u0010\r\u001a\"\u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0087\u0088\u0004\u001a\"\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0087\u0088\u0004\u001a\"\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0087\u0088\u0004\u001a\"\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0087\u0088\u0004\u001a\"\u0010\u0000\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0087\u0088\u0004\u001a\"\u0010\u0000\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0087\u0088\u0004\u001a;\u0010\u0000\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\u000f\"\u0002H\u0001H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0010\u001a\u001e\u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00062\n\u0010\u000e\u001a\u00020\u0011\"\u00020\u0006H\u0087\u0080\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\n\u0010\u000e\u001a\u00020\u0012\"\u00020\u0007H\u0087\u0080\u0004\u001a\u001e\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\n\u0010\u000e\u001a\u00020\u0013\"\u00020\bH\u0087\u0080\u0004\u001a\u001e\u0010\u0000\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\n\u0010\u000e\u001a\u00020\u0014\"\u00020\tH\u0087\u0080\u0004\u001a\u001e\u0010\u0000\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\n\u0010\u000e\u001a\u00020\u0015\"\u00020\nH\u0087\u0080\u0004\u001a\u001e\u0010\u0000\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\n\u0010\u000e\u001a\u00020\u0016\"\u00020\u000bH\u0087\u0080\u0004\u001a/\u0010\u0017\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u0001H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0005\u001a\u001a\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0006H\u0087\u0088\u0004\u001a\u001a\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0087\u0088\u0004\u001a\u001a\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\bH\u0087\u0088\u0004\u001a\u001a\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\tH\u0087\u0088\u0004\u001a\u001a\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\nH\u0087\u0088\u0004\u001a\u001a\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000bH\u0087\u0088\u0004\u001a7\u0010\u0017\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u0006\u0010\f\u001a\u0002H\u0001H\u0087\u0080\u0004¢\u0006\u0002\u0010\r\u001a\"\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0087\u0088\u0004\u001a\"\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0087\u0088\u0004\u001a\"\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0087\u0088\u0004\u001a\"\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0087\u0088\u0004\u001a\"\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0087\u0088\u0004\u001a\"\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0087\u0088\u0004\u001a;\u0010\u0017\u001a\u0002H\u0001\"\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001a\u0002H\u00012\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\u000f\"\u0002H\u0001H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0010\u001a\u001e\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00062\n\u0010\u000e\u001a\u00020\u0011\"\u00020\u0006H\u0087\u0080\u0004\u001a\u001e\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\n\u0010\u000e\u001a\u00020\u0012\"\u00020\u0007H\u0087\u0080\u0004\u001a\u001e\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b2\n\u0010\u000e\u001a\u00020\u0013\"\u00020\bH\u0087\u0080\u0004\u001a\u001e\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t2\n\u0010\u000e\u001a\u00020\u0014\"\u00020\tH\u0087\u0080\u0004\u001a\u001e\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n2\n\u0010\u000e\u001a\u00020\u0015\"\u00020\nH\u0087\u0080\u0004\u001a\u001e\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u000b2\n\u0010\u000e\u001a\u00020\u0016\"\u00020\u000bH\u0087\u0080\u0004¨\u0006\u0018"}, d2 = {"maxOf", "T", _UrlKt.FRAGMENT_ENCODE_SET, "a", "b", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "c", "(Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "other", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/Comparable;[Ljava/lang/Comparable;)Ljava/lang/Comparable;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "minOf", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/comparisons/ComparisonsKt")
/* loaded from: classes.dex */
class ComparisonsKt___ComparisonsJvmKt extends ComparisonsKt__ComparisonsKt {
    public ComparisonsKt___ComparisonsJvmKt() {
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte maxOf(byte r0, byte r1) {
        return (byte) Math.max(r0, r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte maxOf(byte r0, byte r1, byte r2) {
        return (byte) Math.max(r0, Math.max(r1, r2));
    }

    @SinceKotlin(version = "1.4")
    public static final byte maxOf(byte r3, byte... r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3 = (byte) Math.max(r3, r4[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final double maxOf(double r0, double r2) {
        return Math.max(r0, r2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final double maxOf(double r0, double r2, double r4) {
        return Math.max(r0, Math.max(r2, r4));
    }

    @SinceKotlin(version = "1.4")
    public static final double maxOf(double r4, double... r6) {
        int r0 = r6.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r4 = Math.max(r4, r6[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r4;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final float maxOf(float r0, float r1) {
        return Math.max(r0, r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final float maxOf(float r0, float r1, float r2) {
        return Math.max(r0, Math.max(r1, r2));
    }

    @SinceKotlin(version = "1.4")
    public static final float maxOf(float r3, float... r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3 = Math.max(r3, r4[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int maxOf(int r0, int r1) {
        return Math.max(r0, r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int maxOf(int r0, int r1, int r2) {
        return Math.max(r0, Math.max(r1, r2));
    }

    @SinceKotlin(version = "1.4")
    public static final int maxOf(int r3, int... r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3 = Math.max(r3, r4[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long maxOf(long r0, long r2) {
        return Math.max(r0, r2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long maxOf(long r0, long r2, long r4) {
        return Math.max(r0, Math.max(r2, r4));
    }

    @SinceKotlin(version = "1.4")
    public static final long maxOf(long r4, long... r6) {
        int r0 = r6.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r4 = Math.max(r4, r6[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r4;
    }

    @SinceKotlin(version = "1.1")
    public static <T extends Comparable<? super T>> T maxOf(T r1, T r2) {
        if (r1.compareTo(r2) < 0) goto L5;
        return r1;
    L5:
        return r2;
    }

    @SinceKotlin(version = "1.1")
    public static final <T extends Comparable<? super T>> T maxOf(T r0, T r1, T r2) {
        return (T) maxOf(r0, maxOf(r1, r2));
    }

    @SinceKotlin(version = "1.4")
    public static final <T extends Comparable<? super T>> T maxOf(T r3, T... r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        T r32 = r3;
        r3 = (T) maxOf(r32, r4[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short maxOf(short r0, short r1) {
        return (short) Math.max(r0, r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short maxOf(short r0, short r1, short r2) {
        return (short) Math.max(r0, Math.max(r1, r2));
    }

    @SinceKotlin(version = "1.4")
    public static final short maxOf(short r3, short... r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3 = (short) Math.max(r3, r4[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte minOf(byte r0, byte r1) {
        return (byte) Math.min(r0, r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte minOf(byte r0, byte r1, byte r2) {
        return (byte) Math.min(r0, Math.min(r1, r2));
    }

    @SinceKotlin(version = "1.4")
    public static final byte minOf(byte r3, byte... r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3 = (byte) Math.min(r3, r4[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final double minOf(double r0, double r2) {
        return Math.min(r0, r2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final double minOf(double r0, double r2, double r4) {
        return Math.min(r0, Math.min(r2, r4));
    }

    @SinceKotlin(version = "1.4")
    public static final double minOf(double r4, double... r6) {
        int r0 = r6.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r4 = Math.min(r4, r6[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r4;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final float minOf(float r0, float r1) {
        return Math.min(r0, r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final float minOf(float r0, float r1, float r2) {
        return Math.min(r0, Math.min(r1, r2));
    }

    @SinceKotlin(version = "1.4")
    public static final float minOf(float r3, float... r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3 = Math.min(r3, r4[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int minOf(int r0, int r1) {
        return Math.min(r0, r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int minOf(int r0, int r1, int r2) {
        return Math.min(r0, Math.min(r1, r2));
    }

    @SinceKotlin(version = "1.4")
    public static final int minOf(int r3, int... r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3 = Math.min(r3, r4[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long minOf(long r0, long r2) {
        return Math.min(r0, r2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long minOf(long r0, long r2, long r4) {
        return Math.min(r0, Math.min(r2, r4));
    }

    @SinceKotlin(version = "1.4")
    public static final long minOf(long r4, long... r6) {
        int r0 = r6.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r4 = Math.min(r4, r6[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r4;
    }

    @SinceKotlin(version = "1.1")
    public static final <T extends Comparable<? super T>> T minOf(T r1, T r2) {
        if (r1.compareTo(r2) > 0) goto L5;
        return r1;
    L5:
        return r2;
    }

    @SinceKotlin(version = "1.1")
    public static final <T extends Comparable<? super T>> T minOf(T r0, T r1, T r2) {
        return (T) minOf(r0, minOf(r1, r2));
    }

    @SinceKotlin(version = "1.4")
    public static final <T extends Comparable<? super T>> T minOf(T r3, T... r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        T r32 = r3;
        r3 = (T) minOf(r32, r4[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short minOf(short r0, short r1) {
        return (short) Math.min(r0, r1);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short minOf(short r0, short r1, short r2) {
        return (short) Math.min(r0, Math.min(r1, r2));
    }

    @SinceKotlin(version = "1.4")
    public static final short minOf(short r3, short... r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3 = (short) Math.min(r3, r4[r1]);
        r1 = r1 + 1;
        goto L3
    L5:
        return r3;
    }
}
