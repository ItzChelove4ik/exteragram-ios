package androidx.collection.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u001a \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0000\u001a \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\u001c\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\u0000\u001a\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tH\u0000\u001a\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tH\u0000\u001a\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tH\u0000\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058\u0000X\u0081\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0016"}, d2 = {"EMPTY_INTS", _UrlKt.FRAGMENT_ENCODE_SET, "EMPTY_LONGS", _UrlKt.FRAGMENT_ENCODE_SET, "EMPTY_OBJECTS", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/Object;", "binarySearch", _UrlKt.FRAGMENT_ENCODE_SET, "array", "size", "value", _UrlKt.FRAGMENT_ENCODE_SET, "equal", _UrlKt.FRAGMENT_ENCODE_SET, "a", "b", "idealByteArraySize", "need", "idealIntArraySize", "idealLongArraySize", "collection"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ContainerHelpersKt {

    @JvmField
    public static final int[] EMPTY_INTS = null;

    @JvmField
    public static final long[] EMPTY_LONGS = null;

    @JvmField
    public static final Object[] EMPTY_OBJECTS = null;

    static {
        EMPTY_INTS = new int[0];
        EMPTY_LONGS = new long[0];
        EMPTY_OBJECTS = new Object[0];
    }

    public static final int binarySearch(int[] r3, int r4, int r5) {
        int r42 = r4 - 1;
        int r0 = 0;
    L3:
        if (r0 > r42) goto L11;
        int r1 = (r0 + r42) >>> 1;
        int r2 = r3[r1];
        if (r2 < r5) goto L6;
        if (r2 <= r5) goto L9;
        r42 = r1 - 1;
        goto L3
    L9:
        return r1;
    L6:
        r0 = r1 + 1;
        goto L3
    L11:
        return ~r0;
    }

    public static final int binarySearch(long[] r4, int r5, long r6) {
        int r52 = r5 - 1;
        int r0 = 0;
    L3:
        if (r0 > r52) goto L11;
        int r1 = (r0 + r52) >>> 1;
        long r2 = r4[r1];
        if (r2 < r6) goto L6;
        if (r2 <= r6) goto L9;
        r52 = r1 - 1;
        goto L3
    L9:
        return r1;
    L6:
        r0 = r1 + 1;
        goto L3
    L11:
        return ~r0;
    }

    public static final boolean equal(Object r0, Object r1) {
        return Intrinsics.areEqual(r0, r1);
    }

    public static final int idealByteArraySize(int r2) {
        int r0 = 4;
    L4:
        if (r0 >= 32) goto L9;
        int r1 = (1 << r0) - 12;
        if (r2 <= r1) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return r2;
    }

    public static final int idealIntArraySize(int r0) {
        return idealByteArraySize(r0 * 4) / 4;
    }

    public static final int idealLongArraySize(int r0) {
        return idealByteArraySize(r0 * 8) / 8;
    }
}
