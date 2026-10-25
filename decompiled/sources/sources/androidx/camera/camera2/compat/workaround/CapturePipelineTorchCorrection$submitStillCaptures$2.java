package androidx.camera.camera2.compat.workaround;

import android.util.Log;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.impl.TorchControl;
import androidx.camera.core.Logger;
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
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.compat.workaround.CapturePipelineTorchCorrection$submitStillCaptures$2", f = "CapturePipelineTorchCorrection.kt", i = {}, l = {86, 88, 89}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nCapturePipelineTorchCorrection.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CapturePipelineTorchCorrection.kt\nandroidx/camera/camera2/compat/workaround/CapturePipelineTorchCorrection$submitStillCaptures$2\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,131:1\n85#2,4:132\n85#2,4:136\n*S KotlinDebug\n*F\n+ 1 CapturePipelineTorchCorrection.kt\nandroidx/camera/camera2/compat/workaround/CapturePipelineTorchCorrection$submitStillCaptures$2\n*L\n87#1:132,4\n90#1:136,4\n*E\n"})
/* loaded from: classes3.dex */
public final class CapturePipelineTorchCorrection$submitStillCaptures$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<Deferred<Void>> $deferredResults;
    int label;
    final /* synthetic */ CapturePipelineTorchCorrection this$0;

    /* JADX WARN: Multi-variable type inference failed */
    public CapturePipelineTorchCorrection$submitStillCaptures$2(List<? extends Deferred<Void>> r1, CapturePipelineTorchCorrection r2, Continuation<? super CapturePipelineTorchCorrection$submitStillCaptures$2> r3) {
        this.$deferredResults = r1;
        this.this$0 = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        return new CapturePipelineTorchCorrection$submitStillCaptures$2(this.$deferredResults, this.this$0, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((CapturePipelineTorchCorrection$submitStillCaptures$2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r12) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L12;
        if (r1 == 1) goto L11;
        if (r1 == 2) goto L10;
        if (r1 != 3) goto L8;
        ResultKt.throwOnFailure(r12);
    L24:
        Camera2Logger r11 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L28;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Re-enable Torch to correct the Torch state, done");
    L28:
        return Unit.INSTANCE;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L10:
        ResultKt.throwOnFailure(r12);
    L21:
        Deferred r122 = TorchControl.m77setTorchAsyncOup_wC0$camera_camera2$default(CapturePipelineTorchCorrection.access$getTorchControl$p(this.this$0), TorchControl.TorchMode.Companion.m89getUSED_AS_FLASHIRs_R8(), false, false, 6, null);
        this.label = 3;
        if (r122.join(this) != r0) goto L24;
    L23:
        return r0;
    L11:
        ResultKt.throwOnFailure(r12);
    L15:
        Camera2Logger r123 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L18;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Re-enable Torch to correct the Torch state");
    L18:
        Deferred r124 = TorchControl.m77setTorchAsyncOup_wC0$camera_camera2$default(CapturePipelineTorchCorrection.access$getTorchControl$p(this.this$0), TorchControl.TorchMode.Companion.m87getOFFIRs_R8(), false, false, 6, null);
        this.label = 2;
        if (r124.join(this) != r0) goto L21;
    L12:
        ResultKt.throwOnFailure(r12);
        List<Deferred<Void>> r125 = this.$deferredResults;
        this.label = 1;
        if (AwaitKt.joinAll(r125, this) != r0) goto L15;
        goto L15
    }
}
