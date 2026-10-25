package androidx.camera.camera2.pipe.media;

import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u0003H\u0016¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/pipe/media/ClosingFinalizer;", "Landroidx/camera/camera2/pipe/media/Finalizer;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "finalize", _UrlKt.FRAGMENT_ENCODE_SET, "value", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ClosingFinalizer implements Finalizer<AutoCloseable> {
    public static final ClosingFinalizer INSTANCE = null;

    static {
        INSTANCE = new ClosingFinalizer();
    }

    private ClosingFinalizer() {
    }

    /* renamed from: finalize, reason: avoid collision after fix types in other method */
    public void finalize2(AutoCloseable r1) {
        if (r1 == null) goto L5;
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r1);
        return;
    }

    @Override // androidx.camera.camera2.pipe.media.Finalizer
    public /* bridge */ /* synthetic */ void finalize(AutoCloseable r1) {
        finalize2(r1);
    }
}
