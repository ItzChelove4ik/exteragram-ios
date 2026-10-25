package androidx.camera.core.impl;

import androidx.camera.core.CameraUseCaseAdapterProvider;
import androidx.camera.core.UseCase;
import androidx.camera.core.featuregroup.impl.ResolvedFeatureGroup;
import androidx.camera.core.internal.CalculatedUseCaseInfo;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0004\b\r\u0010\u000eR(\u0010\u0010\u001a\u00020\u000f8\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0004\b\u0010\u0010\u0011\u0012\u0004\b\u0016\u0010\u0003\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Landroidx/camera/core/impl/UseCaseAdditionSimulator;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/core/impl/CameraInfoInternal;", "cameraInfoInternal", "Landroidx/camera/core/SessionConfig;", "sessionConfig", _UrlKt.FRAGMENT_ENCODE_SET, "findMaxSupportedFrameRate", "Landroidx/camera/core/featuregroup/impl/ResolvedFeatureGroup;", "resolvedFeatureGroup", "Landroidx/camera/core/internal/CalculatedUseCaseInfo;", "simulateAddUseCases", "(Landroidx/camera/core/impl/CameraInfoInternal;Landroidx/camera/core/SessionConfig;ZLandroidx/camera/core/featuregroup/impl/ResolvedFeatureGroup;)Landroidx/camera/core/internal/CalculatedUseCaseInfo;", "Landroidx/camera/core/CameraUseCaseAdapterProvider;", "cameraUseCaseAdapterProvider", "Landroidx/camera/core/CameraUseCaseAdapterProvider;", "getCameraUseCaseAdapterProvider", "()Landroidx/camera/core/CameraUseCaseAdapterProvider;", "setCameraUseCaseAdapterProvider", "(Landroidx/camera/core/CameraUseCaseAdapterProvider;)V", "getCameraUseCaseAdapterProvider$annotations", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UseCaseAdditionSimulator {
    public static final UseCaseAdditionSimulator INSTANCE = null;
    public static CameraUseCaseAdapterProvider cameraUseCaseAdapterProvider;

    static {
        INSTANCE = new UseCaseAdditionSimulator();
    }

    private UseCaseAdditionSimulator() {
    }

    public static final CameraUseCaseAdapterProvider getCameraUseCaseAdapterProvider() {
        CameraUseCaseAdapterProvider r0 = cameraUseCaseAdapterProvider;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return null;
    }

    public static final void setCameraUseCaseAdapterProvider(CameraUseCaseAdapterProvider r0) {
        cameraUseCaseAdapterProvider = r0;
    }

    @JvmStatic
    @JvmOverloads
    public static final CalculatedUseCaseInfo simulateAddUseCases(CameraInfoInternal r6, androidx.camera.core.SessionConfig r7, boolean r8) {
        return simulateAddUseCases$default(r6, r7, r8, null, 8, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final CalculatedUseCaseInfo simulateAddUseCases(CameraInfoInternal r8, androidx.camera.core.SessionConfig r9, boolean r10, ResolvedFeatureGroup r11) {
        if (cameraUseCaseAdapterProvider == null) goto L9;
        CameraUseCaseAdapter r0 = getCameraUseCaseAdapterProvider().provide(r8.getCameraId());
        r9.getViewPort();
        r0.setViewPort(null);
        r0.setEffects(r9.getEffects());
        r0.setSessionType(r9.getSessionType());
        r0.setFrameRate(r9.getFrameRateRange());
        List<UseCase> r1 = r9.getUseCases();
        if (r11 != null) goto L8;
        r11 = ResolvedFeatureGroup.Companion.resolveFeatureGroup$default(ResolvedFeatureGroup.Companion, r9, r8, null, 2, null);
    L8:
        return r0.simulateAddUseCases(r1, r11, r10);
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("mCameraUseCaseAdapterProvider must be initialized first!");
        return null;
    }

    public static /* synthetic */ CalculatedUseCaseInfo simulateAddUseCases$default(CameraInfoInternal r0, androidx.camera.core.SessionConfig r1, boolean r2, ResolvedFeatureGroup r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r2 = false;
    L6:
        if ((r4 & 8) == 0) goto L9;
        r3 = null;
    L9:
        return simulateAddUseCases(r0, r1, r2, r3);
    }
}
