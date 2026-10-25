package androidx.camera.core;

import androidx.tracing.Trace;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/camera/core/CameraXTracer;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "label", "Ljava/lang/Runnable;", "block", _UrlKt.FRAGMENT_ENCODE_SET, "trace", "(Ljava/lang/String;Ljava/lang/Runnable;)V", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraXTracer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraXTracer.kt\nandroidx/camera/core/CameraXTracer\n+ 2 Trace.android.kt\nandroidx/tracing/TraceKt\n*L\n1#1,48:1\n46#1:49\n317#2,5:50\n317#2,5:55\n*S KotlinDebug\n*F\n+ 1 CameraXTracer.kt\nandroidx/camera/core/CameraXTracer\n*L\n35#1:49\n35#1:50,5\n46#1:55,5\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraXTracer {
    public static final CameraXTracer INSTANCE = null;

    static {
        INSTANCE = new CameraXTracer();
    }

    private CameraXTracer() {
    }

    @JvmStatic
    public static final void trace(String r2, Runnable r3) {
        Trace.beginSection("CX:" + r2);
        r3.run();     // Catch: Throwable -> L6
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L6
        Trace.endSection();
        return;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }
}
