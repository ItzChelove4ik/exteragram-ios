package androidx.camera.core.featuregroup.impl.feature;

import androidx.camera.core.featuregroup.GroupableFeature;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Landroidx/camera/core/featuregroup/impl/feature/ImageFormatFeature;", "Landroidx/camera/core/featuregroup/GroupableFeature;", "imageCaptureOutputFormat", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "getImageCaptureOutputFormat", "()I", "featureTypeInternal", "Landroidx/camera/core/featuregroup/impl/feature/FeatureTypeInternal;", "getFeatureTypeInternal", "()Landroidx/camera/core/featuregroup/impl/feature/FeatureTypeInternal;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "getOutputFormatLabel", "Companion", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ImageFormatFeature extends GroupableFeature {
    public static final Companion Companion = null;
    private final FeatureTypeInternal featureTypeInternal;
    private final int imageCaptureOutputFormat;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/camera/core/featuregroup/impl/feature/ImageFormatFeature$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "DEFAULT_IMAGE_CAPTURE_OUTPUT_FORMAT", _UrlKt.FRAGMENT_ENCODE_SET, "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public ImageFormatFeature(int r1) {
        this.imageCaptureOutputFormat = r1;
        this.featureTypeInternal = FeatureTypeInternal.IMAGE_FORMAT;
    }

    private final String getOutputFormatLabel() {
        int r0 = this.imageCaptureOutputFormat;
        if (r0 != 0) goto L5;
        return "JPEG";
    L5:
        if (r0 != 1) goto L7;
        return "JPEG_R";
    L7:
        return "UNDEFINED(" + this.imageCaptureOutputFormat + ')';
    }

    @Override // androidx.camera.core.featuregroup.GroupableFeature
    public FeatureTypeInternal getFeatureTypeInternal() {
        return this.featureTypeInternal;
    }

    public final int getImageCaptureOutputFormat() {
        return this.imageCaptureOutputFormat;
    }

    public String toString() {
        return "ImageFormatFeature(imageCaptureOutputFormat=" + getOutputFormatLabel() + ')';
    }
}
