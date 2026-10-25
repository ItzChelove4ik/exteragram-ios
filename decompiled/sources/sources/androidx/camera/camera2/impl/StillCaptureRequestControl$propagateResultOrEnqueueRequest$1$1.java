package androidx.camera.camera2.impl;

import android.util.Log;
import androidx.camera.camera2.impl.StillCaptureRequestControl;
import androidx.camera.core.Logger;
import com.android.dx.io.Opcodes;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.sync.Mutex;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.impl.StillCaptureRequestControl$propagateResultOrEnqueueRequest$1$1", f = "StillCaptureRequestControl.kt", i = {0, 0, 1}, l = {183, Opcodes.OR_INT_LIT8}, m = "invokeSuspend", n = {"isPending", "latestRequestControl", "$this$withLock_u24default$iv"}, s = {"L$0", "L$2", "L$0"}, v = 1)
@SourceDebugExtension({"SMAP\nStillCaptureRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StillCaptureRequestControl.kt\nandroidx/camera/camera2/impl/StillCaptureRequestControl$propagateResultOrEnqueueRequest$1$1\n+ 2 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n+ 3 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,216:1\n116#2,11:217\n85#3,4:228\n*S KotlinDebug\n*F\n+ 1 StillCaptureRequestControl.kt\nandroidx/camera/camera2/impl/StillCaptureRequestControl$propagateResultOrEnqueueRequest$1$1\n*L\n194#1:217,11\n195#1:228,4\n*E\n"})
/* loaded from: classes3.dex */
public final class StillCaptureRequestControl$propagateResultOrEnqueueRequest$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ UseCaseCameraRequestControl $currentRequestControl;
    final /* synthetic */ StillCaptureRequestControl.CaptureRequest $submittedRequest;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ StillCaptureRequestControl this$0;

    public StillCaptureRequestControl$propagateResultOrEnqueueRequest$1$1(StillCaptureRequestControl r1, UseCaseCameraRequestControl r2, StillCaptureRequestControl.CaptureRequest r3, Continuation<? super StillCaptureRequestControl$propagateResultOrEnqueueRequest$1$1> r4) {
        this.this$0 = r1;
        this.$currentRequestControl = r2;
        this.$submittedRequest = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
        return new StillCaptureRequestControl$propagateResultOrEnqueueRequest$1$1(this.this$0, this.$currentRequestControl, this.$submittedRequest, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((StillCaptureRequestControl$propagateResultOrEnqueueRequest$1$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r9) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L9;
        if (r1 != 2) goto L7;
        StillCaptureRequestControl.CaptureRequest r02 = (StillCaptureRequestControl.CaptureRequest) this.L$2;
        StillCaptureRequestControl r12 = (StillCaptureRequestControl) this.L$1;
        Mutex r2 = (Mutex) this.L$0;
        ResultKt.throwOnFailure(r9);
    L34:
        StillCaptureRequestControl.access$getPendingRequests$p(r12).add(r02);     // Catch: Throwable -> L29
        r2.unlock(null);
        Camera2Logger r92 = Camera2Logger.INSTANCE;
        StillCaptureRequestControl.CaptureRequest r8 = this.$submittedRequest;
        if (Logger.isDebugEnabled("CXCP") == false) goto L33;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "StillCaptureRequestControl: failed to submit " + r8 + ", will be retried with a future UseCaseCamera");
    L33:
        return Unit.INSTANCE;
    L29:
        th = move-exception;
        r2.unlock(null);
        throw th;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L9:
        StillCaptureRequestControl r13 = (StillCaptureRequestControl) this.L$3;
        UseCaseCameraRequestControl r3 = (UseCaseCameraRequestControl) this.L$2;
        StillCaptureRequestControl.CaptureRequest r5 = (StillCaptureRequestControl.CaptureRequest) this.L$1;
        Ref.BooleanRef r6 = (Ref.BooleanRef) this.L$0;
        ResultKt.throwOnFailure(r9);
    L18:
        StillCaptureRequestControl.access$propagateResultOrEnqueueRequest(r13, (Deferred) r9, r5, r3);
        r6.element = false;
    L20:
        if (r6.element == false) goto L33;
        Mutex r93 = StillCaptureRequestControl.access$getMutex$p(this.this$0);
        r12 = this.this$0;
        StillCaptureRequestControl.CaptureRequest r32 = this.$submittedRequest;
        this.L$0 = r93;
        this.L$1 = r12;
        this.L$2 = r32;
        this.L$3 = null;
        this.label = 2;
        if (r93.lock(null, this) != r0) goto L24;
    L23:
        return r0;
    L24:
        r2 = r93;
        r02 = r32;
        goto L34
    L10:
        ResultKt.throwOnFailure(r9);
        r6 = new Ref.BooleanRef();
        r6.element = true;
        UseCaseCameraRequestControl r94 = this.this$0.getRequestControl();
        if (r94 == null) goto L20;
        UseCaseCameraRequestControl r14 = this.$currentRequestControl;
        StillCaptureRequestControl r52 = this.this$0;
        StillCaptureRequestControl.CaptureRequest r7 = this.$submittedRequest;
        if (Intrinsics.areEqual(r14, r94) == true) goto L20;
        this.L$0 = r6;
        this.L$1 = r7;
        this.L$2 = r94;
        this.L$3 = r52;
        this.label = 1;
        Object r15 = StillCaptureRequestControl.access$submitRequest(r52, r7, r94, this);
        if (r15 == r0) goto L23;
        r3 = r94;
        r9 = r15;
        r13 = r52;
        r5 = r7;
        goto L18
    }
}
