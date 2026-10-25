package okhttp3.internal.platform;

import android.content.Context;
import android.os.Build;
import kotlin.Metadata;
import okhttp3.internal.platform.android.AndroidLog;
import okhttp3.internal.url._UrlKt;
import org.telegram.ui.WearAuthSheet$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR(\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lokhttp3/internal/platform/PlatformRegistry;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "findPlatform", "Lokhttp3/internal/platform/Platform;", "isAndroid", _UrlKt.FRAGMENT_ENCODE_SET, "()Z", "value", "Landroid/content/Context;", "applicationContext", "getApplicationContext", "()Landroid/content/Context;", "setApplicationContext", "(Landroid/content/Context;)V", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PlatformRegistry {
    public static final PlatformRegistry INSTANCE = null;

    static {
        INSTANCE = new PlatformRegistry();
    }

    private PlatformRegistry() {
    }

    public final Platform findPlatform() {
        AndroidLog.INSTANCE.enable();
        Platform r1 = Android10Platform.Companion.buildIfSupported();
        if (r1 != null) goto L5;
        r1 = AndroidPlatform.Companion.buildIfSupported();
    L5:
        if (r1 == null) goto L7;
        return r1;
    L7:
        WearAuthSheet$$ExternalSyntheticBUOutline0.m("Expected Android API level 21+ but was ", Build.VERSION.SDK_INT);
        return null;
    }

    public final Context getApplicationContext() {
        Object r2 = Platform.Companion.get();
        if ((r2 instanceof ContextAwarePlatform) == false) goto L5;
        ContextAwarePlatform r22 = (ContextAwarePlatform) r2;
    L6:
        if (r22 != null) goto L8;
        return null;
    L8:
        return r22.getApplicationContext();
    L5:
        r22 = null;
        goto L6
    }

    public final boolean isAndroid() {
        return true;
    }

    public final void setApplicationContext(Context r2) {
        Object r1 = Platform.Companion.get();
        if ((r1 instanceof ContextAwarePlatform) == false) goto L5;
        ContextAwarePlatform r12 = (ContextAwarePlatform) r1;
    L6:
        if (r12 == null) goto L9;
        r12.setApplicationContext(r2);
        return;
    L9:
        return;
    L5:
        r12 = null;
        goto L6
    }
}
