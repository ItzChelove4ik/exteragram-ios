package androidx.camera.camera2;

import android.content.Context;
import androidx.camera.camera2.adapter.CameraFactoryProvider;
import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.impl.CameraThreadConfig;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00022\u00020\u0001:\u0002\u0003\u0002¨\u0006\u0004"}, d2 = {"Landroidx/camera/camera2/Camera2Config;", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "DefaultProvider", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class Camera2Config {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007J,\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007¨\u0006\r"}, d2 = {"Landroidx/camera/camera2/Camera2Config$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "defaultConfig", "Landroidx/camera/core/CameraXConfig;", "from", "sharedCameraPipe", "Landroidx/camera/camera2/pipe/CameraPipe;", "sharedAppContext", "Landroid/content/Context;", "sharedThreadConfig", "Landroidx/camera/core/impl/CameraThreadConfig;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static /* synthetic */ CameraXConfig from$default(Companion r1, CameraPipe r2, Context r3, CameraThreadConfig r4, int r5, Object r6) {
            if ((r5 & 1) == 0) goto L6;
            r2 = null;
        L6:
            if ((r5 & 2) == 0) goto L9;
            r3 = null;
        L9:
            if ((r5 & 4) == 0) goto L12;
            r4 = null;
        L12:
            return r1.from(r2, r3, r4);
        }

        @JvmStatic
        public final CameraXConfig defaultConfig() {
            return from$default(this, null, null, null, 7, null);
        }

        @JvmStatic
        public final CameraXConfig from(CameraPipe r1, Context r2, CameraThreadConfig r3) {
            CameraFactoryProvider r0 = new CameraFactoryProvider(r1, r2, r3);
            return new CameraXConfig.Builder().setCameraFactoryProvider(r0).setDeviceSurfaceManagerProvider(new Camera2Config$Companion$$ExternalSyntheticLambda0()).setUseCaseConfigFactoryProvider(new Camera2Config$Companion$$ExternalSyntheticLambda1()).setRepeatingStreamForced(true).build();
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Landroidx/camera/camera2/Camera2Config$DefaultProvider;", "Landroidx/camera/core/CameraXConfig$Provider;", "<init>", "()V", "getCameraXConfig", "Landroidx/camera/core/CameraXConfig;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultProvider implements CameraXConfig.Provider {
        public DefaultProvider() {
        }

        @Override // androidx.camera.core.CameraXConfig.Provider
        public CameraXConfig getCameraXConfig() {
            return Camera2Config.Companion.defaultConfig();
        }
    }

    static {
        Companion = new Companion(null);
    }
}
