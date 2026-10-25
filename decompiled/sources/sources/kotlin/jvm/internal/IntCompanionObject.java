package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u000f\u0010\u0004\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0005X\u0086Ô\b¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\u00020\u00058\u0006X\u0087Ô\b¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003R\u0017\u0010\t\u001a\u00020\u00058\u0006X\u0087Ô\b¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003¨\u0006\u000b"}, d2 = {"Lkotlin/jvm/internal/IntCompanionObject;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MIN_VALUE", _UrlKt.FRAGMENT_ENCODE_SET, "MAX_VALUE", "SIZE_BYTES", "getSIZE_BYTES$annotations", "SIZE_BITS", "getSIZE_BITS$annotations", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class IntCompanionObject {
    public static final IntCompanionObject INSTANCE = null;
    public static final int MAX_VALUE = Integer.MAX_VALUE;
    public static final int MIN_VALUE = Integer.MIN_VALUE;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;

    static {
        INSTANCE = new IntCompanionObject();
    }

    private IntCompanionObject() {
    }

    @SinceKotlin(version = "1.3")
    public static /* synthetic */ void getSIZE_BITS$annotations() {
    }

    @SinceKotlin(version = "1.3")
    public static /* synthetic */ void getSIZE_BYTES$annotations() {
    }
}
