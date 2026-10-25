package androidx.camera.camera2.impl;

import android.util.Log;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.DeferrableSurface;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", _UrlKt.FRAGMENT_ENCODE_SET, "<anonymous>", "(Lkotlinx/coroutines/CoroutineScope;)V"}, k = 3, mv = {2, 1, 0})
@DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseCameraImpl$start$$inlined$confineLaunch$1", f = "UseCaseCamera.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nUseCaseThreads.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseThreads.kt\nandroidx/camera/camera2/impl/UseCaseThreads$confineLaunch$1\n+ 2 UseCaseCamera.kt\nandroidx/camera/camera2/impl/UseCaseCameraImpl\n+ 3 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,200:1\n97#2,2:201\n99#2:205\n101#2,16:207\n117#2,12:227\n129#2:241\n134#2,2:243\n85#3,2:203\n88#3:206\n85#3,4:223\n136#3,2:239\n139#3:242\n*S KotlinDebug\n*F\n+ 1 UseCaseCamera.kt\nandroidx/camera/camera2/impl/UseCaseCameraImpl\n*L\n98#1:203,2\n98#1:206\n116#1:223,4\n128#1:239,2\n128#1:242\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseCameraImpl$start$$inlined$confineLaunch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ UseCaseCameraImpl this$0;

    public UseCaseCameraImpl$start$$inlined$confineLaunch$1(Continuation r1, UseCaseCameraImpl r2) {
        this.this$0 = r2;
        super(2, r1);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
        return new UseCaseCameraImpl$start$$inlined$confineLaunch$1(r2, this.this$0);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((UseCaseCameraImpl$start$$inlined$confineLaunch$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) goto L21;
        ResultKt.throwOnFailure(r10);
        if (UseCaseCameraImpl.access$getClosed$p(this.this$0).getValue() == false) goto L9;
        Camera2Logger r9 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L20;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseCamera is closed before starting the CameraGraph, skipping setup.");
    L20:
        return Unit.INSTANCE;
    L9:
        CameraGraph r2 = UseCaseCameraImpl.access$getUseCaseGraphContext$p(this.this$0).getGraph();
        UseCaseCameraImpl.access$getUseCaseGraphContext$p(this.this$0).configureCameraStateListener();
        r2.start();
        Map<DeferrableSurface, StreamId> r4 = UseCaseCameraImpl.access$getUseCaseGraphContext$p(this.this$0).getSurfaceToStreamMap();
        StreamId r102 = UseCaseCameraImpl.m92access$findStillCaptureStreamId4TVKcYk(this.this$0);
        Camera2Logger r1 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L13;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Setting up Surfaces with UseCaseSurfaceManager");
    L13:
        if (UseCaseCameraImpl.access$getSessionConfigAdapter(this.this$0).isSessionConfigValid() == false) goto L16;
        UseCaseSurfaceManager.setupAsync$default(UseCaseCameraImpl.access$getUseCaseSurfaceManager(this.this$0), r2, UseCaseCameraImpl.access$getSessionConfigAdapter(this.this$0), r4, 0, 8, null).invokeOnCompletion(UseCaseCameraImpl$start$1$3.INSTANCE);
    L18:
        UseCaseCameraImpl.m93access$setCaptureSessionRequestProcessor9O56998(this.this$0, r102, r2);
        goto L20
    L16:
        if (Logger.isErrorEnabled("CXCP") == false) goto L18;
        Log.e(Camera2Logger.access$getTRUNCATED_TAG$p(), "Unable to create capture session due to conflicting configurations");
        goto L18
    L21:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
