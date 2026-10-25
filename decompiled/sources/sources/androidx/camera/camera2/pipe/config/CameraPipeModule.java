package androidx.camera.camera2.pipe.config;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Trace;
import androidx.camera.camera2.pipe.CameraBackend;
import androidx.camera.camera2.pipe.CameraBackendId;
import androidx.camera.camera2.pipe.CameraBackends;
import androidx.camera.camera2.pipe.CameraContext;
import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.camera2.pipe.CameraSurfaceManager;
import androidx.camera.camera2.pipe.StrictMode;
import androidx.camera.camera2.pipe.compat.AndroidDevicePolicyManagerWrapper;
import androidx.camera.camera2.pipe.compat.DevicePolicyManagerWrapper;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.graph.SurfaceGraph$$ExternalSyntheticBUOutline0;
import androidx.camera.camera2.pipe.internal.CameraBackendsImpl;
import androidx.camera.camera2.pipe.internal.CameraPipeLifetime;
import androidx.camera.camera2.pipe.media.ImageReaderImageSources;
import androidx.camera.camera2.pipe.media.ImageSources;
import androidx.camera.featurecombinationquery.CameraDeviceSetupCompatFactory;
import androidx.lifecycle.LifecycleRegistry$$ExternalSyntheticBUOutline0;
import java.util.Map;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b!\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/pipe/config/CameraPipeModule;", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class CameraPipeModule {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\b\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\u000e\u001a\u00020\u0005H\u0007J\u0012\u0010\u000f\u001a\u00020\u00102\b\b\u0001\u0010\u000e\u001a\u00020\u0005H\u0007J\"\u0010\u0011\u001a\u00020\u00122\b\b\u0001\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J\u0012\u0010\u0017\u001a\u00020\u00182\b\b\u0001\u0010\u000e\u001a\u00020\u0005H\u0007J:\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0006\u001a\u00020\u00072\u000e\b\u0001\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\b\b\u0001\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0007H\u0007J\b\u0010$\u001a\u00020%H\u0007J\u0010\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0007J\u0012\u0010*\u001a\u00020+2\b\b\u0001\u0010\u000e\u001a\u00020\u0005H\u0007¨\u0006,"}, d2 = {"Landroidx/camera/camera2/pipe/config/CameraPipeModule$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "provideContext", "Landroid/content/Context;", "config", "Landroidx/camera/camera2/pipe/CameraPipe$Config;", "provideCameraPipeJob", "Lkotlinx/coroutines/Job;", "provideCameraMetadataConfig", "Landroidx/camera/camera2/pipe/CameraPipe$CameraMetadataConfig;", "provideCameraManager", "Landroid/hardware/camera2/CameraManager;", "cameraPipeContext", "provideDevicePolicyManagerWrapper", "Landroidx/camera/camera2/pipe/compat/DevicePolicyManagerWrapper;", "provideCameraContext", "Landroidx/camera/camera2/pipe/CameraContext;", "threads", "Landroidx/camera/camera2/pipe/core/Threads;", "cameraBackends", "Landroidx/camera/camera2/pipe/CameraBackends;", "providePackageManager", "Landroid/content/pm/PackageManager;", "provideCameraBackends", "defaultCameraBackend", "Ljavax/inject/Provider;", "Landroidx/camera/camera2/pipe/CameraBackend;", "cameraPipeLifetime", "Landroidx/camera/camera2/pipe/internal/CameraPipeLifetime;", "configureImageSources", "Landroidx/camera/camera2/pipe/media/ImageSources;", "imageReaderImageSources", "Landroidx/camera/camera2/pipe/media/ImageReaderImageSources;", "cameraPipeConfig", "provideCameraSurfaceManager", "Landroidx/camera/camera2/pipe/CameraSurfaceManager;", "provideStrictMode", "Landroidx/camera/camera2/pipe/StrictMode;", "flags", "Landroidx/camera/camera2/pipe/CameraPipe$Flags;", "provideCameraDeviceSetupCompatFactory", "Landroidx/camera/featurecombinationquery/CameraDeviceSetupCompatFactory;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCameraPipeComponent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraPipeComponent.kt\nandroidx/camera/camera2/pipe/config/CameraPipeModule$Companion\n+ 2 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n*L\n1#1,215:1\n48#2,2:216\n71#2,4:218\n50#2,3:222\n78#2,4:225\n*S KotlinDebug\n*F\n+ 1 CameraPipeComponent.kt\nandroidx/camera/camera2/pipe/config/CameraPipeModule$Companion\n*L\n166#1:216,2\n166#1:218,4\n166#1:222,3\n166#1:225,4\n*E\n"})
    public static final class Companion {
        /* renamed from: $r8$lambda$u5fcAhcmf6Jw690I5-P-Cj5GlYg, reason: not valid java name */
        public static CameraBackend m488$r8$lambda$u5fcAhcmf6Jw690I5PCj5GlYg(CameraBackend r0, CameraContext r1) {
            return r0;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final ImageSources configureImageSources(ImageReaderImageSources r1, CameraPipe.Config r2) {
            if (r2.getImageSources() != null) goto L5;
            return r1;
        L5:
            return r2.getImageSources();
        }

        public final CameraBackends provideCameraBackends(CameraPipe.Config r10, Provider<CameraBackend> r11, Context r12, Threads r13, CameraPipeLifetime r14) {
            CameraBackend r9 = r10.getCameraBackendConfig().getInternalBackend();
            if (r9 != null) goto L11;
            Debug r92 = Debug.INSTANCE;
            Trace.beginSection("Initialize defaultCameraBackend");     // Catch: Throwable -> L7
            r9 = r11.get();     // Catch: Throwable -> L7
            Trace.endSection();
        L7:
            th = move-exception;
            Trace.endSection();
            throw th;
        L11:
            if (r10.getCameraBackendConfig().getCameraBackends().containsKey(CameraBackendId.m151boximpl(r9.mo149getIdQwmhuAM())) == true) goto L21;
            Map r4 = MapsKt.plus(r10.getCameraBackendConfig().getCameraBackends(), TuplesKt.to(CameraBackendId.m151boximpl(r9.mo149getIdQwmhuAM()), new CameraPipeModule$Companion$$ExternalSyntheticLambda0(r9)));
            String r102 = r10.getCameraBackendConfig().m239getDefaultBackendAKmI2lo();
            if (r102 != null) goto L15;
            r102 = r9.mo149getIdQwmhuAM();
        L15:
            String r3 = r102;
            if (r4.containsKey(CameraBackendId.m151boximpl(r3)) == true) goto L18;
            StringBuilder r93 = new StringBuilder("Failed to find ");
            r93.append(CameraBackendId.m156toStringimpl(r3));
            SurfaceGraph$$ExternalSyntheticBUOutline0.m(r93, " in the list of available CameraPipe backends! Available values are ", r4.keySet());
            return null;
        L18:
            return new CameraBackendsImpl(r3, r4, r12, r13, r14, null);
        L21:
            LifecycleRegistry$$ExternalSyntheticBUOutline0.m("CameraBackendConfig#cameraBackends should not contain a backend with ", CameraBackendId.m156toStringimpl(r9.mo149getIdQwmhuAM()), ". Use CameraBackendConfig#internalBackend field instead.");
            return null;
        }

        public final CameraContext provideCameraContext(Context r1, Threads r2, CameraBackends r3) {
            return new CameraPipeModule$Companion$provideCameraContext$1(r1, r2, r3);
        }

        public final CameraDeviceSetupCompatFactory provideCameraDeviceSetupCompatFactory(Context r1) {
            return new CameraDeviceSetupCompatFactory(r1);
        }

        public final CameraManager provideCameraManager(Context r1) {
            return (CameraManager) r1.getSystemService("camera");
        }

        public final CameraPipe.CameraMetadataConfig provideCameraMetadataConfig(CameraPipe.Config r1) {
            return r1.getCameraMetadataConfig();
        }

        public final Job provideCameraPipeJob() {
            return JobKt.Job$default(null, 1, null);
        }

        public final CameraSurfaceManager provideCameraSurfaceManager() {
            return new CameraSurfaceManager();
        }

        public final Context provideContext(CameraPipe.Config r1) {
            return r1.getAppContext();
        }

        public final DevicePolicyManagerWrapper provideDevicePolicyManagerWrapper(Context r1) {
            return new AndroidDevicePolicyManagerWrapper((DevicePolicyManager) r1.getSystemService("device_policy"));
        }

        public final PackageManager providePackageManager(Context r1) {
            return r1.getPackageManager();
        }

        public final StrictMode provideStrictMode(CameraPipe.Flags r1) {
            return new StrictMode(r1.getStrictModeEnabled());
        }
    }

    static {
        Companion = new Companion(null);
    }
}
