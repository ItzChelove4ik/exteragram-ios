package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraManager;
import android.os.Build;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.internal.CameraStatusMonitor;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B-\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00160#H\u0002J\b\u0010&\u001a\u00020\u001dH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0016\u0010\u000e\u001a\n \u000f*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00160#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Landroidx/camera/camera2/pipe/compat/Camera2CameraStatusMonitor;", "Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor;", "cameraManager", "Ljavax/inject/Provider;", "Landroid/hardware/camera2/CameraManager;", "threads", "Landroidx/camera/camera2/pipe/core/Threads;", "cameraId", "Landroidx/camera/camera2/pipe/CameraId;", "cameraPipeJob", "Lkotlinx/coroutines/Job;", "<init>", "(Ljavax/inject/Provider;Landroidx/camera/camera2/pipe/core/Threads;Ljava/lang/String;Lkotlinx/coroutines/Job;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "Ljava/lang/String;", "manager", "kotlin.jvm.PlatformType", "scope", "Lkotlinx/coroutines/CoroutineScope;", "closed", "Lkotlinx/atomicfu/AtomicBoolean;", "_cameraAvailability", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor$CameraStatus;", "cameraAvailability", "Lkotlinx/coroutines/flow/StateFlow;", "getCameraAvailability", "()Lkotlinx/coroutines/flow/StateFlow;", "_cameraPriorities", "Lkotlinx/coroutines/flow/MutableSharedFlow;", _UrlKt.FRAGMENT_ENCODE_SET, "cameraPriorities", "Lkotlinx/coroutines/flow/SharedFlow;", "getCameraPriorities", "()Lkotlinx/coroutines/flow/SharedFlow;", "cameraStatus", "Lkotlinx/coroutines/flow/Flow;", "cameraStatusJob", "cameraStatusFlow", "close", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Camera2CameraStatusMonitor implements CameraStatusMonitor {
    private final MutableStateFlow<CameraStatusMonitor.CameraStatus> _cameraAvailability;
    private final MutableSharedFlow<Unit> _cameraPriorities;
    private final StateFlow<CameraStatusMonitor.CameraStatus> cameraAvailability;
    private final String cameraId;
    private final SharedFlow<Unit> cameraPriorities;
    private final Flow<CameraStatusMonitor.CameraStatus> cameraStatus;
    private final Job cameraStatusJob;
    private final AtomicBoolean closed;
    private final CameraManager manager;
    private final CoroutineScope scope;
    private final Threads threads;

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/channels/ProducerScope;", "Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor$CameraStatus;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2CameraStatusMonitor$cameraStatusFlow$1", f = "Camera2CameraStatusMonitor.kt", i = {}, l = {114}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.Camera2CameraStatusMonitor$cameraStatusFlow$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<ProducerScope<? super CameraStatusMonitor.CameraStatus>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ Camera2CameraStatusMonitor this$0;

        public static Unit $r8$lambda$QvSZz9zGJDm3OJ9gzI7Mr43H2fA(Camera2CameraStatusMonitor r0, Camera2CameraStatusMonitor$cameraStatusFlow$1$availabilityCallback$1 r1) {
            Camera2CameraStatusMonitor.access$getManager$p(r0).unregisterAvailabilityCallback(r1);
            return Unit.INSTANCE;
        }

        public AnonymousClass1(Camera2CameraStatusMonitor r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            AnonymousClass1 r0 = new AnonymousClass1(this.this$0, r3);
            r0.L$0 = r2;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(ProducerScope<? super CameraStatusMonitor.CameraStatus> r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(ProducerScope<? super CameraStatusMonitor.CameraStatus> r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r7);
        L16:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r7);
            ProducerScope r72 = (ProducerScope) this.L$0;
            Camera2CameraStatusMonitor$cameraStatusFlow$1$availabilityCallback$1 r12 = new Camera2CameraStatusMonitor$cameraStatusFlow$1$availabilityCallback$1(r72, this.this$0);
            int r3 = Build.VERSION.SDK_INT;
            Camera2CameraStatusMonitor r4 = this.this$0;
            if (r3 < 28) goto L11;
            Api28Compat.registerAvailabilityCallback(Camera2CameraStatusMonitor.access$getManager$p(r4), Camera2CameraStatusMonitor.access$getThreads$p(this.this$0).getLightweightExecutor(), r12);
        L12:
            Camera2CameraStatusMonitor$cameraStatusFlow$1$$ExternalSyntheticLambda0 r42 = new Camera2CameraStatusMonitor$cameraStatusFlow$1$$ExternalSyntheticLambda0(this.this$0, r12);
            this.label = 1;
            if (ProduceKt.awaitClose(r72, r42, this) != r0) goto L16;
            return r0;
        L11:
            Camera2CameraStatusMonitor.access$getManager$p(r4).registerAvailabilityCallback(r12, Camera2CameraStatusMonitor.access$getThreads$p(this.this$0).getCamera2Handler());
            goto L12
        }
    }

    private Camera2CameraStatusMonitor(Provider<CameraManager> r7, Threads r8, String r9, Job r10) {
        this.threads = r8;
        this.cameraId = r9;
        this.manager = r7.get();
        CoroutineScope r0 = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob(r10).plus(r8.getLightweightDispatcher().plus(new CoroutineName("CXCP-CameraStatusMonitor"))));
        this.scope = r0;
        this.closed = AtomicFU.atomic(false);
        MutableStateFlow<CameraStatusMonitor.CameraStatus> r82 = StateFlowKt.MutableStateFlow(CameraStatusMonitor.CameraStatus.Unknown.INSTANCE);
        this._cameraAvailability = r82;
        this.cameraAvailability = FlowKt.asStateFlow(r82);
        MutableSharedFlow<Unit> r72 = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this._cameraPriorities = r72;
        this.cameraPriorities = FlowKt.asSharedFlow(r72);
        this.cameraStatus = cameraStatusFlow();
        this.cameraStatusJob = BuildersKt.launch$default(r0, null, null, new Camera2CameraStatusMonitor$cameraStatusJob$1(this, null), 3, null);
    }

    public /* synthetic */ Camera2CameraStatusMonitor(Provider r1, Threads r2, String r3, Job r4, DefaultConstructorMarker r5) {
        this(r1, r2, r3, r4);
    }

    public static final /* synthetic */ String access$getCameraId$p(Camera2CameraStatusMonitor r0) {
        return r0.cameraId;
    }

    public static final /* synthetic */ Flow access$getCameraStatus$p(Camera2CameraStatusMonitor r0) {
        return r0.cameraStatus;
    }

    public static final /* synthetic */ CameraManager access$getManager$p(Camera2CameraStatusMonitor r0) {
        return r0.manager;
    }

    public static final /* synthetic */ Threads access$getThreads$p(Camera2CameraStatusMonitor r0) {
        return r0.threads;
    }

    public static final /* synthetic */ MutableStateFlow access$get_cameraAvailability$p(Camera2CameraStatusMonitor r0) {
        return r0._cameraAvailability;
    }

    public static final /* synthetic */ MutableSharedFlow access$get_cameraPriorities$p(Camera2CameraStatusMonitor r0) {
        return r0._cameraPriorities;
    }

    private final Flow<CameraStatusMonitor.CameraStatus> cameraStatusFlow() {
        return FlowKt.callbackFlow(new AnonymousClass1(this, null));
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        if (this.closed.compareAndSet(false, true) == false) goto L6;
        Job.DefaultImpls.cancel$default(this.cameraStatusJob, null, 1, null);
        CoroutineScopeKt.cancel$default(this.scope, null, 1, null);
        return;
    }

    @Override // androidx.camera.camera2.pipe.internal.CameraStatusMonitor
    public StateFlow<CameraStatusMonitor.CameraStatus> getCameraAvailability() {
        return this.cameraAvailability;
    }

    @Override // androidx.camera.camera2.pipe.internal.CameraStatusMonitor
    public SharedFlow<Unit> getCameraPriorities() {
        return this.cameraPriorities;
    }
}
