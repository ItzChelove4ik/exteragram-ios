package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraDevice;
import android.os.Trace;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.DurationNs;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Timestamps;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0002H\u0000¨\u0006\u0003"}, d2 = {"closeWithTrace", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CameraDevice;", "camera-camera2-pipe"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraDeviceWrapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraDeviceWrapper.kt\nandroidx/camera/camera2/pipe/compat/CameraDeviceWrapperKt\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 3 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n+ 4 Timestamps.kt\nandroidx/camera/camera2/pipe/core/TimestampNs\n+ 5 Timestamps.kt\nandroidx/camera/camera2/pipe/core/Timestamps\n*L\n1#1,714:1\n59#2,2:715\n75#2,2:725\n50#2:735\n51#2:738\n58#3,3:717\n71#3,4:720\n61#3:724\n63#3:727\n78#3,4:728\n64#3:732\n65#3:734\n29#4:733\n74#5,2:736\n*S KotlinDebug\n*F\n+ 1 CameraDeviceWrapper.kt\nandroidx/camera/camera2/pipe/compat/CameraDeviceWrapperKt\n*L\n110#1:715,2\n117#1:725,2\n111#1:735\n111#1:738\n111#1:717,3\n111#1:720,4\n111#1:724\n111#1:727\n111#1:728,4\n111#1:732\n111#1:734\n111#1:733\n111#1:736,2\n*E\n"})
/* loaded from: classes3.dex */
public abstract class CameraDeviceWrapperKt {
    public static final void closeWithTrace(CameraDevice r13) {
        if (r13 != null) goto L5;
        return;
    L5:
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L7;
        android.util.Log.i("CXCP", "Closing Camera " + r13.getId());
    L7:
        Debug r2 = Debug.INSTANCE;
        String r4 = "CXCP#CameraDevice-" + r13.getId() + "#close";
        long r5 = r2.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU();
        Trace.beginSection(r4);     // Catch: Throwable -> L11
        r13.close();     // Catch: Throwable -> L11 NullPointerException -> L13
    L17:
        Unit r132 = Unit.INSTANCE;     // Catch: Throwable -> L11
        Trace.endSection();
        long r52 = DurationNs.m498constructorimpl(r2.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r5);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L30;
        StringBuilder r133 = new StringBuilder();
        r133.append(r4);
        r133.append(" - ");
        Timestamps r1 = Timestamps.INSTANCE;
        r133.append(String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Double.valueOf(r52 / 1000000.0d)}, 1)));
        android.util.Log.d("CXCP", r133.toString());
        return;
    L30:
        return;
    L13:
        e = move-exception;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L17;
        android.util.Log.w("CXCP", "NPE encountered during CameraDevice.close()", e);     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        Trace.endSection();
        long r53 = DurationNs.m498constructorimpl(r2.getSystemTimeSource$camera_camera2_pipe().mo506nowvQl9yQU() - r5);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L24;
        StringBuilder r22 = new StringBuilder();
        r22.append(r4);
        r22.append(" - ");
        Timestamps r12 = Timestamps.INSTANCE;
        r22.append(String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Double.valueOf(r53 / 1000000.0d)}, 1)));
        android.util.Log.d("CXCP", r22.toString());
    L24:
        throw th;
    }
}
