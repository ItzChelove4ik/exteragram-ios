package androidx.camera.camera2.pipe.compat;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "it", "Landroidx/camera/camera2/pipe/compat/CameraState;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.CameraStateOpener$tryOpenCamera$2$resultDeferred$1$result$1", f = "RetryingCameraStateOpener.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
public final class CameraStateOpener$tryOpenCamera$2$resultDeferred$1$result$1 extends SuspendLambda implements Function2<CameraState, Continuation<? super Boolean>, Object> {
    /* synthetic */ Object L$0;
    int label;

    public CameraStateOpener$tryOpenCamera$2$resultDeferred$1$result$1(Continuation<? super CameraStateOpener$tryOpenCamera$2$resultDeferred$1$result$1> r2) {
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
        CameraStateOpener$tryOpenCamera$2$resultDeferred$1$result$1 r0 = new CameraStateOpener$tryOpenCamera$2$resultDeferred$1$result$1(r2);
        r0.L$0 = r1;
        return r0;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CameraState r1, Continuation<? super Boolean> r2) {
        return ((CameraStateOpener$tryOpenCamera$2$resultDeferred$1$result$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CameraState r1, Continuation<? super Boolean> r2) {
        return invoke2(r1, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) goto L6;
        ResultKt.throwOnFailure(r2);
        return Boxing.boxBoolean(!(((CameraState) this.L$0) instanceof CameraStateUnopened));
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
