package androidx.camera.camera2.impl;

import android.hardware.camera2.params.MeteringRectangle;
import android.util.Log;
import androidx.camera.camera2.pipe.AeMode;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.Lock3ABehavior;
import androidx.camera.camera2.pipe.Result3A;
import androidx.camera.core.Logger;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.Deferred;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/Result3A;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl$startFocusAndMeteringAsync$1$1", f = "UseCaseCameraRequestControl.kt", i = {}, l = {749, 475}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nUseCaseCameraRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$startFocusAndMeteringAsync$1$1\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl\n+ 4 UseCaseCameraConfig.kt\nandroidx/camera/camera2/config/UseCaseGraphContext\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,742:1\n85#2,4:743\n95#2,4:753\n656#3,2:747\n658#3,2:751\n660#3,2:757\n242#4:749\n1#5:750\n*S KotlinDebug\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$startFocusAndMeteringAsync$1$1\n*L\n473#1:743,4\n474#1:753,4\n474#1:747,2\n474#1:751,2\n474#1:757,2\n474#1:749\n474#1:750\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseCameraRequestControlImpl$startFocusAndMeteringAsync$1$1 extends SuspendLambda implements Function1<Continuation<? super Deferred<? extends Result3A>>, Object> {
    final /* synthetic */ Lock3ABehavior $aeLockBehavior;
    final /* synthetic */ List<MeteringRectangle> $aeRegions;
    final /* synthetic */ Lock3ABehavior $afLockBehavior;
    final /* synthetic */ List<MeteringRectangle> $afRegions;
    final /* synthetic */ AeMode $afTriggerStartAeMode;
    final /* synthetic */ Lock3ABehavior $awbLockBehavior;
    final /* synthetic */ List<MeteringRectangle> $awbRegions;
    final /* synthetic */ long $timeLimitNs;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    final /* synthetic */ UseCaseCameraRequestControlImpl this$0;

    public UseCaseCameraRequestControlImpl$startFocusAndMeteringAsync$1$1(UseCaseCameraRequestControlImpl r1, List<MeteringRectangle> r2, List<MeteringRectangle> r3, List<MeteringRectangle> r4, Lock3ABehavior r5, Lock3ABehavior r6, Lock3ABehavior r7, AeMode r8, long r9, Continuation<? super UseCaseCameraRequestControlImpl$startFocusAndMeteringAsync$1$1> r11) {
        this.this$0 = r1;
        this.$aeRegions = r2;
        this.$afRegions = r3;
        this.$awbRegions = r4;
        this.$aeLockBehavior = r5;
        this.$afLockBehavior = r6;
        this.$awbLockBehavior = r7;
        this.$afTriggerStartAeMode = r8;
        this.$timeLimitNs = r9;
        super(1, r11);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> r13) {
        return new UseCaseCameraRequestControlImpl$startFocusAndMeteringAsync$1$1(this.this$0, this.$aeRegions, this.$afRegions, this.$awbRegions, this.$aeLockBehavior, this.$afLockBehavior, this.$awbLockBehavior, this.$afTriggerStartAeMode, this.$timeLimitNs, r13);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Deferred<? extends Result3A>> r1) {
        return invoke2(r1);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Continuation<? super Deferred<Result3A>> r1) {
        return ((UseCaseCameraRequestControlImpl$startFocusAndMeteringAsync$1$1) create(r1)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r27) {
        Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = this.label;
        if (r2 == 0) goto L19;
        if (r2 == 1) goto L14;
        if (r2 != 2) goto L12;
        AutoCloseable r12 = (AutoCloseable) this.L$0;
        ResultKt.throwOnFailure(r27);     // Catch: Throwable -> L9
        Object r22 = r27;
        Throwable r0 = null;
    L34:
        Deferred r23 = (Deferred) r22;     // Catch: Throwable -> L9
        AutoCloseableKt.closeFinally(r12, r0);     // Catch: CancellationException -> L17
        return r23;
    L9:
        th = th;
        AutoCloseable r24 = r12;
    L11:
        Throwable r13 = th;
    L52:
        throw r13;     // Catch: Throwable -> L42
    L42:
        th = move-exception;
        AutoCloseableKt.closeFinally(r24, r13);     // Catch: CancellationException -> L17
        throw th;     // Catch: CancellationException -> L17
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        long r6 = this.J$0;
        AeMode r25 = (AeMode) this.L$6;
        Lock3ABehavior r4 = (Lock3ABehavior) this.L$5;
        Lock3ABehavior r8 = (Lock3ABehavior) this.L$4;
        Lock3ABehavior r9 = (Lock3ABehavior) this.L$3;
        List<MeteringRectangle> r10 = (List) this.L$2;
        List<MeteringRectangle> r11 = (List) this.L$1;
        List<MeteringRectangle> r122 = (List) this.L$0;
        ResultKt.throwOnFailure(r27);     // Catch: CancellationException -> L17
        Lock3ABehavior r102 = r8;
        List<MeteringRectangle> r82 = r10;
        long r16 = r6;
        List<MeteringRectangle> r7 = r11;
        List<MeteringRectangle> r62 = r122;
        AeMode r123 = r25;
        Lock3ABehavior r112 = r4;
        Object r26 = r27;
    L27:
        r24 = (AutoCloseable) r26;     // Catch: CancellationException -> L17
        CameraGraph.Session r42 = (CameraGraph.Session) r24;     // Catch: Throwable -> L39
        this.L$0 = r24;     // Catch: Throwable -> L39
        this.L$1 = null;     // Catch: Throwable -> L39
        this.L$2 = null;     // Catch: Throwable -> L39
        this.L$3 = null;     // Catch: Throwable -> L39
        this.L$4 = null;     // Catch: Throwable -> L39
        this.L$5 = null;     // Catch: Throwable -> L39
        this.L$6 = null;     // Catch: Throwable -> L39
        this.label = 2;     // Catch: Throwable -> L39
        r0 = null;
        r22 = CameraGraph.Session.m224lock3AtS25XM$default(r42, null, null, null, r62, r7, r82, r9, r102, r112, r123, null, null, 0, r16, r16, this, 7175, null);     // Catch: Throwable -> L37
        if (r22 != r1) goto L33;
    L32:
        return r1;
    L33:
        r12 = r24;
    L37:
        th = move-exception;
        r13 = th;
        r24 = r24;
    L39:
        th = th;
        goto L11
    L19:
        ResultKt.throwOnFailure(r27);
        Camera2Logger r28 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L22;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseCameraRequestControlImpl#startFocusAndMeteringAsync");
    L22:
        UseCaseCameraRequestControlImpl r29 = this.this$0;
        List<MeteringRectangle> r124 = this.$aeRegions;
        List<MeteringRectangle> r113 = this.$afRegions;
        List<MeteringRectangle> r103 = this.$awbRegions;
        r9 = this.$aeLockBehavior;
        Lock3ABehavior r83 = this.$afLockBehavior;
        Lock3ABehavior r63 = this.$awbLockBehavior;
        AeMode r72 = this.$afTriggerStartAeMode;
        long r132 = this.$timeLimitNs;
        CameraGraph r210 = UseCaseCameraRequestControlImpl.access$getUseCaseGraphContext$p(r29).getGraph();     // Catch: CancellationException -> L17
        this.L$0 = r124;     // Catch: CancellationException -> L17
        this.L$1 = r113;     // Catch: CancellationException -> L17
        this.L$2 = r103;     // Catch: CancellationException -> L17
        this.L$3 = r9;     // Catch: CancellationException -> L17
        this.L$4 = r83;     // Catch: CancellationException -> L17
        this.L$5 = r63;     // Catch: CancellationException -> L17
        this.L$6 = r72;     // Catch: CancellationException -> L17
        this.J$0 = r132;     // Catch: CancellationException -> L17
        this.label = 1;     // Catch: CancellationException -> L17
        r26 = r210.acquireSession(this);     // Catch: CancellationException -> L17
        if (r26 == r1) goto L32;
        r112 = r63;
        r62 = r124;
        r123 = r72;
        r7 = r113;
        r102 = r83;
        r82 = r103;
        r16 = r132;
    L17:
        e = move-exception;
        Camera2Logger r14 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L49;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Cannot acquire the CameraGraph.Session", e);
    L49:
        return UseCaseCameraRequestControlImpl.access$getSubmitFailedResult$cp();
    }
}
