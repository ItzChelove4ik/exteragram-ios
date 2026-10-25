package androidx.camera.camera2.impl;

import android.util.Log;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.core.Logger;
import com.google.android.gms.cast.MediaError;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", _UrlKt.FRAGMENT_ENCODE_SET, "<anonymous>", "(Lkotlinx/coroutines/CoroutineScope;)V"}, k = 3, mv = {2, 1, 0})
@DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl$aePreCaptureApplyCapture$$inlined$invoke$1", f = "CapturePipeline.kt", i = {}, l = {MediaError.DetailedErrorCode.HLS_NETWORK_PLAYLIST, 885, 892}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nCapturePipeline.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CapturePipeline.kt\nandroidx/camera/camera2/impl/CapturePipelineImpl$invoke$7$1\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 CapturePipeline.kt\nandroidx/camera/camera2/impl/CapturePipelineImpl\n+ 4 UseCaseCameraConfig.kt\nandroidx/camera/camera2/config/UseCaseGraphContext\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,870:1\n85#2,4:871\n85#2,4:875\n85#2,2:880\n88#2:883\n85#2,4:888\n85#2,4:894\n506#3:879\n507#3:882\n509#3:884\n510#3:887\n512#3,2:892\n514#3,2:898\n242#4:885\n1#5:886\n*S KotlinDebug\n*F\n+ 1 CapturePipeline.kt\nandroidx/camera/camera2/impl/CapturePipelineImpl$invoke$7$1\n+ 2 CapturePipeline.kt\nandroidx/camera/camera2/impl/CapturePipelineImpl\n*L\n308#1:871,4\n313#1:875,4\n506#2:880,2\n506#2:883\n510#2:888,4\n513#2:894,4\n509#2:885\n509#2:886\n*E\n"})
/* loaded from: classes3.dex */
public final class CapturePipelineImpl$aePreCaptureApplyCapture$$inlined$invoke$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $captureMode$inlined;
    final /* synthetic */ List $captureSignal;
    Object L$0;
    int label;
    final /* synthetic */ CapturePipelineImpl this$0;

    public CapturePipelineImpl$aePreCaptureApplyCapture$$inlined$invoke$1(List r1, Continuation r2, CapturePipelineImpl r3, int r4) {
        this.$captureSignal = r1;
        this.this$0 = r3;
        this.$captureMode$inlined = r4;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
        return new CapturePipelineImpl$aePreCaptureApplyCapture$$inlined$invoke$1(this.$captureSignal, r4, this.this$0, this.$captureMode$inlined);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((CapturePipelineImpl$aePreCaptureApplyCapture$$inlined$invoke$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        boolean r4 = true;
        if (r1 == 0) goto L16;
        if (r1 == 1) goto L15;
        if (r1 == 2) goto L14;
        if (r1 != 3) goto L12;
        AutoCloseable r9 = (AutoCloseable) this.L$0;
        ResultKt.throwOnFailure(r10);     // Catch: Throwable -> L10
    L45:
        Camera2Logger r102 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L10
        if (Logger.isDebugEnabled("CXCP") == false) goto L48;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#aePreCaptureApplyCapture: Unlocking 3A done");     // Catch: Throwable -> L10
    L48:
        Unit r103 = Unit.INSTANCE;     // Catch: Throwable -> L10
        AutoCloseableKt.closeFinally(r9, null);
        return Unit.INSTANCE;
    L10:
        Throwable th = th;
    L57:
        throw th;     // Catch: Throwable -> L52
    L52:
        th = move-exception;
        AutoCloseableKt.closeFinally(r9, th);
        throw th;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r10);
    L31:
        AutoCloseable r104 = (AutoCloseable) r10;
        CameraGraph.Session r12 = (CameraGraph.Session) r104;     // Catch: Throwable -> L35
        Camera2Logger r3 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L35
        if (Logger.isDebugEnabled("CXCP") == false) goto L38;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#aePreCaptureApplyCapture: Unlocking 3A");     // Catch: Throwable -> L35
    L38:
        if (this.$captureMode$inlined == 0) goto L41;
        r4 = false;
    L41:
        this.L$0 = r104;     // Catch: Throwable -> L35
        this.label = 3;     // Catch: Throwable -> L35
        if (r12.unlock3APostCapture(r4, this) != r0) goto L44;
    L43:
        return r0;
    L44:
        r9 = r104;
    L35:
        th = move-exception;
        th = th;
        r9 = r104;
        goto L57
    L15:
        ResultKt.throwOnFailure(r10);
    L22:
        Camera2Logger r105 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L26;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: Waiting for POST_CAPTURE signal done");
    L26:
        if (Logger.isDebugEnabled("CXCP") == false) goto L28;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#aePreCaptureApplyCapture: Acquiring session for unlocking 3A");
    L28:
        CameraGraph r106 = CapturePipelineImpl.access$getUseCaseGraphContext$p(this.this$0).getGraph();
        this.label = 2;
        r10 = r106.acquireSession(this);
        if (r10 != r0) goto L31;
    L16:
        ResultKt.throwOnFailure(r10);
        Camera2Logger r107 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L19;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: Waiting for POST_CAPTURE signal");
    L19:
        List r108 = this.$captureSignal;
        this.label = 1;
        if (AwaitKt.joinAll(r108, this) != r0) goto L22;
        goto L22
    }
}
