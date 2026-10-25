package androidx.camera.core.featuregroup.impl.feature;

import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.SessionConfig;
import androidx.camera.core.UseCase;
import androidx.camera.core.featuregroup.GroupableFeature;
import androidx.camera.core.impl.CameraInfoInternal;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Landroidx/camera/core/featuregroup/impl/feature/DynamicRangeFeature;", "Landroidx/camera/core/featuregroup/GroupableFeature;", "dynamicRange", "Landroidx/camera/core/DynamicRange;", "<init>", "(Landroidx/camera/core/DynamicRange;)V", "getDynamicRange", "()Landroidx/camera/core/DynamicRange;", "featureTypeInternal", "Landroidx/camera/core/featuregroup/impl/feature/FeatureTypeInternal;", "getFeatureTypeInternal", "()Landroidx/camera/core/featuregroup/impl/feature/FeatureTypeInternal;", "isSupportedIndividually", _UrlKt.FRAGMENT_ENCODE_SET, "cameraInfoInternal", "Landroidx/camera/core/impl/CameraInfoInternal;", "sessionConfig", "Landroidx/camera/core/SessionConfig;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DynamicRangeFeature extends GroupableFeature {
    public static final Companion Companion = null;

    @JvmField
    public static final DynamicRange DEFAULT_DYNAMIC_RANGE = null;
    private final DynamicRange dynamicRange;
    private final FeatureTypeInternal featureTypeInternal;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/camera/core/featuregroup/impl/feature/DynamicRangeFeature$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "TAG", "Ljava/lang/String;", "Landroidx/camera/core/DynamicRange;", "DEFAULT_DYNAMIC_RANGE", "Landroidx/camera/core/DynamicRange;", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        DEFAULT_DYNAMIC_RANGE = DynamicRange.SDR;
    }

    public DynamicRangeFeature(DynamicRange r1) {
        this.dynamicRange = r1;
        this.featureTypeInternal = FeatureTypeInternal.DYNAMIC_RANGE;
    }

    public final DynamicRange getDynamicRange() {
        return this.dynamicRange;
    }

    @Override // androidx.camera.core.featuregroup.GroupableFeature
    public FeatureTypeInternal getFeatureTypeInternal() {
        return this.featureTypeInternal;
    }

    @Override // androidx.camera.core.featuregroup.GroupableFeature
    public boolean isSupportedIndividually(CameraInfoInternal r8, SessionConfig r9) {
        Set<DynamicRange> r0 = r8.getSupportedDynamicRanges();
        Logger.d("DynamicRangeFeature", "isSupportedIndividually: cameraInfoSupportedDynamicRanges = " + r0 + ", this = " + this);
        if (r0.contains(this.dynamicRange) == true) goto L5;
        return false;
    L5:
        Iterator<UseCase> r92 = r9.getUseCases().iterator();
    L7:
        if (r92.hasNext() == false) goto L13;
        UseCase r02 = r92.next();
        Set<DynamicRange> r4 = r02.getSupportedDynamicRanges(r8);
        Logger.d("DynamicRangeFeature", "isSupportedIndividually: useCaseSupportedDynamicRanges = " + r4 + ", this = " + this + ", useCases = " + r02);
        if (r4 == null) goto L7;
        if (r4.contains(this.dynamicRange) == true) goto L7;
        return false;
    L13:
        return true;
    }

    public String toString() {
        return "DynamicRangeFeature(dynamicRange=" + this.dynamicRange + ')';
    }
}
