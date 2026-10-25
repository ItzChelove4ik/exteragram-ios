package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\t\bÀ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0014\u001a\u00020\u0005H\u0086\u0080\u0004J\n\u0010\u0015\u001a\u00020\u0005H\u0086\u0080\u0004J\n\u0010\u0016\u001a\u00020\u0005H\u0086\u0080\u0004J\n\u0010\u0017\u001a\u00020\u0005H\u0086\u0080\u0004J\n\u0010\u0018\u001a\u00020\u0005H\u0086\u0080\u0004R\u0017\u0010\u0004\u001a\u00020\u00058\u0006X\u0087Ô\b¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0003R\u0017\u0010\u0007\u001a\u00020\u00058\u0006X\u0087Ô\b¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003R\u0017\u0010\t\u001a\u00020\u00058\u0006X\u0087Ô\b¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003R\u0017\u0010\u000b\u001a\u00020\u00058\u0006X\u0087Ô\b¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u0003R\u0017\u0010\r\u001a\u00020\u00058\u0006X\u0087Ô\b¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u0003R\u0017\u0010\u000f\u001a\u00020\u00108\u0006X\u0087Ô\b¢\u0006\b\n\u0000\u0012\u0004\b\u0011\u0010\u0003R\u0017\u0010\u0012\u001a\u00020\u00108\u0006X\u0087Ô\b¢\u0006\b\n\u0000\u0012\u0004\b\u0013\u0010\u0003¨\u0006\u0019"}, d2 = {"Lkotlin/jvm/internal/FloatCompanionObject;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MIN_VALUE", _UrlKt.FRAGMENT_ENCODE_SET, "getMIN_VALUE$annotations", "MAX_VALUE", "getMAX_VALUE$annotations", "POSITIVE_INFINITY", "getPOSITIVE_INFINITY$annotations", "NEGATIVE_INFINITY", "getNEGATIVE_INFINITY$annotations", "NaN", "getNaN$annotations", "SIZE_BYTES", _UrlKt.FRAGMENT_ENCODE_SET, "getSIZE_BYTES$annotations", "SIZE_BITS", "getSIZE_BITS$annotations", "getMIN_VALUE", "getMAX_VALUE", "getPOSITIVE_INFINITY", "getNEGATIVE_INFINITY", "getNaN", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class FloatCompanionObject {
    public static final FloatCompanionObject INSTANCE = null;
    public static final float MAX_VALUE = Float.MAX_VALUE;
    public static final float MIN_VALUE = Float.MIN_VALUE;
    public static final float NEGATIVE_INFINITY = Float.NEGATIVE_INFINITY;
    public static final float NaN = Float.NaN;
    public static final float POSITIVE_INFINITY = Float.POSITIVE_INFINITY;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;

    static {
        INSTANCE = new FloatCompanionObject();
    }

    private FloatCompanionObject() {
    }

    @SinceKotlin(version = "1.4")
    public static /* synthetic */ void getMAX_VALUE$annotations() {
    }

    @SinceKotlin(version = "1.4")
    public static /* synthetic */ void getMIN_VALUE$annotations() {
    }

    @SinceKotlin(version = "1.4")
    public static /* synthetic */ void getNEGATIVE_INFINITY$annotations() {
    }

    @SinceKotlin(version = "1.4")
    public static /* synthetic */ void getNaN$annotations() {
    }

    @SinceKotlin(version = "1.4")
    public static /* synthetic */ void getPOSITIVE_INFINITY$annotations() {
    }

    @SinceKotlin(version = "1.4")
    public static /* synthetic */ void getSIZE_BITS$annotations() {
    }

    @SinceKotlin(version = "1.4")
    public static /* synthetic */ void getSIZE_BYTES$annotations() {
    }

    public final float getMAX_VALUE() {
        return Float.MAX_VALUE;
    }

    public final float getMIN_VALUE() {
        return Float.MIN_VALUE;
    }

    public final float getNEGATIVE_INFINITY() {
        return Float.NEGATIVE_INFINITY;
    }

    public final float getNaN() {
        return Float.NaN;
    }

    public final float getPOSITIVE_INFINITY() {
        return Float.POSITIVE_INFINITY;
    }
}
