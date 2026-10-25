package androidx.camera.camera2.pipe.graph;

import android.hardware.camera2.params.MeteringRectangle;
import androidx.camera.camera2.pipe.AeMode;
import androidx.camera.camera2.pipe.AfMode;
import androidx.camera.camera2.pipe.AwbMode;
import androidx.camera.camera2.pipe.Result3A;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/Result3A;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.graph.CameraGraphImpl$update3A$1", f = "CameraGraphImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
public final class CameraGraphImpl$update3A$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Deferred<? extends Result3A>>, Object> {
    final /* synthetic */ AeMode $aeMode;
    final /* synthetic */ List<MeteringRectangle> $aeRegions;
    final /* synthetic */ AfMode $afMode;
    final /* synthetic */ List<MeteringRectangle> $afRegions;
    final /* synthetic */ AwbMode $awbMode;
    final /* synthetic */ List<MeteringRectangle> $awbRegions;
    int label;
    final /* synthetic */ CameraGraphImpl this$0;

    public CameraGraphImpl$update3A$1(CameraGraphImpl r1, AeMode r2, AfMode r3, AwbMode r4, List<MeteringRectangle> r5, List<MeteringRectangle> r6, List<MeteringRectangle> r7, Continuation<? super CameraGraphImpl$update3A$1> r8) {
        this.this$0 = r1;
        this.$aeMode = r2;
        this.$afMode = r3;
        this.$awbMode = r4;
        this.$aeRegions = r5;
        this.$afRegions = r6;
        this.$awbRegions = r7;
        super(2, r8);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r10, Continuation<?> r11) {
        return new CameraGraphImpl$update3A$1(this.this$0, this.$aeMode, this.$afMode, this.$awbMode, this.$aeRegions, this.$afRegions, this.$awbRegions, r11);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Deferred<? extends Result3A>> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Deferred<Result3A>> r2) {
        return ((CameraGraphImpl$update3A$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) goto L6;
        ResultKt.throwOnFailure(r11);
        return Controller3A.m517update3A169HPGg$default(CameraGraphImpl.access$getController3A$p(this.this$0), this.$aeMode, this.$afMode, this.$awbMode, null, this.$aeRegions, this.$afRegions, this.$awbRegions, 8, null);
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
