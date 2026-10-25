package androidx.camera.camera2.pipe.compat;

import android.os.Build;
import android.os.Trace;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.Log;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLazy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Lazy.kt\nandroidx/camera/camera2/pipe/core/LazyKt$lazyOrFalse$1\n+ 2 Camera2CameraExtensionMetadata.kt\nandroidx/camera/camera2/pipe/compat/Camera2CameraExtensionMetadata\n+ 3 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n+ 4 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,87:1\n181#2:88\n182#2,8:96\n48#3,2:89\n71#3,4:91\n50#3:95\n52#3:104\n78#3,4:105\n75#4,2:109\n*S KotlinDebug\n*F\n+ 1 Lazy.kt\nandroidx/camera/camera2/pipe/core/LazyKt$lazyOrFalse$1\n*L\n30#1:89,2\n30#1:91,4\n30#1:95\n30#1:104\n30#1:105,4\n32#1:109,2\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2CameraExtensionMetadata$special$$inlined$lazyOrFalse$2 implements Function0<Boolean> {
    final /* synthetic */ Camera2CameraExtensionMetadata this$0;

    public Camera2CameraExtensionMetadata$special$$inlined$lazyOrFalse$2(Camera2CameraExtensionMetadata r1, Camera2CameraExtensionMetadata r2) {
        this.this$0 = r1;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Boolean invoke() {
        String r0 = CameraId.m232toStringimpl(this.this$0.m431getCameraDz_R5H8()) + "#isCaptureProgressSupported";
        boolean r1 = false;
        Debug r2 = Debug.INSTANCE;     // Catch: Throwable -> L12
        Trace.beginSection(r0);     // Catch: Throwable -> L8
        if (Build.VERSION.SDK_INT < 34) goto L10;
        boolean r4 = Api34Compat.isCaptureProcessProgressAvailable(Camera2CameraExtensionMetadata.access$getExtensionCharacteristics$p(this.this$0), this.this$0.getCameraExtension());     // Catch: Throwable -> L8
    L11:
        Trace.endSection();     // Catch: Throwable -> L12
        r1 = r4;
    L20:
        return Boolean.valueOf(r1);
    L10:
        r4 = false;
    L8:
        th = move-exception;
        Trace.endSection();     // Catch: Throwable -> L12
        throw th;     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L20;
        android.util.Log.w("CXCP", "Failed to get " + r0 + "! Caching false and ignoring exception.", th);
        goto L20
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Boolean invoke() {
        return invoke();
    }
}
