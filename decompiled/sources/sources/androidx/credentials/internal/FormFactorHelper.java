package androidx.credentials.internal;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\n"}, d2 = {"Landroidx/credentials/internal/FormFactorHelper;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isTV", _UrlKt.FRAGMENT_ENCODE_SET, "ctx", "Landroid/content/Context;", "isWear", "isAuto", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FormFactorHelper {
    public static final FormFactorHelper INSTANCE = null;

    static {
        INSTANCE = new FormFactorHelper();
    }

    private FormFactorHelper() {
    }

    @JvmStatic
    public static final boolean isAuto(Context r1) {
        return r1.getPackageManager().hasSystemFeature("android.hardware.type.automotive");
    }

    @JvmStatic
    public static final boolean isTV(Context r1) {
        return r1.getPackageManager().hasSystemFeature("android.software.leanback");
    }

    @JvmStatic
    public static final boolean isWear(Context r1) {
        return r1.getPackageManager().hasSystemFeature("android.hardware.type.watch");
    }
}
