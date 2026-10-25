package kotlin.math;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0006\bÂ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u00058\u0000X\u0081\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00058\u0000X\u0081\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u00058\u0000X\u0081\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\u00058\u0000X\u0081\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00058\u0000X\u0081\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u00058\u0000X\u0081\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/math/Constants;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "LN2", _UrlKt.FRAGMENT_ENCODE_SET, "epsilon", "taylor_2_bound", "taylor_n_bound", "upper_taylor_2_bound", "upper_taylor_n_bound", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class Constants {
    public static final Constants INSTANCE = null;

    @JvmField
    public static final double LN2 = 0.0d;

    @JvmField
    public static final double epsilon = 0.0d;

    @JvmField
    public static final double taylor_2_bound = 0.0d;

    @JvmField
    public static final double taylor_n_bound = 0.0d;

    @JvmField
    public static final double upper_taylor_2_bound = 0.0d;

    @JvmField
    public static final double upper_taylor_n_bound = 0.0d;

    static {
        INSTANCE = new Constants();
        LN2 = Math.log(2.0d);
        double r2 = Math.ulp(1.0d);
        epsilon = r2;
        double r22 = Math.sqrt(r2);
        taylor_2_bound = r22;
        double r4 = Math.sqrt(r22);
        taylor_n_bound = r4;
        upper_taylor_2_bound = 1.0d / r22;
        upper_taylor_n_bound = 1.0d / r4;
    }

    private Constants() {
    }
}
