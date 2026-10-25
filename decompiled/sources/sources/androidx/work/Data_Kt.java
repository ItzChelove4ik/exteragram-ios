package androidx.work;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000L\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0016\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0014\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0013\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00070\u00022\u0006\u0010\u0001\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0004\u0010\b\u001a\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\n0\u00022\u0006\u0010\u0001\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0004\u0010\u000b\u001a\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\r0\u00022\u0006\u0010\u0001\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0004\u0010\u000e\u001a\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00100\u00022\u0006\u0010\u0001\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0004\u0010\u0011\u001a\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00130\u00022\u0006\u0010\u0001\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0004\u0010\u0014\"\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "convertPrimitiveArray", "([Z)[Ljava/lang/Boolean;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "([B)[Ljava/lang/Byte;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "([I)[Ljava/lang/Integer;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "([J)[Ljava/lang/Long;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "([F)[Ljava/lang/Float;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "([D)[Ljava/lang/Double;", _UrlKt.FRAGMENT_ENCODE_SET, "TAG", "Ljava/lang/String;", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class Data_Kt {
    private static final String TAG = null;

    static {
        TAG = Logger.tagWithPrefix("Data");
    }

    public static final /* synthetic */ Boolean[] access$convertPrimitiveArray(boolean[] r0) {
        return convertPrimitiveArray(r0);
    }

    public static final /* synthetic */ Byte[] access$convertPrimitiveArray(byte[] r0) {
        return convertPrimitiveArray(r0);
    }

    public static final /* synthetic */ Double[] access$convertPrimitiveArray(double[] r0) {
        return convertPrimitiveArray(r0);
    }

    public static final /* synthetic */ Float[] access$convertPrimitiveArray(float[] r0) {
        return convertPrimitiveArray(r0);
    }

    public static final /* synthetic */ Integer[] access$convertPrimitiveArray(int[] r0) {
        return convertPrimitiveArray(r0);
    }

    public static final /* synthetic */ Long[] access$convertPrimitiveArray(long[] r0) {
        return convertPrimitiveArray(r0);
    }

    public static final /* synthetic */ String access$getTAG$p() {
        return TAG;
    }

    private static final Boolean[] convertPrimitiveArray(boolean[] r4) {
        int r0 = r4.length;
        Boolean[] r1 = new Boolean[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1[r2] = Boolean.valueOf(r4[r2]);
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    private static final Byte[] convertPrimitiveArray(byte[] r4) {
        int r0 = r4.length;
        Byte[] r1 = new Byte[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1[r2] = Byte.valueOf(r4[r2]);
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    private static final Double[] convertPrimitiveArray(double[] r5) {
        int r0 = r5.length;
        Double[] r1 = new Double[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1[r2] = Double.valueOf(r5[r2]);
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    private static final Float[] convertPrimitiveArray(float[] r4) {
        int r0 = r4.length;
        Float[] r1 = new Float[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1[r2] = Float.valueOf(r4[r2]);
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    private static final Integer[] convertPrimitiveArray(int[] r4) {
        int r0 = r4.length;
        Integer[] r1 = new Integer[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1[r2] = Integer.valueOf(r4[r2]);
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    private static final Long[] convertPrimitiveArray(long[] r5) {
        int r0 = r5.length;
        Long[] r1 = new Long[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1[r2] = Long.valueOf(r5[r2]);
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }
}
