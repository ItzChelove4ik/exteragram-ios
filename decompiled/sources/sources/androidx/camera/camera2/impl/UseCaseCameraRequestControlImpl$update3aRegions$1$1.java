package androidx.camera.camera2.impl;

import android.hardware.camera2.params.MeteringRectangle;
import android.util.Log;
import androidx.camera.camera2.pipe.CameraControls3A;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.Result3A;
import androidx.camera.core.Logger;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.Deferred;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/Result3A;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl$update3aRegions$1$1", f = "UseCaseCameraRequestControl.kt", i = {}, l = {749}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nUseCaseCameraRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$update3aRegions$1$1\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl\n+ 4 UseCaseCameraConfig.kt\nandroidx/camera/camera2/config/UseCaseGraphContext\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,742:1\n85#2,4:743\n95#2,4:753\n656#3,2:747\n658#3,2:751\n660#3,2:757\n242#4:749\n1#5:750\n*S KotlinDebug\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$update3aRegions$1$1\n*L\n553#1:743,4\n554#1:753,4\n554#1:747,2\n554#1:751,2\n554#1:757,2\n554#1:749\n554#1:750\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseCameraRequestControlImpl$update3aRegions$1$1 extends SuspendLambda implements Function1<Continuation<? super Deferred<? extends Result3A>>, Object> {
    final /* synthetic */ List<MeteringRectangle> $aeRegions;
    final /* synthetic */ List<MeteringRectangle> $afRegions;
    final /* synthetic */ List<MeteringRectangle> $awbRegions;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ UseCaseCameraRequestControlImpl this$0;

    public UseCaseCameraRequestControlImpl$update3aRegions$1$1(UseCaseCameraRequestControlImpl r1, List<MeteringRectangle> r2, List<MeteringRectangle> r3, List<MeteringRectangle> r4, Continuation<? super UseCaseCameraRequestControlImpl$update3aRegions$1$1> r5) {
        this.this$0 = r1;
        this.$aeRegions = r2;
        this.$afRegions = r3;
        this.$awbRegions = r4;
        super(1, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> r7) {
        return new UseCaseCameraRequestControlImpl$update3aRegions$1$1(this.this$0, this.$aeRegions, this.$afRegions, this.$awbRegions, r7);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Deferred<? extends Result3A>> r1) {
        return invoke2(r1);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Continuation<? super Deferred<Result3A>> r1) {
        return ((UseCaseCameraRequestControlImpl$update3aRegions$1$1) create(r1)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r14) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L12;
        if (r1 != 1) goto L10;
        List<MeteringRectangle> r02 = (List) this.L$2;
        List<MeteringRectangle> r12 = (List) this.L$1;
        List<MeteringRectangle> r13 = (List) this.L$0;
        ResultKt.throwOnFailure(r14);     // Catch: CancellationException -> L8
    L20:
        AutoCloseable r142 = (AutoCloseable) r14;     // Catch: CancellationException -> L8
        CameraGraph.Session r4 = (CameraGraph.Session) r142;     // Catch: Throwable -> L25
        if (r13 != null) goto L24;
        r13 = ArraysKt.asList(CameraGraph.Constants3A.INSTANCE.getMETERING_REGIONS_DEFAULT());     // Catch: Throwable -> L25
    L24:
        List<MeteringRectangle> r8 = r13;
        if (r12 != null) goto L29;
        r12 = ArraysKt.asList(CameraGraph.Constants3A.INSTANCE.getMETERING_REGIONS_DEFAULT());     // Catch: Throwable -> L25
    L29:
        List<MeteringRectangle> r9 = r12;
        if (r02 != null) goto L32;
        r02 = ArraysKt.asList(CameraGraph.Constants3A.INSTANCE.getMETERING_REGIONS_DEFAULT());     // Catch: Throwable -> L25
    L32:
        Deferred r132 = CameraControls3A.m164update3AydBZfZg$default(r4, null, null, null, r8, r9, r02, 7, null);     // Catch: Throwable -> L25
        AutoCloseableKt.closeFinally(r142, null);     // Catch: CancellationException -> L8
        return r132;
    L25:
        th = move-exception;
        throw th;     // Catch: Throwable -> L36
    L36:
        th = move-exception;
        AutoCloseableKt.closeFinally(r142, th);     // Catch: CancellationException -> L8
        throw th;     // Catch: CancellationException -> L8
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L12:
        ResultKt.throwOnFailure(r14);
        Camera2Logger r143 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L15;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseCameraRequestControlImpl#update3aRegions");
    L15:
        UseCaseCameraRequestControlImpl r144 = this.this$0;
        List<MeteringRectangle> r15 = this.$aeRegions;
        List<MeteringRectangle> r5 = this.$afRegions;
        List<MeteringRectangle> r6 = this.$awbRegions;
        CameraGraph r145 = UseCaseCameraRequestControlImpl.access$getUseCaseGraphContext$p(r144).getGraph();     // Catch: CancellationException -> L8
        this.L$0 = r15;     // Catch: CancellationException -> L8
        this.L$1 = r5;     // Catch: CancellationException -> L8
        this.L$2 = r6;     // Catch: CancellationException -> L8
        this.label = 1;     // Catch: CancellationException -> L8
        r14 = r145.acquireSession(this);     // Catch: CancellationException -> L8
        if (r14 != r0) goto L19;
        return r0;
    L19:
        r13 = r15;
        r12 = r5;
        r02 = r6;
    L8:
        e = move-exception;
        Camera2Logger r146 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L43;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Cannot acquire the CameraGraph.Session", e);
    L43:
        return UseCaseCameraRequestControlImpl.access$getSubmitFailedResult$cp();
    }
}
