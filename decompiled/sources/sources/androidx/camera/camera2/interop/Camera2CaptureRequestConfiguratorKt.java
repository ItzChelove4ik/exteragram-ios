package androidx.camera.camera2.interop;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.impl.Config;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u0007¢\u0006\u0004\b\u0002\u0010\u0003\"\"\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/camera/core/CameraXConfig;", "Landroidx/camera/camera2/interop/Camera2CaptureRequestConfigurator;", "getCamera2CaptureRequestConfigurator", "(Landroidx/camera/core/CameraXConfig;)Landroidx/camera/camera2/interop/Camera2CaptureRequestConfigurator;", "Landroidx/camera/core/impl/Config$Option;", "OPTION_CAPTURE_REQUEST_CONFIGURATOR", "Landroidx/camera/core/impl/Config$Option;", "getOPTION_CAPTURE_REQUEST_CONFIGURATOR", "()Landroidx/camera/core/impl/Config$Option;", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCamera2CaptureRequestConfigurator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2CaptureRequestConfigurator.kt\nandroidx/camera/camera2/interop/Camera2CaptureRequestConfiguratorKt\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,72:1\n490#2,7:73\n*S KotlinDebug\n*F\n+ 1 Camera2CaptureRequestConfigurator.kt\nandroidx/camera/camera2/interop/Camera2CaptureRequestConfiguratorKt\n*L\n69#1:73,7\n*E\n"})
/* loaded from: classes3.dex */
public abstract class Camera2CaptureRequestConfiguratorKt {
    private static final Config.Option<Camera2CaptureRequestConfigurator> OPTION_CAPTURE_REQUEST_CONFIGURATOR = null;

    static {
        OPTION_CAPTURE_REQUEST_CONFIGURATOR = Config.Option.create("camerax.core.appConfig.captureRequestConfigurator", Camera2CaptureRequestConfigurator.class);
    }

    public static final Camera2CaptureRequestConfigurator getCamera2CaptureRequestConfigurator(CameraXConfig r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2.getConfig().retrieveOption(OPTION_CAPTURE_REQUEST_CONFIGURATOR, null));
        return null;
    }
}
