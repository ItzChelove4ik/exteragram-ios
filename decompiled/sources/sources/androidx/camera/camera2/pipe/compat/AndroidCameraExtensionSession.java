package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraExtensionSession;
import android.hardware.camera2.CameraExtensionSession$ExtensionCaptureCallback;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Build;
import android.view.Surface;
import androidx.camera.camera2.impl.CameraCallbackMap$$ExternalSyntheticApiModelOutline0;
import androidx.camera.camera2.pipe.CameraError;
import androidx.camera.camera2.pipe.CameraInterop;
import androidx.camera.camera2.pipe.FrameNumber;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.internal.CameraErrorListener;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicLong;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0011\u0018\u00002\u00020\u0001:\u0002@AB'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ!\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J!\u0010\u0013\u001a\u0004\u0018\u00010\u00102\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0013\u0010\u0012J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0017\u0010\u0016J'\u0010\u001a\u001a\u0004\u0018\u00010\u00102\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u00182\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ'\u0010\u001c\u001a\u0004\u0018\u00010\u00102\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u00182\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u001c\u0010\u001bJ\u001d\u0010\u001f\u001a\u00020\u00142\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0018H\u0016¢\u0006\u0004\b\u001f\u0010 J)\u0010%\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\"*\u00020!2\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0016¢\u0006\u0004\b%\u0010&J\u000f\u0010(\u001a\u00020'H\u0016¢\u0006\u0004\b(\u0010)R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010*\u001a\u0004\b+\u0010,R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010-R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010.R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010/R\u001a\u00101\u001a\u0002008\u0016X\u0096\u0004¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R\u0014\u00106\u001a\u0002058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107R \u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u000209088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u0016\u0010?\u001a\u0004\u0018\u00010<8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>¨\u0006B"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidCameraExtensionSession;", "Landroidx/camera/camera2/pipe/compat/CameraExtensionSessionWrapper;", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "device", "Landroid/hardware/camera2/CameraExtensionSession;", "cameraExtensionSession", "Landroidx/camera/camera2/pipe/internal/CameraErrorListener;", "cameraErrorListener", "Ljava/util/concurrent/Executor;", "callbackExecutor", "<init>", "(Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;Landroid/hardware/camera2/CameraExtensionSession;Landroidx/camera/camera2/pipe/internal/CameraErrorListener;Ljava/util/concurrent/Executor;)V", "Landroid/hardware/camera2/CaptureRequest;", PluginsConstants.REQUEST, "Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;", "listener", _UrlKt.FRAGMENT_ENCODE_SET, "capture", "(Landroid/hardware/camera2/CaptureRequest;Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;)Ljava/lang/Integer;", "setRepeatingRequest", _UrlKt.FRAGMENT_ENCODE_SET, "stopRepeating", "()Z", "abortCaptures", _UrlKt.FRAGMENT_ENCODE_SET, "requests", "captureBurst", "(Ljava/util/List;Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;)Ljava/lang/Integer;", "setRepeatingBurst", "Landroidx/camera/camera2/pipe/compat/OutputConfigurationWrapper;", "outputConfigs", "finalizeOutputConfigurations", "(Ljava/util/List;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlin/reflect/KClass;", "type", "unwrapAs", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "close", "()V", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "getDevice", "()Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "Landroid/hardware/camera2/CameraExtensionSession;", "Landroidx/camera/camera2/pipe/internal/CameraErrorListener;", "Ljava/util/concurrent/Executor;", "Landroidx/camera/camera2/pipe/CameraInterop$CameraCaptureSessionId;", "id", "I", "getId-159jkk4", "()I", "Lkotlinx/atomicfu/AtomicLong;", "frameNumbers", "Lkotlinx/atomicfu/AtomicLong;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "extensionSessionMap", "Ljava/util/Map;", "Landroid/view/Surface;", "getInputSurface", "()Landroid/view/Surface;", "inputSurface", "Camera2CaptureSessionCallbackToExtensionCaptureCallback", "Camera2CaptureSessionCallbackToExtensionCaptureCallbackAndroidS", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nExtensionSessionWrapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExtensionSessionWrapper.kt\nandroidx/camera/camera2/pipe/compat/AndroidCameraExtensionSession\n+ 2 Exceptions.kt\nandroidx/camera/camera2/pipe/compat/ExceptionsKt\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,389:1\n53#2,6:390\n59#2,24:398\n83#2,3:424\n53#2,6:427\n59#2,24:435\n83#2,3:461\n53#2,6:464\n59#2,24:472\n83#2,3:498\n71#3,2:396\n50#3,2:422\n71#3,2:433\n50#3,2:459\n71#3,2:470\n50#3,2:496\n71#3,2:503\n1869#4,2:501\n*S KotlinDebug\n*F\n+ 1 ExtensionSessionWrapper.kt\nandroidx/camera/camera2/pipe/compat/AndroidCameraExtensionSession\n*L\n159#1:390,6\n159#1:398,24\n159#1:424,3\n184#1:427,6\n184#1:435,24\n184#1:461,3\n206#1:464,6\n206#1:472,24\n206#1:498,3\n159#1:396,2\n159#1:422,2\n184#1:433,2\n184#1:459,2\n206#1:470,2\n206#1:496,2\n240#1:503,2\n222#1:501,2\n*E\n"})
/* loaded from: classes3.dex */
public class AndroidCameraExtensionSession implements CameraExtensionSessionWrapper {
    private final Executor callbackExecutor;
    private final CameraErrorListener cameraErrorListener;
    private final CameraExtensionSession cameraExtensionSession;
    private final CameraDeviceWrapper device;
    private final Map<CameraExtensionSession, Long> extensionSessionMap;
    private final AtomicLong frameNumbers;
    private final int id;

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0016J\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0013H\u0016J\u0018\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0013H\u0016J \u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\u001c\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidCameraExtensionSession$Camera2CaptureSessionCallbackToExtensionCaptureCallback;", "Landroid/hardware/camera2/CameraExtensionSession$ExtensionCaptureCallback;", "captureCallback", "Landroidx/camera/camera2/pipe/compat/Camera2CaptureCallback;", "<init>", "(Landroidx/camera/camera2/pipe/compat/AndroidCameraExtensionSession;Landroidx/camera/camera2/pipe/compat/Camera2CaptureCallback;)V", "frameQueue", "Ljava/util/concurrent/ConcurrentLinkedQueue;", _UrlKt.FRAGMENT_ENCODE_SET, "onCaptureStarted", _UrlKt.FRAGMENT_ENCODE_SET, "session", "Landroid/hardware/camera2/CameraExtensionSession;", PluginsConstants.REQUEST, "Landroid/hardware/camera2/CaptureRequest;", "timestamp", "onCaptureProcessStarted", "onCaptureProcessProgressed", "progress", _UrlKt.FRAGMENT_ENCODE_SET, "onCaptureFailed", "onCaptureSequenceCompleted", "sequenceId", "onCaptureSequenceAborted", "onCaptureResultAvailable", "result", "Landroid/hardware/camera2/TotalCaptureResult;", "incrementAndGetNextFrameNumber", "dequeueFrameNumber", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public final class Camera2CaptureSessionCallbackToExtensionCaptureCallback extends CameraExtensionSession$ExtensionCaptureCallback {
        private final Camera2CaptureCallback captureCallback;
        private final ConcurrentLinkedQueue<Long> frameQueue;
        final /* synthetic */ AndroidCameraExtensionSession this$0;

        public Camera2CaptureSessionCallbackToExtensionCaptureCallback(AndroidCameraExtensionSession r1, Camera2CaptureCallback r2) {
            this.this$0 = r1;
            this.captureCallback = r2;
            this.frameQueue = new ConcurrentLinkedQueue();
        }

        private final long dequeueFrameNumber(CameraExtensionSession r2) {
            if (this.frameQueue.isEmpty() == false) goto L6;
            incrementAndGetNextFrameNumber(r2);
        L6:
            return this.frameQueue.remove().longValue();
        }

        private final long incrementAndGetNextFrameNumber(CameraExtensionSession r5) {
            long r0 = AndroidCameraExtensionSession.access$getFrameNumbers$p(this.this$0).incrementAndGet();
            AndroidCameraExtensionSession.access$getExtensionSessionMap$p(this.this$0).put(r5, Long.valueOf(r0));
            this.frameQueue.add(Long.valueOf(r0));
            return r0;
        }

        public void onCaptureFailed(CameraExtensionSession r3, CaptureRequest r4) {
            long r0 = dequeueFrameNumber(r3);
            this.captureCallback.mo438onCaptureFailedRuT0dZU(r4, FrameNumber.m268constructorimpl(r0));
        }

        public void onCaptureProcessProgressed(CameraExtensionSession r1, CaptureRequest r2, int r3) {
            this.captureCallback.onCaptureProcessProgressed(r2, r3);
        }

        public void onCaptureProcessStarted(CameraExtensionSession r1, CaptureRequest r2) {
        }

        public void onCaptureResultAvailable(CameraExtensionSession r3, CaptureRequest r4, TotalCaptureResult r5) {
            long r0 = dequeueFrameNumber(r3);
            this.captureCallback.mo437onCaptureCompletedrmrZIYk(r4, r5, FrameNumber.m268constructorimpl(r0));
        }

        public void onCaptureSequenceAborted(CameraExtensionSession r1, int r2) {
            this.captureCallback.onCaptureSequenceAborted(r2);
        }

        public void onCaptureSequenceCompleted(CameraExtensionSession r3, int r4) {
            Long r32 = (Long) AndroidCameraExtensionSession.access$getExtensionSessionMap$p(this.this$0).get(r3);
            this.captureCallback.onCaptureSequenceCompleted(r4, r32.longValue());
        }

        public void onCaptureStarted(CameraExtensionSession r7, CaptureRequest r8, long r9) {
            long r2 = incrementAndGetNextFrameNumber(r7);
            this.captureCallback.onCaptureStarted(r8, r2, r9);
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005¢\u0006\u0004\b\t\u0010\nJ \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\bH\u0016J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H\u0016J\u0018\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H\u0016J \u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0015H\u0016J\u0018\u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0015H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidCameraExtensionSession$Camera2CaptureSessionCallbackToExtensionCaptureCallbackAndroidS;", "Landroid/hardware/camera2/CameraExtensionSession$ExtensionCaptureCallback;", "captureCallback", "Landroidx/camera/camera2/pipe/compat/Camera2CaptureCallback;", "captureRequestMap", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Landroidx/camera/camera2/pipe/compat/AndroidCameraExtensionSession;Landroidx/camera/camera2/pipe/compat/Camera2CaptureCallback;Ljava/util/Map;)V", "onCaptureStarted", _UrlKt.FRAGMENT_ENCODE_SET, "session", "Landroid/hardware/camera2/CameraExtensionSession;", PluginsConstants.REQUEST, "timestamp", "onCaptureProcessStarted", "onCaptureFailed", "onCaptureProcessProgressed", "progress", _UrlKt.FRAGMENT_ENCODE_SET, "onCaptureSequenceCompleted", "sequenceId", "onCaptureSequenceAborted", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nExtensionSessionWrapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExtensionSessionWrapper.kt\nandroidx/camera/camera2/pipe/compat/AndroidCameraExtensionSession$Camera2CaptureSessionCallbackToExtensionCaptureCallbackAndroidS\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,389:1\n384#2,7:390\n59#3,2:397\n*S KotlinDebug\n*F\n+ 1 ExtensionSessionWrapper.kt\nandroidx/camera/camera2/pipe/compat/AndroidCameraExtensionSession$Camera2CaptureSessionCallbackToExtensionCaptureCallbackAndroidS\n*L\n349#1:390,7\n363#1:397,2\n*E\n"})
    public final class Camera2CaptureSessionCallbackToExtensionCaptureCallbackAndroidS extends CameraExtensionSession$ExtensionCaptureCallback {
        private final Camera2CaptureCallback captureCallback;
        private final Map<CaptureRequest, List<Long>> captureRequestMap;
        final /* synthetic */ AndroidCameraExtensionSession this$0;

        public Camera2CaptureSessionCallbackToExtensionCaptureCallbackAndroidS(AndroidCameraExtensionSession r1, Camera2CaptureCallback r2, Map<CaptureRequest, List<Long>> r3) {
            this.this$0 = r1;
            this.captureCallback = r2;
            this.captureRequestMap = r3;
        }

        public static final /* synthetic */ Map access$getCaptureRequestMap$p(Camera2CaptureSessionCallbackToExtensionCaptureCallbackAndroidS r0) {
            return r0.captureRequestMap;
        }

        public void onCaptureFailed(CameraExtensionSession r3, CaptureRequest r4) {
            if (this.captureRequestMap.get(r4).size() != 1) goto L7;
            this.captureCallback.mo438onCaptureFailedRuT0dZU(r4, FrameNumber.m268constructorimpl(this.captureRequestMap.get(r4).get(0).longValue()));
            return;
        L7:
            if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L10;
            android.util.Log.i("CXCP", "onCaptureFailed is not triggered for repeating requests. Request frame numbers: " + ((List) access$getCaptureRequestMap$p(this).get(r4)).stream());
            return;
        }

        public void onCaptureProcessProgressed(CameraExtensionSession r1, CaptureRequest r2, int r3) {
            this.captureCallback.onCaptureProcessProgressed(r2, r3);
        }

        public void onCaptureProcessStarted(CameraExtensionSession r1, CaptureRequest r2) {
        }

        public void onCaptureSequenceAborted(CameraExtensionSession r1, int r2) {
            this.captureCallback.onCaptureSequenceAborted(r2);
        }

        public void onCaptureSequenceCompleted(CameraExtensionSession r3, int r4) {
            Long r32 = (Long) AndroidCameraExtensionSession.access$getExtensionSessionMap$p(this.this$0).get(r3);
            this.captureCallback.onCaptureSequenceCompleted(r4, r32.longValue());
        }

        public void onCaptureStarted(CameraExtensionSession r8, CaptureRequest r9, long r10) {
            long r3 = AndroidCameraExtensionSession.access$getFrameNumbers$p(this.this$0).incrementAndGet();
            AndroidCameraExtensionSession.access$getExtensionSessionMap$p(this.this$0).put(r8, Long.valueOf(r3));
            Map<CaptureRequest, List<Long>> r82 = this.captureRequestMap;
            List<Long> r0 = r82.get(r9);
            if (r0 != null) goto L5;
            r0 = new ArrayList();
            r82.put(r9, r0);
        L5:
            r0.add(Long.valueOf(r3));
            this.captureCallback.onCaptureStarted(r9, r3, r10);
        }
    }

    public AndroidCameraExtensionSession(CameraDeviceWrapper r1, CameraExtensionSession r2, CameraErrorListener r3, Executor r4) {
        this.device = r1;
        this.cameraExtensionSession = r2;
        this.cameraErrorListener = r3;
        this.callbackExecutor = r4;
        this.id = CameraInterop.m234nextCameraCaptureSessionId159jkk4$camera_camera2_pipe();
        this.frameNumbers = AtomicFU.atomic(0);
        this.extensionSessionMap = new HashMap();
    }

    public static final /* synthetic */ Executor access$getCallbackExecutor$p(AndroidCameraExtensionSession r0) {
        return r0.callbackExecutor;
    }

    public static final /* synthetic */ CameraExtensionSession access$getCameraExtensionSession$p(AndroidCameraExtensionSession r0) {
        return r0.cameraExtensionSession;
    }

    public static final /* synthetic */ Map access$getExtensionSessionMap$p(AndroidCameraExtensionSession r0) {
        return r0.extensionSessionMap;
    }

    public static final /* synthetic */ AtomicLong access$getFrameNumbers$p(AndroidCameraExtensionSession r0) {
        return r0.frameNumbers;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper
    public boolean abortCaptures() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper
    public Integer capture(CaptureRequest r7, CameraCaptureSession.CaptureCallback r8) {
        String r0 = getDevice().mo412getCameraIdDz_R5H8();
        CameraErrorListener r1 = this.cameraErrorListener;
    L6:
        e = move-exception;
        if ((e instanceof CameraAccessException) == false) goto L18;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L16;
        android.util.Log.w("CXCP", "Failed to execute call: Camera encountered an error: " + e.getMessage());
    L16:
        r1.mo447onCameraError3M5Xam4(r0, CameraError.Companion.m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) e), true);
        return null;
    L18:
        if ((e instanceof IllegalArgumentException) == true) goto L33;
        if ((e instanceof SecurityException) == true) goto L33;
        if ((e instanceof UnsupportedOperationException) == true) goto L33;
        if ((e instanceof NullPointerException) == true) goto L33;
        if ((e instanceof IllegalStateException) == true) goto L29;
        throw e;
    L29:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L41;
        android.util.Log.d("CXCP", "Failed to execute call: Camera may be closed");
        return null;
    L41:
        return null;
    L33:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L35;
        android.util.Log.w("CXCP", "Failed to execute call: Unexpected exception: " + e.getMessage());
    L35:
        r1.mo447onCameraError3M5Xam4(r0, CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false);
        return null;
    L4:
        if (Build.VERSION.SDK_INT < 33) goto L8;
        int r6 = access$getCameraExtensionSession$p(this).capture(r7, access$getCallbackExecutor$p(this), new Camera2CaptureSessionCallbackToExtensionCaptureCallback(this, (Camera2CaptureCallback) r8));     // Catch: Exception -> L6
    L9:
        return Integer.valueOf(r6);
    L8:
        r6 = access$getCameraExtensionSession$p(this).capture(r7, access$getCallbackExecutor$p(this), new Camera2CaptureSessionCallbackToExtensionCaptureCallbackAndroidS(this, (Camera2CaptureCallback) r8, new LinkedHashMap()));     // Catch: Exception -> L6
        goto L9
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper
    public Integer captureBurst(List<CaptureRequest> r2, CameraCaptureSession.CaptureCallback r3) {
        Iterator<T> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        capture((CaptureRequest) r22.next(), r3);
        goto L4
    L6:
        return null;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.cameraExtensionSession.close();
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper
    public boolean finalizeOutputConfigurations(List<? extends OutputConfigurationWrapper> r1) {
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L7;
        android.util.Log.w("CXCP", "CameraExtensionSession does not support finalizeOutputConfigurations()");
        return false;
    L7:
        return false;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper
    public CameraDeviceWrapper getDevice() {
        return this.device;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper
    /* renamed from: getId-159jkk4 */
    public int mo410getId159jkk4() {
        return this.id;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper
    public Surface getInputSurface() {
        return null;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper
    public Integer setRepeatingBurst(List<CaptureRequest> r3, CameraCaptureSession.CaptureCallback r4) {
        if (r3.size() == 1) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("CameraExtensionSession does not support setRepeatingBurst for more than oneCaptureRequest");
        return null;
    L5:
        return setRepeatingRequest((CaptureRequest) CollectionsKt.single(r3), r4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper
    public Integer setRepeatingRequest(CaptureRequest r7, CameraCaptureSession.CaptureCallback r8) {
        String r0 = getDevice().mo412getCameraIdDz_R5H8();
        CameraErrorListener r1 = this.cameraErrorListener;
    L6:
        e = move-exception;
        if ((e instanceof CameraAccessException) == false) goto L18;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L16;
        android.util.Log.w("CXCP", "Failed to execute call: Camera encountered an error: " + e.getMessage());
    L16:
        r1.mo447onCameraError3M5Xam4(r0, CameraError.Companion.m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) e), true);
        return null;
    L18:
        if ((e instanceof IllegalArgumentException) == true) goto L33;
        if ((e instanceof SecurityException) == true) goto L33;
        if ((e instanceof UnsupportedOperationException) == true) goto L33;
        if ((e instanceof NullPointerException) == true) goto L33;
        if ((e instanceof IllegalStateException) == true) goto L29;
        throw e;
    L29:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L41;
        android.util.Log.d("CXCP", "Failed to execute call: Camera may be closed");
        return null;
    L41:
        return null;
    L33:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L35;
        android.util.Log.w("CXCP", "Failed to execute call: Unexpected exception: " + e.getMessage());
    L35:
        r1.mo447onCameraError3M5Xam4(r0, CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false);
        return null;
    L4:
        if (Build.VERSION.SDK_INT < 33) goto L8;
        int r6 = access$getCameraExtensionSession$p(this).setRepeatingRequest(r7, access$getCallbackExecutor$p(this), new Camera2CaptureSessionCallbackToExtensionCaptureCallback(this, (Camera2CaptureCallback) r8));     // Catch: Exception -> L6
    L9:
        return Integer.valueOf(r6);
    L8:
        r6 = access$getCameraExtensionSession$p(this).setRepeatingRequest(r7, access$getCallbackExecutor$p(this), new Camera2CaptureSessionCallbackToExtensionCaptureCallbackAndroidS(this, (Camera2CaptureCallback) r8, new LinkedHashMap()));     // Catch: Exception -> L6
        goto L9
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper
    public boolean stopRepeating() {
        String r0 = getDevice().mo412getCameraIdDz_R5H8();
        CameraErrorListener r1 = this.cameraErrorListener;
        access$getCameraExtensionSession$p(this).stopRepeating();     // Catch: Exception -> L5
        Unit r8 = Unit.INSTANCE;     // Catch: Exception -> L5
    L32:
        if (r8 != null) goto L34;
        return false;
    L34:
        return true;
    L5:
        e = move-exception;
        if ((e instanceof CameraAccessException) == false) goto L14;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L11;
        android.util.Log.w("CXCP", "Failed to execute call: Camera encountered an error: " + e.getMessage());
    L11:
        r1.mo447onCameraError3M5Xam4(r0, CameraError.Companion.m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) e), true);
    L12:
        r8 = null;
        goto L32
    L14:
        if ((e instanceof IllegalArgumentException) == true) goto L29;
        if ((e instanceof SecurityException) == true) goto L29;
        if ((e instanceof UnsupportedOperationException) == true) goto L29;
        if ((e instanceof NullPointerException) == true) goto L29;
        if ((e instanceof IllegalStateException) == true) goto L25;
        throw e;
    L25:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L12;
        android.util.Log.d("CXCP", "Failed to execute call: Camera may be closed");
    L29:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L31;
        android.util.Log.w("CXCP", "Failed to execute call: Unexpected exception: " + e.getMessage());
    L31:
        r1.mo447onCameraError3M5Xam4(r0, CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false);
        goto L12
    }

    @Override // androidx.camera.camera2.pipe.UnsafeWrapper
    public <T> T unwrapAs(KClass<T> r2) {
        if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(CameraCallbackMap$$ExternalSyntheticApiModelOutline0.m())) == true) goto L5;
        return null;
    L5:
        return (T) this.cameraExtensionSession;
    }
}
