package androidx.camera.camera2.impl;

import android.util.Log;
import androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CaptureConfig;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00020\u0001H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/Deferred;", "Ljava/lang/Void;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl$issueSingleCaptureAsync$1$1", f = "UseCaseCameraRequestControl.kt", i = {}, l = {530}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nUseCaseCameraRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$issueSingleCaptureAsync$1$1\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,742:1\n85#2,4:743\n85#2,4:747\n*S KotlinDebug\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$issueSingleCaptureAsync$1$1\n*L\n517#1:743,4\n527#1:747,4\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseCameraRequestControlImpl$issueSingleCaptureAsync$1$1 extends SuspendLambda implements Function1<Continuation<? super List<? extends Deferred<? extends Void>>>, Object> {
    final /* synthetic */ int $captureMode;
    final /* synthetic */ List<CaptureConfig> $captureSequence;
    final /* synthetic */ int $flashMode;
    final /* synthetic */ int $flashType;
    int label;
    final /* synthetic */ UseCaseCameraRequestControlImpl this$0;

    public UseCaseCameraRequestControlImpl$issueSingleCaptureAsync$1$1(UseCaseCameraRequestControlImpl r1, List<CaptureConfig> r2, int r3, int r4, int r5, Continuation<? super UseCaseCameraRequestControlImpl$issueSingleCaptureAsync$1$1> r6) {
        this.this$0 = r1;
        this.$captureSequence = r2;
        this.$captureMode = r3;
        this.$flashType = r4;
        this.$flashMode = r5;
        super(1, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> r8) {
        return new UseCaseCameraRequestControlImpl$issueSingleCaptureAsync$1$1(this.this$0, this.$captureSequence, this.$captureMode, this.$flashType, this.$flashMode, r8);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Continuation<? super List<? extends Deferred<? extends Void>>> r1) {
        return invoke2(r1);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Continuation<? super List<? extends Deferred<Void>>> r1) {
        return ((UseCaseCameraRequestControlImpl$issueSingleCaptureAsync$1$1) create(r1)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r13) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L8;
        if (r1 != 1) goto L6;
        ResultKt.throwOnFailure(r13);
    L21:
        return (List) r13;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L8:
        ResultKt.throwOnFailure(r13);
        Camera2Logger r132 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L12;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseCameraRequestControlImpl#issueSingleCaptureAsync");
    L12:
        if (UseCaseCameraRequestControlImpl.access$hasInvalidSurface(this.this$0, this.$captureSequence) == false) goto L14;
        UseCaseCameraRequestControlImpl.access$failedResults(this.this$0, this.$captureSequence.size(), "Capture request failed due to invalid surface");
    L14:
        UseCaseCameraRequestControlImpl r12 = this.this$0;
        UseCaseCameraRequestControlImpl.InfoBundle r14 = UseCaseCameraRequestControlImpl.access$merge(r12, UseCaseCameraRequestControlImpl.access$getInfoBundleMap$p(r12));
        UseCaseCameraRequestControlImpl r3 = this.this$0;
        List<CaptureConfig> r5 = this.$captureSequence;
        int r8 = this.$captureMode;
        int r9 = this.$flashType;
        int r10 = this.$flashMode;
        if (Logger.isDebugEnabled("CXCP") == false) goto L17;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseCameraRequestControl: Submitting still captures to capture pipeline");
    L17:
        CapturePipeline r4 = UseCaseCameraRequestControlImpl.access$getCapturePipeline(r3);
        int r6 = r14.m102getTemplateejQnlcg().m377unboximpl();
        Camera2ImplConfig r7 = r14.getOptions().build();
        this.label = 1;
        r13 = r4.mo33submitStillCapturesBvXKQx0(r5, r6, r7, r8, r9, r10, this);
        if (r13 != r0) goto L21;
        return r0;
    }
}
