package androidx.camera.core.internal;

import android.util.Range;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraInfoInternal;
import java.util.List;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000?\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001Jb\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¨\u0006\u0014"}, d2 = {"androidx/camera/core/internal/StreamSpecsCalculator$Companion$NO_OP_STREAM_SPECS_CALCULATOR$1", "Landroidx/camera/core/internal/StreamSpecsCalculator;", "calculateSuggestedStreamSpecs", "Landroidx/camera/core/internal/StreamSpecQueryResult;", "cameraMode", _UrlKt.FRAGMENT_ENCODE_SET, "cameraInfoInternal", "Landroidx/camera/core/impl/CameraInfoInternal;", "newUseCases", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "attachedUseCases", "cameraConfig", "Landroidx/camera/core/impl/CameraConfig;", "sessionType", "targetFrameRate", "Landroid/util/Range;", "isFeatureComboInvocation", _UrlKt.FRAGMENT_ENCODE_SET, "findMaxSupportedFrameRate", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class StreamSpecsCalculator$Companion$NO_OP_STREAM_SPECS_CALCULATOR$1 implements StreamSpecsCalculator {
    public StreamSpecsCalculator$Companion$NO_OP_STREAM_SPECS_CALCULATOR$1() {
    }

    @Override // androidx.camera.core.internal.StreamSpecsCalculator
    public StreamSpecQueryResult calculateSuggestedStreamSpecs(int r1, CameraInfoInternal r2, List<? extends UseCase> r3, List<? extends UseCase> r4, CameraConfig r5, int r6, Range<Integer> r7, boolean r8, boolean r9) {
        return new StreamSpecQueryResult(null, 0, 3, null);
    }
}
