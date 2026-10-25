package androidx.camera.lifecycle;

import androidx.camera.core.impl.utils.futures.FutureCallback;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"androidx/camera/lifecycle/LifecycleCameraProviderImpl$initAsync$1$2", "Landroidx/camera/core/impl/utils/futures/FutureCallback;", "Ljava/lang/Void;", "onSuccess", _UrlKt.FRAGMENT_ENCODE_SET, "void", "onFailure", "t", _UrlKt.FRAGMENT_ENCODE_SET, "camera-lifecycle"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LifecycleCameraProviderImpl$initAsync$1$2 implements FutureCallback<Void> {
    final /* synthetic */ LifecycleCameraProviderImpl this$0;

    public LifecycleCameraProviderImpl$initAsync$1$2(LifecycleCameraProviderImpl r1) {
        this.this$0 = r1;
    }

    @Override // androidx.camera.core.impl.utils.futures.FutureCallback
    public void onFailure(Throwable r1) {
        this.this$0.shutdownAsync$camera_lifecycle(false);
    }

    @Override // androidx.camera.core.impl.utils.futures.FutureCallback
    public /* bridge */ /* synthetic */ void onSuccess(Void r1) {
        onSuccess2(r1);
    }

    /* renamed from: onSuccess, reason: avoid collision after fix types in other method */
    public void onSuccess2(Void r1) {
    }
}
