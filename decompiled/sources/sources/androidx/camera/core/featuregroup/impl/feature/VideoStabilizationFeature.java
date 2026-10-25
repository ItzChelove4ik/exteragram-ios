package androidx.camera.core.featuregroup.impl.feature;

import androidx.camera.core.SessionConfig;
import androidx.camera.core.featuregroup.GroupableFeature;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.stabilization.VideoStabilization;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Landroidx/camera/core/featuregroup/impl/feature/VideoStabilizationFeature;", "Landroidx/camera/core/featuregroup/GroupableFeature;", "videoStabilization", "Landroidx/camera/core/impl/stabilization/VideoStabilization;", "<init>", "(Landroidx/camera/core/impl/stabilization/VideoStabilization;)V", "getVideoStabilization", "()Landroidx/camera/core/impl/stabilization/VideoStabilization;", "featureTypeInternal", "Landroidx/camera/core/featuregroup/impl/feature/FeatureTypeInternal;", "getFeatureTypeInternal", "()Landroidx/camera/core/featuregroup/impl/feature/FeatureTypeInternal;", "isSupportedIndividually", _UrlKt.FRAGMENT_ENCODE_SET, "cameraInfoInternal", "Landroidx/camera/core/impl/CameraInfoInternal;", "sessionConfig", "Landroidx/camera/core/SessionConfig;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoStabilizationFeature extends GroupableFeature {
    public static final Companion Companion = null;

    @JvmField
    public static final VideoStabilization DEFAULT_STABILIZATION = null;
    private final FeatureTypeInternal featureTypeInternal;
    private final VideoStabilization videoStabilization;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/camera/core/featuregroup/impl/feature/VideoStabilizationFeature$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "DEFAULT_STABILIZATION", "Landroidx/camera/core/impl/stabilization/VideoStabilization;", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[VideoStabilization.values().length];
            r0[VideoStabilization.ON.ordinal()] = 1;     // Catch: NoSuchFieldError -> L9
        L13:
            r0[VideoStabilization.PREVIEW.ordinal()] = 2;     // Catch: NoSuchFieldError -> L10
        L19:
            r0[VideoStabilization.OFF.ordinal()] = 3;     // Catch: NoSuchFieldError -> L11
        L15:
            r0[VideoStabilization.UNSPECIFIED.ordinal()] = 4;     // Catch: NoSuchFieldError -> L12
        L7:
            $EnumSwitchMapping$0 = r0;
        }
    }

    static {
        Companion = new Companion(null);
        DEFAULT_STABILIZATION = VideoStabilization.OFF;
    }

    public VideoStabilizationFeature(VideoStabilization r1) {
        this.videoStabilization = r1;
        this.featureTypeInternal = FeatureTypeInternal.VIDEO_STABILIZATION;
    }

    @Override // androidx.camera.core.featuregroup.GroupableFeature
    public FeatureTypeInternal getFeatureTypeInternal() {
        return this.featureTypeInternal;
    }

    public final VideoStabilization getVideoStabilization() {
        return this.videoStabilization;
    }

    @Override // androidx.camera.core.featuregroup.GroupableFeature
    public boolean isSupportedIndividually(CameraInfoInternal r2, SessionConfig r3) {
        VideoStabilization r1 = this.videoStabilization;
        int r12 = WhenMappings.$EnumSwitchMapping$0[r1.ordinal()];
        if (r12 == 1) goto L17;
        if (r12 == 2) goto L15;
        if (r12 != 3) goto L9;
    L13:
        return true;
    L9:
        if (r12 == 4) goto L13;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return false;
    L15:
        return r2.isPreviewStabilizationSupported();
    L17:
        return r2.isVideoStabilizationSupported();
    }

    public String toString() {
        return "VideoStabilizationFeature(mode=" + this.videoStabilization.name() + ')';
    }
}
