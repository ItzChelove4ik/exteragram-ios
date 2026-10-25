package androidx.camera.camera2.pipe.compat;

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
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.VirtualCameraState$connect$2$1", f = "VirtualCamera.kt", i = {}, l = {177}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
public final class VirtualCameraState$connect$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow<CameraState> $state;
    int label;
    final /* synthetic */ VirtualCameraState this$0;

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* renamed from: androidx.camera.camera2.pipe.compat.VirtualCameraState$connect$2$1$1, reason: invalid class name */
    public static final class AnonymousClass1<T> implements FlowCollector {
        final /* synthetic */ VirtualCameraState this$0;

        public AnonymousClass1(VirtualCameraState r1) {
            this.this$0 = r1;
        }

        public final Object emit(CameraState r2, Continuation<? super Unit> r3) {
            Object r32 = VirtualCameraState.access$getLock$p(this.this$0);
            VirtualCameraState r1 = this.this$0;
            monitor-enter(r32);
        L7:
            th = move-exception;
            throw th;
        L5:
            if ((r2 instanceof CameraStateOpen) == false) goto L9;
            VirtualAndroidCameraDevice r0 = new VirtualAndroidCameraDevice((AndroidCameraDevice) ((CameraStateOpen) r2).getCameraDevice());     // Catch: Throwable -> L7
            VirtualCameraState.access$setCurrentVirtualAndroidCamera$p(r1, r0);     // Catch: Throwable -> L7
            VirtualCameraState.access$emitState(r1, new CameraStateOpen(r0));     // Catch: Throwable -> L7
        L10:
            monitor-exit(r32);
            return Unit.INSTANCE;
        L9:
            VirtualCameraState.access$emitState(r1, r2);     // Catch: Throwable -> L7
            goto L10
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public /* bridge */ /* synthetic */ Object emit(Object r1, Continuation r2) {
            return emit((CameraState) r1, r2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public VirtualCameraState$connect$2$1(Flow<? extends CameraState> r1, VirtualCameraState r2, Continuation<? super VirtualCameraState$connect$2$1> r3) {
        this.$state = r1;
        this.this$0 = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        return new VirtualCameraState$connect$2$1(this.$state, this.this$0, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((VirtualCameraState$connect$2$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
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
        Flow<CameraState> r52 = this.$state;
        AnonymousClass1 r12 = new AnonymousClass1(this.this$0);
        this.label = 1;
        if (r52.collect(r12, this) != r0) goto L12;
        return r0;
    }
}
