package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CaptureResult;
import android.os.Build;
import android.os.Trace;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.Log;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLazy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Lazy.kt\nandroidx/camera/camera2/pipe/core/LazyKt$lazyOrEmptySet$1\n+ 2 Camera2CameraExtensionMetadata.kt\nandroidx/camera/camera2/pipe/compat/Camera2CameraExtensionMetadata\n+ 3 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n+ 4 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,87:1\n162#2:88\n163#2,6:96\n48#3,2:89\n71#3,4:91\n50#3:95\n52#3:102\n78#3,4:103\n75#4,2:107\n*S KotlinDebug\n*F\n+ 1 Lazy.kt\nandroidx/camera/camera2/pipe/core/LazyKt$lazyOrEmptySet$1\n*L\n53#1:89,2\n53#1:91,4\n53#1:95\n53#1:102\n53#1:103,4\n55#1:107,2\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2CameraExtensionMetadata$special$$inlined$lazyOrEmptySet$2 implements Function0<Set<? extends CaptureResult.Key<Object>>> {
    final /* synthetic */ Camera2CameraExtensionMetadata this$0;

    public Camera2CameraExtensionMetadata$special$$inlined$lazyOrEmptySet$2(Camera2CameraExtensionMetadata r1, Camera2CameraExtensionMetadata r2) {
        this.this$0 = r1;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Set<? extends CaptureResult.Key<Object>> invoke() {
        return invoke2();
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Set<? extends CaptureResult.Key<Object>> invoke2() {
        String r0 = CameraId.m232toStringimpl(this.this$0.m431getCameraDz_R5H8()) + "#availableCaptureResultKeys";
        Debug r1 = Debug.INSTANCE;     // Catch: Throwable -> L14
        Trace.beginSection(r0);     // Catch: Throwable -> L7
        if (Build.VERSION.SDK_INT < 33) goto L9;
        Set<? extends CaptureResult.Key<Object>> r3 = CollectionsKt.toSet(Api33Compat.getAvailableCaptureResultKeys(Camera2CameraExtensionMetadata.access$getExtensionCharacteristics$p(this.this$0), this.this$0.getCameraExtension()));     // Catch: Throwable -> L7
    L10:
        if (r3 != null) goto L12;
        r3 = SetsKt.emptySet();     // Catch: Throwable -> L7
    L12:
        Trace.endSection();     // Catch: Throwable -> L14
        return r3;
    L9:
        r3 = SetsKt.emptySet();     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        Trace.endSection();     // Catch: Throwable -> L14
        throw th;     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L22;
        android.util.Log.w("CXCP", "Failed to get " + r0 + "! Caching {} and ignoring exception.", th);
    L22:
        return SetsKt.emptySet();
    }
}
