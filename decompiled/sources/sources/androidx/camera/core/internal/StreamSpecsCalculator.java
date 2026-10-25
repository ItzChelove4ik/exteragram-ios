package androidx.camera.core.internal;

import android.util.Range;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraInfoInternal;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016Jn\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\t2\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0016H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0019À\u0006\u0001"}, d2 = {"Landroidx/camera/core/internal/StreamSpecsCalculator;", _UrlKt.FRAGMENT_ENCODE_SET, "setCameraDeviceSurfaceManager", _UrlKt.FRAGMENT_ENCODE_SET, "cameraDeviceSurfaceManager", "Landroidx/camera/core/impl/CameraDeviceSurfaceManager;", "calculateSuggestedStreamSpecs", "Landroidx/camera/core/internal/StreamSpecQueryResult;", "cameraMode", _UrlKt.FRAGMENT_ENCODE_SET, "cameraInfoInternal", "Landroidx/camera/core/impl/CameraInfoInternal;", "newUseCases", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "attachedUseCases", "cameraConfig", "Landroidx/camera/core/impl/CameraConfig;", "sessionType", "targetFrameRate", "Landroid/util/Range;", "isFeatureComboInvocation", _UrlKt.FRAGMENT_ENCODE_SET, "findMaxSupportedFrameRate", "Companion", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface StreamSpecsCalculator {
    public static final Companion Companion = null;

    @JvmField
    public static final StreamSpecsCalculator NO_OP_STREAM_SPECS_CALCULATOR = null;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\u0001¨\u0006\u0007"}, d2 = {"Landroidx/camera/core/internal/StreamSpecsCalculator$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/core/internal/StreamSpecsCalculator;", "NO_OP_STREAM_SPECS_CALCULATOR", "Landroidx/camera/core/internal/StreamSpecsCalculator;", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        static {
            $$INSTANCE = new Companion();
        }

        private Companion() {
        }
    }

    static {
        Companion = Companion.$$INSTANCE;
        NO_OP_STREAM_SPECS_CALCULATOR = new StreamSpecsCalculator$Companion$NO_OP_STREAM_SPECS_CALCULATOR$1();
    }

    StreamSpecQueryResult calculateSuggestedStreamSpecs(int r1, CameraInfoInternal r2, List<? extends UseCase> r3, List<? extends UseCase> r4, CameraConfig r5, int r6, Range<Integer> r7, boolean r8, boolean r9);

    default void setCameraDeviceSurfaceManager(CameraDeviceSurfaceManager r1) {
    }
}
