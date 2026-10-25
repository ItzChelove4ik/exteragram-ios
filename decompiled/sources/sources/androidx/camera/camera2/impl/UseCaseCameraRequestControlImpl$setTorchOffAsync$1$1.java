package androidx.camera.camera2.impl;

import android.util.Log;
import androidx.camera.camera2.pipe.AeMode;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.Result3A;
import androidx.camera.core.Logger;
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
@DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl$setTorchOffAsync$1$1", f = "UseCaseCameraRequestControl.kt", i = {}, l = {749}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nUseCaseCameraRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$setTorchOffAsync$1$1\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl\n+ 4 UseCaseCameraConfig.kt\nandroidx/camera/camera2/config/UseCaseGraphContext\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,742:1\n85#2,4:743\n95#2,4:753\n656#3,2:747\n658#3,2:751\n660#3,2:757\n242#4:749\n1#5:750\n*S KotlinDebug\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$setTorchOffAsync$1$1\n*L\n456#1:743,4\n457#1:753,4\n457#1:747,2\n457#1:751,2\n457#1:757,2\n457#1:749\n457#1:750\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseCameraRequestControlImpl$setTorchOffAsync$1$1 extends SuspendLambda implements Function1<Continuation<? super Deferred<? extends Result3A>>, Object> {
    final /* synthetic */ int $aeMode;
    int I$0;
    int label;
    final /* synthetic */ UseCaseCameraRequestControlImpl this$0;

    public UseCaseCameraRequestControlImpl$setTorchOffAsync$1$1(UseCaseCameraRequestControlImpl r1, int r2, Continuation<? super UseCaseCameraRequestControlImpl$setTorchOffAsync$1$1> r3) {
        this.this$0 = r1;
        this.$aeMode = r2;
        super(1, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> r3) {
        return new UseCaseCameraRequestControlImpl$setTorchOffAsync$1$1(this.this$0, this.$aeMode, r3);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Deferred<? extends Result3A>> r1) {
        return invoke2(r1);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Continuation<? super Deferred<Result3A>> r1) {
        return ((UseCaseCameraRequestControlImpl$setTorchOffAsync$1$1) create(r1)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L12;
        if (r1 != 1) goto L10;
        int r5 = this.I$0;
        ResultKt.throwOnFailure(r6);     // Catch: CancellationException -> L8
    L20:
        AutoCloseable r62 = (AutoCloseable) r6;     // Catch: CancellationException -> L8
        Deferred<Result3A> r52 = ((CameraGraph.Session) r62).mo165setTorchOffNqN7i0k(AeMode.m109boximpl(r5));     // Catch: Throwable -> L24
        AutoCloseableKt.closeFinally(r62, null);     // Catch: CancellationException -> L8
        return r52;
    L24:
        th = move-exception;
        throw th;     // Catch: Throwable -> L26
    L26:
        th = move-exception;
        AutoCloseableKt.closeFinally(r62, th);     // Catch: CancellationException -> L8
        throw th;     // Catch: CancellationException -> L8
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L12:
        ResultKt.throwOnFailure(r6);
        Camera2Logger r63 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L15;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseCameraRequestControlImpl#setTorchOffAsync");
    L15:
        UseCaseCameraRequestControlImpl r64 = this.this$0;
        int r12 = this.$aeMode;
        CameraGraph r65 = UseCaseCameraRequestControlImpl.access$getUseCaseGraphContext$p(r64).getGraph();     // Catch: CancellationException -> L8
        this.I$0 = r12;     // Catch: CancellationException -> L8
        this.label = 1;     // Catch: CancellationException -> L8
        r6 = r65.acquireSession(this);     // Catch: CancellationException -> L8
        if (r6 != r0) goto L19;
        return r0;
    L19:
        r5 = r12;
    L8:
        e = move-exception;
        Camera2Logger r66 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L33;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Cannot acquire the CameraGraph.Session", e);
    L33:
        return UseCaseCameraRequestControlImpl.access$getSubmitFailedResult$cp();
    }
}
