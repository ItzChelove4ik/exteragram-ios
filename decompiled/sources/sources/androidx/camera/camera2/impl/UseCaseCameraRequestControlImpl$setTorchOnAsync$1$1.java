package androidx.camera.camera2.impl;

import android.util.Log;
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
@DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl$setTorchOnAsync$1$1", f = "UseCaseCameraRequestControl.kt", i = {}, l = {749}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nUseCaseCameraRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$setTorchOnAsync$1$1\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl\n+ 4 UseCaseCameraConfig.kt\nandroidx/camera/camera2/config/UseCaseGraphContext\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,742:1\n85#2,4:743\n95#2,4:753\n656#3,2:747\n658#3,2:751\n660#3,2:757\n242#4:749\n1#5:750\n*S KotlinDebug\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$setTorchOnAsync$1$1\n*L\n448#1:743,4\n449#1:753,4\n449#1:747,2\n449#1:751,2\n449#1:757,2\n449#1:749\n449#1:750\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseCameraRequestControlImpl$setTorchOnAsync$1$1 extends SuspendLambda implements Function1<Continuation<? super Deferred<? extends Result3A>>, Object> {
    int label;
    final /* synthetic */ UseCaseCameraRequestControlImpl this$0;

    public UseCaseCameraRequestControlImpl$setTorchOnAsync$1$1(UseCaseCameraRequestControlImpl r1, Continuation<? super UseCaseCameraRequestControlImpl$setTorchOnAsync$1$1> r2) {
        this.this$0 = r1;
        super(1, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> r2) {
        return new UseCaseCameraRequestControlImpl$setTorchOnAsync$1$1(this.this$0, r2);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Deferred<? extends Result3A>> r1) {
        return invoke2(r1);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Continuation<? super Deferred<Result3A>> r1) {
        return ((UseCaseCameraRequestControlImpl$setTorchOnAsync$1$1) create(r1)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L11;
        if (r1 != 1) goto L9;
        ResultKt.throwOnFailure(r6);     // Catch: CancellationException -> L7
    L18:
        AutoCloseable r62 = (AutoCloseable) r6;     // Catch: CancellationException -> L7
        Deferred<Result3A> r5 = ((CameraGraph.Session) r62).setTorchOn();     // Catch: Throwable -> L22
        AutoCloseableKt.closeFinally(r62, null);     // Catch: CancellationException -> L7
        return r5;
    L22:
        th = move-exception;
        throw th;     // Catch: Throwable -> L24
    L24:
        th = move-exception;
        AutoCloseableKt.closeFinally(r62, th);     // Catch: CancellationException -> L7
        throw th;     // Catch: CancellationException -> L7
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L11:
        ResultKt.throwOnFailure(r6);
        Camera2Logger r63 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L15;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseCameraRequestControlImpl#setTorchOnAsync");
    L15:
        CameraGraph r64 = UseCaseCameraRequestControlImpl.access$getUseCaseGraphContext$p(this.this$0).getGraph();     // Catch: CancellationException -> L7
        this.label = 1;     // Catch: CancellationException -> L7
        r6 = r64.acquireSession(this);     // Catch: CancellationException -> L7
        if (r6 != r0) goto L18;
        return r0;
    L7:
        e = move-exception;
        Camera2Logger r65 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L31;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Cannot acquire the CameraGraph.Session", e);
    L31:
        return UseCaseCameraRequestControlImpl.access$getSubmitFailedResult$cp();
    }
}
