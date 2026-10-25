package androidx.camera.camera2.impl;

import android.os.Build;
import android.util.Log;
import androidx.camera.camera2.adapter.CoroutineAdaptersKt;
import androidx.camera.camera2.compat.Api35Compat;
import androidx.camera.camera2.compat.workaround.FlashAvailabilityCheckerKt;
import androidx.camera.camera2.pipe.AeMode;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.Result3A;
import androidx.camera.core.CameraControl;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.Threads;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001:\u0001TB!\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0012\u0010\u0011J\u000f\u0010\u0013\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0013\u0010\u0011J+\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\u0014*\b\u0012\u0004\u0012\u00020\r0\u00142\n\u0010\u0017\u001a\u00060\u0015j\u0002`\u0016H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ!\u0010!\u001a\u00020\r*\b\u0012\u0004\u0012\u00020\n0\u001f2\u0006\u0010 \u001a\u00020\nH\u0002¢\u0006\u0004\b!\u0010\"J\u0017\u0010'\u001a\u00020$2\u0006\u0010#\u001a\u00020\u001aH\u0002¢\u0006\u0004\b%\u0010&J\u000f\u0010(\u001a\u00020\rH\u0016¢\u0006\u0004\b(\u0010\u0011J/\u0010,\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010)\u001a\u00020$2\b\b\u0002\u0010*\u001a\u00020$2\b\b\u0002\u0010+\u001a\u00020$¢\u0006\u0004\b,\u0010-J1\u0010,\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010*\u001a\u00020$2\b\b\u0002\u0010+\u001a\u00020$H\u0000¢\u0006\u0004\b.\u0010/R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u00100R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u00101R\u0018\u00103\u001a\u0004\u0018\u0001028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00104R\u0014\u00105\u001a\u00020$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R*\u00107\u001a\u0004\u0018\u00010\u001a8\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0004\b7\u00108\u0012\u0004\b=\u0010\u0011\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\"\u0010?\u001a\u0010\u0012\f\u0012\n >*\u0004\u0018\u00010\n0\n0\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010A\u001a\u00020$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u00106R\u0014\u0010B\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010CR\u0014\u0010D\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bD\u0010CR\"\u0010E\u001a\u0010\u0012\f\u0012\n >*\u0004\u0018\u00010\n0\n0\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010@R\u001e\u0010F\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bF\u0010GR\u001e\u0010H\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bH\u0010GR(\u0010M\u001a\u0004\u0018\u0001022\b\u0010 \u001a\u0004\u0018\u0001028V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\u0017\u0010Q\u001a\b\u0012\u0004\u0012\u00020\n0N8F¢\u0006\u0006\u001a\u0004\bO\u0010PR\u0017\u0010S\u001a\b\u0012\u0004\u0012\u00020\n0N8F¢\u0006\u0006\u001a\u0004\bR\u0010P¨\u0006U"}, d2 = {"Landroidx/camera/camera2/impl/TorchControl;", "Landroidx/camera/camera2/impl/UseCaseCameraControl;", "Landroidx/camera/camera2/impl/CameraProperties;", "cameraProperties", "Landroidx/camera/camera2/impl/State3AControl;", "state3AControl", "Landroidx/camera/camera2/impl/UseCaseThreads;", "threads", "<init>", "(Landroidx/camera/camera2/impl/CameraProperties;Landroidx/camera/camera2/impl/State3AControl;Landroidx/camera/camera2/impl/UseCaseThreads;)V", _UrlKt.FRAGMENT_ENCODE_SET, "level", "Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "updateTorchStrengthLevelAsync", "(I)Lkotlinx/coroutines/Deferred;", "stopRunningTaskInternal", "()V", "stopTorchStateTask", "stopTorchStrengthTask", "Lkotlinx/coroutines/CompletableDeferred;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "createFailureResult", "(Lkotlinx/coroutines/CompletableDeferred;Ljava/lang/Exception;)Lkotlinx/coroutines/CompletableDeferred;", "Landroidx/camera/camera2/impl/TorchControl$TorchMode;", "mode", "updateTorchState-RaJ5uN0", "(I)V", "updateTorchState", "Landroidx/lifecycle/MutableLiveData;", "value", "setLiveDataValue", "(Landroidx/lifecycle/MutableLiveData;I)V", "torchState", _UrlKt.FRAGMENT_ENCODE_SET, "isFlashUnitOn-RaJ5uN0", "(I)Z", "isFlashUnitOn", "reset", "torch", "cancelPreviousTask", "ignoreFlashUnitAvailability", "setTorchAsync", "(ZZZ)Lkotlinx/coroutines/Deferred;", "setTorchAsync-Oup_wC0$camera_camera2", "(IZZ)Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/impl/State3AControl;", "Landroidx/camera/camera2/impl/UseCaseThreads;", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "_requestControl", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "hasFlashUnit", "Z", "torchMode", "Landroidx/camera/camera2/impl/TorchControl$TorchMode;", "getTorchMode-MnUA4hI$camera_camera2", "()Landroidx/camera/camera2/impl/TorchControl$TorchMode;", "setTorchMode-UuNXre8$camera_camera2", "(Landroidx/camera/camera2/impl/TorchControl$TorchMode;)V", "getTorchMode-MnUA4hI$camera_camera2$annotations", "kotlin.jvm.PlatformType", "_torchState", "Landroidx/lifecycle/MutableLiveData;", "isTorchStrengthSupported", "defaultTorchStrength", "I", "maxTorchStrength", "_torchStrength", "_updateTorchStateSignal", "Lkotlinx/coroutines/CompletableDeferred;", "_updateTorchStrengthSignal", "getRequestControl", "()Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "setRequestControl", "(Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;)V", "requestControl", "Landroidx/lifecycle/LiveData;", "getTorchStateLiveData", "()Landroidx/lifecycle/LiveData;", "torchStateLiveData", "getTorchStrengthLiveData", "torchStrengthLiveData", "TorchMode", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTorchControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TorchControl.kt\nandroidx/camera/camera2/impl/TorchControl\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,316:1\n85#2,4:317\n119#2,4:321\n1#3:325\n*S KotlinDebug\n*F\n+ 1 TorchControl.kt\nandroidx/camera/camera2/impl/TorchControl\n*L\n123#1:317,4\n154#1:321,4\n*E\n"})
/* loaded from: classes3.dex */
public final class TorchControl implements UseCaseCameraControl {
    private UseCaseCameraRequestControl _requestControl;
    private final MutableLiveData<Integer> _torchState;
    private final MutableLiveData<Integer> _torchStrength;
    private CompletableDeferred<Unit> _updateTorchStateSignal;
    private CompletableDeferred<Unit> _updateTorchStrengthSignal;
    private final int defaultTorchStrength;
    private final boolean hasFlashUnit;
    private final boolean isTorchStrengthSupported;
    private final int maxTorchStrength;
    private final State3AControl state3AControl;
    private final UseCaseThreads threads;
    private TorchMode torchMode;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\b\u0081@\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u0005J\u001a\u0010\u0010\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0015"}, d2 = {"Landroidx/camera/camera2/impl/TorchControl$TorchMode;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(I)Ljava/lang/String;", "toString", "hashCode-impl", "hashCode", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals-impl", "(ILjava/lang/Object;)Z", "equals", "I", "getValue", "()I", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @JvmInline
    public static final class TorchMode {
        public static final Companion Companion = null;
        private static final int OFF = 0;
        private static final int ON = 0;
        private static final int USED_AS_FLASH = 0;
        private final int value;

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007¨\u0006\r"}, d2 = {"Landroidx/camera/camera2/impl/TorchControl$TorchMode$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "OFF", "Landroidx/camera/camera2/impl/TorchControl$TorchMode;", "getOFF-IRs_-R8", "()I", "I", "ON", "getON-IRs_-R8", "USED_AS_FLASH", "getUSED_AS_FLASH-IRs_-R8", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            /* renamed from: getOFF-IRs_-R8, reason: not valid java name */
            public final int m87getOFFIRs_R8() {
                return TorchMode.access$getOFF$cp();
            }

            /* renamed from: getON-IRs_-R8, reason: not valid java name */
            public final int m88getONIRs_R8() {
                return TorchMode.access$getON$cp();
            }

            /* renamed from: getUSED_AS_FLASH-IRs_-R8, reason: not valid java name */
            public final int m89getUSED_AS_FLASHIRs_R8() {
                return TorchMode.access$getUSED_AS_FLASH$cp();
            }
        }

        static {
            Companion = new Companion(null);
            OFF = m81constructorimpl(0);
            ON = m81constructorimpl(1);
            USED_AS_FLASH = m81constructorimpl(2);
        }

        private /* synthetic */ TorchMode(int r1) {
            this.value = r1;
        }

        public static final /* synthetic */ int access$getOFF$cp() {
            return OFF;
        }

        public static final /* synthetic */ int access$getON$cp() {
            return ON;
        }

        public static final /* synthetic */ int access$getUSED_AS_FLASH$cp() {
            return USED_AS_FLASH;
        }

        /* renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ TorchMode m80boximpl(int r1) {
            return new TorchMode(r1);
        }

        /* renamed from: constructor-impl, reason: not valid java name */
        private static int m81constructorimpl(int r0) {
            return r0;
        }

        /* renamed from: equals-impl, reason: not valid java name */
        public static boolean m82equalsimpl(int r2, Object r3) {
            if ((r3 instanceof TorchMode) == true) goto L6;
            return false;
        L6:
            if (r2 == ((TorchMode) r3).m86unboximpl()) goto L8;
            return false;
        L8:
            return true;
        }

        /* renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m83equalsimpl0(int r0, int r1) {
            if (r0 != r1) goto L5;
            return true;
        L5:
            return false;
        }

        /* renamed from: hashCode-impl, reason: not valid java name */
        public static int m84hashCodeimpl(int r0) {
            return Integer.hashCode(r0);
        }

        /* renamed from: toString-impl, reason: not valid java name */
        public static String m85toStringimpl(int r2) {
            return "TorchMode(value=" + r2 + ')';
        }

        public boolean equals(Object r1) {
            return m82equalsimpl(this.value, r1);
        }

        public int hashCode() {
            return m84hashCodeimpl(this.value);
        }

        public String toString() {
            return m85toStringimpl(this.value);
        }

        /* renamed from: unbox-impl, reason: not valid java name */
        public final /* synthetic */ int m86unboximpl() {
            return this.value;
        }
    }

    public static Unit $r8$lambda$WpwW2OEsOoPvgaaFVKL9Qn3v_s4(TorchControl r0, Throwable r1) {
        r0._updateTorchStrengthSignal = null;
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$zWFTHKLjGUB-SQony1A4xNaMs1M, reason: not valid java name */
    public static /* synthetic */ Unit m75$r8$lambda$zWFTHKLjGUBSQony1A4xNaMs1M(Result3A r0) {
        return setTorchAsync_Oup_wC0$lambda$1$3(r0);
    }

    public TorchControl(CameraProperties r2, State3AControl r3, UseCaseThreads r4) {
        this.state3AControl = r3;
        this.threads = r4;
        this.hasFlashUnit = FlashAvailabilityCheckerKt.isFlashAvailable$default(r2, false, 1, null);
        this._torchState = new MutableLiveData(0);
        CameraMetadata.Companion r32 = CameraMetadata.Companion;
        this.isTorchStrengthSupported = r32.getSupportsTorchStrength(r2.getMetadata());
        int r42 = r32.getDefaultTorchStrengthLevel(r2.getMetadata());
        this.defaultTorchStrength = r42;
        this.maxTorchStrength = r32.getMaxTorchStrengthLevel(r2.getMetadata());
        this._torchStrength = new MutableLiveData(Integer.valueOf(r42));
    }

    public static final /* synthetic */ State3AControl access$getState3AControl$p(TorchControl r0) {
        return r0.state3AControl;
    }

    private final CompletableDeferred<Unit> createFailureResult(CompletableDeferred<Unit> r1, Exception r2) {
        r1.completeExceptionally(r2);
        return r1;
    }

    /* renamed from: isFlashUnitOn-RaJ5uN0, reason: not valid java name */
    private final boolean m76isFlashUnitOnRaJ5uN0(int r1) {
        return !TorchMode.m83equalsimpl0(r1, TorchMode.Companion.m87getOFFIRs_R8());
    }

    private final void setLiveDataValue(MutableLiveData<Integer> r1, int r2) {
        if (Threads.isMainThread() == false) goto L6;
        r1.setValue(Integer.valueOf(r2));
        return;
    L6:
        r1.postValue(Integer.valueOf(r2));
    }

    public static /* synthetic */ Deferred setTorchAsync$default(TorchControl r0, boolean r1, boolean r2, boolean r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = true;
    L6:
        if ((r4 & 4) == 0) goto L9;
        r3 = false;
    L9:
        return r0.setTorchAsync(r1, r2, r3);
    }

    /* renamed from: setTorchAsync-Oup_wC0$camera_camera2$default, reason: not valid java name */
    public static /* synthetic */ Deferred m77setTorchAsyncOup_wC0$camera_camera2$default(TorchControl r0, int r1, boolean r2, boolean r3, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = true;
    L6:
        if ((r4 & 4) == 0) goto L9;
        r3 = false;
    L9:
        return r0.m79setTorchAsyncOup_wC0$camera_camera2(r1, r2, r3);
    }

    private static final Unit setTorchAsync_Oup_wC0$lambda$1$3(Result3A r0) {
        return Unit.INSTANCE;
    }

    private final void stopRunningTaskInternal() {
        stopTorchStateTask();
        stopTorchStrengthTask();
    }

    private final void stopTorchStateTask() {
        CompletableDeferred<Unit> r0 = this._updateTorchStateSignal;
        if (r0 == null) goto L5;
        createFailureResult(r0, new CameraControl.OperationCanceledException("There is a new enableTorch being set"));
    L5:
        this._updateTorchStateSignal = null;
    }

    private final void stopTorchStrengthTask() {
        CompletableDeferred<Unit> r0 = this._updateTorchStrengthSignal;
        if (r0 == null) goto L5;
        createFailureResult(r0, new CameraControl.OperationCanceledException("There is a new torch strength being set"));
    L5:
        this._updateTorchStrengthSignal = null;
    }

    /* renamed from: updateTorchState-RaJ5uN0, reason: not valid java name */
    private final void m78updateTorchStateRaJ5uN0(int r2) {
        this.torchMode = TorchMode.m80boximpl(r2);
        boolean r22 = TorchMode.m83equalsimpl0(r2, TorchMode.Companion.m88getONIRs_R8());
        setLiveDataValue(this._torchState, r22 ? 1 : 0);
    }

    private final Deferred<Unit> updateTorchStrengthLevelAsync(int r9) {
        CompletableDeferred<Unit> r0 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        if (Build.VERSION.SDK_INT >= 35) goto L5;
    L17:
        createFailureResult(r0, new UnsupportedOperationException("Configuring torch strength is not supported on the device."));
        return r0;
    L5:
        if (this.isTorchStrengthSupported == false) goto L17;
        if (this._updateTorchStrengthSignal == null) goto L9;
        stopTorchStrengthTask();
    L9:
        this._updateTorchStrengthSignal = r0;
        r0.invokeOnCompletion(new TorchControl$$ExternalSyntheticLambda1(this));
        LinkedHashMap r3 = new LinkedHashMap();
        Api35Compat.setFlashStrengthLevel(r3, r9);
        UseCaseCameraRequestControl r2 = getRequestControl();
        if (r2 == null) goto L15;
        Deferred r92 = UseCaseCameraRequestControl.setParametersAsync$default(r2, r3, null, null, 6, null);
        if (r92 == null) goto L15;
        CoroutineAdaptersKt.propagateTo(r92, r0);
        Unit r8 = Unit.INSTANCE;
        return r0;
    L15:
        createFailureResult(r0, new CameraControl.OperationCanceledException("Camera is not active."));
        return r0;
    }

    public UseCaseCameraRequestControl getRequestControl() {
        return this._requestControl;
    }

    public final LiveData<Integer> getTorchStateLiveData() {
        return this._torchState;
    }

    public final LiveData<Integer> getTorchStrengthLiveData() {
        return this._torchStrength;
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void reset() {
        stopRunningTaskInternal();
        if (this.torchMode == null) goto L6;
        m78updateTorchStateRaJ5uN0(TorchMode.Companion.m87getOFFIRs_R8());
        setTorchAsync$default(this, false, false, false, 6, null);
        this.torchMode = null;
        return;
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void setRequestControl(UseCaseCameraRequestControl r8) {
        this._requestControl = r8;
        if (this.torchMode == null) goto L13;
        Integer r82 = getTorchStateLiveData().getValue();
        if (r82 == null) goto L10;
        boolean r0 = true;
        if (r82.intValue() != 1) goto L10;
    L11:
        setTorchAsync$default(this, r0, false, false, 4, null);
        return;
    L10:
        r0 = false;
        goto L11
    }

    public final Deferred<Unit> setTorchAsync(boolean r1, boolean r2, boolean r3) {
        if (r1 == false) goto L4;
        int r12 = TorchMode.Companion.m88getONIRs_R8();
    L6:
        return m79setTorchAsyncOup_wC0$camera_camera2(r12, r2, r3);
    L4:
        r12 = TorchMode.Companion.m87getOFFIRs_R8();
        goto L6
    }

    /* renamed from: setTorchAsync-Oup_wC0$camera_camera2, reason: not valid java name */
    public final Deferred<Unit> m79setTorchAsyncOup_wC0$camera_camera2(int r6, boolean r7, boolean r8) {
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L5;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "TorchControl#setTorchAsync: torch mode = " + TorchMode.m85toStringimpl(r6));
    L5:
        Integer r1 = null;
        CompletableDeferred<Unit> r3 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        if (r8 == false) goto L8;
    L11:
        UseCaseCameraRequestControl r82 = getRequestControl();
        if (r82 == null) goto L41;
        m78updateTorchStateRaJ5uN0(r6);
        if (r7 == false) goto L16;
        stopTorchStateTask();
    L19:
        this._updateTorchStateSignal = r3;
        State3AControl r72 = this.state3AControl;
        if (m76isFlashUnitOnRaJ5uN0(r6) == false) goto L22;
        r1 = 1;
    L22:
        r72.setPreferredAeModeAsync(r1);
        AeMode.Companion r73 = AeMode.Companion;
        AeMode r12 = r73.m117fromIntOrNullkQd0u18(this.state3AControl.getFinalSupportedAeMode());
        if (r12 == null) goto L26;
        int r74 = r12.m116unboximpl();
    L30:
        if (m76isFlashUnitOnRaJ5uN0(r6) == true) goto L32;
        Deferred<Result3A> r5 = r82.mo69setTorchOffAsyncMtizInI(r74);
    L39:
        CoroutineAdaptersKt.propagateTo(r5, r3, new TorchControl$$ExternalSyntheticLambda0());
        return r3;
    L32:
        if (TorchMode.m83equalsimpl0(r6, TorchMode.Companion.m88getONIRs_R8()) == false) goto L36;
        Integer r62 = getTorchStrengthLiveData().getValue();
        if (r62 == null) goto L37;
        updateTorchStrengthLevelAsync(r62.intValue());
    L37:
        r5 = r82.setTorchOnAsync();
        goto L39
    L36:
        updateTorchStrengthLevelAsync(this.defaultTorchStrength);
        goto L37
    L26:
        if (Logger.isWarnEnabled("CXCP") == false) goto L28;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "TorchControl#setTorchAsync: Failed to convert ae mode of value " + access$getState3AControl$p(this).getFinalSupportedAeMode() + " with AeMode.fromIntOrNull, fallback to AeMode.ON");
    L28:
        r74 = r73.m119getONbOjpiJc();
        goto L30
    L16:
        CompletableDeferred<Unit> r75 = this._updateTorchStateSignal;
        if (r75 == null) goto L19;
        CoroutineAdaptersKt.propagateTo(r3, r75);
        goto L19
    L41:
        createFailureResult(r3, new CameraControl.OperationCanceledException("Camera is not active."));
        return r3;
    L8:
        if (this.hasFlashUnit == true) goto L11;
        return createFailureResult(r3, new IllegalStateException("No flash unit"));
    }
}
