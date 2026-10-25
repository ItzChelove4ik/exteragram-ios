package androidx.camera.camera2.impl;

import android.util.Log;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", _UrlKt.FRAGMENT_ENCODE_SET, "<anonymous>", "(Lkotlinx/coroutines/CoroutineScope;)V"}, k = 3, mv = {2, 1, 0})
@DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl$defaultNoFlashCapture$$inlined$invoke$1", f = "CapturePipeline.kt", i = {}, l = {MediaError.DetailedErrorCode.HLS_NETWORK_PLAYLIST, 885}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nCapturePipeline.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CapturePipeline.kt\nandroidx/camera/camera2/impl/CapturePipelineImpl$invoke$7$1\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 CapturePipeline.kt\nandroidx/camera/camera2/impl/CapturePipelineImpl\n*L\n1#1,870:1\n85#2,4:871\n85#2,4:875\n85#2,4:881\n85#2,4:887\n383#3,2:879\n385#3,2:885\n388#3:891\n*S KotlinDebug\n*F\n+ 1 CapturePipeline.kt\nandroidx/camera/camera2/impl/CapturePipelineImpl$invoke$7$1\n+ 2 CapturePipeline.kt\nandroidx/camera/camera2/impl/CapturePipelineImpl\n*L\n308#1:871,4\n313#1:875,4\n384#2:881,4\n386#2:887,4\n*E\n"})
/* loaded from: classes3.dex */
public final class CapturePipelineImpl$defaultNoFlashCapture$$inlined$invoke$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List $captureSignal;
    final /* synthetic */ boolean $lock3ARequired$inlined;
    int label;
    final /* synthetic */ CapturePipelineImpl this$0;

    public CapturePipelineImpl$defaultNoFlashCapture$$inlined$invoke$1(List r1, Continuation r2, boolean r3, CapturePipelineImpl r4) {
        this.$captureSignal = r1;
        this.$lock3ARequired$inlined = r3;
        this.this$0 = r4;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
        return new CapturePipelineImpl$defaultNoFlashCapture$$inlined$invoke$1(this.$captureSignal, r4, this.$lock3ARequired$inlined, this.this$0);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((CapturePipelineImpl$defaultNoFlashCapture$$inlined$invoke$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L9;
        if (r1 != 2) goto L7;
        ResultKt.throwOnFailure(r8);
    L27:
        Camera2Logger r7 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L31;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#defaultNoFlashCapture: Unlocking 3A done");
    L31:
        return Unit.INSTANCE;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L9:
        ResultKt.throwOnFailure(r8);
    L16:
        Camera2Logger r82 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L20;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: Waiting for POST_CAPTURE signal done");
    L20:
        if (this.$lock3ARequired$inlined == false) goto L31;
        if (Logger.isDebugEnabled("CXCP") == false) goto L24;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#defaultNoFlashCapture: Unlocking 3A");
    L24:
        CapturePipelineImpl r83 = this.this$0;
        long r5 = CapturePipelineKt.access$getCHECK_3A_TIMEOUT_IN_NS$p();
        this.label = 2;
        if (CapturePipelineImpl.access$unlockAf(r83, r5, this) != r0) goto L27;
    L26:
        return r0;
    L10:
        ResultKt.throwOnFailure(r8);
        Camera2Logger r84 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L13;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: Waiting for POST_CAPTURE signal");
    L13:
        List r85 = this.$captureSignal;
        this.label = 1;
        if (AwaitKt.joinAll(r85, this) != r0) goto L16;
        goto L16
    }
}
