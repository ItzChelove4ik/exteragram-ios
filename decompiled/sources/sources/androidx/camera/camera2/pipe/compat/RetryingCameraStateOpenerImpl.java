package androidx.camera.camera2.pipe.compat;

import android.os.Build;
import androidx.camera.camera2.pipe.CameraError;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.camera2.pipe.compat.CameraAvailabilityMonitor;
import androidx.camera.camera2.pipe.core.DurationNs;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.core.TimeSource;
import androidx.camera.camera2.pipe.core.Timestamps;
import androidx.camera.camera2.pipe.internal.CameraErrorListener;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 %2\u00020\u0001:\u0001%BK\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J4\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001bH\u0096@¢\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010 \u001a\u00020!2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\"\u0010#J\b\u0010$\u001a\u00020\u001cH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/camera/camera2/pipe/compat/RetryingCameraStateOpenerImpl;", "Landroidx/camera/camera2/pipe/compat/RetryingCameraStateOpener;", "cameraStateOpener", "Landroidx/camera/camera2/pipe/compat/CameraStateOpener;", "cameraErrorListener", "Landroidx/camera/camera2/pipe/internal/CameraErrorListener;", "cameraAvailabilityMonitor", "Landroidx/camera/camera2/pipe/compat/CameraAvailabilityMonitor;", "timeSource", "Landroidx/camera/camera2/pipe/core/TimeSource;", "devicePolicyManager", "Landroidx/camera/camera2/pipe/compat/DevicePolicyManagerWrapper;", "audioRestrictionController", "Landroidx/camera/camera2/pipe/compat/AudioRestrictionController;", "cameraInteropConfig", "Landroidx/camera/camera2/pipe/CameraPipe$CameraInteropConfig;", "threads", "Landroidx/camera/camera2/pipe/core/Threads;", "<init>", "(Landroidx/camera/camera2/pipe/compat/CameraStateOpener;Landroidx/camera/camera2/pipe/internal/CameraErrorListener;Landroidx/camera/camera2/pipe/compat/CameraAvailabilityMonitor;Landroidx/camera/camera2/pipe/core/TimeSource;Landroidx/camera/camera2/pipe/compat/DevicePolicyManagerWrapper;Landroidx/camera/camera2/pipe/compat/AudioRestrictionController;Landroidx/camera/camera2/pipe/CameraPipe$CameraInteropConfig;Landroidx/camera/camera2/pipe/core/Threads;)V", "openCameraWithRetry", "Landroidx/camera/camera2/pipe/compat/OpenCameraResult;", "cameraId", "Landroidx/camera/camera2/pipe/CameraId;", "camera2DeviceCloser", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceCloser;", "isForegroundObserver", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "openCameraWithRetry-aeCOTgg", "(Ljava/lang/String;Landroidx/camera/camera2/pipe/compat/Camera2DeviceCloser;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openAndAwaitCameraWithRetry", "Landroidx/camera/camera2/pipe/compat/AwaitOpenCameraResult;", "openAndAwaitCameraWithRetry-0r8Bogc", "(Ljava/lang/String;Landroidx/camera/camera2/pipe/compat/Camera2DeviceCloser;)Landroidx/camera/camera2/pipe/compat/AwaitOpenCameraResult;", "cancelOpen", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRetryingCameraStateOpener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/RetryingCameraStateOpenerImpl\n+ 2 Timestamps.kt\nandroidx/camera/camera2/pipe/core/Timestamps\n+ 3 Timestamps.kt\nandroidx/camera/camera2/pipe/core/TimestampNs\n+ 4 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,665:1\n70#2:666\n70#2:667\n70#2:672\n74#2,2:674\n29#3:668\n29#3:673\n71#4,2:669\n82#4:671\n83#4:676\n50#4,2:677\n50#4,2:679\n*S KotlinDebug\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/RetryingCameraStateOpenerImpl\n*L\n415#1:666\n430#1:667\n467#1:672\n467#1:674,2\n430#1:668\n467#1:673\n440#1:669,2\n465#1:671\n465#1:676\n484#1:677,2\n495#1:679,2\n*E\n"})
/* loaded from: classes3.dex */
public final class RetryingCameraStateOpenerImpl implements RetryingCameraStateOpener {
    public static final Companion Companion = null;
    private final AudioRestrictionController audioRestrictionController;
    private final CameraAvailabilityMonitor cameraAvailabilityMonitor;
    private final CameraErrorListener cameraErrorListener;
    private final CameraPipe.CameraInteropConfig cameraInteropConfig;
    private final CameraStateOpener cameraStateOpener;
    private final DevicePolicyManagerWrapper devicePolicyManager;
    private final Threads threads;
    private final TimeSource timeSource;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JE\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000bH\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0011\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\u0012\u0010\u0013J#\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000bH\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u0018\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ!\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Landroidx/camera/camera2/pipe/compat/RetryingCameraStateOpenerImpl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "shouldRetry", _UrlKt.FRAGMENT_ENCODE_SET, "errorCode", "Landroidx/camera/camera2/pipe/CameraError;", "attempts", _UrlKt.FRAGMENT_ENCODE_SET, "elapsedNs", "Landroidx/camera/camera2/pipe/core/DurationNs;", "camerasDisabledByDevicePolicy", "isForeground", "cameraOpenRetryMaxTimeoutNs", "shouldRetry-rbpwgO0$camera_camera2_pipe", "(IIJZZLandroidx/camera/camera2/pipe/core/DurationNs;)Z", "shouldActivateActiveResume", "shouldActivateActiveResume-8PWMtlg$camera_camera2_pipe", "(ZI)Z", "getRetryTimeoutNs", "activeResumeActivated", "getRetryTimeoutNs-om-7c1s$camera_camera2_pipe", "(ZLandroidx/camera/camera2/pipe/core/DurationNs;)J", "getRetryDelayMs", _UrlKt.FRAGMENT_ENCODE_SET, "getRetryDelayMs-t8DbYm4$camera_camera2_pipe", "(JZ)J", "min", "d1", "d2", "min-L1EDjxI", "(JLandroidx/camera/camera2/pipe/core/DurationNs;)J", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nRetryingCameraStateOpener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/RetryingCameraStateOpenerImpl$Companion\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,665:1\n50#2,2:666\n82#2,2:668\n*S KotlinDebug\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/RetryingCameraStateOpenerImpl$Companion\n*L\n528#1:666,2\n614#1:668,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: min-L1EDjxI, reason: not valid java name */
        private final long m479minL1EDjxI(long r3, DurationNs r5) {
            if (r5 != null) goto L5;
        L6:
            return r3;
        L5:
            if (DurationNs.m497compareTozYRVrok(r3, r5.m502unboximpl()) == (-1)) goto L6;
            return r5.m502unboximpl();
        }

        /* renamed from: getRetryDelayMs-t8DbYm4$camera_camera2_pipe, reason: not valid java name */
        public final long m480getRetryDelayMst8DbYm4$camera_camera2_pipe(long r5, boolean r7) {
            if (r7 == true) goto L6;
            return 500;
        L6:
            if (DurationNs.m497compareTozYRVrok(r5, RetryingCameraStateOpenerKt.access$getActiveResumeCameraRetryThresholds$p()[0].m502unboximpl()) >= 0) goto L9;
            return 500;
        L9:
            if (DurationNs.m497compareTozYRVrok(r5, RetryingCameraStateOpenerKt.access$getActiveResumeCameraRetryThresholds$p()[1].m502unboximpl()) >= 0) goto L12;
            return 2000;
        L12:
            return 4000;
        }

        /* renamed from: getRetryTimeoutNs-om-7c1s$camera_camera2_pipe, reason: not valid java name */
        public final long m481getRetryTimeoutNsom7c1s$camera_camera2_pipe(boolean r3, DurationNs r4) {
            if (r3 == true) goto L6;
            return m479minL1EDjxI(RetryingCameraStateOpenerKt.access$getDefaultCameraRetryTimeoutNs$p(), r4);
        L6:
            return m479minL1EDjxI(RetryingCameraStateOpenerKt.access$getActiveResumeCameraRetryTimeoutNs$p(), r4);
        }

        /* renamed from: shouldActivateActiveResume-8PWMtlg$camera_camera2_pipe, reason: not valid java name */
        public final boolean m482shouldActivateActiveResume8PWMtlg$camera_camera2_pipe(boolean r1, int r2) {
            if (r1 == false) goto L15;
            int r0 = Build.VERSION.SDK_INT;
            if (29 <= r0) goto L6;
            return false;
        L6:
            if (r0 >= 33) goto L20;
            CameraError.Companion r02 = CameraError.Companion;
            if (CameraError.m178equalsimpl0(r2, r02.m189getERROR_CAMERA_IN_USEv7Vf74A()) == false) goto L10;
            return true;
        L10:
            if (CameraError.m178equalsimpl0(r2, r02.m190getERROR_CAMERA_LIMIT_EXCEEDEDv7Vf74A()) == false) goto L12;
            return true;
        L12:
            if (CameraError.m178equalsimpl0(r2, r02.m188getERROR_CAMERA_DISCONNECTEDv7Vf74A()) == false) goto L21;
            return true;
        L21:
            return false;
        L20:
            return false;
        L15:
            return false;
        }

        /* renamed from: shouldRetry-rbpwgO0$camera_camera2_pipe, reason: not valid java name */
        public final boolean m483shouldRetryrbpwgO0$camera_camera2_pipe(int r3, int r4, long r5, boolean r7, boolean r8, DurationNs r9) {
            boolean r82 = m482shouldActivateActiveResume8PWMtlg$camera_camera2_pipe(r8, r3);
            if (r82 == false) goto L8;
            if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L8;
            android.util.Log.d("CXCP", "shouldRetry: Active resume mode is activated");
        L8:
            if (DurationNs.m497compareTozYRVrok(r5, m481getRetryTimeoutNsom7c1s$camera_camera2_pipe(r82, r9)) <= 0) goto L10;
            return false;
        L10:
            CameraError.Companion r2 = CameraError.Companion;
            if (CameraError.m178equalsimpl0(r3, r2.m198getERROR_UNDETERMINEDv7Vf74A()) == false) goto L16;
            if (r4 > 1) goto L14;
            return true;
        L14:
            return false;
        L16:
            if (CameraError.m178equalsimpl0(r3, r2.m189getERROR_CAMERA_IN_USEv7Vf74A()) == false) goto L24;
            if (Build.VERSION.SDK_INT >= 29) goto L22;
            if (r4 > 1) goto L21;
            return true;
        L21:
            return false;
        L22:
            return true;
        L24:
            if (CameraError.m178equalsimpl0(r3, r2.m190getERROR_CAMERA_LIMIT_EXCEEDEDv7Vf74A()) == false) goto L27;
            return true;
        L27:
            if (CameraError.m178equalsimpl0(r3, r2.m187getERROR_CAMERA_DISABLEDv7Vf74A()) == false) goto L34;
            if (r7 == false) goto L32;
            if (r4 > 1) goto L31;
            return true;
        L31:
            return false;
        L32:
            return true;
        L34:
            if (CameraError.m178equalsimpl0(r3, r2.m186getERROR_CAMERA_DEVICEv7Vf74A()) == false) goto L37;
            return true;
        L37:
            if (CameraError.m178equalsimpl0(r3, r2.m193getERROR_CAMERA_SERVICEv7Vf74A()) == false) goto L40;
            return true;
        L40:
            if (CameraError.m178equalsimpl0(r3, r2.m188getERROR_CAMERA_DISCONNECTEDv7Vf74A()) == false) goto L43;
            return true;
        L43:
            if (CameraError.m178equalsimpl0(r3, r2.m196getERROR_ILLEGAL_ARGUMENT_EXCEPTIONv7Vf74A()) == false) goto L46;
            return true;
        L46:
            if (CameraError.m178equalsimpl0(r3, r2.m197getERROR_SECURITY_EXCEPTIONv7Vf74A()) == false) goto L51;
            if (r4 > 1) goto L49;
            return true;
        L49:
            return false;
        L51:
            if (CameraError.m178equalsimpl0(r3, r2.m194getERROR_DO_NOT_DISTURB_ENABLEDv7Vf74A()) == false) goto L54;
            return false;
        L54:
            if (CameraError.m178equalsimpl0(r3, r2.m199getERROR_UNKNOWN_EXCEPTIONv7Vf74A()) == false) goto L59;
            if (r4 > 1) goto L57;
            return true;
        L57:
            return false;
        L59:
            if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L61;
            android.util.Log.e("CXCP", "Unexpected CameraError: " + RetryingCameraStateOpenerImpl.Companion);
        L61:
            return false;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public RetryingCameraStateOpenerImpl(CameraStateOpener r1, CameraErrorListener r2, CameraAvailabilityMonitor r3, TimeSource r4, DevicePolicyManagerWrapper r5, AudioRestrictionController r6, CameraPipe.CameraInteropConfig r7, Threads r8) {
        this.cameraStateOpener = r1;
        this.cameraErrorListener = r2;
        this.cameraAvailabilityMonitor = r3;
        this.timeSource = r4;
        this.devicePolicyManager = r5;
        this.audioRestrictionController = r6;
        this.cameraInteropConfig = r7;
        this.threads = r8;
    }

    public static final /* synthetic */ TimeSource access$getTimeSource$p(RetryingCameraStateOpenerImpl r0) {
        return r0.timeSource;
    }

    @Override // androidx.camera.camera2.pipe.compat.RetryingCameraStateOpener
    public void cancelOpen() {
        this.cameraStateOpener.cancelOpen$camera_camera2_pipe();
    }

    @Override // androidx.camera.camera2.pipe.compat.RetryingCameraStateOpener
    /* renamed from: openAndAwaitCameraWithRetry-0r8Bogc */
    public AwaitOpenCameraResult mo477openAndAwaitCameraWithRetry0r8Bogc(String r4, Camera2DeviceCloser r5) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L6;
        android.util.Log.d("CXCP", this + "#openAndAwaitCameraWithRetry(" + CameraId.m232toStringimpl(r4) + ')');
    L6:
        return (AwaitOpenCameraResult) BuildersKt.runBlocking(this.threads.getBlockingDispatcher(), new RetryingCameraStateOpenerImpl$openAndAwaitCameraWithRetry$2(this, r4, r5, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.AutoCloseable] */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x0256 -> B:16:0x0056). Please report as a decompilation issue!!! */
    @Override // androidx.camera.camera2.pipe.compat.RetryingCameraStateOpener
    /* renamed from: openCameraWithRetry-aeCOTgg */
    public Object mo478openCameraWithRetryaeCOTgg(String r32, Camera2DeviceCloser r33, Function1<? super Unit, Boolean> r34, Continuation<? super OpenCameraResult> r35) {
        if ((r35 instanceof RetryingCameraStateOpenerImpl$openCameraWithRetry$1) == false) goto L7;
        RetryingCameraStateOpenerImpl$openCameraWithRetry$1 r3 = (RetryingCameraStateOpenerImpl$openCameraWithRetry$1) r35;
        int r4 = r3.label;
        if ((r4 & Integer.MIN_VALUE) == 0) goto L7;
        r3.label = r4 - Integer.MIN_VALUE;
    L8:
        Object r2 = r3.result;
        Object r42 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r5 = r3.label;
        int r6 = 2;
        int r9 = 1;
        if (r5 == 0) goto L25;
        if (r5 == 1) goto L24;
        if (r5 == 2) goto L21;
        if (r5 != 3) goto L19;
        long r11 = r3.J$0;
        CameraAvailabilityMonitor.Session r1 = (CameraAvailabilityMonitor.Session) r3.L$5;
        AutoCloseable r52 = (AutoCloseable) r3.L$4;
        Ref.IntRef r13 = (Ref.IntRef) r3.L$3;
        Function1<? super Unit, Boolean> r14 = (Function1) r3.L$2;
        Camera2DeviceCloser r15 = (Camera2DeviceCloser) r3.L$1;
        String r8 = (String) r3.L$0;
        ResultKt.throwOnFailure(r2);     // Catch: Throwable -> L17
        Object r92 = r42;
        int r43 = 1;
        Object r62 = r2;
    L16:
        long r17 = r11;
        CameraAvailabilityMonitor.Session r112 = r1;
        String r12 = r8;
        Ref.IntRef r16 = r13;
        Camera2DeviceCloser r82 = r15;
        if (((Boolean) r62).booleanValue() == false) goto L71;
    L73:
        Object r22 = r92;
        r9 = r43;
        r42 = r22;
        AutoCloseable r23 = r52;
        Function1<? super Unit, Boolean> r53 = r14;
        long r142 = r17;
        r6 = 2;
    L31:
        int r132 = r16.element + r9;     // Catch: Throwable -> L74
        r16.element = r132;     // Catch: Throwable -> L74
        CameraStateOpener r93 = this.cameraStateOpener;     // Catch: Throwable -> L74
        AudioRestrictionController r10 = this.audioRestrictionController;     // Catch: Throwable -> L74
        r3.L$0 = r12;     // Catch: Throwable -> L74
        r3.L$1 = r82;     // Catch: Throwable -> L74
        r3.L$2 = r53;     // Catch: Throwable -> L74
        r3.L$3 = r16;     // Catch: Throwable -> L74
        r3.L$4 = r23;     // Catch: Throwable -> L74
        r3.L$5 = r112;     // Catch: Throwable -> L74
        r3.J$0 = r142;     // Catch: Throwable -> L74
        r3.label = r6;     // Catch: Throwable -> L74
        RetryingCameraStateOpenerImpl$openCameraWithRetry$1 r18 = r3;
        Camera2DeviceCloser r162 = r82;
        CameraAvailabilityMonitor.Session r36 = r112;
        Object r83 = r93.m464tryOpenCamera7pD7j80$camera_camera2_pipe(r12, r132, r142, r162, r10, r18);     // Catch: Throwable -> L74
        if (r83 == r42) goto L67;
        Function1<? super Unit, Boolean> r133 = r53;
        r52 = r23;
        r2 = r83;
        r8 = r12;
        r11 = r142;
        r14 = r133;
        r13 = r16;
        r1 = r36;
        r15 = r162;
        r3 = r18;
    L35:
        OpenCameraResult r24 = (OpenCameraResult) r2;     // Catch: Throwable -> L17
        Timestamps r94 = Timestamps.INSTANCE;     // Catch: Throwable -> L17
        long r222 = DurationNs.m498constructorimpl(this.timeSource.mo506nowvQl9yQU() - r11);     // Catch: Throwable -> L17
        if (r24.getCameraState() == null) goto L40;
        AutoCloseableKt.closeFinally(r52, null);
        return r24;
    L40:
        if (r24.m465getErrorCodemVEW8x0() == null) goto L42;
        boolean r25 = r14.invoke(Unit.INSTANCE).booleanValue();     // Catch: Throwable -> L17
        Companion r19 = Companion;     // Catch: Throwable -> L17
        int r20 = r24.m465getErrorCodemVEW8x0().m182unboximpl();     // Catch: Throwable -> L17
        int r95 = r13.element;     // Catch: Throwable -> L17
        boolean r242 = this.devicePolicyManager.getCamerasDisabled();     // Catch: Throwable -> L17
        CameraPipe.CameraInteropConfig r102 = this.cameraInteropConfig;     // Catch: Throwable -> L17
        if (r102 == null) goto L50;
        DurationNs r26 = r102.m240getCameraOpenRetryMaxTimeoutNsQWez1Bs();     // Catch: Throwable -> L17
    L51:
        boolean r96 = r19.m483shouldRetryrbpwgO0$camera_camera2_pipe(r20, r95, r222, r242, r25, r26);     // Catch: Throwable -> L17
        if (r96 == false) goto L56;
        OpenCameraResult r322 = r24;
        Object r172 = r42;
        if (r13.element > 1) goto L57;
    L58:
        if (r96 == false) goto L60;
        r43 = 1;
        long r97 = r19.m480getRetryDelayMst8DbYm4$camera_camera2_pipe(r222, r19.m482shouldActivateActiveResume8PWMtlg$camera_camera2_pipe(r25, r322.m465getErrorCodemVEW8x0().m182unboximpl()));     // Catch: Throwable -> L17
        r3.L$0 = r8;     // Catch: Throwable -> L17
        r3.L$1 = r15;     // Catch: Throwable -> L17
        r3.L$2 = r14;     // Catch: Throwable -> L17
        r3.L$3 = r13;     // Catch: Throwable -> L17
        r3.L$4 = r52;     // Catch: Throwable -> L17
        r3.L$5 = r1;     // Catch: Throwable -> L17
        r3.J$0 = r11;     // Catch: Throwable -> L17
        r3.label = 3;     // Catch: Throwable -> L17
        r62 = r1.awaitAvailableCamera(r97, r3);     // Catch: Throwable -> L17
        r92 = r172;
        if (r62 != r92) goto L16;
        return r92;
    L60:
        if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L62;
        StringBuilder r110 = new StringBuilder();     // Catch: Throwable -> L17
        r110.append("Failed to open camera ");     // Catch: Throwable -> L17
        r110.append(CameraId.m232toStringimpl(r8));     // Catch: Throwable -> L17
        r110.append(" after ");     // Catch: Throwable -> L17
        r110.append(r13.element);     // Catch: Throwable -> L17
        r110.append(" attempts and ");     // Catch: Throwable -> L17
        r110.append(String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Boxing.boxDouble(DurationNs.m498constructorimpl(access$getTimeSource$p(this).mo506nowvQl9yQU() - r11) / 1000000.0d)}, 1)));     // Catch: Throwable -> L17
        r110.append(". Last error was ");     // Catch: Throwable -> L17
        r110.append(CameraError.m181toStringimpl(r322.m465getErrorCodemVEW8x0().m182unboximpl()));     // Catch: Throwable -> L17
        r110.append('.');     // Catch: Throwable -> L17
        android.util.Log.e("CXCP", r110.toString());     // Catch: Throwable -> L17
    L62:
        AutoCloseableKt.closeFinally(r52, null);
        return r322;
    L57:
        this.cameraErrorListener.mo447onCameraError3M5Xam4(r8, r322.m465getErrorCodemVEW8x0().m182unboximpl(), r96);     // Catch: Throwable -> L17
        goto L58
    L56:
        r322 = r24;
        r172 = r42;
        goto L57
    L50:
        r26 = null;
        goto L51
    L42:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L44;
        android.util.Log.w("CXCP", "Camera open failed without an error. The CameraGraph may have been stopped or closed. Abandoning the camera open attempt.");     // Catch: Throwable -> L17
    L44:
        AutoCloseableKt.closeFinally(r52, null);
        return r24;
    L67:
        return r42;
    L74:
        th = move-exception;
        Throwable r111 = th;
        ?? r54 = r23;
    L80:
        throw r111;     // Catch: Throwable -> L77
    L77:
        th = move-exception;
        AutoCloseableKt.closeFinally(r54, r111);
        throw th;
    L71:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L73;
        android.util.Log.d("CXCP", "Timeout expired, retrying camera open for camera " + CameraId.m232toStringimpl(r12));     // Catch: Throwable -> L17
        goto L73
    L19:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L21:
        r11 = r3.J$0;
        r1 = (CameraAvailabilityMonitor.Session) r3.L$5;
        r52 = (AutoCloseable) r3.L$4;
        Ref.IntRef r84 = (Ref.IntRef) r3.L$3;
        Function1<? super Unit, Boolean> r134 = (Function1) r3.L$2;
        Camera2DeviceCloser r143 = (Camera2DeviceCloser) r3.L$1;
        String r152 = (String) r3.L$0;
        ResultKt.throwOnFailure(r2);     // Catch: Throwable -> L17
        r13 = r84;
        r8 = r152;
        r15 = r143;
        r14 = r134;
        goto L35
    L24:
        long r113 = r3.J$0;
        r16 = (Ref.IntRef) r3.L$3;
        r53 = (Function1) r3.L$2;
        r82 = (Camera2DeviceCloser) r3.L$1;
        String r135 = (String) r3.L$0;
        ResultKt.throwOnFailure(r2);
    L29:
        r23 = (AutoCloseable) r2;
        long r29 = r113;
        r112 = (CameraAvailabilityMonitor.Session) r23;     // Catch: Throwable -> L74
        r142 = r29;
        r12 = r135;
        goto L31
    L25:
        ResultKt.throwOnFailure(r2);
        Timestamps r27 = Timestamps.INSTANCE;
        r113 = this.timeSource.mo506nowvQl9yQU();
        Ref.IntRef r28 = new Ref.IntRef();
        CameraAvailabilityMonitor r55 = this.cameraAvailabilityMonitor;
        r3.L$0 = r32;
        r82 = r33;
        r3.L$1 = r82;
        r3.L$2 = r34;
        r3.L$3 = r28;
        r3.J$0 = r113;
        r3.label = 1;
        Object r56 = r55.mo427startMonitoring0r8Bogc(r32, r3);
        if (r56 == r42) goto L67;
        r135 = r32;
        r16 = r28;
        r2 = r56;
        r53 = r34;
    L17:
        th = move-exception;
        r111 = th;
        r54 = r5;
    L7:
        r3 = new RetryingCameraStateOpenerImpl$openCameraWithRetry$1(this, r35);
        goto L8
    }
}
