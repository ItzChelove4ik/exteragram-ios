package androidx.camera.camera2.config;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Log;
import androidx.camera.camera2.adapter.EncoderProfilesProviderAdapter;
import androidx.camera.camera2.adapter.ZslControl;
import androidx.camera.camera2.adapter.ZslControlImpl;
import androidx.camera.camera2.compat.Camera2CameraControlCompat;
import androidx.camera.camera2.compat.quirk.CameraQuirks;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.impl.CameraProperties;
import androidx.camera.camera2.impl.ComboRequestListener;
import androidx.camera.camera2.impl.UseCaseThreads;
import androidx.camera.camera2.interop.Camera2CameraControl;
import androidx.camera.camera2.pipe.CameraDevices;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraThreadConfig;
import androidx.camera.core.impl.EncoderProfilesProvider;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b'\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/config/CameraModule;", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class CameraModule {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\nJ)\u0010\u0011\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\b2\b\b\u0001\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0011\u0010\u0012J!\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u001a\u0010\u001bJ\u001b\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0016H\u0007¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010#\u001a\u00020\"2\u0006\u0010!\u001a\u00020 H\u0007¢\u0006\u0004\b#\u0010$J!\u0010)\u001a\u00020(2\b\b\u0001\u0010%\u001a\u00020\u00192\u0006\u0010'\u001a\u00020&H\u0007¢\u0006\u0004\b)\u0010*¨\u0006+"}, d2 = {"Landroidx/camera/camera2/config/CameraModule$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/config/CameraConfig;", "cameraConfig", "Landroidx/camera/core/impl/CameraThreadConfig;", "cameraThreadConfig", "Landroidx/camera/camera2/impl/UseCaseThreads;", "provideUseCaseThreads", "(Landroidx/camera/camera2/config/CameraConfig;Landroidx/camera/core/impl/CameraThreadConfig;)Landroidx/camera/camera2/impl/UseCaseThreads;", "Landroidx/camera/camera2/compat/Camera2CameraControlCompat;", "compat", "threads", "Landroidx/camera/camera2/impl/ComboRequestListener;", "requestListener", "Landroidx/camera/camera2/interop/Camera2CameraControl;", "provideCamera2CameraControl", "(Landroidx/camera/camera2/compat/Camera2CameraControlCompat;Landroidx/camera/camera2/impl/UseCaseThreads;Landroidx/camera/camera2/impl/ComboRequestListener;)Landroidx/camera/camera2/interop/Camera2CameraControl;", "Landroidx/camera/camera2/pipe/CameraPipe;", "cameraPipe", "config", "Landroidx/camera/camera2/pipe/CameraMetadata;", "provideCameraMetadata", "(Landroidx/camera/camera2/pipe/CameraPipe;Landroidx/camera/camera2/config/CameraConfig;)Landroidx/camera/camera2/pipe/CameraMetadata;", _UrlKt.FRAGMENT_ENCODE_SET, "provideCameraIdString", "(Landroidx/camera/camera2/config/CameraConfig;)Ljava/lang/String;", "cameraMetadata", "Landroid/hardware/camera2/params/StreamConfigurationMap;", "provideStreamConfigurationMap", "(Landroidx/camera/camera2/pipe/CameraMetadata;)Landroid/hardware/camera2/params/StreamConfigurationMap;", "Landroidx/camera/camera2/impl/CameraProperties;", "cameraProperties", "Landroidx/camera/camera2/adapter/ZslControl;", "provideZslControl", "(Landroidx/camera/camera2/impl/CameraProperties;)Landroidx/camera/camera2/adapter/ZslControl;", "cameraIdString", "Landroidx/camera/camera2/compat/quirk/CameraQuirks;", "cameraQuirks", "Landroidx/camera/core/impl/EncoderProfilesProvider;", "provideEncoderProfilesProvider", "(Ljava/lang/String;Landroidx/camera/camera2/compat/quirk/CameraQuirks;)Landroidx/camera/core/impl/EncoderProfilesProvider;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCameraConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraConfig.kt\nandroidx/camera/camera2/config/CameraModule$Companion\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,218:1\n136#2,4:219\n*S KotlinDebug\n*F\n+ 1 CameraConfig.kt\nandroidx/camera/camera2/config/CameraModule$Companion\n*L\n139#1:219,4\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final Camera2CameraControl provideCamera2CameraControl(Camera2CameraControlCompat r1, UseCaseThreads r2, ComboRequestListener r3) {
            return Camera2CameraControl.Companion.create(r1, r2, r3);
        }

        public final String provideCameraIdString(CameraConfig r1) {
            return r1.m37getCameraIdDz_R5H8();
        }

        public final CameraMetadata provideCameraMetadata(CameraPipe r2, CameraConfig r3) {
            return CameraDevices.m168awaitCameraMetadataFpsL5FU$default(r2.cameras(), r3.m37getCameraIdDz_R5H8(), null, 2, null);
        L5:
            Camera2Logger r22 = Camera2Logger.INSTANCE;
            if (Logger.isErrorEnabled("CXCP") == false) goto L8;
            Log.e(Camera2Logger.access$getTRUNCATED_TAG$p(), "Failed to inject camera metadata: Do Not Disturb mode is on.");
        L8:
            return null;
        }

        public final EncoderProfilesProvider provideEncoderProfilesProvider(String r1, CameraQuirks r2) {
            return new EncoderProfilesProviderAdapter(r1, r2.getQuirks());
        }

        public final StreamConfigurationMap provideStreamConfigurationMap(CameraMetadata r1) {
            if (r1 != null) goto L4;
            return null;
        L4:
            return (StreamConfigurationMap) r1.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        }

        public final UseCaseThreads provideUseCaseThreads(CameraConfig r5, CameraThreadConfig r6) {
            Executor r4 = r6.getCameraExecutor();
            CoroutineDispatcher r62 = ExecutorsKt.from(r6.getCameraExecutor());
            return new UseCaseThreads(CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default(null, 1, null).plus(r62).plus(new CoroutineName("CXCP-UseCase-" + r5.m37getCameraIdDz_R5H8()))), r4, r62);
        }

        public final ZslControl provideZslControl(CameraProperties r1) {
            return new ZslControlImpl(r1);
        }
    }

    static {
        Companion = new Companion(null);
    }
}
