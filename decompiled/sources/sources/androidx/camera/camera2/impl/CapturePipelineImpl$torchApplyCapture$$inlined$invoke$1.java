package androidx.camera.camera2.impl;

import android.util.Log;
import androidx.camera.camera2.impl.TorchControl;
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
@DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl$torchApplyCapture$$inlined$invoke$1", f = "CapturePipeline.kt", i = {}, l = {MediaError.DetailedErrorCode.HLS_NETWORK_PLAYLIST, 898, 900, 907}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nCapturePipeline.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CapturePipeline.kt\nandroidx/camera/camera2/impl/CapturePipelineImpl$invoke$7$1\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 CapturePipeline.kt\nandroidx/camera/camera2/impl/CapturePipelineImpl\n+ 4 UseCaseCameraConfig.kt\nandroidx/camera/camera2/config/UseCaseGraphContext\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,870:1\n85#2,4:871\n85#2,4:875\n85#2,4:881\n85#2,4:887\n85#2,4:893\n85#2,4:912\n85#2,4:916\n454#3,2:879\n456#3,2:885\n459#3,2:891\n462#3:897\n463#3,12:900\n242#4:898\n1#5:899\n*S KotlinDebug\n*F\n+ 1 CapturePipeline.kt\nandroidx/camera/camera2/impl/CapturePipelineImpl$invoke$7$1\n+ 2 CapturePipeline.kt\nandroidx/camera/camera2/impl/CapturePipelineImpl\n*L\n308#1:871,4\n313#1:875,4\n455#2:881,4\n457#2:887,4\n460#2:893,4\n469#2:912,4\n471#2:916,4\n462#2:898\n462#2:899\n*E\n"})
/* loaded from: classes3.dex */
public final class CapturePipelineImpl$torchApplyCapture$$inlined$invoke$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $captureMode$inlined;
    final /* synthetic */ List $captureSignal;
    final /* synthetic */ boolean $lock3ARequired$inlined;
    final /* synthetic */ boolean $torchOnRequired$inlined;
    final /* synthetic */ boolean $triggerAePreCapture$inlined;
    Object L$0;
    int label;
    final /* synthetic */ CapturePipelineImpl this$0;

    public CapturePipelineImpl$torchApplyCapture$$inlined$invoke$1(List r1, Continuation r2, boolean r3, CapturePipelineImpl r4, boolean r5, boolean r6, int r7) {
        this.$captureSignal = r1;
        this.$torchOnRequired$inlined = r3;
        this.this$0 = r4;
        this.$triggerAePreCapture$inlined = r5;
        this.$lock3ARequired$inlined = r6;
        this.$captureMode$inlined = r7;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r9, Continuation<?> r10) {
        return new CapturePipelineImpl$torchApplyCapture$$inlined$invoke$1(this.$captureSignal, r10, this.$torchOnRequired$inlined, this.this$0, this.$triggerAePreCapture$inlined, this.$lock3ARequired$inlined, this.$captureMode$inlined);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((CapturePipelineImpl$torchApplyCapture$$inlined$invoke$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r15) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        boolean r5 = true;
        if (r1 == 0) goto L18;
        if (r1 == 1) goto L17;
        if (r1 == 2) goto L16;
        if (r1 == 3) goto L11;
        if (r1 != 4) goto L9;
        ResultKt.throwOnFailure(r15);
    L70:
        Camera2Logger r14 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L74;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#torchApplyCapture: Unlocking 3A done");
    L74:
        return Unit.INSTANCE;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L11:
        AutoCloseable r142 = (AutoCloseable) this.L$0;
        ResultKt.throwOnFailure(r15);     // Catch: Throwable -> L14
    L52:
        Unit r02 = Unit.INSTANCE;     // Catch: Throwable -> L14
        AutoCloseableKt.closeFinally(r142, null);
    L14:
        th = th;
    L15:
        Throwable r12 = th;
        throw r12;     // Catch: Throwable -> L57
    L57:
        th = move-exception;
        AutoCloseableKt.closeFinally(r142, r12);
        throw th;
    L16:
        ResultKt.throwOnFailure(r15);
        Object r13 = r15;
    L43:
        AutoCloseable r16 = (AutoCloseable) r13;
        CameraGraph.Session r2 = (CameraGraph.Session) r16;     // Catch: Throwable -> L54
        if (this.$captureMode$inlined == 0) goto L48;
        r5 = false;
    L48:
        this.L$0 = r16;     // Catch: Throwable -> L54
        this.label = 3;     // Catch: Throwable -> L54
        if (r2.unlock3APostCapture(r5, this) == r0) goto L69;
        r142 = r16;
    L69:
        return r0;
    L54:
        th = th;
        r142 = r16;
        goto L15
    L17:
        ResultKt.throwOnFailure(r15);
    L24:
        Camera2Logger r17 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L28;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: Waiting for POST_CAPTURE signal done");
    L28:
        if (this.$torchOnRequired$inlined == false) goto L36;
        if (Logger.isDebugEnabled("CXCP") == false) goto L32;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#torchApplyCapture: Unsetting torch");
    L32:
        TorchControl.m77setTorchAsyncOup_wC0$camera_camera2$default(CapturePipelineImpl.access$getTorchControl$p(this.this$0), TorchControl.TorchMode.Companion.m87getOFFIRs_R8(), false, false, 6, null);
        if (Logger.isDebugEnabled("CXCP") == false) goto L36;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#torchApplyCapture: Unsetting torch done");
    L36:
        if (this.$triggerAePreCapture$inlined == false) goto L61;
        if (Logger.isDebugEnabled("CXCP") == false) goto L40;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#torchApplyCapture: Unlocking 3A for capture");
    L40:
        CameraGraph r18 = CapturePipelineImpl.access$getUseCaseGraphContext$p(this.this$0).getGraph();
        this.label = 2;
        r13 = r18.acquireSession(this);
        if (r13 != r0) goto L43;
    L61:
        if (this.$lock3ARequired$inlined == false) goto L74;
        if (this.$captureMode$inlined != 0) goto L74;
        if (Logger.isDebugEnabled("CXCP") == false) goto L67;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#torchApplyCapture: Unlocking 3A");
    L67:
        CapturePipelineImpl r19 = this.this$0;
        long r3 = CapturePipelineKt.access$getCHECK_3A_TIMEOUT_IN_NS$p();
        this.label = 4;
        if (CapturePipelineImpl.access$unlockAf(r19, r3, this) != r0) goto L70;
    L18:
        ResultKt.throwOnFailure(r15);
        Camera2Logger r110 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L21;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: Waiting for POST_CAPTURE signal");
    L21:
        List r111 = this.$captureSignal;
        this.label = 1;
        if (AwaitKt.joinAll(r111, this) != r0) goto L24;
        goto L24
    }
}
