package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.Metadata;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u00048\u0006¢\u0006\f\n\u0004\b\n\u0010\u0007\u001a\u0004\b\u000b\u0010\tR\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0007\u001a\u0004\b\u000e\u0010\t¨\u0006\u000f"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CameraPipeKeys;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/Metadata$Key;", _UrlKt.FRAGMENT_ENCODE_SET, "camera2ExtensionMode", "Landroidx/camera/camera2/pipe/Metadata$Key;", "getCamera2ExtensionMode", "()Landroidx/camera/camera2/pipe/Metadata$Key;", "camera2CaptureRequestTag", "getCamera2CaptureRequestTag", _UrlKt.FRAGMENT_ENCODE_SET, "ignore3ARequiredParameters", "getIgnore3ARequiredParameters", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraPipeKeys.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraPipeKeys.kt\nandroidx/camera/camera2/pipe/compat/CameraPipeKeys\n+ 2 Metadata.kt\nandroidx/camera/camera2/pipe/Metadata$Key$Companion\n*L\n1#1,70:1\n47#2:71\n47#2:72\n47#2:73\n*S KotlinDebug\n*F\n+ 1 CameraPipeKeys.kt\nandroidx/camera/camera2/pipe/compat/CameraPipeKeys\n*L\n25#1:71\n29#1:72\n36#1:73\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraPipeKeys {
    public static final CameraPipeKeys INSTANCE = null;
    private static final Metadata.Key<Object> camera2CaptureRequestTag = null;
    private static final Metadata.Key<Integer> camera2ExtensionMode = null;
    private static final Metadata.Key<Boolean> ignore3ARequiredParameters = null;

    static {
        INSTANCE = new CameraPipeKeys();
        Metadata.Key.Companion r0 = Metadata.Key.Companion;
        camera2ExtensionMode = r0.create("androidx.camera.camera2.pipe.extensionMode", Reflection.getOrCreateKotlinClass(Integer.class));
        camera2CaptureRequestTag = r0.create("androidx.camera.camera2.pipe.captureRequestTag", Reflection.getOrCreateKotlinClass(Object.class));
        ignore3ARequiredParameters = r0.create("androidx.camera.camera2.pipe.ignore3ARequiredParameters", Reflection.getOrCreateKotlinClass(Boolean.class));
    }

    private CameraPipeKeys() {
    }

    public final Metadata.Key<Object> getCamera2CaptureRequestTag() {
        return camera2CaptureRequestTag;
    }

    public final Metadata.Key<Integer> getCamera2ExtensionMode() {
        return camera2ExtensionMode;
    }

    public final Metadata.Key<Boolean> getIgnore3ARequiredParameters() {
        return ignore3ARequiredParameters;
    }
}
