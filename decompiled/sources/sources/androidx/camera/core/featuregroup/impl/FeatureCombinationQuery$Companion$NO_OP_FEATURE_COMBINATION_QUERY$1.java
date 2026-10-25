package androidx.camera.core.featuregroup.impl;

import androidx.camera.core.impl.SessionConfig;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"androidx/camera/core/featuregroup/impl/FeatureCombinationQuery$Companion$NO_OP_FEATURE_COMBINATION_QUERY$1", "Landroidx/camera/core/featuregroup/impl/FeatureCombinationQuery;", "isSupported", _UrlKt.FRAGMENT_ENCODE_SET, "sessionConfig", "Landroidx/camera/core/impl/SessionConfig;", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FeatureCombinationQuery$Companion$NO_OP_FEATURE_COMBINATION_QUERY$1 implements FeatureCombinationQuery {
    public FeatureCombinationQuery$Companion$NO_OP_FEATURE_COMBINATION_QUERY$1() {
    }

    @Override // androidx.camera.core.featuregroup.impl.FeatureCombinationQuery
    public boolean isSupported(SessionConfig r1) {
        return false;
    }
}
