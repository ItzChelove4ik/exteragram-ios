package androidx.work.impl.utils;

import android.app.Application;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/work/impl/utils/Api28Impl;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "processName", _UrlKt.FRAGMENT_ENCODE_SET, "getProcessName", "()Ljava/lang/String;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
final class Api28Impl {
    public static final Api28Impl INSTANCE = null;

    static {
        INSTANCE = new Api28Impl();
    }

    private Api28Impl() {
    }

    public final String getProcessName() {
        return Application.getProcessName();
    }
}
