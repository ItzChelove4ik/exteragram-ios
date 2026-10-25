package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.core.Log;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.CaptureSessionState$finalizeSession$1", f = "CaptureSessionState.kt", i = {}, l = {475}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nCaptureSessionState.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CaptureSessionState.kt\nandroidx/camera/camera2/pipe/compat/CaptureSessionState$finalizeSession$1\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,657:1\n50#2,2:658\n*S KotlinDebug\n*F\n+ 1 CaptureSessionState.kt\nandroidx/camera/camera2/pipe/compat/CaptureSessionState$finalizeSession$1\n*L\n474#1:658,2\n*E\n"})
/* loaded from: classes3.dex */
public final class CaptureSessionState$finalizeSession$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $delayMs;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CaptureSessionState this$0;

    public CaptureSessionState$finalizeSession$1(long r1, CaptureSessionState r3, Continuation<? super CaptureSessionState$finalizeSession$1> r4) {
        this.$delayMs = r1;
        this.this$0 = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r4, Continuation<?> r5) {
        CaptureSessionState$finalizeSession$1 r0 = new CaptureSessionState$finalizeSession$1(this.$delayMs, this.this$0, r5);
        r0.L$0 = r4;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((CaptureSessionState$finalizeSession$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L8;
        if (r1 != 1) goto L6;
        ResultKt.throwOnFailure(r7);
    L14:
        this.this$0.finalizeSession$camera_camera2_pipe(0);
        return Unit.INSTANCE;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L8:
        ResultKt.throwOnFailure(r7);
        CoroutineScope r72 = (CoroutineScope) this.L$0;
        Log r12 = Log.INSTANCE;
        long r3 = this.$delayMs;
        if (r12.getDEBUG_LOGGABLE() == false) goto L11;
        android.util.Log.d("CXCP", "Finalizing " + r72 + " in " + r3 + " ms");
    L11:
        long r32 = this.$delayMs;
        this.label = 1;
        if (DelayKt.delay(r32, this) != r0) goto L14;
        return r0;
    }
}
