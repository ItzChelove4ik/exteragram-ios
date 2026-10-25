package androidx.camera.core;

import androidx.camera.core.concurrent.CameraCoordinator;
import androidx.camera.core.impl.AdapterCameraInfo;
import androidx.camera.core.impl.CameraConfigs;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CameraRepository;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.camera.core.internal.StreamSpecsCalculator;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J<\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0016JD\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Landroidx/camera/core/CameraUseCaseAdapterProviderImpl;", "Landroidx/camera/core/CameraUseCaseAdapterProvider;", "cameraRepository", "Landroidx/camera/core/impl/CameraRepository;", "cameraCoordinator", "Landroidx/camera/core/concurrent/CameraCoordinator;", "useCaseConfigFactory", "Landroidx/camera/core/impl/UseCaseConfigFactory;", "streamSpecsCalculator", "Landroidx/camera/core/internal/StreamSpecsCalculator;", "<init>", "(Landroidx/camera/core/impl/CameraRepository;Landroidx/camera/core/concurrent/CameraCoordinator;Landroidx/camera/core/impl/UseCaseConfigFactory;Landroidx/camera/core/internal/StreamSpecsCalculator;)V", "provide", "Landroidx/camera/core/internal/CameraUseCaseAdapter;", "cameraId", _UrlKt.FRAGMENT_ENCODE_SET, "camera", "Landroidx/camera/core/impl/CameraInternal;", "secondaryCamera", "adapterCameraInfo", "Landroidx/camera/core/impl/AdapterCameraInfo;", "secondaryAdapterCameraInfo", "compositionSettings", "Landroidx/camera/core/CompositionSettings;", "secondaryCompositionSettings", "provideInternal", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CameraUseCaseAdapterProviderImpl implements CameraUseCaseAdapterProvider {
    private final CameraCoordinator cameraCoordinator;
    private final CameraRepository cameraRepository;
    private final StreamSpecsCalculator streamSpecsCalculator;
    private final UseCaseConfigFactory useCaseConfigFactory;

    public CameraUseCaseAdapterProviderImpl(CameraRepository r1, CameraCoordinator r2, UseCaseConfigFactory r3, StreamSpecsCalculator r4) {
        this.cameraRepository = r1;
        this.cameraCoordinator = r2;
        this.useCaseConfigFactory = r3;
        this.streamSpecsCalculator = r4;
    }

    private final CameraUseCaseAdapter provideInternal(CameraInternal r11, CameraInternal r12, AdapterCameraInfo r13, AdapterCameraInfo r14, CompositionSettings r15, CompositionSettings r16) {
        return new CameraUseCaseAdapter(r11, r12, r13, r14, r15, r16, this.cameraCoordinator, this.streamSpecsCalculator, this.useCaseConfigFactory);
    }

    public static /* synthetic */ CameraUseCaseAdapter provideInternal$default(CameraUseCaseAdapterProviderImpl r1, CameraInternal r2, CameraInternal r3, AdapterCameraInfo r4, AdapterCameraInfo r5, CompositionSettings r6, CompositionSettings r7, int r8, Object r9) {
        if ((r8 & 2) == 0) goto L6;
        r3 = null;
    L6:
        if ((r8 & 8) == 0) goto L9;
        r5 = null;
    L9:
        if ((r8 & 16) == 0) goto L12;
        r6 = CompositionSettings.DEFAULT;
    L12:
        if ((r8 & 32) == 0) goto L15;
        r7 = CompositionSettings.DEFAULT;
    L15:
        return r1.provideInternal(r2, r3, r4, r5, r6, r7);
    }

    @Override // androidx.camera.core.CameraUseCaseAdapterProvider
    public CameraUseCaseAdapter provide(CameraInternal r1, CameraInternal r2, AdapterCameraInfo r3, AdapterCameraInfo r4, CompositionSettings r5, CompositionSettings r6) {
        return provideInternal(r1, r2, r3, r4, r5, r6);
    }

    @Override // androidx.camera.core.CameraUseCaseAdapterProvider
    public CameraUseCaseAdapter provide(String r11) {
        CameraInternal r2 = this.cameraRepository.getCamera(r11);
        return provideInternal$default(this, r2, null, new AdapterCameraInfo(r2.getCameraInfoInternal(), CameraConfigs.defaultConfig()), null, null, null, 58, null);
    }
}
