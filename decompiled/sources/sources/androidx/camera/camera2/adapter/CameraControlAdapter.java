package androidx.camera.camera2.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.impl.CameraProperties;
import androidx.camera.camera2.impl.EvCompControl;
import androidx.camera.camera2.impl.FlashControl;
import androidx.camera.camera2.impl.FocusMeteringControl;
import androidx.camera.camera2.impl.LowLightBoostControl;
import androidx.camera.camera2.impl.StillCaptureRequestControl;
import androidx.camera.camera2.impl.TorchControl;
import androidx.camera.camera2.impl.UseCaseManager;
import androidx.camera.camera2.impl.UseCaseThreads;
import androidx.camera.camera2.impl.VideoUsageControl;
import androidx.camera.camera2.impl.ZoomControl;
import androidx.camera.camera2.interop.Camera2CameraControl;
import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.futures.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0007\u0018\u00002\u00020\u0001Bq\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020 H\u0016¢\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\u001eH\u0016¢\u0006\u0004\b%\u0010&J\u001d\u0010+\u001a\b\u0012\u0004\u0012\u00020*0)2\u0006\u0010(\u001a\u00020'H\u0016¢\u0006\u0004\b+\u0010,J\u001d\u00100\u001a\b\u0012\u0004\u0012\u00020/0)2\u0006\u0010.\u001a\u00020-H\u0016¢\u0006\u0004\b0\u00101J\u001d\u00104\u001a\b\u0012\u0004\u0012\u00020*0)2\u0006\u00103\u001a\u000202H\u0016¢\u0006\u0004\b4\u00105J\u0017\u00108\u001a\u00020 2\u0006\u00107\u001a\u000206H\u0016¢\u0006\u0004\b8\u00109J\u0019\u0010<\u001a\u00020 2\b\u0010;\u001a\u0004\u0018\u00010:H\u0016¢\u0006\u0004\b<\u0010=J\u0017\u0010@\u001a\u00020 2\u0006\u0010?\u001a\u00020>H\u0016¢\u0006\u0004\b@\u0010AJ\u000f\u0010B\u001a\u00020 H\u0016¢\u0006\u0004\bB\u0010$R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010CR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010DR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010ER\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010FR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010GR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010HR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010IR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010JR\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010KR\u0017\u0010\u0015\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010L\u001a\u0004\bM\u0010NR\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010OR\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010PR\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010Q¨\u0006R"}, d2 = {"Landroidx/camera/camera2/adapter/CameraControlAdapter;", "Landroidx/camera/core/impl/CameraControlInternal;", "Landroidx/camera/camera2/impl/CameraProperties;", "cameraProperties", "Landroidx/camera/camera2/impl/EvCompControl;", "evCompControl", "Landroidx/camera/camera2/impl/FlashControl;", "flashControl", "Landroidx/camera/camera2/impl/FocusMeteringControl;", "focusMeteringControl", "Landroidx/camera/camera2/impl/StillCaptureRequestControl;", "stillCaptureRequestControl", "Landroidx/camera/camera2/impl/TorchControl;", "torchControl", "Landroidx/camera/camera2/impl/LowLightBoostControl;", "lowLightBoostControl", "Landroidx/camera/camera2/impl/ZoomControl;", "zoomControl", "Landroidx/camera/camera2/adapter/ZslControl;", "zslControl", "Landroidx/camera/camera2/interop/Camera2CameraControl;", "camera2cameraControl", "Landroidx/camera/camera2/impl/UseCaseManager;", "useCaseManager", "Landroidx/camera/camera2/impl/UseCaseThreads;", "threads", "Landroidx/camera/camera2/impl/VideoUsageControl;", "videoUsageControl", "<init>", "(Landroidx/camera/camera2/impl/CameraProperties;Landroidx/camera/camera2/impl/EvCompControl;Landroidx/camera/camera2/impl/FlashControl;Landroidx/camera/camera2/impl/FocusMeteringControl;Landroidx/camera/camera2/impl/StillCaptureRequestControl;Landroidx/camera/camera2/impl/TorchControl;Landroidx/camera/camera2/impl/LowLightBoostControl;Landroidx/camera/camera2/impl/ZoomControl;Landroidx/camera/camera2/adapter/ZslControl;Landroidx/camera/camera2/interop/Camera2CameraControl;Landroidx/camera/camera2/impl/UseCaseManager;Landroidx/camera/camera2/impl/UseCaseThreads;Landroidx/camera/camera2/impl/VideoUsageControl;)V", "Landroidx/camera/core/impl/Config;", "config", _UrlKt.FRAGMENT_ENCODE_SET, "addInteropConfig", "(Landroidx/camera/core/impl/Config;)V", "clearInteropConfig", "()V", "getInteropConfig", "()Landroidx/camera/core/impl/Config;", _UrlKt.FRAGMENT_ENCODE_SET, "torch", "Lcom/google/common/util/concurrent/ListenableFuture;", "Ljava/lang/Void;", "enableTorch", "(Z)Lcom/google/common/util/concurrent/ListenableFuture;", "Landroidx/camera/core/FocusMeteringAction;", "action", "Landroidx/camera/core/FocusMeteringResult;", "startFocusAndMetering", "(Landroidx/camera/core/FocusMeteringAction;)Lcom/google/common/util/concurrent/ListenableFuture;", _UrlKt.FRAGMENT_ENCODE_SET, "ratio", "setZoomRatio", "(F)Lcom/google/common/util/concurrent/ListenableFuture;", _UrlKt.FRAGMENT_ENCODE_SET, "flashMode", "setFlashMode", "(I)V", "Landroidx/camera/core/ImageCapture$ScreenFlash;", "screenFlash", "setScreenFlash", "(Landroidx/camera/core/ImageCapture$ScreenFlash;)V", "Landroidx/camera/core/impl/SessionConfig$Builder;", "sessionConfigBuilder", "addZslConfig", "(Landroidx/camera/core/impl/SessionConfig$Builder;)V", "clearZslConfig", "Landroidx/camera/camera2/impl/CameraProperties;", "Landroidx/camera/camera2/impl/EvCompControl;", "Landroidx/camera/camera2/impl/FlashControl;", "Landroidx/camera/camera2/impl/FocusMeteringControl;", "Landroidx/camera/camera2/impl/StillCaptureRequestControl;", "Landroidx/camera/camera2/impl/TorchControl;", "Landroidx/camera/camera2/impl/LowLightBoostControl;", "Landroidx/camera/camera2/impl/ZoomControl;", "Landroidx/camera/camera2/adapter/ZslControl;", "Landroidx/camera/camera2/interop/Camera2CameraControl;", "getCamera2cameraControl", "()Landroidx/camera/camera2/interop/Camera2CameraControl;", "Landroidx/camera/camera2/impl/UseCaseManager;", "Landroidx/camera/camera2/impl/UseCaseThreads;", "Landroidx/camera/camera2/impl/VideoUsageControl;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"UnsafeOptInUsageError"})
@SourceDebugExtension({"SMAP\nCameraControlAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraControlAdapter.kt\nandroidx/camera/camera2/adapter/CameraControlAdapter\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 CoroutineAdapters.kt\nandroidx/camera/camera2/adapter/CoroutineAdaptersKt\n*L\n1#1,247:1\n85#2,4:248\n85#2,4:252\n119#2,4:260\n1#3:256\n102#4,3:257\n*S KotlinDebug\n*F\n+ 1 CameraControlAdapter.kt\nandroidx/camera/camera2/adapter/CameraControlAdapter\n*L\n106#1:248,4\n126#1:252,4\n233#1:260,4\n223#1:257,3\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraControlAdapter implements CameraControlInternal {
    private final Camera2CameraControl camera2cameraControl;
    private final CameraProperties cameraProperties;
    private final EvCompControl evCompControl;
    private final FlashControl flashControl;
    private final FocusMeteringControl focusMeteringControl;
    private final LowLightBoostControl lowLightBoostControl;
    private final StillCaptureRequestControl stillCaptureRequestControl;
    private final UseCaseThreads threads;
    private final TorchControl torchControl;
    private final UseCaseManager useCaseManager;
    private final VideoUsageControl videoUsageControl;
    private final ZoomControl zoomControl;
    private final ZslControl zslControl;

    public CameraControlAdapter(CameraProperties r1, EvCompControl r2, FlashControl r3, FocusMeteringControl r4, StillCaptureRequestControl r5, TorchControl r6, LowLightBoostControl r7, ZoomControl r8, ZslControl r9, Camera2CameraControl r10, UseCaseManager r11, UseCaseThreads r12, VideoUsageControl r13) {
        this.cameraProperties = r1;
        this.evCompControl = r2;
        this.flashControl = r3;
        this.focusMeteringControl = r4;
        this.stillCaptureRequestControl = r5;
        this.torchControl = r6;
        this.lowLightBoostControl = r7;
        this.zoomControl = r8;
        this.zslControl = r9;
        this.camera2cameraControl = r10;
        this.useCaseManager = r11;
        this.threads = r12;
        this.videoUsageControl = r13;
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void addInteropConfig(Config r2) {
        this.camera2cameraControl.addCaptureRequestOptions(CaptureRequestOptions.Builder.Companion.from(r2).build());
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void addZslConfig(SessionConfig.Builder r1) {
        this.zslControl.addZslConfig(r1);
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void clearInteropConfig() {
        this.camera2cameraControl.clearCaptureRequestOptions();
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void clearZslConfig() {
        this.zslControl.clearZslConfig();
    }

    @Override // androidx.camera.core.CameraControl
    public ListenableFuture<Void> enableTorch(boolean r7) {
        if (CameraMetadata.Companion.getSupportsLowLightBoost(this.cameraProperties.getMetadata()) == false) goto L15;
        Integer r0 = this.lowLightBoostControl.getLowLightBoostStateLiveData().getValue();
        if (r0 != null) goto L8;
    L9:
        Camera2Logger r6 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L13;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Unable to enable/disable torch when low-light boost is on.");
    L13:
        return Futures.immediateFailedFuture(new IllegalStateException("Torch can not be enabled/disable when low-light boost is on!"));
    L8:
        if (r0.intValue() != (-1)) goto L9;
    L15:
        return Futures.nonCancellationPropagating(CoroutineAdaptersKt.asVoidListenableFuture(TorchControl.setTorchAsync$default(this.torchControl, r7, false, false, 6, null)));
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public Config getInteropConfig() {
        return this.camera2cameraControl.getCaptureRequestOptions();
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void setFlashMode(int r5) {
        boolean r3 = false;
        FlashControl.setFlashAsync$default(this.flashControl, r5, false, 2, null);
        ZslControl r4 = this.zslControl;
        if (r5 == 1) goto L5;
        if (r5 == 0) goto L5;
    L6:
        r4.setZslDisabledByFlashMode(r3);
        return;
    L5:
        r3 = true;
        goto L6
    }

    @Override // androidx.camera.core.impl.CameraControlInternal
    public void setScreenFlash(ImageCapture.ScreenFlash r1) {
        this.flashControl.setScreenFlash(r1);
    }

    @Override // androidx.camera.core.CameraControl
    public ListenableFuture<Void> setZoomRatio(float r1) {
        return this.zoomControl.setZoomRatio(r1);
    }

    @Override // androidx.camera.core.CameraControl
    public ListenableFuture<FocusMeteringResult> startFocusAndMetering(FocusMeteringAction r7) {
        return Futures.nonCancellationPropagating(FocusMeteringControl.startFocusAndMetering$default(this.focusMeteringControl, r7, 0, 2, null));
    }
}
