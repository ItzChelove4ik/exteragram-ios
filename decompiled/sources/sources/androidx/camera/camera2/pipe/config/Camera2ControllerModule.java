package androidx.camera.camera2.pipe.config;

import android.hardware.camera2.CameraManager;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.compat.Camera2CameraStatusMonitor;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.internal.CameraStatusMonitor;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b!\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/pipe/config/Camera2ControllerModule;", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class Camera2ControllerModule {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\u0007J0\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0001\u0010\b\u001a\u00020\tH\u0007¨\u0006\u0011"}, d2 = {"Landroidx/camera/camera2/pipe/config/Camera2ControllerModule$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "provideCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "threads", "Landroidx/camera/camera2/pipe/core/Threads;", "cameraPipeJob", "Lkotlinx/coroutines/Job;", "provideCameraStatusMonitor", "Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor;", "cameraManager", "Ljavax/inject/Provider;", "Landroid/hardware/camera2/CameraManager;", "graphConfig", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final CameraStatusMonitor provideCameraStatusMonitor(Provider<CameraManager> r7, Threads r8, CameraGraph.Config r9, Job r10) {
            return new Camera2CameraStatusMonitor(r7, r8, r9.m200getCameraDz_R5H8(), r10, null);
        }

        public final CoroutineScope provideCoroutineScope(Threads r2, Job r3) {
            return CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob(r3).plus(r2.getLightweightDispatcher().plus(new CoroutineName("CXCP-Camera2Controller"))));
        }
    }

    static {
        Companion = new Companion(null);
    }
}
