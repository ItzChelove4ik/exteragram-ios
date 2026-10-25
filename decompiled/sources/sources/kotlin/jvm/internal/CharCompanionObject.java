package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\u00020\u00058\u0006X\u0087Ô\b¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0003R\u0017\u0010\u0007\u001a\u00020\u00058\u0006X\u0087Ô\b¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003R\u000f\u0010\t\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000b\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\f\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\r\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000e\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\u00020\u00108\u0006X\u0087Ô\b¢\u0006\b\n\u0000\u0012\u0004\b\u0011\u0010\u0003R\u0017\u0010\u0012\u001a\u00020\u00108\u0006X\u0087Ô\b¢\u0006\b\n\u0000\u0012\u0004\b\u0013\u0010\u0003¨\u0006\u0014"}, d2 = {"Lkotlin/jvm/internal/CharCompanionObject;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MIN_VALUE", _UrlKt.FRAGMENT_ENCODE_SET, "getMIN_VALUE$annotations", "MAX_VALUE", "getMAX_VALUE$annotations", "MIN_HIGH_SURROGATE", "MAX_HIGH_SURROGATE", "MIN_LOW_SURROGATE", "MAX_LOW_SURROGATE", "MIN_SURROGATE", "MAX_SURROGATE", "SIZE_BYTES", _UrlKt.FRAGMENT_ENCODE_SET, "getSIZE_BYTES$annotations", "SIZE_BITS", "getSIZE_BITS$annotations", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class CharCompanionObject {
    public static final CharCompanionObject INSTANCE = null;
    public static final char MAX_HIGH_SURROGATE = 56319;
    public static final char MAX_LOW_SURROGATE = 57343;
    public static final char MAX_SURROGATE = 57343;
    public static final char MAX_VALUE = 65535;
    public static final char MIN_HIGH_SURROGATE = 55296;
    public static final char MIN_LOW_SURROGATE = 56320;
    public static final char MIN_SURROGATE = 55296;
    public static final char MIN_VALUE = 0;
    public static final int SIZE_BITS = 16;
    public static final int SIZE_BYTES = 2;

    static {
        INSTANCE = new CharCompanionObject();
    }

    private CharCompanionObject() {
    }

    @SinceKotlin(version = "1.3")
    public static /* synthetic */ void getMAX_VALUE$annotations() {
    }

    @SinceKotlin(version = "1.3")
    public static /* synthetic */ void getMIN_VALUE$annotations() {
    }

    @SinceKotlin(version = "1.3")
    public static /* synthetic */ void getSIZE_BITS$annotations() {
    }

    @SinceKotlin(version = "1.3")
    public static /* synthetic */ void getSIZE_BYTES$annotations() {
    }
}
