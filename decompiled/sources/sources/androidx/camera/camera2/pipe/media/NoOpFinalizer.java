package androidx.camera.camera2.pipe.media;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/pipe/media/NoOpFinalizer;", "Landroidx/camera/camera2/pipe/media/Finalizer;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "finalize", _UrlKt.FRAGMENT_ENCODE_SET, "value", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NoOpFinalizer implements Finalizer<Object> {
    public static final NoOpFinalizer INSTANCE = null;

    static {
        INSTANCE = new NoOpFinalizer();
    }

    private NoOpFinalizer() {
    }

    @Override // androidx.camera.camera2.pipe.media.Finalizer
    public void finalize(Object r1) {
    }
}
