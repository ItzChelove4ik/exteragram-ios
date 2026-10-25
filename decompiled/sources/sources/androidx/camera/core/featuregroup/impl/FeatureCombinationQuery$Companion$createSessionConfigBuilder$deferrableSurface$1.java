package androidx.camera.core.featuregroup.impl;

import android.util.Size;
import android.view.Surface;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.utils.futures.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\u0014¨\u0006\u0005"}, d2 = {"androidx/camera/core/featuregroup/impl/FeatureCombinationQuery$Companion$createSessionConfigBuilder$deferrableSurface$1", "Landroidx/camera/core/impl/DeferrableSurface;", "provideSurface", "Lcom/google/common/util/concurrent/ListenableFuture;", "Landroid/view/Surface;", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FeatureCombinationQuery$Companion$createSessionConfigBuilder$deferrableSurface$1 extends DeferrableSurface {
    public FeatureCombinationQuery$Companion$createSessionConfigBuilder$deferrableSurface$1(Size r1, int r2) {
        super(r1, r2);
    }

    @Override // androidx.camera.core.impl.DeferrableSurface
    public ListenableFuture<Surface> provideSurface() {
        return Futures.immediateFuture(null);
    }
}
