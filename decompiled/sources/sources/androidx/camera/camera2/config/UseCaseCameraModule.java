package androidx.camera.camera2.config;

import androidx.camera.camera2.compat.workaround.CapturePipelineTorchCorrection;
import androidx.camera.camera2.impl.CapturePipeline;
import androidx.camera.camera2.impl.CapturePipelineImpl;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b'\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/config/UseCaseCameraModule;", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class UseCaseCameraModule {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H\u0007¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/config/UseCaseCameraModule$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "provideCapturePipeline", "Landroidx/camera/camera2/impl/CapturePipeline;", "capturePipelineImplProvider", "Ljavax/inject/Provider;", "Landroidx/camera/camera2/impl/CapturePipelineImpl;", "capturePipelineTorchCorrectionProvider", "Landroidx/camera/camera2/compat/workaround/CapturePipelineTorchCorrection;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final CapturePipeline provideCapturePipeline(Provider<CapturePipelineImpl> r1, Provider<CapturePipelineTorchCorrection> r2) {
            if (CapturePipelineTorchCorrection.Companion.isEnabled() == false) goto L7;
            return r2.get();
        L7:
            return r1.get();
        }
    }

    static {
        Companion = new Companion(null);
    }
}
