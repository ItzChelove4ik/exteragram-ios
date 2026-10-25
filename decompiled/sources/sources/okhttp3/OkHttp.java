package okhttp3;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import okhttp3.internal._InternalVersionKt;
import okhttp3.internal.platform.PlatformRegistry;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lokhttp3/OkHttp;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "VERSION", _UrlKt.FRAGMENT_ENCODE_SET, "initialize", _UrlKt.FRAGMENT_ENCODE_SET, "applicationContext", "Landroid/content/Context;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class OkHttp {
    public static final OkHttp INSTANCE = null;

    @JvmField
    public static final String VERSION = null;

    static {
        INSTANCE = new OkHttp();
        VERSION = _InternalVersionKt.CONST_VERSION;
    }

    private OkHttp() {
    }

    public final void initialize(Context r2) {
        PlatformRegistry r1 = PlatformRegistry.INSTANCE;
        if (r1.getApplicationContext() != null) goto L6;
        r1.setApplicationContext(r2.getApplicationContext());
        return;
    }
}
