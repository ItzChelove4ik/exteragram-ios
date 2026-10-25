package androidx.camera.camera2.impl;

import android.util.Log;
import androidx.camera.camera2.pipe.CameraControls3A;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.Result3A;
import androidx.camera.core.Logger;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.Deferred;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/Result3A;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl$cancelFocusAndMeteringAsync$1$1", f = "UseCaseCameraRequestControl.kt", i = {}, l = {749, 497, 497, 761}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nUseCaseCameraRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$cancelFocusAndMeteringAsync$1$1\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl\n+ 4 UseCaseCameraConfig.kt\nandroidx/camera/camera2/config/UseCaseGraphContext\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,742:1\n85#2,4:743\n95#2,4:753\n95#2,4:765\n656#3,2:747\n658#3,2:751\n660#3,2:757\n656#3,2:759\n658#3,2:763\n660#3,2:769\n242#4:749\n242#4:761\n1#5:750\n1#5:762\n*S KotlinDebug\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$cancelFocusAndMeteringAsync$1$1\n*L\n493#1:743,4\n497#1:753,4\n499#1:765,4\n497#1:747,2\n497#1:751,2\n497#1:757,2\n499#1:759,2\n499#1:763,2\n499#1:769,2\n497#1:749\n499#1:761\n497#1:750\n499#1:762\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseCameraRequestControlImpl$cancelFocusAndMeteringAsync$1$1 extends SuspendLambda implements Function1<Continuation<? super Deferred<? extends Result3A>>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ UseCaseCameraRequestControlImpl this$0;

    public UseCaseCameraRequestControlImpl$cancelFocusAndMeteringAsync$1$1(UseCaseCameraRequestControlImpl r1, Continuation<? super UseCaseCameraRequestControlImpl$cancelFocusAndMeteringAsync$1$1> r2) {
        this.this$0 = r1;
        super(1, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> r2) {
        return new UseCaseCameraRequestControlImpl$cancelFocusAndMeteringAsync$1$1(this.this$0, r2);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Deferred<? extends Result3A>> r1) {
        return invoke2(r1);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Continuation<? super Deferred<Result3A>> r1) {
        return ((UseCaseCameraRequestControlImpl$cancelFocusAndMeteringAsync$1$1) create(r1)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v23 */
    /* JADX WARN: Type inference failed for: r13v24 */
    /* JADX WARN: Type inference failed for: r13v3 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r19) {
        Object r11 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r0 = this.label;
        ?? r13 = 4;
        if (r0 == 0) goto L25;
        if (r0 == 1) goto L21;
        if (r0 == 2) goto L15;
        if (r0 == 3) goto L14;
        if (r0 != 4) goto L12;
        ResultKt.throwOnFailure(r19);     // Catch: CancellationException -> L10
        Object r02 = r19;
        Throwable r132 = null;
    L65:
        AutoCloseable r1 = (AutoCloseable) r02;     // Catch: CancellationException -> L10
        CameraGraph.Constants3A r03 = CameraGraph.Constants3A.INSTANCE;     // Catch: Throwable -> L69
        Deferred r04 = CameraControls3A.m164update3AydBZfZg$default((CameraGraph.Session) r1, null, null, null, ArraysKt.asList(r03.getMETERING_REGIONS_DEFAULT()), ArraysKt.asList(r03.getMETERING_REGIONS_DEFAULT()), ArraysKt.asList(r03.getMETERING_REGIONS_DEFAULT()), 7, null);     // Catch: Throwable -> L69
        AutoCloseableKt.closeFinally(r1, r132);     // Catch: CancellationException -> L10
        return r04;
    L69:
        th = move-exception;
        throw th;     // Catch: Throwable -> L72
    L72:
        th = move-exception;
        AutoCloseableKt.closeFinally(r1, th);     // Catch: CancellationException -> L10
        throw th;     // Catch: CancellationException -> L10
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r19);
        Throwable r133 = null;
    L62:
        CameraGraph r05 = UseCaseCameraRequestControlImpl.access$getUseCaseGraphContext$p(this.this$0).getGraph();     // Catch: CancellationException -> L10
        this.label = 4;     // Catch: CancellationException -> L10
        r02 = r05.acquireSession(this);     // Catch: CancellationException -> L10
        r132 = r133;
        if (r02 != r11) goto L65;
    L64:
        return r11;
    L15:
        AutoCloseable r12 = (AutoCloseable) this.L$0;
        ResultKt.throwOnFailure(r19);     // Catch: Throwable -> L18
        Object r06 = r19;
        Throwable r134 = null;
    L83:
        Deferred r07 = (Deferred) r06;     // Catch: Throwable -> L44
        AutoCloseableKt.closeFinally(r12, r134);     // Catch: CancellationException -> L42
        Throwable r135 = r134;
    L58:
        this.L$0 = r135;
        this.label = 3;
        r133 = r135;
        if (r07.await(this) != r11) goto L62;
    L44:
        Throwable th = th;
        AutoCloseable r2 = r12;
        Throwable r136 = r134;
    L20:
        Throwable r14 = th;
        r13 = r136;
    L80:
        throw r14;     // Catch: Throwable -> L51
    L51:
        th = move-exception;
        AutoCloseableKt.closeFinally(r2, r14);     // Catch: CancellationException -> L42
        throw th;     // Catch: CancellationException -> L42
    L18:
        th = th;
        r2 = r12;
        r136 = null;
        goto L20
    L21:
        ResultKt.throwOnFailure(r19);     // Catch: CancellationException -> L23
        Object r08 = r19;
    L32:
        AutoCloseable r4 = (AutoCloseable) r08;     // Catch: CancellationException -> L23
        CameraGraph.Session r09 = (CameraGraph.Session) r4;     // Catch: Throwable -> L48
        Boolean r5 = Boxing.boxBoolean(true);     // Catch: Throwable -> L48
        Boolean r22 = Boxing.boxBoolean(true);     // Catch: Throwable -> L48
        Boolean r6 = Boxing.boxBoolean(true);     // Catch: Throwable -> L48
        this.L$0 = r4;     // Catch: Throwable -> L48
        this.label = 2;     // Catch: Throwable -> L48
        AutoCloseable r16 = r4;
        Throwable r137 = null;
        r06 = CameraGraph.Session.unlock3A$default(r09, r5, r22, r6, null, 0, 0, this, 56, null);     // Catch: Throwable -> L46
        if (r06 == r11) goto L64;
        r12 = r16;
        r134 = r137;
    L46:
        th = th;
    L47:
        r14 = th;
        r2 = r16;
        r13 = r137;
    L48:
        th = th;
        r137 = null;
        r16 = r4;
        goto L47
    L25:
        ResultKt.throwOnFailure(r19);
        Camera2Logger r010 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L29;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseCameraRequestControlImpl#cancelFocusAndMeteringAsync");
    L29:
        CameraGraph r011 = UseCaseCameraRequestControlImpl.access$getUseCaseGraphContext$p(this.this$0).getGraph();     // Catch: CancellationException -> L23
        this.label = 1;     // Catch: CancellationException -> L23
        r08 = r011.acquireSession(this);     // Catch: CancellationException -> L23
        if (r08 != r11) goto L32;
    L42:
        e = e;
    L54:
        Camera2Logger r15 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L57;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Cannot acquire the CameraGraph.Session", e);
    L57:
        r07 = UseCaseCameraRequestControlImpl.access$getSubmitFailedResult$cp();
        r135 = r13;
    L23:
        e = e;
        r13 = 0;
    L10:
        e = move-exception;
        Camera2Logger r17 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L79;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Cannot acquire the CameraGraph.Session", e);
    L79:
        return UseCaseCameraRequestControlImpl.access$getSubmitFailedResult$cp();
    }
}
