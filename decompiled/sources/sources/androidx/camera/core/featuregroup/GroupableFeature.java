package androidx.camera.core.featuregroup;

import androidx.camera.core.DynamicRange;
import androidx.camera.core.SessionConfig;
import androidx.camera.core.featuregroup.impl.feature.DynamicRangeFeature;
import androidx.camera.core.featuregroup.impl.feature.FeatureTypeInternal;
import androidx.camera.core.featuregroup.impl.feature.FpsRangeFeature;
import androidx.camera.core.featuregroup.impl.feature.ImageFormatFeature;
import androidx.camera.core.featuregroup.impl.feature.VideoStabilizationFeature;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.stabilization.VideoStabilization;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\b&\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u00020\u0005*\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0017¢\u0006\u0004\b\r\u0010\u000eR\u001b\u0010\u0013\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0016\u001a\u00020\u00048'X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Landroidx/camera/core/featuregroup/GroupableFeature;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/core/featuregroup/impl/feature/FeatureTypeInternal;", _UrlKt.FRAGMENT_ENCODE_SET, "toFeatureType", "(Landroidx/camera/core/featuregroup/impl/feature/FeatureTypeInternal;)I", "Landroidx/camera/core/impl/CameraInfoInternal;", "cameraInfoInternal", "Landroidx/camera/core/SessionConfig;", "sessionConfig", _UrlKt.FRAGMENT_ENCODE_SET, "isSupportedIndividually", "(Landroidx/camera/core/impl/CameraInfoInternal;Landroidx/camera/core/SessionConfig;)Z", "featureType$delegate", "Lkotlin/Lazy;", "getFeatureType", "()I", "featureType", "getFeatureTypeInternal", "()Landroidx/camera/core/featuregroup/impl/feature/FeatureTypeInternal;", "featureTypeInternal", "Companion", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class GroupableFeature {
    public static final Companion Companion = null;

    @JvmField
    public static final GroupableFeature FPS_60 = null;

    @JvmField
    public static final GroupableFeature HDR_HLG10 = null;

    @JvmField
    public static final GroupableFeature IMAGE_ULTRA_HDR = null;

    @JvmField
    public static final GroupableFeature PREVIEW_STABILIZATION = null;
    private final Lazy featureType$delegate;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/camera/core/featuregroup/GroupableFeature$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "HDR_HLG10", "Landroidx/camera/core/featuregroup/GroupableFeature;", "FPS_60", "PREVIEW_STABILIZATION", "IMAGE_ULTRA_HDR", "FEATURE_TYPE_DYNAMIC_RANGE", _UrlKt.FRAGMENT_ENCODE_SET, "FEATURE_TYPE_FPS_RANGE", "FEATURE_TYPE_VIDEO_STABILIZATION", "FEATURE_TYPE_IMAGE_FORMAT", "FEATURE_TYPE_RECORDING_QUALITY", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
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
            int[] r0 = new int[FeatureTypeInternal.values().length];
            r0[FeatureTypeInternal.DYNAMIC_RANGE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L10
        L15:
            r0[FeatureTypeInternal.FPS_RANGE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L11
        L23:
            r0[FeatureTypeInternal.VIDEO_STABILIZATION.ordinal()] = 3;     // Catch: NoSuchFieldError -> L12
        L17:
            r0[FeatureTypeInternal.IMAGE_FORMAT.ordinal()] = 4;     // Catch: NoSuchFieldError -> L13
        L19:
            r0[FeatureTypeInternal.RECORDING_QUALITY.ordinal()] = 5;     // Catch: NoSuchFieldError -> L14
        L8:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public static int $r8$lambda$5wZzs3lk5429yTODBHOaNC_0Qs8(GroupableFeature r1) {
        return r1.toFeatureType(r1.getFeatureTypeInternal());
    }

    static {
        Companion = new Companion(null);
        HDR_HLG10 = new DynamicRangeFeature(DynamicRange.HLG_10_BIT);
        FPS_60 = new FpsRangeFeature(60, 60);
        PREVIEW_STABILIZATION = new VideoStabilizationFeature(VideoStabilization.PREVIEW);
        IMAGE_ULTRA_HDR = new ImageFormatFeature(1);
    }

    public GroupableFeature() {
        this.featureType$delegate = LazyKt.lazy(new GroupableFeature$$ExternalSyntheticLambda0(this));
    }

    private final int toFeatureType(FeatureTypeInternal r2) {
        int r1 = WhenMappings.$EnumSwitchMapping$0[r2.ordinal()];
        if (r1 != 1) goto L5;
        return 0;
    L5:
        if (r1 != 2) goto L7;
        return 1;
    L7:
        if (r1 != 3) goto L9;
        return 2;
    L9:
        if (r1 != 4) goto L11;
        return 3;
    L11:
        if (r1 != 5) goto L13;
        return 4;
    L13:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    public abstract FeatureTypeInternal getFeatureTypeInternal();

    public boolean isSupportedIndividually(CameraInfoInternal r1, SessionConfig r2) {
        return true;
    }
}
