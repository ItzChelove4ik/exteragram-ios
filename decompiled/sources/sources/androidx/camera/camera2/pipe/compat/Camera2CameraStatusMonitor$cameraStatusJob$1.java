package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.internal.CameraStatusMonitor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableSharedFlow;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2CameraStatusMonitor$cameraStatusJob$1", f = "Camera2CameraStatusMonitor.kt", i = {}, l = {69}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
public final class Camera2CameraStatusMonitor$cameraStatusJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ Camera2CameraStatusMonitor this$0;

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* renamed from: androidx.camera.camera2.pipe.compat.Camera2CameraStatusMonitor$cameraStatusJob$1$1, reason: invalid class name */
    public static final class AnonymousClass1<T> implements FlowCollector {
        final /* synthetic */ Camera2CameraStatusMonitor this$0;

        public AnonymousClass1(Camera2CameraStatusMonitor r1) {
            this.this$0 = r1;
        }

        public final Object emit(CameraStatusMonitor.CameraStatus r2, Continuation<? super Unit> r3) {
            if ((r2 instanceof CameraStatusMonitor.CameraStatus.CameraAvailable) == false) goto L10;
            Object r1 = Camera2CameraStatusMonitor.access$get_cameraAvailability$p(this.this$0).emit(r2, r3);
            if (r1 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L8;
            return r1;
        L8:
            return Unit.INSTANCE;
        L10:
            if ((r2 instanceof CameraStatusMonitor.CameraStatus.CameraUnavailable) == false) goto L17;
            Object r12 = Camera2CameraStatusMonitor.access$get_cameraAvailability$p(this.this$0).emit(r2, r3);
            if (r12 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L15;
            return r12;
        L15:
            return Unit.INSTANCE;
        L17:
            if ((r2 instanceof CameraStatusMonitor.CameraStatus.CameraPrioritiesChanged) == false) goto L23;
            MutableSharedFlow r13 = Camera2CameraStatusMonitor.access$get_cameraPriorities$p(this.this$0);
            Unit r22 = Unit.INSTANCE;
            Object r14 = r13.emit(r22, r3);
            if (r14 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L21;
            return r14;
        L21:
            return r22;
        L23:
            return Unit.INSTANCE;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public /* bridge */ /* synthetic */ Object emit(Object r1, Continuation r2) {
            return emit((CameraStatusMonitor.CameraStatus) r1, r2);
        }
    }

    public Camera2CameraStatusMonitor$cameraStatusJob$1(Camera2CameraStatusMonitor r1, Continuation<? super Camera2CameraStatusMonitor$cameraStatusJob$1> r2) {
        this.this$0 = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
        return new Camera2CameraStatusMonitor$cameraStatusJob$1(this.this$0, r2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((Camera2CameraStatusMonitor$cameraStatusJob$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L8;
        if (r1 != 1) goto L6;
        ResultKt.throwOnFailure(r5);
    L12:
        return Unit.INSTANCE;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L8:
        ResultKt.throwOnFailure(r5);
        Flow r52 = Camera2CameraStatusMonitor.access$getCameraStatus$p(this.this$0);
        AnonymousClass1 r12 = new AnonymousClass1(this.this$0);
        this.label = 1;
        if (r52.collect(r12, this) != r0) goto L12;
        return r0;
    }
}
