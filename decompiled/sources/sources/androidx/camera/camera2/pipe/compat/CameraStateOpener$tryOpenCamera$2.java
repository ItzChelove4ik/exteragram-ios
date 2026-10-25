package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraError;
import androidx.camera.camera2.pipe.core.Log;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.selects.SelectImplementation;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/camera/camera2/pipe/compat/OpenCameraResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.CameraStateOpener$tryOpenCamera$2", f = "RetryingCameraStateOpener.kt", i = {0, 0, 0, 0, 0}, l = {670}, m = "invokeSuspend", n = {"$this$supervisorScope", "cameraOpenDeferred", "resultDeferred", "timeoutJob", "cameraOpenCancelJob"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"}, v = 1)
@SourceDebugExtension({"SMAP\nRetryingCameraStateOpener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/CameraStateOpener$tryOpenCamera$2\n+ 2 Select.kt\nkotlinx/coroutines/selects/SelectKt\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,665:1\n54#2,5:666\n59#3,2:671\n86#3,2:673\n*S KotlinDebug\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/CameraStateOpener$tryOpenCamera$2\n*L\n324#1:666,5\n358#1:671,2\n368#1:673,2\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraStateOpener$tryOpenCamera$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super OpenCameraResult>, Object> {
    final /* synthetic */ String $cameraId;
    final /* synthetic */ AndroidCameraState $cameraState;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ CameraStateOpener this$0;

    public CameraStateOpener$tryOpenCamera$2(CameraStateOpener r1, String r2, AndroidCameraState r3, Continuation<? super CameraStateOpener$tryOpenCamera$2> r4) {
        this.this$0 = r1;
        this.$cameraId = r2;
        this.$cameraState = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r4, Continuation<?> r5) {
        CameraStateOpener$tryOpenCamera$2 r0 = new CameraStateOpener$tryOpenCamera$2(this.this$0, this.$cameraId, this.$cameraState, r5);
        r0.L$0 = r4;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super OpenCameraResult> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super OpenCameraResult> r2) {
        return ((CameraStateOpener$tryOpenCamera$2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [T, kotlinx.coroutines.Deferred] */
    /* JADX WARN: Type inference failed for: r6v4, types: [T, kotlinx.coroutines.Deferred] */
    /* JADX WARN: Type inference failed for: r6v6, types: [T, kotlinx.coroutines.Job] */
    /* JADX WARN: Type inference failed for: r6v9, types: [T, kotlinx.coroutines.Job] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x00fa -> B:30:0x00fd). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r14) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L12;
        if (r1 != 1) goto L10;
        Ref.ObjectRef r12 = (Ref.ObjectRef) this.L$4;
        Ref.ObjectRef r5 = (Ref.ObjectRef) this.L$3;
        Ref.ObjectRef r6 = (Ref.ObjectRef) this.L$2;
        Ref.ObjectRef r7 = (Ref.ObjectRef) this.L$1;
        CoroutineScope r8 = (CoroutineScope) this.L$0;
        ResultKt.throwOnFailure(r14);     // Catch: Throwable -> L8
    L30:
        OpenCameraResult r142 = (OpenCameraResult) r14;     // Catch: Throwable -> L8
        if (r142 != null) goto L33;
    L14:
        if (CoroutineScopeKt.isActive(r8) == false) goto L53;
        String r143 = this.$cameraId;     // Catch: Throwable -> L8
        AndroidCameraState r9 = this.$cameraState;     // Catch: Throwable -> L8
        SelectImplementation r10 = new SelectImplementation(getContext());     // Catch: Throwable -> L8
        Deferred r11 = (Deferred) r7.element;     // Catch: Throwable -> L8
        if (r11 == null) goto L18;
        r10.invoke(r11.getOnAwait(), new CameraStateOpener$tryOpenCamera$2$result$1$1(r7, r143, null));     // Catch: Throwable -> L8
    L18:
        Deferred r112 = (Deferred) r6.element;     // Catch: Throwable -> L8
        if (r112 == null) goto L21;
        r10.invoke(r112.getOnAwait(), new CameraStateOpener$tryOpenCamera$2$result$1$2(r6, r143, null));     // Catch: Throwable -> L8
    L21:
        Job r144 = (Job) r5.element;     // Catch: Throwable -> L8
        if (r144 == null) goto L24;
        r10.invoke(r144.getOnJoin(), new CameraStateOpener$tryOpenCamera$2$result$1$3(r5, r7, r9, null));     // Catch: Throwable -> L8
    L24:
        Job r145 = (Job) r12.element;     // Catch: Throwable -> L8
        if (r145 == null) goto L27;
        r10.invoke(r145.getOnJoin(), new CameraStateOpener$tryOpenCamera$2$result$1$4(r12, null));     // Catch: Throwable -> L8
    L27:
        this.L$0 = r8;     // Catch: Throwable -> L8
        this.L$1 = r7;     // Catch: Throwable -> L8
        this.L$2 = r6;     // Catch: Throwable -> L8
        this.L$3 = r5;     // Catch: Throwable -> L8
        this.L$4 = r12;     // Catch: Throwable -> L8
        this.label = 1;     // Catch: Throwable -> L8
        r14 = r10.doSelect(this);     // Catch: Throwable -> L8
        if (r14 != r0) goto L30;
        return r0;
    L53:
        return new OpenCameraResult(null, CameraError.m175boximpl(CameraError.Companion.m191getERROR_CAMERA_OPENERv7Vf74A()), 1, null);
    L33:
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L35;
        android.util.Log.i("CXCP", "Camera open completed: " + r142);     // Catch: Throwable -> L8
    L35:
        Deferred r13 = (Deferred) r7.element;     // Catch: Throwable -> L8
        if (r13 == null) goto L38;
        Job.DefaultImpls.cancel$default(r13, null, 1, null);     // Catch: Throwable -> L8
    L38:
        Deferred r132 = (Deferred) r6.element;     // Catch: Throwable -> L8
        if (r132 == null) goto L41;
        Job.DefaultImpls.cancel$default(r132, null, 1, null);     // Catch: Throwable -> L8
    L41:
        Job r133 = (Job) r5.element;     // Catch: Throwable -> L8
        if (r133 == null) goto L44;
        Job.DefaultImpls.cancel$default(r133, null, 1, null);     // Catch: Throwable -> L8
    L44:
        Job r134 = (Job) r12.element;     // Catch: Throwable -> L8
        if (r134 == null) goto L47;
        Job.DefaultImpls.cancel$default(r134, null, 1, null);     // Catch: Throwable -> L8
    L47:
        return r142;
    L8:
        th = move-exception;
        if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L56;
        android.util.Log.e("CXCP", "Unexpected throwable during camera opening!", th);
        throw th;
    L56:
        throw th;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L12:
        ResultKt.throwOnFailure(r14);
        CoroutineScope r52 = (CoroutineScope) this.L$0;
        Ref.ObjectRef r146 = new Ref.ObjectRef();
        r146.element = BuildersKt.async$default(r52, null, null, new CameraStateOpener$tryOpenCamera$2$cameraOpenDeferred$1(this.this$0, this.$cameraId, this.$cameraState, null), 3, null);
        Ref.ObjectRef r15 = new Ref.ObjectRef();
        r15.element = BuildersKt.async$default(r52, null, null, new CameraStateOpener$tryOpenCamera$2$resultDeferred$1(this.$cameraState, null), 3, null);
        Ref.ObjectRef r113 = new Ref.ObjectRef();
        r113.element = BuildersKt.launch$default(r52, null, null, new CameraStateOpener$tryOpenCamera$2$timeoutJob$1(null), 3, null);
        Ref.ObjectRef r122 = new Ref.ObjectRef();
        r122.element = BuildersKt.launch$default(r52, null, null, new CameraStateOpener$tryOpenCamera$2$cameraOpenCancelJob$1(this.this$0, null), 3, null);
        r7 = r146;
        r6 = r15;
        r8 = r52;
        r5 = r113;
        r12 = r122;
        goto L14
    }
}
