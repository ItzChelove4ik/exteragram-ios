package androidx.camera.camera2.adapter;

import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.util.Log;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.core.CameraIdentifier;
import androidx.camera.core.impl.AbstractCameraPresenceSource;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.concurrent.futures.ListenableFutureKt;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB9\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\u0014\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00040\u0018H\u0016R\u001a\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Landroidx/camera/camera2/adapter/PipeCameraPresenceSource;", "Landroidx/camera/core/impl/AbstractCameraPresenceSource;", "idFlow", "Lkotlinx/coroutines/flow/Flow;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraId;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "initialCameraIds", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Landroid/content/Context;", "<init>", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/CoroutineScope;Ljava/util/List;Landroid/content/Context;)V", "isMonitoring", "Ljava/util/concurrent/atomic/AtomicBoolean;", "flowCollectionJob", "Lkotlinx/coroutines/Job;", "cameraManager", "Landroid/hardware/camera2/CameraManager;", "startMonitoring", _UrlKt.FRAGMENT_ENCODE_SET, "stopMonitoring", "fetchData", "Lcom/google/common/util/concurrent/ListenableFuture;", "Landroidx/camera/core/CameraIdentifier;", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPipeCameraPresenceSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PipeCameraPresenceSource.kt\nandroidx/camera/camera2/adapter/PipeCameraPresenceSource\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,151:1\n49#2:152\n51#2:156\n46#3:153\n51#3:155\n105#4:154\n*S KotlinDebug\n*F\n+ 1 PipeCameraPresenceSource.kt\nandroidx/camera/camera2/adapter/PipeCameraPresenceSource\n*L\n65#1:152\n65#1:156\n65#1:153\n65#1:155\n65#1:154\n*E\n"})
/* loaded from: classes3.dex */
public final class PipeCameraPresenceSource extends AbstractCameraPresenceSource {
    public static final Companion Companion = null;
    private final CameraManager cameraManager;
    private final CoroutineScope coroutineScope;
    private Job flowCollectionJob;
    private final Flow<List<CameraId>> idFlow;
    private final AtomicBoolean isMonitoring;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/camera/camera2/adapter/PipeCameraPresenceSource$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "identifiers", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/CameraIdentifier;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.adapter.PipeCameraPresenceSource$startMonitoring$2", f = "PipeCameraPresenceSource.kt", i = {}, l = {84}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.adapter.PipeCameraPresenceSource$startMonitoring$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<List<? extends CameraIdentifier>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.BooleanRef $isFirstEmission;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ PipeCameraPresenceSource this$0;

        public AnonymousClass2(PipeCameraPresenceSource r1, Ref.BooleanRef r2, Continuation<? super AnonymousClass2> r3) {
            this.this$0 = r1;
            this.$isFirstEmission = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            AnonymousClass2 r0 = new AnonymousClass2(this.this$0, this.$isFirstEmission, r4);
            r0.L$0 = r3;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(List<? extends CameraIdentifier> r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(List<CameraIdentifier> r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r14) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r14);
        L15:
            this.$isFirstEmission.element = false;
        L19:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r14);
            List r142 = (List) this.L$0;
            Log.d("PipePresenceSrc", "Flow emitted new camera set: " + CollectionsKt.joinToString$default(r142, null, null, null, 0, null, null, 63, null));
            if (PipeCameraPresenceSource.access$isMonitoring$p(this.this$0).get() == true) goto L11;
            Boxing.boxInt(Log.d("PipePresenceSrc", "Ignoring camera update because monitoring is stopped."));
            goto L19
        L11:
            if (this.$isFirstEmission.element == false) goto L16;
            Log.i("PipePresenceSrc", "Handling first camera set, triggering fresh query.");
            ListenableFuture<List<CameraIdentifier>> r143 = this.this$0.fetchData();
            this.label = 1;
            if (ListenableFutureKt.await(r143, this) != r0) goto L15;
            return r0;
        L16:
            PipeCameraPresenceSource.access$updateData(this.this$0, r142);
            goto L19
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/flow/FlowCollector;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/CameraIdentifier;", "e", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.adapter.PipeCameraPresenceSource$startMonitoring$3", f = "PipeCameraPresenceSource.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.adapter.PipeCameraPresenceSource$startMonitoring$3, reason: invalid class name */
    public static final class AnonymousClass3 extends SuspendLambda implements Function3<FlowCollector<? super List<? extends CameraIdentifier>>, Throwable, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ PipeCameraPresenceSource this$0;

        public AnonymousClass3(PipeCameraPresenceSource r1, Continuation<? super AnonymousClass3> r2) {
            this.this$0 = r1;
            super(3, r2);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super List<? extends CameraIdentifier>> r1, Throwable r2, Continuation<? super Unit> r3) {
            return invoke2(r1, r2, r3);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super List<CameraIdentifier>> r1, Throwable r2, Continuation<? super Unit> r3) {
            AnonymousClass3 r12 = new AnonymousClass3(this.this$0, r3);
            r12.L$0 = r2;
            return r12.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r3) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L10;
            ResultKt.throwOnFailure(r3);
            Throwable r32 = (Throwable) this.L$0;
            Log.e("PipePresenceSrc", "Error in camera ID flow collection.", r32);
            if (PipeCameraPresenceSource.access$isMonitoring$p(this.this$0).get() == false) goto L7;
            PipeCameraPresenceSource.access$updateError(this.this$0, r32);
        L9:
            return Unit.INSTANCE;
        L7:
            Boxing.boxInt(Log.d("PipePresenceSrc", "Ignoring error because monitoring is stopped."));
            goto L9
        L10:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    public static Object $r8$lambda$pNibnT2Ln7rKug19LH2UnicKFAs(PipeCameraPresenceSource r6, CallbackToFutureAdapter.Completer r7) {
        BuildersKt.launch$default(r6.coroutineScope, null, null, new PipeCameraPresenceSource$fetchData$1$1(r6, r7, null), 3, null);
        return "FetchData for PipeCameraPresence0";
    }

    static {
        Companion = new Companion(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PipeCameraPresenceSource(Flow<? extends List<CameraId>> r1, CoroutineScope r2, List<String> r3, Context r4) {
        super(r3);
        this.idFlow = r1;
        this.coroutineScope = r2;
        this.isMonitoring = new AtomicBoolean(false);
        this.cameraManager = (CameraManager) r4.getSystemService("camera");
    }

    public static final /* synthetic */ CameraManager access$getCameraManager$p(PipeCameraPresenceSource r0) {
        return r0.cameraManager;
    }

    public static final /* synthetic */ AtomicBoolean access$isMonitoring$p(PipeCameraPresenceSource r0) {
        return r0.isMonitoring;
    }

    public static final /* synthetic */ void access$updateData(PipeCameraPresenceSource r0, List r1) {
        r0.updateData(r1);
    }

    public static final /* synthetic */ void access$updateError(PipeCameraPresenceSource r0, Throwable r1) {
        r0.updateError(r1);
    }

    @Override // androidx.camera.core.impl.Observable
    public ListenableFuture<List<CameraIdentifier>> fetchData() {
        return CallbackToFutureAdapter.getFuture(new PipeCameraPresenceSource$$ExternalSyntheticLambda0(this));
    }

    @Override // androidx.camera.core.impl.AbstractCameraPresenceSource
    public void startMonitoring() {
        if (this.isMonitoring.compareAndSet(false, true) == true) goto L6;
        Log.i("PipePresenceSrc", "Monitoring is already active. Ignoring redundant start call.");
        return;
    L6:
        Log.i("PipePresenceSrc", "Starting to collect camera ID flow.");
        Job r0 = this.flowCollectionJob;
        if (r0 == null) goto L9;
        Job.DefaultImpls.cancel$default(r0, null, 1, null);
    L9:
        Ref.BooleanRef r02 = new Ref.BooleanRef();
        r02.element = true;
        this.flowCollectionJob = FlowKt.launchIn(FlowKt.m3551catch(FlowKt.onEach(new PipeCameraPresenceSource$startMonitoring$$inlined$map$1(this.idFlow), new AnonymousClass2(this, r02, null)), new AnonymousClass3(this, null)), this.coroutineScope);
    }

    @Override // androidx.camera.core.impl.AbstractCameraPresenceSource
    public void stopMonitoring() {
        Log.i("PipePresenceSrc", "Stopping camera ID flow collection.");
        if (this.isMonitoring.compareAndSet(true, false) == true) goto L5;
        return;
    L5:
        Job r0 = this.flowCollectionJob;
        if (r0 == null) goto L8;
        Job.DefaultImpls.cancel$default(r0, null, 1, null);
    L8:
        this.flowCollectionJob = null;
    }
}
