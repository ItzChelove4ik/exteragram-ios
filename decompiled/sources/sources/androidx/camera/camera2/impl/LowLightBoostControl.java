package androidx.camera.camera2.impl;

import android.os.Build;
import android.util.Log;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.FrameInfo;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.core.CameraControl;
import androidx.camera.core.Logger;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.utils.Threads;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B+\b\u0007\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\u000f\u001a\u00020\u000e*\b\u0012\u0004\u0012\u00020\r0\fH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J+\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00110\u0014*\b\u0012\u0004\u0012\u00020\u00110\u00142\n\u0010\u0017\u001a\u00060\u0015j\u0002`\u0016H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J!\u0010\u001d\u001a\u00020\u0011*\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u001bH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u001f\u0010\u0013J\u001b\u0010\"\u001a\u00020\u00112\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\r0 ¢\u0006\u0004\b\"\u0010#J%\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00110'2\u0006\u0010%\u001a\u00020$2\b\b\u0002\u0010&\u001a\u00020$¢\u0006\u0004\b(\u0010)R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010*R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010+R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010,R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010-R\u0018\u0010/\u001a\u0004\u0018\u00010.8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00101\u001a\u00020$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R\u0016\u00103\u001a\u00020$8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00102R\"\u00105\u001a\u0010\u0012\f\u0012\n 4*\u0004\u0018\u00010\u001b0\u001b0\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00109R\u001e\u0010:\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b:\u0010;R0\u0010<\u001a\n\u0012\u0004\u0012\u00020$\u0018\u00010'8\u0000@\u0000X\u0081\u000e¢\u0006\u0018\n\u0004\b<\u0010=\u0012\u0004\bB\u0010\u0013\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR(\u0010H\u001a\u0004\u0018\u00010.2\b\u0010C\u001a\u0004\u0018\u00010.8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\u0017\u0010L\u001a\b\u0012\u0004\u0012\u00020\u001b0I8F¢\u0006\u0006\u001a\u0004\bJ\u0010K¨\u0006M"}, d2 = {"Landroidx/camera/camera2/impl/LowLightBoostControl;", "Landroidx/camera/camera2/impl/UseCaseCameraControl;", "Landroidx/camera/camera2/pipe/CameraMetadata;", "cameraMetadata", "Landroidx/camera/camera2/impl/State3AControl;", "state3AControl", "Landroidx/camera/camera2/impl/UseCaseThreads;", "threads", "Landroidx/camera/camera2/impl/ComboRequestListener;", "comboRequestListener", "<init>", "(Landroidx/camera/camera2/pipe/CameraMetadata;Landroidx/camera/camera2/impl/State3AControl;Landroidx/camera/camera2/impl/UseCaseThreads;Landroidx/camera/camera2/impl/ComboRequestListener;)V", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "Landroidx/camera/core/impl/SessionConfig;", "getSessionConfig", "(Ljava/util/Collection;)Landroidx/camera/core/impl/SessionConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "stopRunningTaskInternal", "()V", "Lkotlinx/coroutines/CompletableDeferred;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "createFailureResult", "(Lkotlinx/coroutines/CompletableDeferred;Ljava/lang/Exception;)Lkotlinx/coroutines/CompletableDeferred;", "Landroidx/lifecycle/MutableLiveData;", _UrlKt.FRAGMENT_ENCODE_SET, "state", "setLiveDataValue", "(Landroidx/lifecycle/MutableLiveData;I)V", "reset", _UrlKt.FRAGMENT_ENCODE_SET, "useCases", "onSessionConfigChanged", "(Ljava/util/List;)V", _UrlKt.FRAGMENT_ENCODE_SET, "lowLightBoost", "cancelPreviousTask", "Lkotlinx/coroutines/Deferred;", "setLowLightBoostAsync", "(ZZ)Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/CameraMetadata;", "Landroidx/camera/camera2/impl/State3AControl;", "Landroidx/camera/camera2/impl/UseCaseThreads;", "Landroidx/camera/camera2/impl/ComboRequestListener;", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "_requestControl", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "isLowLightBoostSupported", "Z", "isLowLightBoostOn", "kotlin.jvm.PlatformType", "_lowLightBoostState", "Landroidx/lifecycle/MutableLiveData;", "Ljava/util/concurrent/atomic/AtomicInteger;", "lowLightBoostStateAtomic", "Ljava/util/concurrent/atomic/AtomicInteger;", "_updateSignal", "Lkotlinx/coroutines/CompletableDeferred;", "checkFrameRateJob", "Lkotlinx/coroutines/Deferred;", "getCheckFrameRateJob$camera_camera2", "()Lkotlinx/coroutines/Deferred;", "setCheckFrameRateJob$camera_camera2", "(Lkotlinx/coroutines/Deferred;)V", "getCheckFrameRateJob$camera_camera2$annotations", "value", "getRequestControl", "()Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "setRequestControl", "(Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;)V", "requestControl", "Landroidx/lifecycle/LiveData;", "getLowLightBoostStateLiveData", "()Landroidx/lifecycle/LiveData;", "lowLightBoostStateLiveData", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLowLightBoostControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LowLightBoostControl.kt\nandroidx/camera/camera2/impl/LowLightBoostControl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 5 UseCaseThreads.kt\nandroidx/camera/camera2/impl/UseCaseThreads\n*L\n1#1,257:1\n1#2:258\n1869#3,2:259\n85#4,4:261\n194#5:265\n*S KotlinDebug\n*F\n+ 1 LowLightBoostControl.kt\nandroidx/camera/camera2/impl/LowLightBoostControl\n*L\n142#1:259,2\n154#1:261,4\n166#1:265\n*E\n"})
/* loaded from: classes3.dex */
public final class LowLightBoostControl implements UseCaseCameraControl {
    private final MutableLiveData<Integer> _lowLightBoostState;
    private UseCaseCameraRequestControl _requestControl;
    private CompletableDeferred<Unit> _updateSignal;
    private final CameraMetadata cameraMetadata;
    private Deferred<Boolean> checkFrameRateJob;
    private final ComboRequestListener comboRequestListener;
    private boolean isLowLightBoostOn;
    private final boolean isLowLightBoostSupported;
    private final AtomicInteger lowLightBoostStateAtomic;
    private final State3AControl state3AControl;
    private final UseCaseThreads threads;

    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J'\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"androidx/camera/camera2/impl/LowLightBoostControl$1", "Landroidx/camera/camera2/pipe/Request$Listener;", "onTotalCaptureResult", _UrlKt.FRAGMENT_ENCODE_SET, "requestMetadata", "Landroidx/camera/camera2/pipe/RequestMetadata;", "frameNumber", "Landroidx/camera/camera2/pipe/FrameNumber;", "totalCaptureResult", "Landroidx/camera/camera2/pipe/FrameInfo;", "onTotalCaptureResult-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/FrameInfo;)V", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: androidx.camera.camera2.impl.LowLightBoostControl$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Request.Listener {
        final /* synthetic */ LowLightBoostControl this$0;

        public AnonymousClass1(LowLightBoostControl r1) {
            this.this$0 = r1;
        }

        @Override // androidx.camera.camera2.pipe.Request.Listener
        /* renamed from: onTotalCaptureResult-CcXjc1I */
        public void mo15onTotalCaptureResultCcXjc1I(RequestMetadata r1, long r2, FrameInfo r4) {
            if (Build.VERSION.SDK_INT >= 35) goto L5;
            return;
        L5:
            if (LowLightBoostControl.access$get_requestControl$p(this.this$0) != null) goto L7;
            return;
        L7:
            if (LowLightBoostControl.access$isLowLightBoostOn$p(this.this$0) == false) goto L18;
            Integer r12 = (Integer) r4.getMetadata().get(LowLightBoostControl$1$$ExternalSyntheticApiModelOutline0.m());
            if (r12 == null) goto L19;
            LowLightBoostControl r0 = this.this$0;
            int r13 = r12.intValue();
            MutableLiveData r22 = LowLightBoostControl.access$get_lowLightBoostState$p(r0);
            int r3 = 1;
            if (r13 == 1) goto L14;
            r3 = 0;
        L14:
            LowLightBoostControl.access$setLiveDataValue(r0, r22, r3);
            return;
        L19:
            return;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.LowLightBoostControl$onSessionConfigChanged$1", f = "LowLightBoostControl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.LowLightBoostControl$onSessionConfigChanged$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00891 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ List<UseCase> $useCases;
        int label;
        final /* synthetic */ LowLightBoostControl this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public C00891(LowLightBoostControl r1, List<? extends UseCase> r2, Continuation<? super C00891> r3) {
            this.this$0 = r1;
            this.$useCases = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            return new C00891(this.this$0, this.$useCases, r3);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Boolean> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Boolean> r2) {
            return ((C00891) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L10;
            ResultKt.throwOnFailure(r2);
            if (((Number) LowLightBoostControl.access$getSessionConfig(this.this$0, this.$useCases).getExpectedFrameRateRange().getUpper()).intValue() <= 30) goto L7;
            boolean r1 = true;
        L9:
            return Boxing.boxBoolean(r1);
        L7:
            r1 = false;
            goto L9
        L10:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    public LowLightBoostControl(CameraMetadata r3, State3AControl r4, UseCaseThreads r5, ComboRequestListener r6) {
        this.cameraMetadata = r3;
        this.state3AControl = r4;
        this.threads = r5;
        this.comboRequestListener = r6;
        boolean r42 = false;
        if (r3 != null) goto L5;
    L7:
        this.isLowLightBoostSupported = r42;
        this._lowLightBoostState = new MutableLiveData(-1);
        this.lowLightBoostStateAtomic = new AtomicInteger(-1);
        if (r42 == false) goto L11;
        r6.addListener(new AnonymousClass1(this), r5.getSequentialExecutor());
        return;
    L11:
        return;
    L5:
        if (CameraMetadata.Companion.getSupportsLowLightBoost(r3) != true) goto L7;
        r42 = true;
        goto L7
    }

    public static final /* synthetic */ CompletableDeferred access$createFailureResult(LowLightBoostControl r0, CompletableDeferred r1, Exception r2) {
        return r0.createFailureResult(r1, r2);
    }

    public static final /* synthetic */ SessionConfig access$getSessionConfig(LowLightBoostControl r0, Collection r1) {
        return r0.getSessionConfig(r1);
    }

    public static final /* synthetic */ State3AControl access$getState3AControl$p(LowLightBoostControl r0) {
        return r0.state3AControl;
    }

    public static final /* synthetic */ MutableLiveData access$get_lowLightBoostState$p(LowLightBoostControl r0) {
        return r0._lowLightBoostState;
    }

    public static final /* synthetic */ UseCaseCameraRequestControl access$get_requestControl$p(LowLightBoostControl r0) {
        return r0._requestControl;
    }

    public static final /* synthetic */ CompletableDeferred access$get_updateSignal$p(LowLightBoostControl r0) {
        return r0._updateSignal;
    }

    public static final /* synthetic */ boolean access$isLowLightBoostOn$p(LowLightBoostControl r0) {
        return r0.isLowLightBoostOn;
    }

    public static final /* synthetic */ void access$setLiveDataValue(LowLightBoostControl r0, MutableLiveData r1, int r2) {
        r0.setLiveDataValue(r1, r2);
    }

    public static final /* synthetic */ void access$setLowLightBoostOn$p(LowLightBoostControl r0, boolean r1) {
        r0.isLowLightBoostOn = r1;
    }

    public static final /* synthetic */ void access$set_updateSignal$p(LowLightBoostControl r0, CompletableDeferred r1) {
        r0._updateSignal = r1;
    }

    public static final /* synthetic */ void access$stopRunningTaskInternal(LowLightBoostControl r0) {
        r0.stopRunningTaskInternal();
    }

    private final CompletableDeferred<Unit> createFailureResult(CompletableDeferred<Unit> r1, Exception r2) {
        r1.completeExceptionally(r2);
        return r1;
    }

    private final SessionConfig getSessionConfig(Collection<? extends UseCase> r2) {
        SessionConfig.ValidatingBuilder r1 = new SessionConfig.ValidatingBuilder();
        Iterator<T> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L7;
        r1.add(((UseCase) r22.next()).getSessionConfig());
        goto L4
    L7:
        return r1.build();
    }

    private final void setLiveDataValue(MutableLiveData<Integer> r1, int r2) {
        if (this.lowLightBoostStateAtomic.getAndSet(r2) != r2) goto L5;
        return;
    L5:
        if (Threads.isMainThread() == false) goto L8;
        r1.setValue(Integer.valueOf(r2));
        return;
    L8:
        r1.postValue(Integer.valueOf(r2));
    }

    public static /* synthetic */ Deferred setLowLightBoostAsync$default(LowLightBoostControl r0, boolean r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = true;
    L6:
        return r0.setLowLightBoostAsync(r1, r2);
    }

    private final void stopRunningTaskInternal() {
        CompletableDeferred<Unit> r0 = this._updateSignal;
        if (r0 == null) goto L5;
        createFailureResult(r0, new CameraControl.OperationCanceledException("There is a new enableLowLightBoost being set"));
    L5:
        this._updateSignal = null;
    }

    public final Deferred<Boolean> getCheckFrameRateJob$camera_camera2() {
        return this.checkFrameRateJob;
    }

    public final LiveData<Integer> getLowLightBoostStateLiveData() {
        return this._lowLightBoostState;
    }

    public UseCaseCameraRequestControl getRequestControl() {
        return this._requestControl;
    }

    public final void onSessionConfigChanged(List<? extends UseCase> r8) {
        if (this.isLowLightBoostSupported == true) goto L6;
        return;
    L6:
        if (r8.isEmpty() == false) goto L9;
        this.checkFrameRateJob = CompletableDeferredKt.CompletableDeferred(Boolean.FALSE);
        return;
    L9:
        this.checkFrameRateJob = BuildersKt.async$default(this.threads.getSequentialScope(), null, null, new C00891(this, r8, null), 3, null);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void reset() {
        stopRunningTaskInternal();
        setLowLightBoostAsync$default(this, false, false, 2, null);
    }

    public final Deferred<Unit> setLowLightBoostAsync(boolean r13, boolean r14) {
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L5;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "LowLightBoostControl#setLowLightBoostAsync: lowLightBoost = " + r13);
    L5:
        CompletableDeferred<Unit> r5 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        if (this.isLowLightBoostSupported == false) goto L8;
        BuildersKt.launch$default(this.threads.getSequentialScope(), null, null, new LowLightBoostControl$setLowLightBoostAsync$$inlined$confineLaunch$1(null, this, r5, r13, r14), 3, null);
        return r5;
    L8:
        return createFailureResult(r5, new IllegalStateException("Low Light Boost is not supported!"));
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void setRequestControl(UseCaseCameraRequestControl r2) {
        this._requestControl = r2;
        if (this.isLowLightBoostOn == true) goto L5;
        return;
    L5:
        if (r2 == null) goto L8;
        setLowLightBoostAsync(true, false);
        return;
    L8:
        setLiveDataValue(this._lowLightBoostState, 0);
    }
}
