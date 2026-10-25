package androidx.camera.core.featuregroup.impl.resolver;

import androidx.camera.core.SessionConfig;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolver;", _UrlKt.FRAGMENT_ENCODE_SET, "resolveFeatureGroup", "Landroidx/camera/core/featuregroup/impl/resolver/FeatureGroupResolutionResult;", "sessionConfig", "Landroidx/camera/core/SessionConfig;", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface FeatureGroupResolver {
    FeatureGroupResolutionResult resolveFeatureGroup(SessionConfig r1);
}
