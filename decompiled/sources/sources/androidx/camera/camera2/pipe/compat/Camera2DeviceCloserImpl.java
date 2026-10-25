package androidx.camera.camera2.pipe.compat;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraDevice;
import android.os.Build;
import android.os.Trace;
import android.view.Surface;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Threads;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\b\u0001\u0018\u0000 $2\u00020\u0001:\u0001$B!\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJE\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00132\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0017\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0019\u0010\u001aJC\u0010\u001d\u001a\u00020\u00162\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010!R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\"R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010#¨\u0006%"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2DeviceCloserImpl;", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceCloser;", "Landroidx/camera/camera2/pipe/core/Threads;", "threads", "Landroidx/camera/camera2/pipe/compat/Camera2Quirks;", "camera2Quirks", "Landroidx/camera/camera2/pipe/compat/RetryingCameraStateOpener;", "retryingCameraStateOpener", "<init>", "(Landroidx/camera/camera2/pipe/core/Threads;Landroidx/camera/camera2/pipe/compat/Camera2Quirks;Landroidx/camera/camera2/pipe/compat/RetryingCameraStateOpener;)V", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "cameraDeviceWrapper", "Landroid/hardware/camera2/CameraDevice;", "cameraDevice", "Landroidx/camera/camera2/pipe/compat/AndroidCameraState;", "androidCameraState", _UrlKt.FRAGMENT_ENCODE_SET, "shouldReopenCamera", "shouldCreateEmptyCaptureSession", "Lkotlin/Pair;", "handleQuirksBeforeClosing", "(Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;Landroid/hardware/camera2/CameraDevice;Landroidx/camera/camera2/pipe/compat/AndroidCameraState;ZZ)Lkotlin/Pair;", _UrlKt.FRAGMENT_ENCODE_SET, "closeCameraDevice", "(Landroid/hardware/camera2/CameraDevice;Landroidx/camera/camera2/pipe/compat/AndroidCameraState;)V", "createCaptureSession", "(Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;)V", "Landroidx/camera/camera2/pipe/compat/AudioRestrictionController;", "audioRestrictionController", "closeCamera", "(Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;Landroid/hardware/camera2/CameraDevice;Landroidx/camera/camera2/pipe/compat/AndroidCameraState;Landroidx/camera/camera2/pipe/compat/AudioRestrictionController;ZZ)V", "Landroidx/camera/camera2/pipe/core/Threads;", "getThreads", "()Landroidx/camera/camera2/pipe/core/Threads;", "Landroidx/camera/camera2/pipe/compat/Camera2Quirks;", "Landroidx/camera/camera2/pipe/compat/RetryingCameraStateOpener;", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCamera2DeviceCloser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2DeviceCloser.kt\nandroidx/camera/camera2/pipe/compat/Camera2DeviceCloserImpl\n+ 2 CameraDevices.kt\nandroidx/camera/camera2/pipe/CameraId$Companion\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n*L\n1#1,263:1\n172#2:264\n172#2:306\n82#3,2:265\n50#3,2:268\n50#3,2:277\n82#3,2:284\n50#3,2:293\n50#3,2:295\n50#3,2:302\n82#3,2:304\n50#3,2:307\n50#3,2:309\n71#3,2:311\n82#3,2:313\n1#4:267\n48#5,2:270\n71#5,4:272\n50#5:276\n52#5:279\n78#5,4:280\n48#5,2:286\n71#5,4:288\n50#5:292\n52#5:297\n78#5,4:298\n*S KotlinDebug\n*F\n+ 1 Camera2DeviceCloser.kt\nandroidx/camera/camera2/pipe/compat/Camera2DeviceCloserImpl\n*L\n63#1:264\n189#1:306\n89#1:265,2\n143#1:268,2\n148#1:277,2\n156#1:284,2\n162#1:293,2\n164#1:295,2\n176#1:302,2\n183#1:304,2\n194#1:307,2\n196#1:309,2\n198#1:311,2\n248#1:313,2\n147#1:270,2\n147#1:272,4\n147#1:276\n147#1:279\n147#1:280,4\n161#1:286,2\n161#1:288,4\n161#1:292\n161#1:297\n161#1:298,4\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2DeviceCloserImpl implements Camera2DeviceCloser {
    public static final Companion Companion = null;
    private final Camera2Quirks camera2Quirks;
    private final RetryingCameraStateOpener retryingCameraStateOpener;
    private final Threads threads;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2DeviceCloserImpl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "CAMERA_CLOSE_TIMEOUT_MS", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2DeviceCloserImpl$closeCameraDevice$2", f = "Camera2DeviceCloser.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.Camera2DeviceCloserImpl$closeCameraDevice$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ CameraDevice $cameraDevice;
        final /* synthetic */ Ref.BooleanRef $cameraDeviceClosed;
        int label;

        public AnonymousClass2(CameraDevice r1, Ref.BooleanRef r2, Continuation<? super AnonymousClass2> r3) {
            this.$cameraDevice = r1;
            this.$cameraDeviceClosed = r2;
            super(1, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> r3) {
            return new AnonymousClass2(this.$cameraDevice, this.$cameraDeviceClosed, r3);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Unit> r1) {
            return invoke2(r1);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Continuation<? super Unit> r1) {
            return ((AnonymousClass2) create(r1)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L6;
            ResultKt.throwOnFailure(r2);
            CameraDeviceWrapperKt.closeWithTrace(this.$cameraDevice);
            this.$cameraDeviceClosed.element = true;
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public Camera2DeviceCloserImpl(Threads r1, Camera2Quirks r2, RetryingCameraStateOpener r3) {
        this.threads = r1;
        this.camera2Quirks = r2;
        this.retryingCameraStateOpener = r3;
    }

    public static final /* synthetic */ void access$closeCameraDevice(Camera2DeviceCloserImpl r0, CameraDevice r1, AndroidCameraState r2) {
        r0.closeCameraDevice(r1, r2);
    }

    public static final /* synthetic */ void access$createCaptureSession(Camera2DeviceCloserImpl r0, CameraDeviceWrapper r1) {
        r0.createCaptureSession(r1);
    }

    public static final /* synthetic */ RetryingCameraStateOpener access$getRetryingCameraStateOpener$p(Camera2DeviceCloserImpl r0) {
        return r0.retryingCameraStateOpener;
    }

    private final void closeCameraDevice(CameraDevice r9, AndroidCameraState r10) {
        String r0 = r9.getId();
        Log r1 = Log.INSTANCE;
        if (r1.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", "closeCameraDevice(" + r0 + ')');
    L5:
        Ref.BooleanRef r2 = new Ref.BooleanRef();
        if (((Unit) this.threads.runBlockingCheckedOrNull(7000, new AnonymousClass2(r9, r2, null))) == null) goto L8;
    L10:
        CameraId.Companion r02 = CameraId.Companion;
        String r92 = CameraId.m228constructorimpl(r9.getId());
        if (this.camera2Quirks.m461shouldWaitForCameraDeviceOnClosedEfqyGwQ$camera_camera2_pipe(r92) == true) goto L13;
        return;
    L13:
        if (r2.element == true) goto L15;
        return;
    L15:
        if (r1.getDEBUG_LOGGABLE() == false) goto L18;
        android.util.Log.d("CXCP", "Waiting for OnClosed from " + CameraId.m232toStringimpl(r92));
    L18:
        if (r10.awaitCameraDeviceClosed$camera_camera2_pipe(2000) == false) goto L24;
        if (r1.getDEBUG_LOGGABLE() == false) goto L29;
        android.util.Log.d("CXCP", "Received OnClosed for " + CameraId.m232toStringimpl(r92));
        return;
    L29:
        return;
    L24:
        if (r1.getWARN_LOGGABLE() == false) goto L30;
        android.util.Log.w("CXCP", "Failed to close " + CameraId.m232toStringimpl(r92) + " after 2000ms!");
        return;
    L30:
        return;
    L8:
        if (r1.getERROR_LOGGABLE() == false) goto L10;
        android.util.Log.e("CXCP", "Failed to close CameraDevice(" + r0 + ") after 7000ms. The camera is likely in a bad state.");
        goto L10
    }

    private final void createCaptureSession(CameraDeviceWrapper r8) {
        SurfaceTexture r7 = new SurfaceTexture(0);
        r7.setDefaultBufferSize(640, 480);
        Surface r1 = new Surface(r7);
        AtomicBoolean r2 = AtomicFU.atomic(false);
        CountDownLatch r3 = new CountDownLatch(1);
        Camera2DeviceCloserImpl$createCaptureSession$callback$1 r5 = new Camera2DeviceCloserImpl$createCaptureSession$callback$1(r3, r2, r1, r7);
        if (r8.createCaptureSession(CollectionsKt.listOf(r1), r5) == false) goto L7;
        r3.await();
        return;
    L7:
        if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L10;
        android.util.Log.e("CXCP", "Failed to create a blank capture session! Surfaces may not be disconnected properly.");
    L10:
        if (r2.compareAndSet(false, true) == false) goto L13;
        r1.release();
        r7.release();
        return;
    }

    private final Pair<CameraDeviceWrapper, AndroidCameraState> handleQuirksBeforeClosing(CameraDeviceWrapper r6, CameraDevice r7, AndroidCameraState r8, boolean r9, boolean r10) {
        Log r1 = Log.INSTANCE;
        if (r1.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", "handleQuirksBeforeClosing(" + r7 + ')');
    L5:
        String r2 = r6.mo412getCameraIdDz_R5H8();
        if (r9 == false) goto L16;
        Debug r62 = Debug.INSTANCE;
        Trace.beginSection("Camera2DeviceCloserImpl#reopenCameraDevice");     // Catch: Throwable -> L13
        if (r1.getDEBUG_LOGGABLE() == false) goto L11;
        android.util.Log.d("CXCP", "Reopening camera device");     // Catch: Throwable -> L13
    L11:
        access$closeCameraDevice(this, r7, r8);     // Catch: Throwable -> L13
        AwaitOpenCameraResult r63 = access$getRetryingCameraStateOpener$p(this).mo477openAndAwaitCameraWithRetry0r8Bogc(r2, this);     // Catch: Throwable -> L13
        Trace.endSection();
    L18:
        if (r63.getCameraDeviceWrapper() == null) goto L38;
        if (r63.getAndroidCameraState() == null) goto L38;
        if (r10 == false) goto L36;
        Debug r72 = Debug.INSTANCE;
        Trace.beginSection("Camera2DeviceCloserImpl#createCaptureSession");     // Catch: Throwable -> L32
        if (r1.getDEBUG_LOGGABLE() == false) goto L27;
        android.util.Log.d("CXCP", "Creating an empty capture session before closing " + CameraId.m232toStringimpl(r2));     // Catch: Throwable -> L32
    L27:
        access$createCaptureSession(this, r63.getCameraDeviceWrapper());     // Catch: Throwable -> L32
        if (r1.getDEBUG_LOGGABLE() == false) goto L30;
        android.util.Log.d("CXCP", "Created an empty capture session.");     // Catch: Throwable -> L32
    L30:
        Unit r5 = Unit.INSTANCE;     // Catch: Throwable -> L32
        Trace.endSection();
    L32:
        th = move-exception;
        Trace.endSection();
        throw th;
    L36:
        return new Pair(r63.getCameraDeviceWrapper(), r63.getAndroidCameraState());
    L38:
        if (r1.getERROR_LOGGABLE() == false) goto L46;
        android.util.Log.e("CXCP", "Failed to retain an opened camera device!");
        return null;
    L46:
        return null;
    L13:
        th = move-exception;
        Trace.endSection();
        throw th;
    L16:
        r63 = new AwaitOpenCameraResult(r6, r8);
        goto L18
    }

    @Override // androidx.camera.camera2.pipe.compat.Camera2DeviceCloser
    public void closeCamera(CameraDeviceWrapper r5, CameraDevice r6, AndroidCameraState r7, AudioRestrictionController r8, boolean r9, boolean r10) {
        if (r5 == null) goto L5;
        CameraDevice r1 = (CameraDevice) r5.unwrapAs(Reflection.getOrCreateKotlinClass(CameraDevice.class));
    L6:
        if (r1 == null) goto L32;
        CameraId.Companion r2 = CameraId.Companion;
        String r22 = CameraId.m228constructorimpl(r1.getId());
        if (r6 == null) goto L15;
        if (Intrinsics.areEqual(r22, r6.getId()) == true) goto L15;
        Camera2DeviceCloserImpl$$ExternalSyntheticBUOutline0.m("Unwrapped camera device has camera ID ", r22, ", but the wrapped camera device has camera ID ", r6.getId());
        return;
    L15:
        if (Build.VERSION.SDK_INT < 30) goto L17;
        r8.removeListener(r5);
    L17:
        CameraDevice r62 = r1;
        Pair<CameraDeviceWrapper, AndroidCameraState> r92 = handleQuirksBeforeClosing(r5, r62, r7, r9, r10);
        if (r92 == null) goto L20;
        CameraDeviceWrapper r102 = r92.component1();
        AndroidCameraState r93 = r92.component2();
        Object r103 = r102.unwrapAs(Reflection.getOrCreateKotlinClass(CameraDevice.class));
        if (r103 == null) goto L30;
        r5.onDeviceClosing();
        closeCameraDevice((CameraDevice) r103, r93);
        r5.onDeviceClosed();
        if (r9 == false) goto L35;
        r7.onFinalized$camera_camera2_pipe(r62);
        return;
    L35:
        return;
    L30:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return;
    L20:
        if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L22;
        android.util.Log.e("CXCP", "Failed to handle quirks before closing the camera device!");
    L22:
        r5.onDeviceClosing();
        r5.onDeviceClosed();
        r7.onFinalized$camera_camera2_pipe(r62);
        return;
    L32:
        if (r6 == null) goto L36;
        closeCameraDevice(r6, r7);
        return;
    L36:
        return;
    L5:
        r1 = null;
        goto L6
    }
}
