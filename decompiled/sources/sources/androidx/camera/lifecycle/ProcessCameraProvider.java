package androidx.camera.lifecycle;

import android.content.Context;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ConcurrentCamera;
import androidx.camera.core.SessionConfig;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LifecycleOwner;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000 -2\u00020\u0001:\u0001-B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\r\u0010\u000eJ7\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0016\u0010\u0015\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00140\u0013\"\u0004\u0018\u00010\u0014H\u0007¢\u0006\u0004\b\u0017\u0010\u0018J%\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u0017\u0010\u001bJ\u001f\u0010\u0017\u001a\u00020\u001f2\u000e\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u001cH\u0007¢\u0006\u0004\b\u0017\u0010 J\u0017\u0010\"\u001a\u00020!2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\"\u0010#J\u0017\u0010%\u001a\u00020$2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b%\u0010&R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010'R\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020$0\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R \u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u001c0\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010)¨\u0006."}, d2 = {"Landroidx/camera/lifecycle/ProcessCameraProvider;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/lifecycle/LifecycleCameraProviderImpl;", "lifecycleCameraProvider", "<init>", "(Landroidx/camera/lifecycle/LifecycleCameraProviderImpl;)V", "Landroid/content/Context;", "context", "Lcom/google/common/util/concurrent/ListenableFuture;", "Ljava/lang/Void;", "initAsync", "(Landroid/content/Context;)Lcom/google/common/util/concurrent/ListenableFuture;", _UrlKt.FRAGMENT_ENCODE_SET, "unbindAll", "()V", "Landroidx/lifecycle/LifecycleOwner;", "lifecycleOwner", "Landroidx/camera/core/CameraSelector;", "cameraSelector", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "useCases", "Landroidx/camera/core/Camera;", "bindToLifecycle", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/camera/core/CameraSelector;[Landroidx/camera/core/UseCase;)Landroidx/camera/core/Camera;", "Landroidx/camera/core/SessionConfig;", "sessionConfig", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/camera/core/CameraSelector;Landroidx/camera/core/SessionConfig;)Landroidx/camera/core/Camera;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/ConcurrentCamera$SingleCameraConfig;", "singleCameraConfigs", "Landroidx/camera/core/ConcurrentCamera;", "(Ljava/util/List;)Landroidx/camera/core/ConcurrentCamera;", _UrlKt.FRAGMENT_ENCODE_SET, "hasCamera", "(Landroidx/camera/core/CameraSelector;)Z", "Landroidx/camera/core/CameraInfo;", "getCameraInfo", "(Landroidx/camera/core/CameraSelector;)Landroidx/camera/core/CameraInfo;", "Landroidx/camera/lifecycle/LifecycleCameraProviderImpl;", "getAvailableCameraInfos", "()Ljava/util/List;", "availableCameraInfos", "getAvailableConcurrentCameraInfos", "availableConcurrentCameraInfos", "Companion", "camera-lifecycle"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ProcessCameraProvider {
    public static final Companion Companion = null;
    private static final ProcessCameraProvider sAppInstance = null;
    private final LifecycleCameraProviderImpl lifecycleCameraProvider;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/camera/lifecycle/ProcessCameraProvider$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/content/Context;", "context", "Lcom/google/common/util/concurrent/ListenableFuture;", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "getInstance", "(Landroid/content/Context;)Lcom/google/common/util/concurrent/ListenableFuture;", "sAppInstance", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "camera-lifecycle"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nProcessCameraProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProcessCameraProvider.kt\nandroidx/camera/lifecycle/ProcessCameraProvider$Companion\n+ 2 Trace.kt\nandroidx/tracing/TraceKt\n*L\n1#1,539:1\n27#2,5:540\n*S KotlinDebug\n*F\n+ 1 ProcessCameraProvider.kt\nandroidx/camera/lifecycle/ProcessCameraProvider$Companion\n*L\n512#1:540,5\n*E\n"})
    public static final class Companion {
        public static ProcessCameraProvider $r8$lambda$CjFKEa1HNEh7c_io6Z4hztLk6yo(Function1 r0, Object r1) {
            return (ProcessCameraProvider) r0.invoke(r1);
        }

        public static ProcessCameraProvider $r8$lambda$cqQcQxl1ave5WeNGbuERjQg2mr8(Void r0) {
            return ProcessCameraProvider.access$getSAppInstance$cp();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final ListenableFuture<ProcessCameraProvider> getInstance(Context r2) {
            Preconditions.checkNotNull(r2);
            return Futures.transform(ProcessCameraProvider.access$initAsync(ProcessCameraProvider.access$getSAppInstance$cp(), r2), new ProcessCameraProvider$Companion$$ExternalSyntheticLambda1(new ProcessCameraProvider$Companion$$ExternalSyntheticLambda0()), CameraXExecutors.directExecutor());
        }
    }

    static {
        Companion = new Companion(null);
        sAppInstance = new ProcessCameraProvider(new LifecycleCameraProviderImpl());
    }

    private ProcessCameraProvider(LifecycleCameraProviderImpl r1) {
        this.lifecycleCameraProvider = r1;
    }

    public static final /* synthetic */ ProcessCameraProvider access$getSAppInstance$cp() {
        return sAppInstance;
    }

    public static final /* synthetic */ ListenableFuture access$initAsync(ProcessCameraProvider r0, Context r1) {
        return r0.initAsync(r1);
    }

    @JvmStatic
    public static final ListenableFuture<ProcessCameraProvider> getInstance(Context r1) {
        return Companion.getInstance(r1);
    }

    private final ListenableFuture<Void> initAsync(Context r2) {
        return this.lifecycleCameraProvider.initAsync$camera_lifecycle(r2, null);
    }

    public final Camera bindToLifecycle(LifecycleOwner r1, CameraSelector r2, SessionConfig r3) {
        return this.lifecycleCameraProvider.bindToLifecycle(r1, r2, r3);
    }

    public final Camera bindToLifecycle(LifecycleOwner r2, CameraSelector r3, UseCase... r4) {
        return this.lifecycleCameraProvider.bindToLifecycle(r2, r3, (UseCase[]) Arrays.copyOf(r4, r4.length));
    }

    public final ConcurrentCamera bindToLifecycle(List<ConcurrentCamera.SingleCameraConfig> r1) {
        return this.lifecycleCameraProvider.bindToLifecycle(r1);
    }

    public List<CameraInfo> getAvailableCameraInfos() {
        return this.lifecycleCameraProvider.getAvailableCameraInfos();
    }

    public List<List<CameraInfo>> getAvailableConcurrentCameraInfos() {
        return this.lifecycleCameraProvider.getAvailableConcurrentCameraInfos();
    }

    public CameraInfo getCameraInfo(CameraSelector r1) {
        return this.lifecycleCameraProvider.getCameraInfo(r1);
    }

    public boolean hasCamera(CameraSelector r1) {
        return this.lifecycleCameraProvider.hasCamera(r1);
    }

    public final void unbindAll() {
        this.lifecycleCameraProvider.unbindAll();
    }
}
