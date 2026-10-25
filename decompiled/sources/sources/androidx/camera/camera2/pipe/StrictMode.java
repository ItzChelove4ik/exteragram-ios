package androidx.camera.camera2.pipe;

import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/pipe/StrictMode;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "enabled", "<init>", "(Z)V", "Z", "getEnabled", "()Z", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStrictMode.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StrictMode.kt\nandroidx/camera/camera2/pipe/StrictMode\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,35:1\n71#2,2:36\n*S KotlinDebug\n*F\n+ 1 StrictMode.kt\nandroidx/camera/camera2/pipe/StrictMode\n*L\n28#1:36,2\n*E\n"})
/* loaded from: classes3.dex */
public final class StrictMode {
    private final boolean enabled;

    public StrictMode(boolean r1) {
        this.enabled = r1;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }
}
