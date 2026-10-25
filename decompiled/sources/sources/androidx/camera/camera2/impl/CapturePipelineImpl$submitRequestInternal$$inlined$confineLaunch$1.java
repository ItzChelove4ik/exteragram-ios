package androidx.camera.camera2.impl;

import android.util.Log;
import androidx.camera.camera2.compat.workaround.StillCaptureFlowKt;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Logger;
import com.android.dx.io.Opcodes;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", _UrlKt.FRAGMENT_ENCODE_SET, "<anonymous>", "(Lkotlinx/coroutines/CoroutineScope;)V"}, k = 3, mv = {2, 1, 0})
@DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl$submitRequestInternal$$inlined$confineLaunch$1", f = "CapturePipeline.kt", i = {0}, l = {Opcodes.MUL_INT_LIT16, 246, 247}, m = "invokeSuspend", n = {"requiresStopRepeating"}, s = {"L$0"}, v = 1)
@SourceDebugExtension({"SMAP\nUseCaseThreads.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseThreads.kt\nandroidx/camera/camera2/impl/UseCaseThreads$confineLaunch$1\n+ 2 CapturePipeline.kt\nandroidx/camera/camera2/impl/CapturePipelineImpl\n+ 3 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 4 UseCaseCameraConfig.kt\nandroidx/camera/camera2/config/UseCaseGraphContext\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 6 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,200:1\n724#2:201\n725#2:204\n728#2,4:206\n732#2,6:212\n738#2,4:222\n742#2,2:228\n747#2:231\n748#2,8:233\n756#2,8:242\n85#3,2:202\n88#3:205\n85#3,4:218\n102#3,2:226\n105#3:230\n242#4:210\n1#5:211\n1869#6:232\n1870#6:241\n*S KotlinDebug\n*F\n+ 1 CapturePipeline.kt\nandroidx/camera/camera2/impl/CapturePipelineImpl\n*L\n724#1:202,2\n724#1:205\n737#1:218,4\n741#1:226,2\n741#1:230\n731#1:210\n731#1:211\n747#1:232\n747#1:241\n*E\n"})
/* loaded from: classes3.dex */
public final class CapturePipelineImpl$submitRequestInternal$$inlined$confineLaunch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List $deferredList$inlined;
    final /* synthetic */ List $requests$inlined;
    Object L$0;
    int label;
    final /* synthetic */ CapturePipelineImpl this$0;

    public CapturePipelineImpl$submitRequestInternal$$inlined$confineLaunch$1(Continuation r1, CapturePipelineImpl r2, List r3, List r4) {
        this.this$0 = r2;
        this.$deferredList$inlined = r3;
        this.$requests$inlined = r4;
        super(2, r1);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
        return new CapturePipelineImpl$submitRequestInternal$$inlined$confineLaunch$1(r4, this.this$0, this.$deferredList$inlined, this.$requests$inlined);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((CapturePipelineImpl$submitRequestInternal$$inlined$confineLaunch$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = this.label;
        if (r2 == 0) goto L14;
        if (r2 == 1) goto L11;
        if (r2 == 2) goto L10;
        if (r2 != 3) goto L8;
        ResultKt.throwOnFailure(r11);
    L52:
        return Unit.INSTANCE;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L10:
        ResultKt.throwOnFailure(r11);
    L37:
        UseCaseCameraState r112 = CapturePipelineImpl.access$getUseCaseCameraState(this.this$0);
        this.label = 3;
        if (r112.tryStartRepeating(this) != r1) goto L52;
    L39:
        return r1;
    L11:
        Ref.BooleanRef r22 = (Ref.BooleanRef) this.L$0;
        ResultKt.throwOnFailure(r11);     // Catch: CancellationException -> L44
    L21:
        AutoCloseable r113 = (AutoCloseable) r11;     // Catch: CancellationException -> L44
        CameraGraph.Session r4 = (CameraGraph.Session) r113;     // Catch: Throwable -> L25
        boolean r8 = StillCaptureFlowKt.shouldStopRepeatingBeforeCapture(this.$requests$inlined);     // Catch: Throwable -> L25
        r22.element = r8;     // Catch: Throwable -> L25
        if (r8 == false) goto L27;
        r4.stopRepeating();     // Catch: Throwable -> L25
    L27:
        Camera2Logger r82 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L25
        if (Logger.isDebugEnabled("CXCP") == false) goto L30;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#submitRequestInternal: Submitting " + this.$requests$inlined);     // Catch: Throwable -> L25
    L30:
        r4.submit(this.$requests$inlined);     // Catch: Throwable -> L25
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L25
        AutoCloseableKt.closeFinally(r113, null);     // Catch: CancellationException -> L44
        if (r22.element == false) goto L52;
        List r114 = this.$deferredList$inlined;
        this.L$0 = null;
        this.label = 2;
        if (AwaitKt.joinAll(r114, this) != r1) goto L37;
    L25:
        th = move-exception;
        throw th;     // Catch: Throwable -> L41
    L41:
        th = move-exception;
        AutoCloseableKt.closeFinally(r113, th);     // Catch: CancellationException -> L44
        throw th;     // Catch: CancellationException -> L44
    L14:
        ResultKt.throwOnFailure(r11);
        Camera2Logger r115 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L17;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#submitRequestInternal: Acquiring session for submitting requests");
    L17:
        r22 = new Ref.BooleanRef();
        CameraGraph r116 = CapturePipelineImpl.access$getUseCaseGraphContext$p(this.this$0).getGraph();     // Catch: CancellationException -> L44
        this.L$0 = r22;     // Catch: CancellationException -> L44
        this.label = 1;     // Catch: CancellationException -> L44
        r11 = r116.acquireSession(this);     // Catch: CancellationException -> L44
        if (r11 != r1) goto L21;
    L44:
        Camera2Logger r117 = Camera2Logger.INSTANCE;
        if (Logger.isInfoEnabled("CXCP") == false) goto L47;
        Log.i(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#submitRequestInternal: CameraGraph.Session could not be acquired, requests may need re-submission");
    L47:
        Iterator r10 = this.$deferredList$inlined.iterator();
    L49:
        if (r10.hasNext() == false) goto L52;
        ((CompletableDeferred) r10.next()).completeExceptionally(new ImageCaptureException(3, "Capture request is cancelled because camera is closed", null));
        goto L49
    }
}
