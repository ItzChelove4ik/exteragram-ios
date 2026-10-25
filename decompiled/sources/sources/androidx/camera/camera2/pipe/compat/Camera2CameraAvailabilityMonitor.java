package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraManager;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.compat.CameraAvailabilityMonitor;
import androidx.camera.camera2.pipe.core.Threads;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B)\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0096@¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2CameraAvailabilityMonitor;", "Landroidx/camera/camera2/pipe/compat/CameraAvailabilityMonitor;", "cameraManager", "Ljavax/inject/Provider;", "Landroid/hardware/camera2/CameraManager;", "threads", "Landroidx/camera/camera2/pipe/core/Threads;", "cameraPipeJob", "Lkotlinx/coroutines/Job;", "<init>", "(Ljavax/inject/Provider;Landroidx/camera/camera2/pipe/core/Threads;Lkotlinx/coroutines/Job;)V", "availableCameraFlow", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/camera/camera2/pipe/CameraId;", "startMonitoring", "Landroidx/camera/camera2/pipe/compat/CameraAvailabilityMonitor$Session;", "cameraId", "startMonitoring-0r8Bogc", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Camera2CameraAvailabilityMonitor implements CameraAvailabilityMonitor {
    private final Flow<CameraId> availableCameraFlow;
    private final Provider<CameraManager> cameraManager;
    private final Job cameraPipeJob;
    private final Threads threads;

    public Camera2CameraAvailabilityMonitor(Provider<CameraManager> r1, Threads r2, Job r3) {
        this.cameraManager = r1;
        this.threads = r2;
        this.cameraPipeJob = r3;
        this.availableCameraFlow = FlowKt.callbackFlow(new Camera2CameraAvailabilityMonitor$availableCameraFlow$1(this, null));
    }

    public static final /* synthetic */ Flow access$getAvailableCameraFlow$p(Camera2CameraAvailabilityMonitor r0) {
        return r0.availableCameraFlow;
    }

    public static final /* synthetic */ Provider access$getCameraManager$p(Camera2CameraAvailabilityMonitor r0) {
        return r0.cameraManager;
    }

    public static final /* synthetic */ Job access$getCameraPipeJob$p(Camera2CameraAvailabilityMonitor r0) {
        return r0.cameraPipeJob;
    }

    public static final /* synthetic */ Threads access$getThreads$p(Camera2CameraAvailabilityMonitor r0) {
        return r0.threads;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraAvailabilityMonitor
    /* renamed from: startMonitoring-0r8Bogc, reason: not valid java name */
    public Object mo427startMonitoring0r8Bogc(String r1, Continuation<? super CameraAvailabilityMonitor.Session> r2) {
        return new Camera2CameraAvailabilityMonitor$startMonitoring$2(this, r1);
    }
}
