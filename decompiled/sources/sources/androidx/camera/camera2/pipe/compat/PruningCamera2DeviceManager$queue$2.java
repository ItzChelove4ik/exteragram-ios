package androidx.camera.camera2.pipe.compat;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "it", "Landroidx/camera/camera2/pipe/compat/CameraRequest;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.PruningCamera2DeviceManager$queue$2", f = "Camera2DeviceManager.kt", i = {}, l = {190}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
public final class PruningCamera2DeviceManager$queue$2 extends SuspendLambda implements Function2<CameraRequest, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PruningCamera2DeviceManager this$0;

    public PruningCamera2DeviceManager$queue$2(PruningCamera2DeviceManager r1, Continuation<? super PruningCamera2DeviceManager$queue$2> r2) {
        this.this$0 = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        PruningCamera2DeviceManager$queue$2 r0 = new PruningCamera2DeviceManager$queue$2(this.this$0, r3);
        r0.L$0 = r2;
        return r0;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CameraRequest r1, Continuation<? super Unit> r2) {
        return ((PruningCamera2DeviceManager$queue$2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CameraRequest r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L8;
        if (r1 != 1) goto L6;
        ResultKt.throwOnFailure(r4);
    L12:
        return Unit.INSTANCE;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L8:
        ResultKt.throwOnFailure(r4);
        CameraRequest r42 = (CameraRequest) this.L$0;
        PruningCamera2DeviceManager r12 = this.this$0;
        this.label = 1;
        if (PruningCamera2DeviceManager.access$process(r12, r42, this) != r0) goto L12;
        return r0;
    }
}
